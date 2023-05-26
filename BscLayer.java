import java.util.ArrayList;
import java.util.List;

public class BscLayer extends Layer{
    @Override
    public Station createStation() {
        return new BSC();
    }


    public class BSC extends Station {
        private SMS sms;
        private int smsCounter;
        public void sendSms() {
            int thisLayerIndex = Storage.getBscLayerList().indexOf(BscLayer.this);
            if (sms != null) {
                if (thisLayerIndex != Storage.getBscLayerList().size() - 1) {
                    BscLayer next = Storage.getBscLayerList().get(thisLayerIndex + 1);
                    next.assignSms(sms);
                } else {
                    sms.getRecipient().setSms(sms);
                }
            }
        }
    }
}
