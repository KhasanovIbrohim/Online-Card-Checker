package card.payment.model;

public class History {
    private int id;
    private int senderID;
    private int receiverID;
    private String firstCard;
    private String secondCard;
    private int sum;
    public History(int senderID, int receiverID, String firstCard, String secondCard, int sum) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.firstCard = firstCard;
        this.secondCard = secondCard;
        this.sum = sum;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getSenderID() {
        return senderID;
    }
    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }
    public int getReceiverID() {
        return receiverID;
    }
    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }
    public String getFirstCard() {
        return firstCard;
    }
    public void setFirstCard(String firstCard) {
        this.firstCard = firstCard;
    }
    public String getSecondCard() {
        return secondCard;
    }
    public void setSecondCard(String secondCard) {
        this.secondCard = secondCard;
    }
    public int getSum() {
        return sum;
    }
    public void setSum(int sum) {
        this.sum = sum;
    }
}
