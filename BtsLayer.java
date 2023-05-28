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
        private final long TIMEOUT = 3000;
        @Override
        public void addSms(SMS sms) {
            System.out.println("BTS got the message");
            sms.setTimeToSend(System.currentTimeMillis() + TIMEOUT);
            super.addSms(sms);
        }

        public void sendSms(SMS sms) {
            if(senderBts) {
                System.out.println("Sender BTS sent the message");
                Storage.getBscLayerList().get(0).assignSms(sms);
            } else {
                System.out.println("Recipient BTS sending messages to the recipient");
                try {
                    sms.getRecipient().setSms(sms);
                } catch (NullPointerException e) {
                    System.err.println("The recipient does not exist");
                }
            }
            shiftSms();
        }
    }
}
