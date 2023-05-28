public class SMS {
    private Sender sender;
    private String text;
    private Recipient recipient;
    private long timeToSend;

    public SMS(Sender sender, String text, Recipient recipient) {
        this.sender = sender;
        this.text = text;
        this.recipient = recipient;
    }

    public long getTimeToSend() {
        return timeToSend;
    }

    public void setTimeToSend(long timeToSend) {
        this.timeToSend = timeToSend;
    }

    public Recipient getRecipient() {
        return recipient;
    }
}
