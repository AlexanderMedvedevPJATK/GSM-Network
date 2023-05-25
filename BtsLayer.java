import java.util.ArrayList;
import java.util.List;

public class BtsLayer {
    private List<BTS> stations = new ArrayList<>();

    public void assignSms(SMS sms) {
        int minSmsNumber = stations.get(0).smsCounter;
        BTS minStation = stations.get(0);
        for (BTS station : stations) {
            if (station.smsCounter == 0 || station.smsCounter < minSmsNumber) {
                minStation = station;
            }
        }
        minStation.sms = sms;
    }

    public void addStation() {
        BTS station = new BTS();
        stations.add(station);
        station.start();
    }

    public class BTS extends Thread {
        private SMS sms;
        private int smsCounter;
        public void sendSms() {
            if (sms != null) {
                Storage.getBscLayersList().get(0).assignSms(sms);
            }
        }

        @Override
        public void run() {
            while(true) {
                while (sms == null) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                sendSms();
                sms = null;
                System.out.println("BTS sent the message");
            }
        }
    }
}
