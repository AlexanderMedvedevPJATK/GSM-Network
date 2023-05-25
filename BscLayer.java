import java.util.ArrayList;
import java.util.List;

public class BscLayer {

    private List<BSC> stations = new ArrayList<>();

    public void assignSms(SMS sms) {
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
        private SMS sms;
        private int smsCounter;
        public void sendSms() throws Exception {
            int thisLayerIndex = Storage.getBscLayersList().indexOf(BscLayer.this);
            if (sms != null) {
                if (thisLayerIndex != Storage.getBscLayersList().size() - 1) {
                    BscLayer next = Storage.getBscLayersList().get(thisLayerIndex + 1);
                    next.assignSms(sms);
                } else {
                    if (sms.getRecipient() == null) {
                        throw new Exception();
                    }
                    sms.getRecipient().setSms(sms);
                }
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
                try {
                    sendSms();
                    sms = null;
                    System.out.println("BSC sent the message");
                } catch (Exception e) {
                    System.out.println("Recipient is not found");
                }
            }
        }
    }
}
