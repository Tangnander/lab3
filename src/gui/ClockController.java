package gui;

import clock.WeekAlarmClock;
import time.Time;
import time.TimeType;
import alarm.Alarm;


import javax.swing.*;

public class ClockController {

    private boolean clockRunning = false;
    private WeekAlarmClock alarmClock = new WeekAlarmClock();
    private Thread clockThread;
    private JLabel timeLabel; // JLabel från GUI
    Time newTime = new Time(0, 0, 0, 0);

    public ClockController(JLabel timeLabel) {
        this.timeLabel = timeLabel;
        updateLabel(); // Visa initial tid
    }

    public void startClock() {
        if (clockRunning) return;
        clockRunning = true;

        clockThread = new Thread(() -> {
            while (clockRunning) {
                alarmClock.tickTack();
                updateLabel();

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
        alarmClock.setTime(newTime);
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

    private void updateLabel() {
        SwingUtilities.invokeLater(() -> timeLabel.setText(alarmClock.toString()));
    }

}
