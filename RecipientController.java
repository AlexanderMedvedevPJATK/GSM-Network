public class RecipientController {
    private RecipientPanel recipientPanel;

    public RecipientController(RecipientPanel recipientPanel) {
        this.recipientPanel = recipientPanel;
    }

    public void createRecipient() {
        Recipient recipient = new Recipient();
        recipient.start();
    }
}
