package card.payment.service;
import card.payment.model.History;
import java.util.ArrayList;
public class HistoryService {
    static ArrayList<History> historyList = new ArrayList<>();
    static int id = 0;
    public static void saveHistory(int senderID, int receiverID, String firstCard, String secondCard, int sum){
        History newHistory = new History(senderID, receiverID, firstCard, secondCard, sum);
        newHistory.setId(id++);
        historyList.add(newHistory);
    }
    public static void getHistory(){
        for (int i = 0; i < historyList.size(); i++) {
            if (historyList.get(i).getSenderID() == UserService.getId()){
                System.out.println("|>> You send to " + historyList.get(i).getSecondCard() + " <<|>> Money <<|>> " + historyList.get(i).getSum() + " <<|");
            }
            if(historyList.get(i).getReceiverID() == UserService.getId()){
                System.out.println("|>> " + historyList.get(i).getFirstCard() + " <<|>> This user have sended you <<|>> " + historyList.get(i).getSum() + " Money <<|");
            }
        }
    }
}
