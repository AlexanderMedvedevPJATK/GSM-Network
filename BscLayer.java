public class BscLayer extends Layer {
    @Override
    public Station createStation() {
        return new BSC();
    }

    public class BSC extends Station {
        @Override
        public void addSms(SMS sms) {
            sms.setTimeToSend(System.currentTimeMillis() + (long)(Math.random() * 10000 + 5000));
            super.addSms(sms);
        }

        @Override
        public void sendSms(SMS sms) {
            int thisLayerIndex = Storage.getBscLayerList().indexOf(BscLayer.this);
            if (thisLayerIndex != Storage.getBscLayerList().size() - 1) {
                BscLayer next = Storage.getBscLayerList().get(thisLayerIndex + 1);
                next.assignSms(sms);
            } else {
                Storage.getBtsRecipientLayer().assignSms(sms);
            }
            shiftSms();
        }
    }
}