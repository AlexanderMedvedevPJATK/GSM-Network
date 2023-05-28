import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
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
        Station minStation = stations.get(0);
        int minSmsNumber = minStation.smsCounter;
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
        return station;
    }
    public abstract Station createStation();
    public abstract class Station extends Thread {
        protected SMS[] smsArr = new SMS[5];
        protected int smsCounter;
        private final int MAX_SMS = 5;

        public void addSms(SMS sms) {
            smsArr[smsCounter] = sms;
            smsCounter++; // IMPORTANT TO INCREMENT AFTER ASSIGNMENT
            if (smsCounter == MAX_SMS) {
                listener.createStation(Layer.this);
            }
        }

        synchronized public void shiftSms() {
            smsCounter--;
            if (smsCounter != 0) {
                for (int i = 0; i < smsCounter; i++) {
                    smsArr[i] = smsArr[i + 1];
                }
            } else smsArr[0] = null;
        }
        public abstract void sendSms(SMS sms);

        public void sendAllSms() {

            for (int i = 0; smsCounter != 0; i++) {
                System.out.println("sent");
                sendSms(smsArr[i]); // smsCounter is reduced in here
                System.out.println(smsCounter);
            }
            System.out.println(smsCounter);
        }

        public void removeStation() {
            stations.get(stations.indexOf(this)).interrupt();
            stations.remove(this);
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                if(smsCounter > 0) {
                    if(smsArr[0].getTimeToSend() <= System.currentTimeMillis()) {
                        sendSms(smsArr[0]);
                    } else {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            interrupt();
                        }
                    }
                }
            }
            System.out.println("Sending all sms");
            sendAllSms();
        }
    }
}
