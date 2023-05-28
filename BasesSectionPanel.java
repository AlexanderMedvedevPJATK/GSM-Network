import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BasesSectionPanel extends JPanel {

    public BasesSectionPanel() {
        setBackground(Color.ORANGE);
        JScrollPane btsSenderScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        btsSenderScrollPane.getVerticalScrollBar().setUnitIncrement(7);
        btsSenderScrollPane.getHorizontalScrollBar().setUnitIncrement(7);
        btsSenderScrollPane.setMinimumSize(new Dimension(115, 420));
        btsSenderScrollPane.setMaximumSize(new Dimension(115, 420));
        btsSenderScrollPane.setAlignmentY(TOP_ALIGNMENT);
        btsSenderScrollPane.setBorder(null);

        BaseSectionPanel btsPanel1 = new BaseSectionPanel();
        btsPanel1.setBackground(Color.ORANGE);
        btsPanel1.createLayer(true);
        btsPanel1.setLayout(new BoxLayout(btsPanel1, BoxLayout.X_AXIS));
        btsPanel1.setBorder(null);

        btsSenderScrollPane.setViewportView(btsPanel1);


        JPanel bscSectionWrapper = new JPanel();
        bscSectionWrapper.setBackground(Color.ORANGE);
        bscSectionWrapper.setLayout(new BoxLayout(bscSectionWrapper, BoxLayout.Y_AXIS));
        bscSectionWrapper.setAlignmentY(TOP_ALIGNMENT);

        JScrollPane bscScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        bscScrollPane.setBorder(null);
        bscScrollPane.getVerticalScrollBar().setUnitIncrement(7);
        bscScrollPane.getHorizontalScrollBar().setUnitIncrement(7);
        bscScrollPane.setPreferredSize(new Dimension(450, 415));

        BaseSectionPanel bscPanel = new BaseSectionPanel();
        bscPanel.setBackground(Color.ORANGE);
        bscPanel.setLayout(new BoxLayout(bscPanel, BoxLayout.X_AXIS));
        bscPanel.createLayer(false);

        JPanel bscButtons = new JPanel();
        bscButtons.setBorder(new LineBorder(Color.BLACK, 1));
        bscButtons.setLayout(new BoxLayout(bscButtons, BoxLayout.X_AXIS));

        JButton addBscLayer = new JButton("+");
        addBscLayer.addActionListener(e -> {
            bscPanel.createLayer(false);
            revalidate();
        });
        JButton removeBscLayer = new JButton("-");
        removeBscLayer.addActionListener(e -> {
            bscPanel.removeLayer();
            revalidate();
        });

        bscScrollPane.setViewportView(bscPanel);

        bscButtons.add(addBscLayer);
        bscButtons.add(removeBscLayer);

        bscSectionWrapper.add(bscScrollPane);
        bscSectionWrapper.add(Box.createVerticalGlue());
        bscSectionWrapper.add(bscButtons);


        JScrollPane btsRecipientScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        btsRecipientScrollPane.getVerticalScrollBar().setUnitIncrement(7);
        btsRecipientScrollPane.getHorizontalScrollBar().setUnitIncrement(7);
        btsRecipientScrollPane.setMinimumSize(new Dimension(115, 420));
        btsRecipientScrollPane.setMaximumSize(new Dimension(115, 420));
        btsRecipientScrollPane.setAlignmentY(TOP_ALIGNMENT);
        btsRecipientScrollPane.setBorder(null);

        BaseSectionPanel btsPanel2 = new BaseSectionPanel();
        btsPanel2.setBackground(Color.ORANGE);
        btsPanel2.setAlignmentY(TOP_ALIGNMENT);
        btsPanel2.setLayout(new BoxLayout(btsPanel2, BoxLayout.Y_AXIS));
        btsPanel2.createLayer(true);

        btsRecipientScrollPane.setViewportView(btsPanel2);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(btsSenderScrollPane);
        add(Box.createHorizontalGlue());
        add(bscSectionWrapper);
        add(Box.createHorizontalGlue());
        add(btsRecipientScrollPane);
    }
}