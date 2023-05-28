import javax.swing.*;

public class SenderController {
    private SenderPanel senderPanel;
    private int number;

    public SenderController(SenderPanel senderPanel) {
        this.senderPanel = senderPanel;
    }

    public void createSender(String sms, JPanel panel) {
        Sender sender = new Sender(sms, ++number);
        Storage.getSenderMap().put(panel, sender);
        sender.start();
    }

    public void terminateSender(JPanel panel) {
        Storage.getSenderMap().get(panel).terminate();
        Storage.getSenderMap().remove(panel);
    }

    public void setFrequency(int frequency, JPanel sender) {
        Storage.getSenderMap().get(sender).setFrequency(frequency);
    }

    public void setRunning(boolean running, JPanel sender) {
        Storage.getSenderMap().get(sender).setRunning(running);
    }
}
