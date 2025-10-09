package gui;

import clock.WeekAlarmClock;
import time.TimeType;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public  class ClockController {

    private boolean clockRunning = false;
    private WeekAlarmClock alarmClock = new WeekAlarmClock();
    private Thread clockThread;
    private JLabel timeLabel; // JLabel från GUI

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
        alarmClock = new WeekAlarmClock(); // skapa en ny klocka
        updateLabel();
    }
    
    public void setTime(TimeType time) {
        alarmClock.setTime(time);
        updateLabel();
    }



    private void updateLabel() {
        SwingUtilities.invokeLater(() -> timeLabel.setText(alarmClock.toString()));
    }
}
