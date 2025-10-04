package counter;

public class Counter24 extends SettableCounter {

    public Counter24() {
        this(Direction.INCREASING, null);
    }

    public Counter24(CounterType next) {
        this(Direction.INCREASING, next);
    }

    public Counter24(Direction direction) {
        this(direction, null);
    }

    public Counter24(Direction direction, CounterType next) {
        super(24, direction, next);
    }

}