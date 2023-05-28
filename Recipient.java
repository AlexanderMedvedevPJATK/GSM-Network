import javax.swing.*;
import java.awt.*;

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
                System.out.println("waiting in setSms");
                wait();
            } catch (InterruptedException ignored) {}
        }
        System.out.println("Set the message");
        this.sms = sms;
        notify();
    }

    synchronized public void getSms() {
        while (sms == null) {
            try {
                System.out.println("waiting in getSms");
                wait();
            } catch(InterruptedException ignored) {}
        }
        System.out.println("Got the message");
        sms = null;
        listener.changeLabel(this, ++counter);
        System.out.println("Changed the label to " + counter);
        notify();
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while(!isInterrupted()) {
            System.out.println("running");
            getSms();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}
