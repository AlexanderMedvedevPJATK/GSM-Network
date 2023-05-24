import java.util.ArrayList;
import java.util.List;

public class BscLayer {

    private List<BSC> stations = new ArrayList<>();

    public void assignSms(String sms) {
        int minSmsNumber = stations.get(0).smsCounter;
        BSC minStation = stations.get(0);
        for (BSC station : stations) {
            if (station.smsCounter == 0 || station.smsCounter < minSmsNumber) {
                minStation = station;
            }
        }
        minStation.sms = sms;
    }

    public void addStation() {
        BSC station = new BSC();
        stations.add(station);
        station.start();
    }

    public class BSC extends Thread {
        private String sms;
        private int smsCounter;
        public void sendSms() {
            int thisLayerIndex = Storage.getBscLayersList().indexOf(BscLayer.this);
            if (sms != null) {
                if (thisLayerIndex != Storage.getBscLayersList().size() - 1) {
                    BscLayer next = Storage.getBscLayersList().get(thisLayerIndex + 1);
                    next.assignSms(sms);
                } else {

                }
            }
        }

        @Override
        public void run() {
            System.out.println(this + " thread started");
            while (sms == null) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            sendSms();
            System.out.println("BTS " + this + " got the message");
        }
    }
}
