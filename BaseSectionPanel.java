import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BaseSectionPanel extends JPanel {
    private List<BaseLayerPanel> baseLayerPanelList = new ArrayList<>();

    public BaseLayerPanel createLayer() {
        BaseLayerPanel layer = this.new BaseLayerPanel();
        layer.createStation(layer);

        layer.setAlignmentY(TOP_ALIGNMENT);
        layer.setBorder(new LineBorder(Color.BLACK, 2));
        layer.setLayout(new BoxLayout(layer, BoxLayout.Y_AXIS));
        
        baseLayerPanelList.add(layer);

        this.add(layer);
        return layer;
    }

    public void removeLayer() {
        if(baseLayerPanelList.size() != 1) {
            remove(baseLayerPanelList.get(baseLayerPanelList.size() - 1));
            baseLayerPanelList.remove(baseLayerPanelList.size() - 1);
        }
    }

    class BaseLayerPanel extends JPanel {
        private List<JPanel> baseStationsList = new ArrayList<>();

        public void createStation(BaseLayerPanel stationLayer) {
            JPanel station = new JPanel();

            JLabel stationNumber = new JLabel("number");
            stationNumber.setAlignmentX(CENTER_ALIGNMENT);
            JButton stationTerminate = new JButton("Terminate");
            stationTerminate.setAlignmentX(CENTER_ALIGNMENT);
            stationTerminate.addActionListener(e -> {
                if (baseLayerPanelList.size() != 1 || baseStationsList.size() != 1) {
                    stationLayer.remove(station);
                    baseStationsList.remove(station);

                    if(stationLayer.baseStationsList.size() == 0) {
                        revalidate();
                        baseLayerPanelList.remove(stationLayer);
                        BaseSectionPanel.this.remove(stationLayer);
                    }

                    revalidate();
                }
            });

            station.setLayout(new BoxLayout(station, BoxLayout.Y_AXIS));

            station.add(stationNumber);
            station.add(stationTerminate);


            stationLayer.add(station);
            stationLayer.baseStationsList.add(station);
        }
    }
}
