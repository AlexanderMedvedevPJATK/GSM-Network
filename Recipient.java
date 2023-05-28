public class Recipient extends Thread {

    private SMS sms;
    private int counter;
    private static LabelChangeListener listener;

    public static void setListener(LabelChangeListener listener) {
        Recipient.listener = listener;
    }

    synchronized public void setSms(SMS sms) {
        while (this.sms != null) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }
        this.sms = sms;
        notify();
    }

    synchronized public void getSms() {
        while (sms == null) {
            try {
                wait();
            } catch(InterruptedException ignored) {}
        }
        sms = null;
        listener.changeLabel(this, ++counter);
        notify();
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while(!isInterrupted()) {
            getSms();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}