import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Map;

public
    class S28572Project03 extends JFrame {

    public S28572Project03() {
        setTitle("GSM Network");
        setSize(1200, 480);

        SenderPanel senderPanel = new SenderPanel();
        senderPanel.setPreferredSize(new Dimension(getWidth() / 5, getHeight()));

        BasesSectionPanel basesSectionPanel = new BasesSectionPanel();
        basesSectionPanel.setPreferredSize(new Dimension(getWidth() / 3 * 5, getHeight()));

        RecipientPanel recipientPanel = new RecipientPanel();
        recipientPanel.setPreferredSize(new Dimension(getWidth() / 5, getHeight()));

        add(senderPanel, BorderLayout.WEST);
        add(basesSectionPanel, BorderLayout.CENTER);
        add(recipientPanel, BorderLayout.EAST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);


        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("VBDs.bin"))) {

                    for (Map.Entry<Sender, SMS> entry : Storage.getSenderSmsMap().entrySet()) {
                        dos.writeInt(entry.getKey().getNumber());
                        for (char ch : entry.getValue().getText().toCharArray()) {
                            dos.writeChar(ch);
                        }
                        dos.writeChar(0);
                        dos.writeShort(entry.getKey().getSmsCounter());
                        dos.writeChar('\n');
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                dispose();

                System.exit(0);
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(S28572Project03::new);
    }
}
