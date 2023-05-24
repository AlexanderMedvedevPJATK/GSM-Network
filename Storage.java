import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static BtsLayer btsSenderLayer;
    private static List<BscLayer> bscLayersList = new ArrayList<>();
    private static BtsLayer btsRecipientLayer;
    private static List<Recipient> recipientList;

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

    public static List<Recipient> getRecipientList() {
        return recipientList;
    }

    public static void setRecipientList(List<Recipient> recipientList) {
        Storage.recipientList = recipientList;
    }
}
