package sharpers;

import java.util.Random;

/**
 * Basic class for player, that
 * extends Thread
 */
public abstract class Player extends Thread {
    // amount of points on all cards
    protected int cardsPoints;
    // amount of points stolen from/by
    protected int stolenPoints;

    /**
     * Abstract function to calculate balance
     * @return - amount of points
     */
    public abstract int getBalance();

    /**
     * Static field, used to control work
     * of threads
     */
    protected static final Object lock = new Object();

    /**
     * Function simulates the process of
     * getting a new card
     * @return - random value (number of points on card)
     */
    protected int getCard() {
        return new Random().nextInt(1, 10 + 1);
    }

    /**
     * Constructor
     * @param index - used to name the thread
     */
    public Player(String index) {
        super(index);
        cardsPoints = 0;
        stolenPoints = 0;
    }
}
