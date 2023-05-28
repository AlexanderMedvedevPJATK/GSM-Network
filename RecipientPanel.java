import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class RecipientPanel extends JPanel {
    private final RecipientController controller;
    public RecipientPanel() {
        controller = new RecipientController();

        setBackground(Color.DARK_GRAY);

        JScrollPane recipientDevicesPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        recipientDevicesPane.getVerticalScrollBar().setUnitIncrement(7);
        recipientDevicesPane.setMaximumSize(new Dimension(220, 400));

        JPanel recipientDevicesPanel = new JPanel();
        recipientDevicesPanel.setLayout(new BoxLayout(recipientDevicesPanel, BoxLayout.Y_AXIS));

        recipientDevicesPane.setViewportView(recipientDevicesPanel);

        JButton button = new JButton("ADD");
        button.setAlignmentY(BOTTOM_ALIGNMENT);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(100, 40));
        button.setMinimumSize(new Dimension(100, 40));
        button.addActionListener(e -> {
            controller.createRecipient(makeRecipientDevice(recipientDevicesPanel));
            revalidate();
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(180, 30)));
        add(Box.createVerticalGlue());
        add(recipientDevicesPane);
        add(Box.createRigidArea(new Dimension(180, 30)));
        add(Box.createVerticalGlue());
        add(button);

    }

    public JPanel makeRecipientDevice(JPanel recipientDevicesPanel) {
        JPanel recipient = new JPanel();

        JLabel phoneNumber = new JLabel("SMS received: 0");
        phoneNumber.setAlignmentX(CENTER_ALIGNMENT);

        JCheckBox checkBox = new JCheckBox("Clear every 10 sec ");
        checkBox.setHorizontalTextPosition(SwingConstants.LEFT);
        checkBox.setAlignmentX(CENTER_ALIGNMENT);
        checkBox.setVerticalAlignment(SwingConstants.CENTER);
        checkBox.addActionListener(e -> {
            new Thread(() -> {
                while (checkBox.isSelected()) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    if(checkBox.isSelected()) {
                        phoneNumber.setText("SMS received: 0");
                        controller.resetSmsNumber(recipient);
                        revalidate();
                    }
                }
            }).start();
        });

        JButton terminate = new JButton("Terminate");
        terminate.addActionListener(e -> {
            Storage.removeRecipient(recipient);
            recipientDevicesPanel.remove(recipient);
            revalidate();
        });
        terminate.setAlignmentX(CENTER_ALIGNMENT);

        recipient.setLayout(new BoxLayout(recipient, BoxLayout.Y_AXIS));
        recipient.setBorder(new LineBorder(Color.BLACK, 2));

        recipient.add(Box.createRigidArea(new Dimension(190, 10)));
        recipient.add(phoneNumber);
        recipient.add(Box.createRigidArea(new Dimension(190, 7)));
        recipient.add(checkBox);
        recipient.add(Box.createRigidArea(new Dimension(190, 7)));
        recipient.add(terminate);
        recipient.add(Box.createRigidArea(new Dimension(190, 10)));

        recipientDevicesPanel.add(recipient);

        return recipient;
    }
}