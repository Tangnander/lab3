package gui;

import clock.WeekAlarmClock;

public class ClockController {

    private boolean clockRunning = true;
    WeekAlarmClock alarmClock = new WeekAlarmClock();

    public ClockController() {

    }

    public void startClock() {
        //This starts the clock and updates the label in MainTab
        while (clockRunning = false) {
            alarmClock.tickTack();
            MainTab.currentTime.setText(alarmClock.toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setClockRunning() {
        clockRunning = true;
    }

    public boolean getClockRunning() {
        return clockRunning;
    }

}
