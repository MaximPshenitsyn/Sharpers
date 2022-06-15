package sharpers;

import java.util.Random;

/**
 * Class of honest player
 * that extends basic class of player
 */
public class HonestPlayer extends Player {
    /**
     * Calculates player's balance
     * @return - balance of the player
     */
    @Override
    public int getBalance() {
        return cardsPoints - stolenPoints;
    }

    /**
     * Constructor
     * @param index - used to name the thread
     */
    public HonestPlayer(String index) {
        super(index);
    }

    /**
     * Function that indicates, how much a sharper
     * can steal from this player
     * @return - amount of robbery
     */
    public int steal() {
        int amount = new Random().nextInt(0, 9);
        int maxStolenAmount = Math.min(getBalance(), amount);
        stolenPoints += maxStolenAmount;
        return maxStolenAmount;
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
     * in synchronized section then tries
     * to sleep until can act again or is interrupted
     */
    @Override
    public void run() {
        while (!isInterrupted()) {
            synchronized (lock) {
                cardsPoints += getCard();
            }
            try {
                sleep(new Random().nextInt(100, 201));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
