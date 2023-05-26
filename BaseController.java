import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BaseController {
    BaseSectionPanel baseSectionPanel;

    public BaseController(BaseSectionPanel baseSectionPanel) {
        this.baseSectionPanel = baseSectionPanel;
    }

    public void createStation(JPanel layerPanel, JPanel stationPanel) {
        Layer.Station station = Storage.getPanelLayerMap().get(layerPanel).addStation();
        Storage.getPanelStationMap().put(stationPanel, station);
    }

    public void removeStation(JPanel station) {
        Storage.getPanelStationMap().get(station).removeStation();
        Storage.removeStation(station);
    }

    public void createBtsSenderLayer(JPanel layerPanel) {
        BtsLayer btsLayer = new BtsLayer();
        Storage.setBtsSenderLayer(layerPanel, btsLayer);
    }

    public void createBtsRecipientLayer(JPanel layerPanel) {
        BtsLayer btsLayer = new BtsLayer();
        Storage.setBtsRecipientLayer(layerPanel, btsLayer);
    }

    public void addBscLayer(JPanel layerPanel) {
        BscLayer bscLayer = new BscLayer();
        Storage.addBscLayer(layerPanel, bscLayer);
    }

    public void removeBscLayer(JPanel panel) {
        List<Layer.Station> stations = new ArrayList<>(Storage.getBscLayerMap().get(panel).getStations());
        for (Layer.Station station : stations) {
            station.removeStation();
        }
        Storage.removeBscLayer(panel);
    }
}
