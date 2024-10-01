public class Gameplay {

    public static void main(String[] args) {
        BlueAstronaut player1 = new BlueAstronaut("Bob", 20, 6, 30);
        BlueAstronaut player2 = new BlueAstronaut("Heath", 30, 3, 21);
        BlueAstronaut player3 = new BlueAstronaut("Albert", 44, 2, 0);
        BlueAstronaut player4 = new BlueAstronaut("Angel", 0, 1, 0);
        RedAstronaut player5 = new RedAstronaut("Liam", 19, "experienced");
        RedAstronaut player6 = new RedAstronaut("Suspicious Person", 100, "expert");

        player5.sabotage(player1);
        System.out.println("1. " + player1.toString());

        player5.freeze(player6);
        System.out.println("2. " + player6.toString());

        player5.freeze(player3);
        System.out.println("3a. " + player5.toString());
        System.out.println("3b. " + player3.toString());

        player3.emergencyMeeting();
        System.out.println("4. " + player6.toString());

        player6.emergencyMeeting();
        System.out.println("5a. " + player1.toString());
        System.out.println("5b. " + player2.toString());

        player1.emergencyMeeting();
        System.out.println("6. " + player6.toString());

        player2.completeTask();
        System.out.println("7. " + player2.toString());

        player2.completeTask();
        System.out.println("8. " + player2.toString());

        player2.completeTask();
        System.out.println("9. " + player2.toString());

        player5.freeze(player4);
        System.out.println("10a. " + player4.toString());
        System.out.println("10b. " + player5.toString());

        player5.sabotage(player1);
        player5.sabotage(player1);
        System.out.println("11. " + player1.toString());

        player5.freeze(player1);
        System.out.println("12. " + player1.toString());

        // player4.emergencyMeeting();
        // System.out.println("13. " + player5.toString());

        player5.sabotage(player2);
        System.out.println("14. " + player2.toString());

        player5.sabotage(player2);
        System.out.println("14. " + player2.toString());

        player5.sabotage(player2);
        System.out.println("14. " + player2.toString());

        player5.sabotage(player2);
        System.out.println("14. " + player2.toString());

        player5.sabotage(player2);
        System.out.println("14. " + player2.toString());

        player5.freeze(player2);

    }
}