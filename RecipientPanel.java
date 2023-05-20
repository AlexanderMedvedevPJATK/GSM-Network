import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class RecipientPanel extends JPanel {
    public RecipientPanel() {
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
            makeRecipient(recipientDevicesPanel);
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

    public void makeRecipient(JPanel recipientDevicesPanel) {
        JPanel recipient = new JPanel();

        JLabel phoneNumber = new JLabel("number");
        phoneNumber.setAlignmentX(CENTER_ALIGNMENT);

        JCheckBox checkBox = new JCheckBox("Clear every 10 sec");
        checkBox.setVerticalTextPosition(SwingConstants.TOP);
        checkBox.setHorizontalTextPosition(SwingConstants.CENTER);
        checkBox.setAlignmentX(CENTER_ALIGNMENT);

        JButton terminate = new JButton("Terminate");
        terminate.addActionListener(e -> {
            recipientDevicesPanel.remove(recipient);
            revalidate();
        });
        terminate.setAlignmentX(CENTER_ALIGNMENT);

        recipient.setLayout(new BoxLayout(recipient, BoxLayout.Y_AXIS));
        recipient.setBorder(new LineBorder(Color.BLACK, 2));

        recipient.add(Box.createRigidArea(new Dimension(180, 10)));
        recipient.add(phoneNumber);
        recipient.add(Box.createRigidArea(new Dimension(180, 7)));
        recipient.add(checkBox);
        recipient.add(Box.createRigidArea(new Dimension(180, 7)));
        recipient.add(terminate);
        recipient.add(Box.createRigidArea(new Dimension(180, 10)));

        recipientDevicesPanel.add(recipient);
    }
}
