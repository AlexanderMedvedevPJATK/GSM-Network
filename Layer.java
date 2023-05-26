import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public abstract class Layer {
    private List<Station> stations = new ArrayList<>();

    public List<Station> getStations() {
        return stations;
    }

    public void assignSms(SMS sms) {
        int minSmsNumber = stations.get(0).smsCounter;
        Station minStation = stations.get(0);
        for (Station station : stations) {
            if (station.smsCounter == 0 || station.smsCounter < minSmsNumber) {
                minStation = station;
            }
        }
        minStation.sms[0] = sms;
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
        private SMS[] sms = new SMS[5];
        private int smsCounter;
        public void sendSms() {
            if (sms != null) {
                // Storage.getBscLayersList().get(0).assignSms(sms);
            }
        }

        public void removeStation() {
            stations.get(stations.indexOf(this)).interrupt();
            stations.remove(this);
        }

        @Override
        public void run() {
            while(!isInterrupted()) {
                while (sms == null) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        interrupt();
                        break;
                    }
                }
                sendSms();
                sms = null;
            }
            System.out.println(this + "has finished");
        }
    }
}
