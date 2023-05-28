public class Sender extends Thread {

    private final int number;
    private final SMS sms;
    private int smsCounter;
    private final Recipient recipient;
    private int frequency = 5;
    private boolean running = true;

    public Sender(String smsText, int number) {
        if (Storage.getRecipientMap().size() == 0) {
            recipient = null;
        } else {
            Object[] recipientArr = Storage.getRecipientMap().values().toArray();
            recipient = (Recipient) recipientArr[(int) (Math.random() * recipientArr.length)];
        }
        this.number = number;
        this.sms = new SMS(this, smsText, recipient);
        Storage.getSenderSmsMap().put(this, sms);
    }

    public int getNumber() {
        return number;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    synchronized public void setRunning(boolean running) {
        this.running = running;
        if(running) notify();
    }

    public void terminate() {
        interrupt();
    }

    @Override
    public void run() {
        while(!isInterrupted()) {
            sendSms();
            try {
                Thread.sleep(60000 / frequency);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    synchronized public void sendSms() {
        if(!running) {
            try {
                System.out.println("The device is turned off");
                wait();
                System.out.println("The device is turned on");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this + " sent the message");
        Storage.getBtsSenderLayer().assignSms(sms);
    }
}
