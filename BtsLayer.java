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
            sms.setTimeToSend(System.currentTimeMillis() + TIMEOUT);
            super.addSms(sms);
        }

        public void sendSms(SMS sms) {
            if(senderBts) {
                Storage.getBscLayerList().get(0).assignSms(sms);
            } else {
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