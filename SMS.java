public class SMS {
    private Sender sender;
    private String text;
    private Recipient recipient;

    public SMS(Sender sender, String text, Recipient recipient) {
        this.sender = sender;
        this.text = text;
        this.recipient = recipient;
    }

    public Recipient getRecipient() {
        return recipient;
    }
}
