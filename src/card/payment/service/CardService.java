package card.payment.service;
import card.payment.model.Card;
import java.util.ArrayList;
public class CardService {
    static ArrayList<Card> cardsList = new ArrayList<>();
    static int id = 0;
    public static int addCard(String number, int cvv){
        int access = accessCard(number);
        if(access == 1){
            Card newCard = new Card(number, cvv);
            newCard.setBalance(50000);
            newCard.setType("MASTERCARD");
            newCard.setUserId(UserService.getId());
            newCard.setId(id++);
            cardsList.add(newCard);
            return 1;
        }
        else if(access == 2){
            Card newCard = new Card(number, cvv);
            newCard.setBalance(50000);
            newCard.setType("VISA");
            newCard.setUserId(UserService.getId());
            newCard.setId(id++);
            cardsList.add(newCard);
            return 2;
        }
        else if(access == 3){
            Card newCard = new Card(number, cvv);
            newCard.setBalance(5000);
            newCard.setType("UZCARD");
            newCard.setUserId(UserService.getId());
            newCard.setId(id++);
            cardsList.add(newCard);
            return 3;
        }else if(access == 4) {
            Card newCard = new Card(number, cvv);
            newCard.setBalance(0);
            newCard.setType("Unknown");
            newCard.setUserId(UserService.getId());
            newCard.setId(id++);
            cardsList.add(newCard);
            return 4;
        }else {
            return -1;
        }
    }
    private static int accessCard(String number){
        String cardnumbers = number.substring(0, 1);
        if(number.length() == 16){
            if(cardnumbers.equals("5")){
                return 1;
            }
            else if(cardnumbers.equals("4")){
                return 2;
            }
            else if(cardnumbers.equals("8")){
                return 3;
            }
            else {
                return 4;
            }
        }
        return -1;
    };
    public static void defaultCards(){
        Card newCard = new Card(1, "8000800080008000", "UZCARD", 50000, 188);
        newCard.setId(id++);
        cardsList.add(newCard);
        Card newCard2 = new Card(1, "4000800080008000", "VISA", 50000, 546);
        newCard2.setId(id++);
        cardsList.add(newCard2);
        Card newCard3 = new Card(2, "5000800080008000", "MASTERCARD", 50000, 765);
        newCard3.setId(id++);
        cardsList.add(newCard3);
        Card newCard4 = new Card(2, "6000800080008000", "UNKNOWN", 0, 289);
        newCard4.setId(id++);
        cardsList.add(newCard4);
    }
    public static void getCardById(){
        for (int i = 0; i < cardsList.size(); i++) {
            if(cardsList.get(i).getUserId() == UserService.getId()){
                System.out.println("|>> " + cardsList.get(i).getId() + " <<|>> " + cardsList.get(i).getType() + " <<|>> Number <<|>> " + cardsList.get(i).getNumbers() + " <<|>> CVV <<|>> " + cardsList.get(i).getCvv() + " <<|");
            }
        }
    }
    public static void getCardBalance(){
        for (int i = 0; i < cardsList.size(); i++) {
            if(cardsList.get(i).getUserId() == UserService.getId()){
                System.out.println("|>> " + cardsList.get(i).getType() +" <<|>> " + cardsList.get(i).getNumbers() + " <<|>> Balance <<|>> " + cardsList.get(i).getBalance() + " <<|");
            }
        }
    }
    public static int deleteCard(int id){
        for (int i = 0; i < cardsList.size(); i++) {
            if (cardsList.get(i).getUserId() == UserService.getId()){
                if(cardsList.get(i).getId() == id){
                    cardsList.remove(i);
                    return 1;
                }else {
                    return -2;
                }
            }
            else {
                return -1;
            }
        }
        return 0;
    }
    public static int addToCard(int id, int money){
        for (int i = 0; i < cardsList.size(); i++) {
            if(cardsList.get(i).getId() == id){
                int balance = cardsList.get(i).getBalance();
                int result = balance + money;
                cardsList.get(i).setBalance(result);
                return 1;
            }
        }
        return -1;
    }
    public static int getIDbyNumber(String num){
        for (int i = 0; i < cardsList.size(); i++) {
            if (cardsList.get(i).getNumbers().equals(num)){
                return cardsList.get(i).getUserId();
            }
        }
        return -1;
    }
    public static int addToOtherCard(int id, String number, int money){
        for (int i = 0; i < cardsList.size(); i++) {
            if(cardsList.get(i).getId() == id){
                boolean chm = checkMoney(id, money);
                if (chm){
                    int numch = checkNumber(number);
                    if (numch == -1){
                        return -1;
                    }
                    else {
                        int firstmoney = cardsList.get(i).getBalance();
                        int firstres = firstmoney - money;
                        cardsList.get(i).setBalance(firstres);
                        int secondmoney = cardsList.get(numch).getBalance();
                        int secondres = secondmoney + money;
                        cardsList.get(numch).setBalance(secondres);
                        int userIdbyNumber = getIDbyNumber(number);
                        HistoryService.saveHistory(UserService.getId(), userIdbyNumber, cardsList.get(i).getNumbers(), number, money);
                        return 1;
                    }
                }
                else {
                    return -2;
                }
            }
        }
        return -3;
    }
    private static boolean checkMoney(int id, int money){
        if(cardsList.get(id).getBalance() >= money){
            return true;
        }
        return false;
    }
    private static int checkNumber(String number){
        for (int i = 0; i < cardsList.size(); i++) {
            if (cardsList.get(i).getNumbers().equals(number)){
                return cardsList.get(i).getId();
            }
        }
        return -1;
    }
}
