public class Recipient extends Thread {

    private String sms;

    public void setSms(String sms) {
        this.sms = sms;
    }

    @Override
    public void run() {
        while(true) {
            if (sms != null) {
                System.out.println("Recipient got the message!!!!");
            }
        }
    }


}
