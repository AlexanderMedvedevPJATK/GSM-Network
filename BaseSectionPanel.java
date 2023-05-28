import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BaseSectionPanel extends JPanel {
    private final List<BaseLayerPanel> baseLayerPanelList = new ArrayList<>();
    private final BaseController controller;
    private static int stationNumber;

    public BaseSectionPanel() {
        controller = new BaseController(this);
    }

    public int getStationNumber() {
        return stationNumber++;
    }

    public void createLayer(boolean isBts) {
        BaseLayerPanel layer = this.new BaseLayerPanel();

        layer.setAlignmentY(TOP_ALIGNMENT);
        layer.setBorder(new LineBorder(Color.BLACK, 2));
        layer.setLayout(new BoxLayout(layer, BoxLayout.Y_AXIS));

        baseLayerPanelList.add(layer);

        if(isBts && Storage.getBtsSenderLayer() != null) controller.createBtsRecipientLayer(layer);
        else if(isBts) controller.createBtsSenderLayer(layer);
        else controller.addBscLayer(layer);

        layer.createStation();

        this.add(layer);
    }

    public void removeLayer() {
        if(baseLayerPanelList.size() != 1) {
            BaseLayerPanel layer = baseLayerPanelList.get(baseLayerPanelList.size() - 1);
            remove(layer);
            baseLayerPanelList.remove(layer);
            controller.removeBscLayer(layer);
            repaint();
        }
    }

    class BaseLayerPanel extends JPanel {
        private final List<JPanel> baseStationsList = new ArrayList<>();

        public void createStation() {
            JPanel station = new JPanel();

            JLabel stationNumber = new JLabel(String.valueOf(getStationNumber()));
            stationNumber.setAlignmentX(CENTER_ALIGNMENT);
            JButton stationTerminate = new JButton("Terminate");
            stationTerminate.setAlignmentX(CENTER_ALIGNMENT);
            stationTerminate.addActionListener(e -> {
                if (baseLayerPanelList.size() != 1 || baseStationsList.size() != 1) {
                    if(baseStationsList.size() == 1) {
                        BaseSectionPanel.this.removeLayer();
                    } else {
                        remove(station);
                        baseStationsList.remove(station);
                        controller.removeStation(station);
                    }
                    revalidate();
                }
            });

            station.setLayout(new BoxLayout(station, BoxLayout.Y_AXIS));

            station.add(stationNumber);
            station.add(stationTerminate);


            add(station);
            baseStationsList.add(station);

            controller.createStation(this, station);

            BaseSectionPanel.this.revalidate();
        }
    }
}