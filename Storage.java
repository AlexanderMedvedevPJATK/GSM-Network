import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {
    // Linking JPanel representations of senders to their logical classes
    private static Map<JPanel, Sender> senderMap = new HashMap<>();
    // The first BTS layer
    private static BtsLayer btsSenderLayer;
    // All the BSC layers
    private static List<BscLayer> bscLayerList = new ArrayList<>();
    // Linking JPanel representations of BscLayers to their logical classes
    private static Map<JPanel, BscLayer> bscLayerMap = new HashMap<>();
    // Reversed
    private static Map<BscLayer, JPanel> bscLayerReverseMap = new HashMap<>();
    // The last BTS layer
    private static BtsLayer btsRecipientLayer;
    // Linking JPanel representations of the stations to their logical classes
    private static Map<JPanel, Layer.Station> panelStationMap = new HashMap<>();
    // Linking JPanel representations of the recipients to their logical classes
    private static Map<JPanel, Recipient> recipientMap = new HashMap<>();
    // Reversed
    private static Map<Recipient, JPanel> recipientReverseMap = new HashMap<>();
    // Linking every layer JPanel representation to their logical classes
    private static Map<JPanel, Layer> panelLayerMap = new HashMap<>();
    // Reversed
    private static Map<Layer, JPanel> layerPanelMap = new HashMap<>();

    public static Map<JPanel, Sender> getSenderMap() {
        return senderMap;
    }

    public static void addSender(JPanel panel, Sender sender) {
        senderMap.put(panel, sender);
    }

    public static void removeSender(JPanel panel) {
        senderMap.remove(panel);
    }

    public static BtsLayer getBtsSenderLayer() {
        return btsSenderLayer;
    }

    public static void setBtsSenderLayer(JPanel layerPanel, BtsLayer btsSenderLayer) {
        Storage.btsSenderLayer = btsSenderLayer;
        panelLayerMap.put(layerPanel, btsSenderLayer);
        layerPanelMap.put(btsSenderLayer, layerPanel);
    }

    public static Map<JPanel, BscLayer> getBscLayerMap() {
        return bscLayerMap;
    }

    public static Map<BscLayer, JPanel> getBscLayerReverseMap() {
        return bscLayerReverseMap;
    }

    public static List<BscLayer> getBscLayerList() {
        return bscLayerList;
    }

    public static void addBscLayer(JPanel panel, BscLayer layer) {
        bscLayerList.add(layer);
        bscLayerMap.put(panel, layer);
        bscLayerReverseMap.put(layer, panel);
        panelLayerMap.put(panel, layer);
        layerPanelMap.put(layer, panel);
    }

    public static void removeBscLayer(JPanel panel) {
        layerPanelMap.remove(bscLayerMap.get(panel));
        bscLayerList.remove(bscLayerMap.get(panel));
        bscLayerReverseMap.remove(bscLayerMap.get(panel));
        bscLayerMap.remove(panel);
        panelLayerMap.remove(panel);
    }

    public static BtsLayer getBtsRecipientLayer() {
        return btsRecipientLayer;
    }

    public static void setBtsRecipientLayer(JPanel layerPanel, BtsLayer btsRecipientLayer) {
        Storage.btsRecipientLayer = btsRecipientLayer;
        panelLayerMap.put(layerPanel, btsRecipientLayer);
        layerPanelMap.put(btsRecipientLayer, layerPanel);
    }

    public static Map<JPanel, Recipient> getRecipientMap() {
        return recipientMap;
    }

    public static void addRecipient(JPanel panel, Recipient recipient) {
        recipientMap.put(panel, recipient);
        recipientReverseMap.put(recipient, panel);
    }

    public static void removeRecipient(JPanel panel) {
        recipientReverseMap.remove(recipientMap.get(panel));
        recipientMap.remove(panel);
    }

    public static Map<Recipient, JPanel> getRecipientReverseMap() {
        return recipientReverseMap;
    }

    public static Map<JPanel, Layer.Station> getPanelStationMap() {
        return panelStationMap;
    }

    public static void removeStation(JPanel stationPanel) {
        panelStationMap.remove(stationPanel);
    }

    public static Map<JPanel, Layer> getPanelLayerMap() {
        return panelLayerMap;
    }

    public static Map<Layer, JPanel> getLayerPanelMap() {
        return layerPanelMap;
    }
}
