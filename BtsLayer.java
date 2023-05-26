public class BtsLayer extends Layer {
    @Override
    public Station createStation() {
        return new BTS();
    }

    public class BTS extends Station {
        private int smsCounter;
        public void sendSms() {

        }
    }
}
