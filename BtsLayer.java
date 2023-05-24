import java.util.ArrayList;
import java.util.List;

public class BtsLayer {
    private List<BTS> stations = new ArrayList<>();

    public void assignSms(String sms) {
        int minSmsNumber = stations.get(0).smsCounter;
        BTS minStation = stations.get(0);
        for (BTS station : stations) {
            if (station.smsCounter == 0 || station.smsCounter < minSmsNumber) {
                minStation = station;
            }
        }
        minStation.sms = sms;
        System.out.println("Sms assigned to the bts station");
    }

    public void addStation() {
        BTS station = new BTS();
        stations.add(station);
        station.start();
    }

    public class BTS extends Thread {
        private String sms;
        private int smsCounter;
        public void sendSms() {
            if (sms != null) {
                Storage.getBscLayersList().get(0).assignSms(sms);
                System.out.println("Bts sent the message");
            }
        }

        @Override
        public void run() {
            while (sms == null) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            sendSms();
        }
    }
}
