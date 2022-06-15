package sharpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * Main class of the project
 * initializes lists of players in the game,
 * simulates the actions of croupier
 */
public class Game {
    // list of honest players
    public static List<HonestPlayer> honestPlayers = new ArrayList<>();
    // list of sharpers
    public static List<Sharper> sharpers = new ArrayList<>();

    /**
     * Main function of the project
     * @param args - if args are empty sets amount
     *             of players to default (1 honest and
     *             2 sharpers); if args contain 2 params
     *             reads the amount of different players
     */
    public static void main(String[] args) {
        // Reading amount of honest players
        // and sharpers
        Scanner input = new Scanner(System.in);
        System.out.print("Amount of honest players: ");
        int honestAmount = input.nextInt();
        System.out.print("Amount of sharpers: ");
        int sharpersAmount = input.nextInt();

        // Checking the entered data
        if (honestAmount < 1 || sharpersAmount < 0) {
            System.out.println("No less than 1 honest player and 0 sharpers!");
            return;
        }

        // reading new values for numbers of
        // different players if args size equals 2
        if (args.length == 2) {
            honestAmount = Integer.parseInt(args[0]);
            sharpersAmount = Integer.parseInt(args[1]);

        }

        // initialize all players and write them
        // to lists
        for (int i = 0; i < honestAmount; ++i) {
            honestPlayers.add(new HonestPlayer(String.valueOf(i + 1)));
        }
        for (int i = 0; i < sharpersAmount; ++i) {
            sharpers.add(new Sharper(String.valueOf(i + 1)));
        }

        // croupier starting the game by
        // starting all threads of players
        System.out.println("\nMatch started...");
        try {
            for (Player p : honestPlayers) {
                p.start();
            }
            for (Player p : sharpers) {
                p.start();
            }

            // croupier sleeps 10 seconds
            sleep(10 * 1000);

            // interrupting all players
            for (Player p : honestPlayers) {
                p.interrupt();
            }
            for (Player p : sharpers) {
                p.interrupt();
            }

            // printing match results
            System.out.println("Match finished, total scores are...\n");
            for (Player p : honestPlayers) {
                System.out.println("Honest player " + p.getName() + ": " + p.getBalance());
            }
            for (Player p : sharpers) {
                System.out.println("Sharper " + p.getName() + ": " + p.getBalance());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
