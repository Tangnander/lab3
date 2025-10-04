package clock;

import alarm.AlarmManager;
import alarm.AlarmType;
import counter.Counter24;
import counter.Counter60;
import counter.Counter7;
import counter.SettableCounterType;
import time.Time;
import time.TimeType;

import java.util.Collection;

public class WeekAlarmClock implements AlarmClockType {

    SettableCounterType dayTimer = new Counter7();
    SettableCounterType hourTimer = new Counter24(dayTimer);
    SettableCounterType minuteTimer = new Counter60(hourTimer);
    SettableCounterType secondTimer = new Counter60(minuteTimer);

    AlarmManager alarmManager = new AlarmManager();

    public WeekAlarmClock() {
    }

    @Override
    public void tickTack() {
        secondTimer.count();
        alarmManager.checkForAlarm(getTime());
    }

    @Override
    public void setTime(TimeType time) {
        dayTimer.setCount(time.getDay());
        hourTimer.setCount(time.getHour());
        minuteTimer.setCount(time.getMinute());
        secondTimer.setCount(time.getSecond());
    }

    @Override
    public void addAlarm(AlarmType larm) {
        alarmManager.addAlarm(larm);
    }

    @Override
    public void removeAlarm(AlarmType alarm) {
        alarmManager.removeAlarm(alarm);
    }

    @Override
    public void removeAllAlarms() {
        alarmManager.removeAllAlarms();
    }

    @Override
    public Collection<AlarmType> getAlarms() {
        return alarmManager.getAlarms();
    }

    @Override
    public TimeType getTime() {
        return new Time(dayTimer.getCount(), hourTimer.getCount(), minuteTimer.getCount(), secondTimer.getCount());
    }

    @Override
    public String toString() {
        return getTime().toString();
    }

}