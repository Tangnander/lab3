package clock;

import alarm.AlarmType;
import time.TimeType;

import java.util.Collection;

public interface AlarmClockType {

    public void tickTack();

    public void setTime(TimeType time);

    public void addAlarm(AlarmType larm);

    public void removeAlarm(AlarmType alarm);

    public void removeAllAlarms();

    public Collection<AlarmType> getAlarms();

    public TimeType getTime();

    public String toString();

}
