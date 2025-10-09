package gui;

import time.Time;
import time.TimeType;

import javax.swing.*;
import java.awt.*;

public class MainTab extends JTabbedPane {

    JLabel currentTime = new JLabel("00:00:00", SwingConstants.CENTER);
    ClockController controller = new ClockController(currentTime);

    public MainTab() {
        this.addTab("Clock", createClockTab());
        this.addTab("Alarm", createAlarmTab());
    }

    private JPanel createClockTab() {
        JPanel clockTab = new JPanel();
        clockTab.setLayout(null);

        JButton startClock = new JButton("Start Clock");
        JButton stopClock = new JButton("Stop Clock");
        JButton resetClock = new JButton("Reset Clock");

        startClock.setBounds(10, 10, 320, 50);
        stopClock.setBounds(340, 10, 320, 50);
        resetClock.setBounds(670, 10, 320, 50);

        clockTab.add(startClock);
        clockTab.add(stopClock);
        clockTab.add(resetClock);

        currentTime.setFont(new Font("FreeMono", Font.PLAIN, 100));
        currentTime.setBounds(10, 70, 980, 200);
        clockTab.add(currentTime);

        // Anonyma lyssnare kopplade till controllern
        startClock.addActionListener(e -> controller.startClock());
        stopClock.addActionListener(e -> controller.stopClock());
        resetClock.addActionListener(e -> controller.resetClock());

        JSpinner daySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 7, 1));      // dag: 0-6
        JSpinner hourSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));    // timme: 0-23
        JSpinner minuteSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));  // minut: 0-59
        JSpinner secondSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));  // sekund: 0-59

        JButton setTimeButton = new JButton("Set Time");

        // Placera spinners och knapp i panelen
        daySpinner.setBounds(10, 300, 60, 30);
        hourSpinner.setBounds(80, 300, 60, 30);
        minuteSpinner.setBounds(150, 300, 60, 30);
        secondSpinner.setBounds(220, 300, 60, 30);
        setTimeButton.setBounds(300, 300, 120, 30);

        clockTab.add(daySpinner);
        clockTab.add(hourSpinner);
        clockTab.add(minuteSpinner);
        clockTab.add(secondSpinner);
        clockTab.add(setTimeButton);

        setTimeButton.addActionListener(e -> {
            int day = ((int) daySpinner.getValue()) - 1;
            int hour = (int) hourSpinner.getValue();
            int minute = (int) minuteSpinner.getValue();
            int second = (int) secondSpinner.getValue();

            controller.stopClock(); // stoppa tickTack-tråden först

            // Skapa Time-objekt och använd setTime-metoden
            TimeType newTime = new Time(day, hour, minute, second);
            controller.setTime(newTime);
        });
        return clockTab;
    }

    private JPanel createAlarmTab() {
        JButton startClock = new JButton("Start Clock");
        JButton stopClock = new JButton("Stop Clock");
        JButton resetClock = new JButton("Reset Clock");

        JPanel alarmTab = new JPanel();

        alarmTab.add(startClock);
        alarmTab.add(stopClock);
        alarmTab.add(resetClock);

        return alarmTab;
    }

}
