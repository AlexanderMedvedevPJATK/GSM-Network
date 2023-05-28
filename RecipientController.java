import javax.swing.*;
import java.awt.*;

public class RecipientController implements LabelChangeListener {

    public RecipientController() {
        Recipient.setListener(this);
    }

    public void createRecipient(JPanel panel) {
        Recipient recipient = new Recipient();
        Storage.addRecipient(panel, recipient);
        recipient.start();
    }

    @Override
    public void changeLabel(Recipient recipient, int number) {
        JPanel panel = Storage.getRecipientReverseMap().get(recipient);
        for(Component component : panel.getComponents()) {
            if(component instanceof JLabel && ((JLabel) component).getText().contains("SMS received")) {
                ((JLabel) component).setText("SMS received: " + number);
            }
        }
    }

    public void resetSmsNumber(JPanel panel) {
        Storage.getRecipientMap().get(panel).setCounter(0);
    }
}