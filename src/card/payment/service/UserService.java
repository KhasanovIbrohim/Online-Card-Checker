package card.payment.service;
import card.payment.model.User;
import java.util.ArrayList;
public class UserService {
    static ArrayList<User> userList = new ArrayList<>();
    static int id = 0;
    private static int currentID;
    public static int getId(){
        return currentID;
    }
    public static void defaultAdmin(){
        User admin = new User("909341235", "ibrohim0777", true, false, true);
        admin.setId(id++);
        User user = new User("972658001", "ibrohim", false, false, true);
        user.setId(id++);
        User user2 = new User("999999999", "ibrohimdev", false, false, true);
        user2.setId(id++);
        userList.add(admin);
        userList.add(user);
        userList.add(user2);
    }
    public static int login(String phone, String password) {
        for (User user: userList) {
            if(user.getPhone().equals(phone) && user.getPassword().equals(password)){
                if (user.isActive()){
                    if(user.isBanned()){
                        return -2;
                    }
                    else if(user.isAdmin()) {
                        return -3;
                    }
                    currentID = user.getId();
                    return user.getId();
                }
            }
        }
        return -1;
    }
    public static int signup(String phone, String password) {
        if(phone.length() > 5) {
            if (password.length() > 3){
                boolean phoneChecker = checkPhone(phone);
                if(!phoneChecker){
                    User newUser = new User(phone, password);
                    newUser.setId(id++);
                    newUser.setAdmin(false);
                    newUser.setBanned(false);
                    newUser.setActive(true);
                    userList.add(newUser);
                    return 1;
                }
                return 3;
            }
            return 2;
        }
        return 0;
    }
    public static boolean checkPhone(String phone){
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getPhone() == phone){
                return true;
            }
        }
        return false;
    }
    public static void getProfile(){
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id){
                System.out.println("|>> Phone number <<|>> " + userList.get(i).getPhone() + " <<|");
                System.out.println("|>> Password <<|>> " + userList.get(i).getPassword() + " <<|");
            }
        }
    }
    public static void getAllUsers(){
        for (int i = 0; i < userList.size(); i++) {
            System.out.println("|>>>");
            System.out.println("|>> Phone <<|>> " + userList.get(i).getPhone() + " <<|");
            System.out.println("|>> Password <<|>> " + userList.get(i).getPassword() + " <<|");
            System.out.println("|>> Is Admin? <<|>> " + userList.get(i).isAdmin() + " <<|");
            System.out.println("|>> Is Banned? <<|>> " + userList.get(i).isBanned() + " <<|");
        }
    }
    public static int addAdminByAdmin(String phone, String password){
        if(phone.length() > 5) {
            if (password.length() > 3){
                boolean phoneChecker = checkPhone(phone);
                if(!phoneChecker){
                    User newadmin = new User(phone, password);
                    newadmin.setId(id++);
                    newadmin.setAdmin(true);
                    newadmin.setBanned(false);
                    userList.add(newadmin);
                    return 1;
                }
                return 3;
            }
            return 2;
        }
        return 0;
    }
    public static void viewBannedPeople(){
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).isBanned()){
                System.out.println("|>> Banned! <<|>> " + userList.get(i).getPhone() + " <<|");
            }
        }
    }
    public static int sendBan(int id){
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getId() == id){
                userList.get(i).setBanned(true);
                return 1;
            }
        }
        return -1;
    }
    public static int removeBan(int id){
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getId() == id){
                userList.get(i).setBanned(false);
                return 1;
            }
        }
        return -1;
    }
    public static void viewUsersToRemove(){
        for (int i = 0; i < userList.size(); i++) {
                System.out.println("|>>>");
                System.out.println("|>> ID <<|>> " + userList.get(i).getId() + " <<|");
                System.out.println("|>> Phone <<|>> " + userList.get(i).getPhone() + " <<|");
                System.out.println("|>> Is Banned? <<|>> " + userList.get(i).isBanned() + " <<|");
        }
    }
    public static void deleteUser(){
        userList.get(currentID).setActive(false);
    }
}
