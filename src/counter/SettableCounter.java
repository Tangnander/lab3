package counter;

public abstract class SettableCounter extends CircularCounter implements SettableCounterType {

    public SettableCounter(int maxNrOfCounts, Direction direction, CounterType nextCounter) {
        super(maxNrOfCounts, direction, nextCounter);
    }

    @Override
    public void setCount(int value) {
        super.currentCount = value;
    }

}
