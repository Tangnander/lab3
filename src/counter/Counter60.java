package counter;

public class Counter60 extends SettableCounter {

    public Counter60() {
        this(Direction.INCREASING, null);
    }

    public Counter60(CounterType next) {
        this(Direction.INCREASING, next);
    }

    public Counter60(Direction direction) {
        this(direction, null);
    }

    public Counter60(Direction direction, CounterType next) {
        super(60, direction, next);
    }

}