import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BasePanel extends JPanel {
    List<JPanel> baseLayersList = new ArrayList<>();

    public BasePanel.BaseLayer createLayer() {
        BasePanel.BaseLayer bscLayer = this.new BaseLayer();
        bscLayer.createStation(bscLayer);

        bscLayer.setAlignmentY(TOP_ALIGNMENT);
        bscLayer.setBorder(new LineBorder(Color.BLACK, 2));
        bscLayer.setLayout(new BoxLayout(bscLayer, BoxLayout.Y_AXIS));

        this.add(bscLayer);
        this.baseLayersList.add(bscLayer);
        return bscLayer;
    }

    public void removeLayer() {
        if(baseLayersList.size() != 1) {
            remove(baseLayersList.get(baseLayersList.size() - 1));
            baseLayersList.remove(baseLayersList.size() - 1);
        }
    }

    class BaseLayer extends JPanel {
        List<JPanel> baseStationsList = new ArrayList<>();

        public void createStation(BasePanel.BaseLayer stationLayer) {
            JPanel station = new JPanel();

            JLabel stationNumber = new JLabel("number");
            stationNumber.setAlignmentX(CENTER_ALIGNMENT);
            JButton stationTerminate = new JButton("Terminate");
            stationTerminate.setAlignmentX(CENTER_ALIGNMENT);
            stationTerminate.addActionListener(e -> {
                stationLayer.remove(station);
                baseStationsList.remove(station);

                if(stationLayer.baseStationsList.size() == 0) {
                    revalidate();
                    baseLayersList.remove(stationLayer);
                    BasePanel.this.remove(stationLayer);
                }

                revalidate();
            });

            station.setLayout(new BoxLayout(station, BoxLayout.Y_AXIS));

            station.add(stationNumber);
            station.add(stationTerminate);

            stationLayer.add(station);
            stationLayer.baseStationsList.add(station);
        }
    }
}
