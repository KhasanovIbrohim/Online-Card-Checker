package card.payment.model;

public class Card {
    private int id;
    private int userId;
    private String numbers;
    private String type;
    private int balance;
    private int cvv;
    private boolean isActive;
    public Card(String numbers, int cvv) {
        this.numbers = numbers;
        this.cvv = cvv;
    }
    public Card(int userId, String numbers, String type, int balance, int cvv) {
        this.userId = userId;
        this.numbers = numbers;
        this.type = type;
        this.balance = balance;
        this.cvv = cvv;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getNumbers() {
        return numbers;
    }
    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public int getCvv() {
        return cvv;
    }
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", userId=" + userId +
                ", numbers='" + numbers + '\'' +
                ", type='" + type + '\'' +
                ", balance=" + balance +
                ", cvv=" + cvv +
                '}';
    }
}
