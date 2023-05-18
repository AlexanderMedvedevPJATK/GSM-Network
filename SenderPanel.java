import javax.swing.*;
import java.awt.*;

public class SenderPanel extends JPanel {
    public SenderPanel() {
        setBackground(Color.RED);

        JScrollPane senderDevicesPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        senderDevicesPane.getVerticalScrollBar().setUnitIncrement(7);

        JPanel senderDevicesPanel = new JPanel();
        senderDevicesPanel.setLayout(new BoxLayout(senderDevicesPanel, BoxLayout.Y_AXIS));
        senderDevicesPanel.add(Box.createRigidArea(new Dimension(180, 20)));

        senderDevicesPane.setViewportView(senderDevicesPanel);

        JButton button = new JButton("add");
        button.setPreferredSize(new Dimension(100, 40));
        button.addActionListener(e -> {
            String msg = "";
            while (msg.equals("")) {
                msg = JOptionPane.showInputDialog(
                        null,
                        "Enter the sms",
                        "SMS",
                        JOptionPane.PLAIN_MESSAGE);
                if (msg == null) {
                    break;
                } else if (msg.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a value.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    makeSender(senderDevicesPanel);
                    revalidate();
                }
            }
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        button.setAlignmentY(BOTTOM_ALIGNMENT);
        button.setAlignmentX(CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(180, 30)));
        add(Box.createVerticalGlue());
        add(senderDevicesPane);
        add(Box.createRigidArea(new Dimension(180, 30)));
        add(Box.createVerticalGlue());
        add(button);

    }

    public void makeSender(JPanel senderDevicesPanel) {
        JPanel sender = new JPanel();
        JLabel phoneNumber = new JLabel("phoneNumber");
        JLabel sliderText = new JLabel("SMS per minute");
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 60, 5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(10);
        JButton terminate = new JButton("Terminate");
        JLabel stateText = new JLabel("State");
        JComboBox<String> stateOptions = new JComboBox<>(new String[] {
                "ON", "OFF"
        });

        phoneNumber.setAlignmentX(CENTER_ALIGNMENT);
        sliderText.setAlignmentX(CENTER_ALIGNMENT);
        stateText.setAlignmentX(CENTER_ALIGNMENT);
        terminate.setAlignmentX(CENTER_ALIGNMENT);
        sender.setLayout(new BoxLayout(sender, BoxLayout.Y_AXIS));

        sender.add(phoneNumber);
        sender.add(Box.createRigidArea(new Dimension(180, 10)));
        sender.add(sliderText);
        sender.add(slider);
        sender.add(Box.createRigidArea(new Dimension(180, 10)));
        sender.add(stateText);
        sender.add(stateOptions);
        sender.add(Box.createRigidArea(new Dimension(180, 5)));
        sender.add(terminate);

        senderDevicesPanel.add(sender);
        senderDevicesPanel.add(Box.createRigidArea(new Dimension(180, 25)));
    }
}
