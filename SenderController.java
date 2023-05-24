public class SenderController {
    private SenderPanel senderPanel;

    public SenderController(SenderPanel senderPanel) {
        this.senderPanel = senderPanel;
    }

    public void createSender() {
        Sender sender = new Sender();
        sender.start();
    }
}
