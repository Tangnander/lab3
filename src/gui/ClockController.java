package gui;

import alarm.Alarm;
import alarm.AlarmType;
import clock.WeekAlarmClock;
import time.Time;
import time.TimeType;

import javax.swing.*;
import java.util.Collection;

public class ClockController {

    public static boolean alarmTriggered;

    Time resetNewTime = new Time(0, 0, 0, 0);
    private boolean clockRunning = false;
    private final WeekAlarmClock alarmClock = new WeekAlarmClock();
    private final JLabel timeLabel; // JLabel från GUI

    public ClockController(JLabel timeLabel) {
        this.timeLabel = timeLabel;
        updateLabel(); // Visa initial tid
    }

    public void startClock() {
        Thread clockThread;

        if (clockRunning) return;
        clockRunning = true;

        clockThread = new Thread(() -> {
            while (clockRunning) {
                alarmClock.tickTack();
                updateLabel();
                checkForTriggeredAlarm();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        clockThread.start();
    }

    public void stopClock() {
        clockRunning = false;
    }

    public void resetClock() {
        stopClock();
        alarmClock.setTime(resetNewTime);
        updateLabel();
        snoozeAlarm();
    }

    public void setTime(TimeType time) {
        alarmClock.setTime(time);
        updateLabel();
    }

    public void addAlarm(TimeType time) {
        alarmClock.addAlarm(new Alarm(time));
    }

    public void removeAlarm(TimeType time) {
        // Tar bort ett specifikt alarm baserat på tiden
        alarmClock.removeAlarm(new alarm.Alarm(time));
    }

    public void removeAllAlarms() {
        // Tar bort alla alarm
        alarmClock.removeAllAlarms();
    }

    public Collection<AlarmType> getAlarms() {
        return alarmClock.getAlarms();
    }

    private void updateLabel() {
        SwingUtilities.invokeLater(() -> {
            timeLabel.setText(alarmClock.toString());

            TimeType timeSplit = alarmClock.getTime();

            //Sets the analog clock to current time and updates it on MainTab.
            AnalogClock.days = timeSplit.getDay();
            AnalogClock.hours = timeSplit.getHour();
            AnalogClock.minutes = timeSplit.getMinute();
            AnalogClock.seconds = timeSplit.getSecond();
            MainTab.analogWeekDay.setText(timeSplit.toString().substring(0, 3));
            MainTab.analogClock.repaint();
        });
    }

    public void snoozeAlarm() {
        alarmTriggered = false;
        MainTab.alarmLabel.setVisible(false);
        MainTab.snoozeAlarm.setVisible(false);
    }

    public void checkForTriggeredAlarm() {
        if (alarmTriggered) {
            MainTab.alarmLabel.setVisible(true);
            MainTab.snoozeAlarm.setVisible(true);
        }
    }

}
