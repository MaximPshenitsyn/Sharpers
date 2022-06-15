package sharpers;

import java.util.Random;

/**
 * Class of sharper
 * that extends basic class of player
 */
public class Sharper extends Player {
    // variable that indicates, whether this player
    // slept this game or not
    private boolean slept;
    // amount of time needed to sleep
    private int sleepTime;

    /**
     * Calculates player's balance
     * @return - balance of the player
     */
    @Override
    public int getBalance() {
        return cardsPoints + stolenPoints;
    }

    /**
     * Constructor
     * @param index - used to name the thread
     */
    public Sharper(String index) {
        super(index);
        slept = false;
        sleepTime = new Random().nextInt(100, 201);
    }

    /**
     * Basic function of Thread
     */
    @Override
    public void start() {
        super.start();
    }

    /**
     * Method to run the thread: takes card
     * in synchronized section or steals one
     * from random honest player then tries
     * to sleep until can act again or is interrupted
     */
    @Override
    public void run() {
        while (!isInterrupted()) {
            synchronized (lock) {
                if (slept && new Random().nextDouble() < 0.4) {
                    int victimIndex = new Random().nextInt(0, Game.honestPlayers.size());
                    stolenPoints += Game.honestPlayers.get(victimIndex).steal();
                    sleepTime = new Random().nextInt(180, 301);
                } else {
                    cardsPoints += getCard();
                    sleepTime = new Random().nextInt(100, 201);
                }
            }
            try {
                sleep(sleepTime);
                slept = true;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
