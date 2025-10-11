package gui;

import javax.swing.*;
import java.awt.*;

public class AnalogClock extends JPanel {

    static public int days;
    static public int hours;
    static public int minutes;
    static public int seconds;

    //Credit to MadProgrammer on StackOverflow for this clever solution.
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g.create();

        //Sets the color of the clock.
        graphics2D.setColor(Color.WHITE); //Changing the opacity makes the arms stay after drawing the next
        graphics2D.fillRect(0, 0, 600, 600);
        graphics2D.setColor(Color.BLACK);
        graphics2D.translate(300, 300);

        //Draws the clock face.
        for (int i = 0; i < 12; i++) {
            graphics2D.setStroke(new BasicStroke(6));
            graphics2D.drawLine(0, -260, 0, -300);
            graphics2D.rotate(Math.PI / 6);
        }
        //Draws seconds arm.
        graphics2D.rotate(seconds * Math.PI / 30);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawLine(0, 0, 0, -290);

        //Draws minutes arm.
        graphics2D.rotate(2 * Math.PI - seconds * Math.PI / 30);
        graphics2D.rotate(minutes * Math.PI / 30);
        graphics2D.setStroke(new BasicStroke(6));
        graphics2D.drawLine(0, 0, 0, -250);

        //Draws hours arm.
        graphics2D.rotate(2 * Math.PI - minutes * Math.PI / 30);
        graphics2D.rotate(hours * Math.PI / 6);
        graphics2D.setStroke(new BasicStroke(9));
        graphics2D.drawLine(0, 0, 0, -200);

        graphics2D.dispose();
    }

}
