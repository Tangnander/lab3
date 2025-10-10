package alarm;

import gui.ClockController;
import time.TimeType;

public class Alarm implements AlarmType {

    private boolean active;
    private TimeType time;

    public Alarm(TimeType time) {
        this.time = time;
        active = true;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public TimeType getTime() {
        return time;
    }

    @Override
    public void doAlarm() {
        if (active) {
            ClockController.alarmTriggered = true;
        }
    }

    public String toString() {
        return time.toString(); // delegera till times toString()-metod
    }

}
