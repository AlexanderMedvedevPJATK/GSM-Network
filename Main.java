import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        setTitle("GSM Network");
        setSize(1200, 480);

        SenderPanel senderPanel = new SenderPanel();
        senderPanel.setPreferredSize(new Dimension(getWidth() / 5, getHeight()));
        BasesPanel basesPanel = new BasesPanel();
        basesPanel.setPreferredSize(new Dimension(getWidth() / 3 * 5, getHeight()));
        RecipientPanel recipientPanel = new RecipientPanel();
        recipientPanel.setPreferredSize(new Dimension(getWidth() / 5, getHeight()));

        add(senderPanel, BorderLayout.WEST);
        add(basesPanel, BorderLayout.CENTER);
        add(recipientPanel, BorderLayout.EAST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}