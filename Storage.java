import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {
    private static Map<JPanel, Sender> senderMap = new HashMap<>();
    private static BtsLayer btsSenderLayer;
    private static List<BscLayer> bscLayersList = new ArrayList<>();
    private static BtsLayer btsRecipientLayer;
    private static Map<JPanel, Recipient> recipientMap = new HashMap<>();
    private static Map<Recipient, JPanel> recipientReverseMap = new HashMap<>();

    public static Map<JPanel, Sender> getSenderMap() {
        return senderMap;
    }

    public static void setSenderMap(Map<JPanel, Sender> senderMap) {
        Storage.senderMap = senderMap;
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

    public static void setBtsSenderLayer(BtsLayer btsSenderLayer) {
        Storage.btsSenderLayer = btsSenderLayer;
    }

    public static List<BscLayer> getBscLayersList() {
        return bscLayersList;
    }

    public static void setBscLayersList(List<BscLayer> bscLayersList) {
        Storage.bscLayersList = bscLayersList;
    }

    public static BtsLayer getBtsRecipientLayer() {
        return btsRecipientLayer;
    }

    public static void setBtsRecipientLayer(BtsLayer btsRecipientLayer) {
        Storage.btsRecipientLayer = btsRecipientLayer;
    }

    public static Map<JPanel, Recipient> getRecipientMap() {
        return recipientMap;
    }

    public static void setRecipientMap(Map<JPanel, Recipient> recipientMap) {
        Storage.recipientMap = recipientMap;
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
}
