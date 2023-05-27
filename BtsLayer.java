public class BtsLayer extends Layer {
    boolean senderBts;

    public BtsLayer(boolean senderBts) {
        this.senderBts = senderBts;
    }

    @Override
    public Station createStation() {
        return new BTS();
    }

    public class BTS extends Station {
        public void sendSms(SMS sms) {
            if(senderBts) {
                Storage.getBscLayerList().get(0).assignSms(sms);
            } else {
                try {
                    sms.getRecipient().setSms(sms);
                } catch (NullPointerException e) {
                    System.out.println("The recipient does not exist");
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
                    System.out.println("Waited outside for " + (messageEntered - checkPoint));
                    try {
                        Thread.sleep(3000 - (System.currentTimeMillis() - messageEntered));
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
