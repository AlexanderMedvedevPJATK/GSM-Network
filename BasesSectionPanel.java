import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BasesSectionPanel extends JPanel {

    BasesController basesController;

    public BasesSectionPanel() {
        basesController = new BasesController(this);

        setBackground(Color.ORANGE);

        BaseSectionPanel btsPanel1 = new BaseSectionPanel();
        btsPanel1.createLayer();
        btsPanel1.setAlignmentY(TOP_ALIGNMENT);
        btsPanel1.setBorder(new LineBorder(Color.BLACK, 2));
        btsPanel1.setLayout(new BoxLayout(btsPanel1, BoxLayout.X_AXIS));
        basesController.createBtsSenderLayer();

        JPanel bscSectionWrapper = new JPanel();
        bscSectionWrapper.setLayout(new BoxLayout(bscSectionWrapper, BoxLayout.Y_AXIS));
        bscSectionWrapper.setBackground(Color.ORANGE);
        bscSectionWrapper.setAlignmentY(TOP_ALIGNMENT);

        BaseSectionPanel bscPanel = new BaseSectionPanel();
        bscPanel.setLayout(new BoxLayout(bscPanel, BoxLayout.X_AXIS));
        bscPanel.createLayer();
        basesController.addBscLayer();

        JPanel bscButtons = new JPanel();
        bscButtons.setBorder(new LineBorder(Color.BLACK, 1));
        bscButtons.setLayout(new BoxLayout(bscButtons, BoxLayout.X_AXIS));

        JButton addBscLayer = new JButton("+");
        addBscLayer.addActionListener(e -> {
            bscPanel.createLayer();
            basesController.addBscLayer();
            revalidate();
        });
        JButton removeBscLayer = new JButton("-");
        removeBscLayer.addActionListener(e -> {
            bscPanel.removeLayer();
            basesController.removeBscLayer();
            revalidate();
        });

        bscButtons.add(addBscLayer);
        bscButtons.add(removeBscLayer);

        bscSectionWrapper.add(bscPanel);
        bscSectionWrapper.add(Box.createVerticalGlue());
        bscSectionWrapper.add(bscButtons);

        BaseSectionPanel btsPanel2 = new BaseSectionPanel();
        btsPanel2.setAlignmentY(TOP_ALIGNMENT);
        btsPanel2.setBorder(new LineBorder(Color.BLACK, 1));
        btsPanel2.setLayout(new BoxLayout(btsPanel2, BoxLayout.Y_AXIS));
        btsPanel2.createLayer();

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(btsPanel1);
        add(Box.createHorizontalGlue());
        add(bscSectionWrapper);
        add(Box.createHorizontalGlue());
        add(btsPanel2);
    }
}