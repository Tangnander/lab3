package clock;

import alarm.Alarm;
import alarm.AlarmType;
import org.junit.jupiter.api.Test;
import time.Time;
import time.TimeType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeekAlarmClockTest {

    AlarmClockType testClock = new WeekAlarmClock();

    @Test
    void testTickTack() {
        TimeType testTime = new Time(0, 0, 0, 1);
        testClock.tickTack();
        assertEquals(testClock.toString(), testTime.toString());
    }

    @Test
    void testTickTackDay() {
        TimeType testTime = new Time(1, 0, 0, 0);

        for (int i = 0; i < 86400; i++) {
            testClock.tickTack();
        }
        assertEquals(testClock.toString(), testTime.toString());
    }

    @Test
    void testAlarm() {
        TimeType testTime = new Time(0, 0, 0, 1);
        TimeType testTimeStringDay = new Time("Mon 00:00:02");
        TimeType testTimeString = new Time("00:00:03");
        AlarmType testAlarmTime = new Alarm(testTime);
        AlarmType testAlarmTimeString = new Alarm(testTimeString);
        AlarmType testAlarmTimeStringDay = new Alarm(testTimeStringDay);

        testClock.addAlarm(testAlarmTime);
        testClock.addAlarm(testAlarmTimeString);
        testClock.addAlarm(testAlarmTimeStringDay);
        assertEquals(3, testClock.getAlarms().size());

        testClock.tickTack();
        testClock.tickTack();
        testClock.tickTack();
    }

    @Test
    void testSetClock() {
        TimeType testTime = new Time(1, 1, 1, 1);
        TimeType testTimePostCount = new Time(1, 1, 1, 2);

        testClock.setTime(testTime);
        assertEquals(testClock.toString(), testTime.toString());
        testClock.tickTack();
        assertEquals(testClock.toString(), testTimePostCount.toString());
    }

    @Test
    void testAlarmRemove() {
        TimeType testTime = new Time(0, 0, 0, 1);
        TimeType testTimeStringDay = new Time("Mon 00:00:02");
        TimeType testTimeString = new Time("00:00:03");
        AlarmType testAlarmTime = new Alarm(testTime);
        AlarmType testAlarmTimeString = new Alarm(testTimeString);
        AlarmType testAlarmTimeStringDay = new Alarm(testTimeStringDay);

        testClock.addAlarm(testAlarmTime);
        testClock.addAlarm(testAlarmTimeString);
        testClock.addAlarm(testAlarmTimeStringDay);

        assertEquals(3, testClock.getAlarms().size());

        testClock.removeAlarm(testAlarmTime);
        testClock.tickTack();

        testClock.removeAllAlarms();
        testClock.tickTack();
        testClock.tickTack();
        assertEquals(0, testClock.getAlarms().size());
    }

    @Test
    void testGetTime() {
        TimeType resetTime = new Time(0, 0, 0, 0);

        testClock.tickTack();
        AlarmType testAlarm = new Alarm(testClock.getTime());
        testClock.addAlarm(testAlarm);

        testClock.setTime(resetTime);
        assertEquals(testClock.toString(), resetTime.toString());

        testClock.tickTack();
    }

}