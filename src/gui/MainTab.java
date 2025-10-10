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
        JPanel alarmTab = new JPanel();
        alarmTab.setLayout(null);

        // Spinners för tid
        JSpinner daySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 7, 1));
        JSpinner hourSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        JSpinner minuteSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        JSpinner secondSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));

        JButton addAlarmButton = new JButton("Add Alarm");
        JButton removeAlarmButton = new JButton("Remove Alarm");
        JButton clearAllButton = new JButton("Remove All Alarms");

        // Lista över aktiva alarm
        DefaultListModel<String> alarmListModel = new DefaultListModel<>();
        JList<String> alarmList = new JList<>(alarmListModel);
        JScrollPane alarmScrollPane = new JScrollPane(alarmList);
        alarmScrollPane.setBounds(10, 170, 400, 200);

        // Placering
        daySpinner.setBounds(10, 30, 60, 30);
        hourSpinner.setBounds(80, 30, 60, 30);
        minuteSpinner.setBounds(150, 30, 60, 30);
        secondSpinner.setBounds(220, 30, 60, 30);

        addAlarmButton.setBounds(10, 80, 150, 30);
        removeAlarmButton.setBounds(170, 80, 150, 30);
        clearAllButton.setBounds(10, 120, 310, 30);

        alarmTab.add(daySpinner);
        alarmTab.add(hourSpinner);
        alarmTab.add(minuteSpinner);
        alarmTab.add(secondSpinner);
        alarmTab.add(addAlarmButton);
        alarmTab.add(removeAlarmButton);
        alarmTab.add(clearAllButton);
        alarmTab.add(alarmScrollPane);

        // Hjälpmetod 
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        java.util.function.Function<TimeType, String> formatAlarmText = time ->
                days[time.getDay()] + " " + String.format("%02d:%02d:%02d",
                        time.getHour(), time.getMinute(), time.getSecond());

        // Lyssnare 

        // Lägg till alarm
        addAlarmButton.addActionListener(e -> {
            int day = (int) daySpinner.getValue() - 1;
            int hour = (int) hourSpinner.getValue();
            int minute = (int) minuteSpinner.getValue();
            int second = (int) secondSpinner.getValue();

            TimeType alarmTime = new Time(day, hour, minute, second);
            controller.addAlarm(alarmTime);

            String alarmText = formatAlarmText.apply(alarmTime);
            if (!alarmListModel.contains(alarmText)) {
                alarmListModel.addElement(alarmText);
            }
        });

        // Ta bort specifikt alarm via popup
        removeAlarmButton.addActionListener(e -> {
            if (alarmListModel.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No alarms to remove.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            Object selected = JOptionPane.showInputDialog(
                    null,
                    "Select alarm to remove:",
                    "Remove Alarm",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    alarmListModel.toArray(),
                    alarmListModel.get(0)
            );

            if (selected != null) {
                String selectedAlarm = selected.toString();
                alarmListModel.removeElement(selectedAlarm);

                // Parsar tillbaka till TimeType för att ta bort korrekt alarm
                for (int i = 0; i < days.length; i++) {
                    if (selectedAlarm.startsWith(days[i])) {
                        String[] parts = selectedAlarm.split(" ")[1].split(":");
                        int hour = Integer.parseInt(parts[0]);
                        int minute = Integer.parseInt(parts[1]);
                        int second = Integer.parseInt(parts[2]);
                        TimeType timeToRemove = new Time(i, hour, minute, second);
                        controller.removeAlarm(timeToRemove);
                        break;
                    }
                }
            }
        });

        // Ta bort alla alarm
        clearAllButton.addActionListener(e -> {
            controller.removeAllAlarms();
            alarmListModel.clear();
        });

        return alarmTab;
    }




}
