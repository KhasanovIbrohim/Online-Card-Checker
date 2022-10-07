package card.payment;

import card.payment.service.CardService;

import card.payment.service.HistoryService;
import card.payment.service.UserService;

import java.util.Scanner;

public class Main {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static Scanner scanInt = new Scanner(System.in);
    static Scanner scanStr = new Scanner(System.in);
    static Scanner scanStr2 = new Scanner(System.in);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Core menu's
    public static void main(String[] argv){
        UserService.defaultAdmin();
        CardService.defaultCards();
        CoreMenu();
    }
    public static void CoreMenu() {
        System.out.println("|>> Welcome to card payment! <<|");
        System.out.println("|>> 1 <<|>> Login            <<|");
        System.out.println("|>> 2 <<|>> SignUp           <<|");
        System.out.println("|>> 0 <<|>> Exit             <<|");
        System.out.println("|>> Version <<|>> 1.5.3      <<|");
        System.out.print("|>>>");
        int i = scanInt.nextInt();
        switch (i){
            case 1 -> {
                login();
            }
            case 2 -> {
                signUp();
            }
            case 0 -> {
                System.out.println("Thank you for using!");
                return;
            }
        }
    }
    public static void login(){
        System.out.println("|>> Login Menu <<|");
        System.out.println("|>> Write your phone number: <<|");
        String number = scanStr2.nextLine();
        System.out.println("|>> Write your password: <<|");
        String password = scanStr.next();
        int result = UserService.login(number, password);
        if(result == -2){
            System.out.println("Your account is banned!");
            CoreMenu();
        } else if (result == -1) {
            System.out.println("Account not found!");
            login();
        } else if (result == -3) {
            System.out.println("Verified admin!");
            AdminMenu();
        } else {
            System.out.println("Verified user!");
            UserMenu();
        }
    }
    public static void signUp() {
        System.out.println("|>> Sign Up Menu <<|");
        System.out.println("|>> Write your phone number: <<|");
        String number = scanStr2.nextLine();
        System.out.println("|>> Write your password: <<|");
        String password = scanStr.next();
        int result = UserService.signup(number, password);
        if (result == 0){
            System.out.println("The phone number is too small (min: 5):");
            signUp();
        }else if(result == 2){
            System.out.println("The password is so small (min: 3): ");
            signUp();
        }else if(result == 3){
            System.out.println("This phone is already used!");
            signUp();
        }else {
            System.out.println("Verified!");
            CoreMenu();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //User menu's
    public static void UserMenu() {
        System.out.println("|>> Online Card Payment <<|");
        System.out.println("|>> 1 <<|>> Cards       <<|");
        System.out.println("|>> 2 <<|>> Balance     <<|");
        System.out.println("|>> 3 <<|>> Profile     <<|");
        System.out.println("|>> 4 <<|>> History     <<|");
        System.out.println("|>> 0 <<|>> Exit        <<|");
        int i = scanInt.nextInt();
        switch (i) {
            case 1 -> userCards();
            case 2 -> userBalance();
            case 3 -> userProfile();
            case 4 -> showHistory();
            case 0 -> userExit();
        }
    }
    public static void userCards(){
        System.out.println("|>> User online cards <<|");
        System.out.println("|>> 1 <<|>> View      <<|");
        System.out.println("|>> 2 <<|>> Add       <<|");
        System.out.println("|>> 3 <<|>> Delete    <<|");
        System.out.println("|>> 0 <<|>> Back      <<|");
        int i = scanInt.nextInt();
        switch (i){
            case 1 -> {
                System.out.println("|>> Your cards: <<");
                CardService.getCardById();
                userCards();
            }
            case 2 -> {
                System.out.println("|>> Write card numbers (16): <<|");
                String numbers = scanStr2.nextLine();
                System.out.println("|>> Write card's cvv: <<|");
                int cvv = scanInt.nextInt();
                int newCard = CardService.addCard(numbers, cvv);
                checkCard(newCard);
            }
            case 3 -> {
                System.out.println("|>> Choose card <<|");
                CardService.getCardById();
                int num = scanInt.nextInt();
                int res = CardService.deleteCard(num);
                if(res == -1){
                    System.out.println("It's not your card!");
                    userCards();
                }else if(res == 1){
                    System.out.println("Successfully deleted!");
                    userCards();
                }else {
                    System.out.println("Card not found!");
                    userCards();
                }
            }
            case 0 -> UserMenu();
        }
    }
    public static void checkCard(int newCard){
        if(newCard < 0){
            System.out.println("Sorry your card is invalid!");
        }else if(newCard == 1){
            System.out.println("|>> MASTERCARD card verified and added! <<|");
            System.out.println("|>> Our team give you 5$ <<|");
            userCards();
        }else if(newCard == 2){
            System.out.println("|>> VISA card verified and added! <<|");
            System.out.println("|>> Our team give you 5$ <<|");
            userCards();
        }else if(newCard == 3){
            System.out.println("|>> UZCARD card verified and added! <<|");
            System.out.println("|>> Our team give you 0.50$ <<|");
            userCards();
        }else if(newCard == 4){
            System.out.println("|>> UNKNOWN card is not verified but added! <<|");
            System.out.println("|>> Sorry it's unknown card our team can not give you bonus <<|");
            userCards();
        }else {
            System.out.println("|>> Sorry it's error! <<|");
            userCards();
        }
    }
    public static void userBalance(){
        System.out.println("|>> Cards balance <<|");
        System.out.println("|>> 1 <<|>> View <<|");
        System.out.println("|>> 2 <<|>> Add Money <<|");
        System.out.println("|>> 3 <<|>> Add to other card <<|");
        System.out.println("|>> 0 <<|>> Back <<|");
        int i = scanInt.nextInt();
        switch (i){
            case 1 -> {
                CardService.getCardBalance();
                userBalance();
            }
            case 2 -> {
                System.out.println("|>> Choose one: <<|");
                CardService.getCardById();
                int card = scanInt.nextInt();
                System.out.println("|>> Write money: <<|");
                int money = scanInt.nextInt();
                int res = CardService.addToCard(card, money);
                if (res == 1){
                    System.out.println("Money successfully added!");
                    userBalance();
                } else {
                    System.out.println("Unknown error! Try again");
                    userBalance();
                }
            }
            case 3 -> {
                System.out.println("|>> Choose your card: <<|");
                CardService.getCardById();
                int fcard = scanInt.nextInt();
                System.out.println("|>> Write other card number: <<|");
                String scard = scanStr.next();
                System.out.println("|>> Write money: <<|");
                int money = scanInt.nextInt();
                int res = CardService.addToOtherCard(fcard, scard, money);
                if(res == 1){
                    System.out.println("Successfully!");
                    userBalance();
                }else if(res == -1){
                    System.out.println("Other card is not found!");
                    userBalance();
                }else if(res == -2){
                    System.out.println("You have not a few money!");
                    userBalance();
                }else {
                    System.out.println("Unknown error!");
                    userBalance();
                }
            }
            case 0 -> {
                UserMenu();
            }
        }
    }
    public static void userProfile(){
        System.out.println("|>> Your profile <<|");
        UserService.getProfile();
        UserMenu();

    }
    public static void showHistory(){
        HistoryService.getHistory();
        UserMenu();
    }
    public static void userExit(){
        System.out.println("|>> Choose how to exit <<|");
        System.out.println("|>> 1 <<|>> Log out    <<|");
        System.out.println("|>> 2 <<|>> Exit fully <<|");
        System.out.println("|>> 3 <<|>> Delete     <<|");
        System.out.println("|>> 0 <<|>> Back       <<|");
        int i = scanInt.nextInt();
        switch (i) {
            case 1 -> {
                CoreMenu();
            }
            case 2 -> {
                System.out.println("|>> Thank you for using! <<|");
                return;
            }
            case 3 -> {
                UserService.deleteUser();
                System.out.println("|>> Your account was deleted! <<|");
                CoreMenu();
            }
            case 0 -> {
                UserMenu();
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Admin menu's
    public static void AdminMenu() {
        System.out.println("|>> Online Card Payment <<|");
        System.out.println("|>> 1 <<|>> View        <<|");
        System.out.println("|>> 2 <<|>> Add         <<|");
        System.out.println("|>> 3 <<|>> Ban         <<|");
        System.out.println("|>> 0 <<|>> Exit        <<|");
        int i = scanInt.nextInt();
        switch(i){
            case 1 -> adminView();
            case 2 -> adminAdd();
            case 3 -> adminBan();
            case 0 -> adminExit();
        }
    }
    public static void adminView(){
        System.out.println("|>> Online Card Users <<|");
        UserService.getAllUsers();
        AdminMenu();
    }
    public static void adminAdd(){
        System.out.println("|>> Online Card Users <<|");
        System.out.println("|>> 1 <<|>> Add User  <<|");
        System.out.println("|>> 2 <<|>> Add Admin <<|");
        System.out.println("|>> 0 <<|>> Back      <<|");
        int i = scanInt.nextInt();
        switch (i){
            case 1 -> {
                System.out.println("|>> Write phone number: <<|");
                String number = scanStr2.nextLine();
                System.out.println("|>> Write password: <<|");
                String password = scanStr.next();
                int result = UserService.addAdminByAdmin(number, password);
                if (result == 0){
                    System.out.println("The phone number is too small (min: 5):");
                    adminAdd();
                }else if(result == 2){
                    System.out.println("The password is so small (min: 3): ");
                    adminAdd();
                }else if(result == 3){
                    System.out.println("This phone is already used!");
                    adminAdd();
                }else {
                    System.out.println("Verified!");
                    adminAdd();
                }
            }
            case 2 -> {
                System.out.println("|>> Write user phone number: <<|");
                String number = scanStr2.nextLine();
                System.out.println("|>> Write user password: <<|");
                String password = scanStr.next();
                int result = UserService.signup(number, password);
                if (result == 0){
                    System.out.println("The phone number is too small (min: 5):");
                    adminAdd();
                }else if(result == 2){
                    System.out.println("The password is so small (min: 3): ");
                    adminAdd();
                }else if(result == 3){
                    System.out.println("This phone is already used!");
                    adminAdd();
                }else {
                    System.out.println("Verified!");
                    adminAdd();
                }
            }
            case 0 -> AdminMenu();
        }
    }
    public static void adminBan(){
        System.out.println("|>> Online Card Ban System <<|");
        System.out.println("|>> 1 <<|>> View           <<|");
        System.out.println("|>> 2 <<|>> Add            <<|");
        System.out.println("|>> 3 <<|>> Remove         <<|");
        System.out.println("|>> 0 <<|>> Back           <<|");
        int i = scanInt.nextInt();
        switch (i) {
            case 1 -> {
                System.out.println("|>> Banned users: <<|");
                UserService.viewBannedPeople();
                adminBan();
            }
            case 2 -> {
                System.out.println("|>> Choose user to ban: <<|");
                UserService.viewUsersToRemove();
                int banId = scanInt.nextInt();
                int res = UserService.sendBan(banId);
                if(res == 1){
                    System.out.println("|>> User successfully banned! <<|");
                    adminBan();
                }else {
                    System.out.println("|>> User not found! <<|");
                    adminBan();
                }
            }
            case 3 -> {
                System.out.println("|>> Choose user to remove ban: <<|");
                UserService.viewUsersToRemove();
                int banId = scanInt.nextInt();
                int res = UserService.removeBan(banId);
                if(res == 1){
                    System.out.println("|>> User successfully unbanned! <<|");
                    adminBan();
                }else {
                    System.out.println("|>> User not found! <<|");
                    adminBan();
                }
            }
            case 0 -> AdminMenu();
        }
    }
    public static void adminExit(){
        System.out.println("|>> Choose how to exit <<|");
        System.out.println("|>> 1 <<|>> Log out    <<|");
        System.out.println("|>> 2 <<|>> Exit fully <<|");
        System.out.println("|>> 0 <<|>> Back       <<|");
        int i = scanInt.nextInt();
        switch (i) {
            case 1 -> {
                CoreMenu();
            }
            case 2 -> {
                System.out.println("|>> Thank you for using! <<|");
                return;
            }
            case 0 -> {
                AdminMenu();
            }

        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
