package counter;

public class Counter7 extends SettableCounter {

    public Counter7() {
        this(Direction.INCREASING, null);
    }

    public Counter7(CounterType next) {
        this(Direction.INCREASING, next);
    }

    public Counter7(Direction direction) {
        this(direction, null);
    }

    public Counter7(Direction direction, CounterType next) {
        super(7, direction, next);
    }

}