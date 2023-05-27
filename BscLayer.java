import java.util.ArrayList;
import java.util.List;

public class BscLayer extends Layer{
    @Override
    public Station createStation() {
        return new BSC();
    }


    public class BSC extends Station {
        private SMS sms;
        private int smsCounter;
        @Override
        public void sendSms(SMS sms) {
            int thisLayerIndex = Storage.getBscLayerList().indexOf(BscLayer.this);
            if (sms != null) {
                if (thisLayerIndex != Storage.getBscLayerList().size() - 1) {
                    BscLayer next = Storage.getBscLayerList().get(thisLayerIndex + 1);
                    next.assignSms(sms);
                } else {
                    Storage.getBtsRecipientLayer().assignSms(sms);
                }
            }
            shiftSms();
        }

        @Override
        public void run() {
            while(!isInterrupted()) {
                while(smsArr[0] == null) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(smsArr[0] + " entered");
                long messageEntered = System.currentTimeMillis();
                synchronized (this) {
                    long checkPoint = System.currentTimeMillis();
                    System.out.println("Waited outside for " + (checkPoint - messageEntered));
                    try {
                        Thread.sleep((int)(Math.random() * 10000 + 5000)
                                        - (System.currentTimeMillis() - messageEntered));
                        System.out.println("Waited for " + (System.currentTimeMillis() - checkPoint));
                        sendSms(smsArr[0]);
                    } catch (InterruptedException e) {
                        sendAllSms();
                        interrupt();
                        break;
                    }
                }
            }
        }
    }
}
