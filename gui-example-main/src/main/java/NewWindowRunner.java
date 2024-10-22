import javax.swing.*;
import java.awt.*;

public class NewWindowRunner {

    public static void main(String[] args) {
        createAnShowGUI();
    }

    private static void createAnShowGUI () {
        JFrame theWindow = new JFrame();
        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //we are making two objects of our NewAdvancedPanel
        //these panels can then be added to the container and
        //frame
        NewAdvancedPanel topPanel = new NewAdvancedPanel("Top Panel");
        NewAdvancedPanel bottomPanel = new NewAdvancedPanel("Bottom Panel");
        Container theHolder = theWindow.getContentPane();

        //we are adding both instances of the NewAdvancePanel
        //to the top and bottom of our window
        //remember each one of these panels
        //has a JTextArea and two buttons
        theHolder.add(topPanel, BorderLayout.NORTH);
        theHolder.add(bottomPanel, BorderLayout.SOUTH);

        theWindow.pack();

        //after we have packed the components, but before
        //we make the window visible we can adjust its size
        //normally you want to avoid setting the size of the
        //JFrame, however, since we are doing this after the
        //pack method so everything is fit into the window
        //nicely we can then set the size of that window to
        //fill up our entire screen or fill up a portion
        //of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theWindow.setSize(screenSize.width - 350, screenSize.height - 350);

        theWindow.setVisible(true);

    }
}
