package gui;

import javax.swing.*;

public class ClockApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());

        ClockController clockController = new ClockController(); //For testing only
        clockController.startClock(); //For testing only

    }

}
