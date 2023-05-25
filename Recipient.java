import javax.swing.*;
import java.awt.*;

public class Recipient extends Thread {

    private SMS sms;
    private int counter;
    private static LabelChangeListener listener;

    public static void setListener(LabelChangeListener listener) {
        Recipient.listener = listener;
    }

    public void setSms(SMS sms) {
        this.sms = sms;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while(true) {
            if (sms != null) {
                listener.changeLabel(this, ++counter);
                sms = null;
                System.out.println("Recipient got the message");
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
