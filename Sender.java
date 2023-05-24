public class Sender extends Thread {

    private SMS sms;

    @Override
    public void run() {
        sendSms();
    }

    public void sendSms() {
        Storage.getBtsSenderLayer().assignSms("sms");
    }
}
