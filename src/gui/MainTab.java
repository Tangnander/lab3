package gui;

import javax.swing.*;
import java.awt.*;

public class MainTab extends JTabbedPane {

    //TODO: Fix this whole mess. Clock is working, but none of it is pretty.
    static JLabel currentTime = new JLabel("00:00:00", SwingConstants.CENTER);

    public MainTab() {
        this.addTab("Clock",createClockTab());
        this.addTab("Alarm",createAlarmTab());
    }

    public JPanel createClockTab() {
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
        currentTime.setSize(1000, 500);
        clockTab.add(currentTime);
        return clockTab;
    }

    public JPanel createAlarmTab() {
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
