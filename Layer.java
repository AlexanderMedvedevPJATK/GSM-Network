import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public abstract class Layer {
    private List<Station> stations = new ArrayList<>();
    private static SmsOverflowListener listener;

    public List<Station> getStations() {
        return stations;
    }

    public static void setListener(SmsOverflowListener listener) {
        Layer.listener = listener;
    }

    synchronized public void assignSms(SMS sms) {
        int minSmsNumber = stations.get(0).smsCounter;
        Station minStation = stations.get(0);
        for (Station station : stations) {
            if (station.smsCounter == 0 || station.smsCounter < minSmsNumber) {
                minStation = station;
            }
        }
        minStation.addSms(sms);
    }

    public Station addStation() {
        Station station = createStation();
        stations.add(station);
        station.start();
        System.out.println(station + " started");
        return station;
    }
    public abstract Station createStation();
    public abstract class Station extends Thread {
        protected SMS[] smsArr = new SMS[5];
        private int smsCounter;

        public void addSms(SMS sms) {
            smsArr[smsCounter++] = sms;
            if (smsCounter == 5) {
                listener.createStation(Layer.this);
            }
        }

        public void shiftSms() {
            smsCounter--;
            if (smsCounter != 0) {
                for (int i = 0; i < smsCounter; i++) {
                    smsArr[i] = smsArr[i + 1];
                }
            } else smsArr[0] = null;
        }
        public abstract void sendSms(SMS sms);

        public void sendAllSms() {
            for (SMS sms : smsArr) sendSms(sms);
        }

        public void removeStation() {
            stations.get(stations.indexOf(this)).interrupt();
            stations.remove(this);
        }

        @Override
        public void run() {
            while(!isInterrupted()) {
                while (smsArr[0] == null) {
                    try {
                        Thread.sleep(50000);
                    } catch (InterruptedException e) {
                        interrupt();
                        break;
                    }
                }
                sendSms(smsArr[0]);
            }
            System.out.println(this + "has finished");
        }
    }
}
