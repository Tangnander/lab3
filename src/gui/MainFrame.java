package gui;

import javax.swing.*;

public class MainFrame extends JFrame {

    JTabbedPane mainTab = new MainTab();

    public MainFrame() {
        setTitle("Clock App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(mainTab);
        setSize(1000, 1000);
        setVisible(true);
    }

}
