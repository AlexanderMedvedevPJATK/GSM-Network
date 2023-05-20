import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BasesPanel extends JPanel {
    List<JPanel> bscLayersList = new ArrayList<>();
    public BasesPanel() {
        setBackground(Color.ORANGE);

        JPanel btsPanel1 = new JPanel();
        createStation(btsPanel1);
        createStation(btsPanel1);
        createStation(btsPanel1);
        btsPanel1.setAlignmentY(TOP_ALIGNMENT);
        btsPanel1.setBorder(new LineBorder(Color.BLACK, 2));
        btsPanel1.setLayout(new BoxLayout(btsPanel1, BoxLayout.Y_AXIS));


        BscPanel bscPanel = new BscPanel();
        bscPanel.setAlignmentY(TOP_ALIGNMENT);
        bscPanel.setLayout(new BoxLayout(bscPanel, BoxLayout.Y_AXIS));
        bscPanel.setBackground(Color.ORANGE);

        BscPanel.BscLayer bscLayers = bscPanel.new BscLayer();
        bscLayers.setLayout(new BoxLayout(bscLayers, BoxLayout.X_AXIS));
        createLayer(bscLayers);
        createLayer(bscLayers);
        createLayer(bscLayers);
        createLayer(bscLayers);

        JPanel bscButtons = new JPanel();

        JButton addBscLayer = new JButton("+");
        addBscLayer.addActionListener(e -> {
            createLayer(bscLayers);
            revalidate();
        });
        JButton removeBscLayer = new JButton("-");
        removeBscLayer.addActionListener(e -> {
            removeLayer(bscLayers);
            revalidate();
        });

        bscButtons.setBorder(new LineBorder(Color.BLACK, 2));
        bscButtons.setLayout(new BoxLayout(bscButtons, BoxLayout.X_AXIS));
        bscButtons.add(addBscLayer);
        bscButtons.add(removeBscLayer);

        bscPanel.add(bscLayers);
        bscPanel.add(Box.createVerticalGlue());
        bscPanel.add(bscButtons);


        JPanel btsPanel2 = new JPanel();
        createStation(btsPanel2);
        createStation(btsPanel2);
        createStation(btsPanel2);
        btsPanel2.setAlignmentY(TOP_ALIGNMENT);
        btsPanel2.setBorder(new LineBorder(Color.BLACK, 2));
        btsPanel2.setLayout(new BoxLayout(btsPanel2, BoxLayout.Y_AXIS));

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(btsPanel1);
        add(Box.createHorizontalGlue());
        add(bscPanel);
        add(Box.createHorizontalGlue());
        add(btsPanel2);
    }

    public void createStation(JPanel stationPanel) {
        JPanel station = new JPanel();

        JLabel stationNumber = new JLabel("number");
        stationNumber.setAlignmentX(CENTER_ALIGNMENT);
        JButton stationTerminate = new JButton("Terminate");
        stationTerminate.setAlignmentX(CENTER_ALIGNMENT);
        stationTerminate.addActionListener(e -> {
            stationPanel.remove(station);
            revalidate();
        });

        station.setLayout(new BoxLayout(station, BoxLayout.Y_AXIS));

        station.add(stationNumber);
        station.add(stationTerminate);

        stationPanel.add(station);
    }

    public List<JPanel> createLayer(JPanel bscLayers) {
        JPanel bscPanel = new JPanel();
        createStation(bscPanel);

        bscPanel.setAlignmentY(TOP_ALIGNMENT);
        bscPanel.setBorder(new LineBorder(Color.BLACK, 2));
        bscPanel.setLayout(new BoxLayout(bscPanel, BoxLayout.Y_AXIS));

        bscLayers.add(bscPanel);
        bscLayersList.add(bscPanel);
        return bscLayersList;
    }

    public void removeLayer(JPanel bscLayers) {
        if(bscLayersList.size() != 0) {
            bscLayers.remove(bscLayersList.get(bscLayersList.size() - 1));
            bscLayersList.remove(bscLayersList.size() - 1);
        }
    }
}
