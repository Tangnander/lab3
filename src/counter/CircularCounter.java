/**
 *
 *
 * En fungerande clocka vars "sekundräknare" som ska räkna till 59 och sedan börja om,
 * och då ska minuträknaren gå ett steg, och sedan till timmar
 *
 * @author Viktor Johansson & Anton Tängnander
 * @version 2025-09-02
 */
package counter;

public abstract class CircularCounter implements CounterType {

    public enum Direction {INCREASING, DECREASING}

    //privat?
    protected int currentCount;
    private boolean isPaused;
    private final int MAX_NR_OF_COUNTS;
    private Direction direction;
    private CounterType nextCounter;

    public CircularCounter(int maxNrOfCounts, Direction direction, CounterType nextCounter) {
        this.direction = direction;
        this.nextCounter = nextCounter;

        if (maxNrOfCounts < 2) this.MAX_NR_OF_COUNTS = 2;
        else this.MAX_NR_OF_COUNTS = maxNrOfCounts;

        //Om det är en nedåträknare, börja räkna från högsta värde istället för från noll
        if (this.direction == Direction.DECREASING && this.MAX_NR_OF_COUNTS > 0) currentCount = MAX_NR_OF_COUNTS - 1;
    }

    @Override
    public int getCount() {
        return currentCount;
    }

    //Starta om räknare från början
    @Override
    public void reset() {
        currentCount = 0;
    }

    @Override
    public void pause() {
        isPaused = true;
    }

    @Override
    public void resume() {
        isPaused = false;
    }

    @Override
    public void count() {
        if (!isPaused && this.MAX_NR_OF_COUNTS > 0) {
            if (direction == Direction.INCREASING) {
                currentCount++;
                if (currentCount >= MAX_NR_OF_COUNTS) {
                    currentCount = 0;
                    //om kopplad till en annan räkanre (nextCounter)
                    if (nextCounter != null) nextCounter.count();
                }
            } else if (direction == Direction.DECREASING) {
                currentCount--;
                if (currentCount < 0) {
                    currentCount = MAX_NR_OF_COUNTS - 1;
                    if (nextCounter != null) nextCounter.count();
                }
            }
        }
    }

    @Override
    public String toString() {
        return String.valueOf(currentCount);
    }

}
