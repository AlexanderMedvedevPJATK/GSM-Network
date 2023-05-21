public class Sender extends Thread {
    private SMS sms;
    private Recipient recipient;

    public Sender(SMS sms) {
        this.sms = sms;
    }
    @Override
    public void run() {

    }
}
