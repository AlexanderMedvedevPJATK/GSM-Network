import java.util.List;

public class BasesController {
    BasesSectionPanel basesSectionPanel;

    public BasesController(BasesSectionPanel basesSectionPanel) {
        this.basesSectionPanel = basesSectionPanel;
    }

    public void createBtsSenderLayer() {
        BtsLayer btsLayer = new BtsLayer();
        btsLayer.addStation();
        Storage.setBtsSenderLayer(btsLayer);
    }

    public void addBscLayer() {
        BscLayer bscLayer = new BscLayer();
        bscLayer.addStation();
        List<BscLayer> newBscLayerList = Storage.getBscLayersList();
        newBscLayerList.add(bscLayer);
        Storage.setBscLayersList(newBscLayerList);
    }

    public void removeBscLayer() {
        List<BscLayer> newBscLayerList = Storage.getBscLayersList();
        newBscLayerList.remove(newBscLayerList.size() - 1);
        Storage.setBscLayersList(newBscLayerList);
    }
}
