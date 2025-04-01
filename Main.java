import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

public class Main {
    // Variable to keep track of rounds
    public static int rounds = 0;
    public static String status;

    // Function to determine current time period. If divisable by 8, 20 minute break. If divisable by 2 5 minute break. Else 25 minute work.
    public static void findStatus () {
        if (rounds%8 == 0) {
            status = "long break";
        } else if (rounds%2 == 0) {
            status = "short break";
        } else {
            status = "work";
        }
    }

    // Function to start timer
    public static void startTimer(int minutes) {
        try (Scanner scanner = new Scanner (System.in)) {
            findStatus();

            Timer myTimer = new Timer();
            System.out.println("Start " + status + "!");

            TimerTask myTask = new TimerTask(){
                int counter = minutes;

                public void run(){
                    if(counter>0){
                        System.out.println(counter+" minutes left.");
                        counter--;
                    } else {
                        myTimer.cancel();
                        // Increases rounds variable and prompts user to move onto next time period
                        rounds++;
                        findStatus();
                        System.out.println("Time is up! Enter X for " + status);
                        String letter = scanner.next();
                        if (letter.equals("X")) {
                            // Checks for which timer to start
                            if (status.equals("long break")){
                                startTimer(20);
                            } else if (status.equals("short break")) {
                                startTimer(5);
                            } else {
                                startTimer(25);
                            }
                        } else if (letter.equals("Q")){
                            System.exit(counter);
                        }
                    }
                }

            };
            myTimer.scheduleAtFixedRate(myTask, 0 , 60000);
        }
    }


    public static void main(String[] args) {
        try (Scanner scanner = new Scanner (System.in)) {
            // Welcome message + starts inital timer of 25 minutes
            System.out.println("Welcome to the pomodoro timer! \nEnter X to start the 25 minute work period. Press Q to quit anytime.");
            String letter = scanner.next();
            rounds++;
            if (letter.equals("X")) {
                startTimer(25);
            } else if (letter.equals("Q")) {
                System.exit(rounds);
            }
        }


    }
}
