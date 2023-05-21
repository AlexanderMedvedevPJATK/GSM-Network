import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BasesPanel extends JPanel {
    public BasesPanel() {
        setBackground(Color.ORANGE);

        BasePanel btsPanel1 = new BasePanel();
        btsPanel1.createLayer();

        btsPanel1.setAlignmentY(TOP_ALIGNMENT);
        btsPanel1.setBorder(new LineBorder(Color.BLACK, 2));
        btsPanel1.setLayout(new BoxLayout(btsPanel1, BoxLayout.X_AXIS));

        JPanel bscSectionWrapper = new JPanel();
        bscSectionWrapper.setLayout(new BoxLayout(bscSectionWrapper, BoxLayout.Y_AXIS));
        bscSectionWrapper.setBackground(Color.ORANGE);
        bscSectionWrapper.setAlignmentY(TOP_ALIGNMENT);

        BasePanel bscPanel = new BasePanel();
        bscPanel.setLayout(new BoxLayout(bscPanel, BoxLayout.X_AXIS));

        bscPanel.createLayer();

        JPanel bscButtons = new JPanel();

        JButton addBscLayer = new JButton("+");
        addBscLayer.addActionListener(e -> {
            bscPanel.createLayer();
            revalidate();
        });
        JButton removeBscLayer = new JButton("-");
        removeBscLayer.addActionListener(e -> {
            bscPanel.removeLayer();
            revalidate();
        });

        bscButtons.setBorder(new LineBorder(Color.BLACK, 1));
        bscButtons.setLayout(new BoxLayout(bscButtons, BoxLayout.X_AXIS));
        bscButtons.add(addBscLayer);
        bscButtons.add(removeBscLayer);

        bscSectionWrapper.add(bscPanel);
        bscSectionWrapper.add(Box.createVerticalGlue());
        bscSectionWrapper.add(bscButtons);

        BasePanel btsPanel2 = new BasePanel();
        btsPanel2.createLayer();

        btsPanel2.setAlignmentY(TOP_ALIGNMENT);
        btsPanel2.setBorder(new LineBorder(Color.BLACK, 1));
        btsPanel2.setLayout(new BoxLayout(btsPanel2, BoxLayout.Y_AXIS));

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(btsPanel1);
        add(Box.createHorizontalGlue());
        add(bscSectionWrapper);
        add(Box.createHorizontalGlue());
        add(btsPanel2);
    }
}
