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
    private boolean clockRunning = false;
    private final WeekAlarmClock alarmClock = new WeekAlarmClock();
    private final JLabel timeLabel; // JLabel från GUI
    Time resetNewTime = new Time(0, 0, 0, 0);

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
        SwingUtilities.invokeLater(() -> timeLabel.setText(alarmClock.toString()));
    }

    public void snoozeAlarm() {
        alarmTriggered = false;
    }

    public void checkForTriggeredAlarm() {
        if (alarmTriggered) {
            MainTab.alarmLabel.setVisible(true);
            MainTab.snoozeAlarm.setVisible(true);
        }
    }

}
