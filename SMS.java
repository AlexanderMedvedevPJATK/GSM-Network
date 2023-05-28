public class SMS {
    private final Sender sender;
    private final String text;
    private final Recipient recipient;
    private long timeToSend;

    public SMS(Sender sender, String text, Recipient recipient) {
        this.sender = sender;
        this.text = text;
        this.recipient = recipient;
    }

    public String getText() {
        return text;
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