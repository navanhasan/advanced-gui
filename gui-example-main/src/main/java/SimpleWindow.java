//This file will show off a Simple GUI Window
//it will have a Label and Button
//the button will change the Label's text when clicked

//in order to use GUI components we need to do special
//library imports from java
//awt came first
//swing is built on top of awt
//javafx is newer but not covered in this course

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleWindow {

    private int upgradeAmount = 0;

    //here is a list of all the components
    //we will be using in our application
    //further details about what each one
    //of these is doing is found below in
    //the constructor when the component
    //gets initialized
    private JFrame foundation;
    private JPanel buttonsPanel;
    private JButton upgradeButton;
    private JButton goBackButton;
    private Container theHolder;
    private JPanel labelPanel;
    private JLabel mySpecialLabel;
    private ButtonListener listenForEvent;

    //we are creating our window and application in
    //a constructor so that it can be called
    //from a main method and it will run according
    //to the setup we do here
    public SimpleWindow() {

        //a JFrame is the foundation for our GUI
        //similar to the foundation for any building
        //it is a container for all other components
        //that will exist inside the JFrame
        //we are using a constructor that takes a String thus
        //allowing us to name the JFrame
        foundation = new JFrame("My Simple GUI App");

        //some of the things you can do when a JFrame is
        //create are the following:
        //set its size, default close operation, and visibility

        //setting the size of the JFrame is done with an
        //int width and int height provided
        //it is not the best strategy to set the size of the frame
        //instead you should set the size of the components
        //in the JFrame and then use the pack() method
        //this will make the JFrame fit around all the components
        //this strategy can be seen in the "advanced" file
        foundation.setSize(650, 350);

        //EXIT_ON_CLOSE means that the user can click the 'X'
        //or 'red' circle to quit the application, this could
        //be turned off and the user may instead have to click
        //a button to quit the application
        foundation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //at this point if there was no other code in here
        //you would get a window that has "My Simple GUI App"
        //across the top and is size 650 by 350 with nothing in it
        //but you could click the 'X' or 'red' circle
        //and it would close down
        //we would need the visibility line at the bottom
        //of the main

        //a JPanel is another container, but it groups
        //components together for organization it
        //still needs to go inside the foundation
        //we could think of a JPanel as a standalone
        //room in a house, it holds all components for that
        //room, but needs a house to hold it together
        //we could have multiple JPanels inside a JFrame
        buttonsPanel = new JPanel();

        //JPanels can be setup in different layout structures
        //for our buttons we will use a GridLayout
        //this makes every component inside this panel equal size
        //and puts them into the specified number of rows and columns
        buttonsPanel.setLayout(new GridLayout(1,2));

        //buttons are components that allow us to capture user
        //events (such as clicking) and then react accordingly
        //this is known as event-driven programming
        //we are going to use the constructor that allows us to
        //put text in the button by passing in a String
        upgradeButton = new JButton("Upgrade");
        goBackButton = new JButton("Go Back");

        //we can specify the text style on a button
        //we need to give it the font type or name we want
        //if the user does not have the specific font name
        //a replacement font may be used and not look as you
        //intend
        //we then given it the style or weight (bold, italics, etc.)
        //we then say the size we want the font
        upgradeButton.setFont(new Font("Arial", Font.BOLD, 57));
        goBackButton.setFont(new Font("Serif", Font.PLAIN, 35));

        //buttons have the ability to change
        //their color (background) and text color (foreground)
        //in IntelliJ when passing in the RGB values of the
        //color you wish to use it should show you
        //the color on the side by the line number
        upgradeButton.setBackground(new Color(0, 255, 0));
        goBackButton.setBackground(Color.BLUE);
        goBackButton.setForeground(Color.WHITE);

        //the above may be enough on Windows to change
        //the color of the buttons but it is absolutely
        //not enough on Mac
        //when changing the color away from default
        //we should make sure our button is opaque
        //this is because they are not opaque by default
        //so there is nothing to paint
        //this is especially true with Macs
        //we also may have to set the border paint
        //a combination of these method calls should
        //get the button to change color on Mac and Windows
        //there are other strategies that we could use as well
        //but we will not examine them here since they are not
        //simple
        upgradeButton.setOpaque(true);
        upgradeButton.setBorderPainted(false);
        goBackButton.setOpaque(true);
        goBackButton.setBorderPainted(false);

        //our buttons are made but they are "nowhere"
        //we need to add them to the JPanel so that
        //they can get organized for us
        //we are using the single "Component" parameter
        //version of add where all we have to pass in
        //is the component we want to add to the JPanel
        buttonsPanel.add(upgradeButton);
        buttonsPanel.add(goBackButton);

        //at this point we still only have our blank
        //"My Simple GUI App" window even though we
        //made a JPanel to organize our components
        //we created two button and added them to this
        //panel, but everything is still nowhere to be
        //seen on our actual application
        //a Container is the different layers for our application
        //content is added to the contentPane of the Container
        theHolder = foundation.getContentPane();

        //we need to add our JPanel to the content pane
        //remember the buttons are part of the JPanel
        //so they get to come along for the ride when we
        //add the JPanel to the Container
        theHolder.add(buttonsPanel);

        //we want another JPanel that will hold
        //a label that is change when we click on our buttons
        //we are using a constructor here that allows us to
        //pass in the type of layout we want when the object
        //is created
        //FlowLayout is the default for every JPanel
        //everything will be placed in a single row
        //a new row will be started if the window is
        //not wide enough
        labelPanel = new JPanel(new FlowLayout());

        //a JLabel is meant to display text or an icon
        //no interaction can be done with a label
        //it is for displaying only
        //we are using the constructor that allows
        //us to display the text of the label
        mySpecialLabel = new JLabel("I'm an ordinary label wish I could be upgraded :(");

        //let us center the label since it is the only
        //component in this panel
        //we are able to do this with JLabel.CENTER which is
        //technically an int value of 0
        //this is known as an enum we look at those more in
        //the data structures class
        mySpecialLabel.setHorizontalAlignment(JLabel.CENTER);

        //we need to make sure we add the label to the
        //panel
        labelPanel.add(mySpecialLabel);

        //we need to remember to add this label to the container
        //we want it to be at the bottom of the container
        //we are using the add method that allows us to
        //add a component and give it a position in the
        //container
        //without this the label would be the only thing in the
        //container so by saying make a BorderLayout which is the
        //default for ContentPanes we can choose from five locations
        //to place components, we are placing this panel in the
        //SOUTH location so that the button appear above it
        theHolder.add(labelPanel, BorderLayout.SOUTH);

        //we want our buttons to be able to do things
        //when they are clicked on they need to be set up
        //with listeners...specifically ActionListeners
        //ActionListener is an interface so we cannot
        //just instantiate it
        //we need to implement it through a class and
        //then instantiate that class
        //this is what out ButtonListener class below is
        //doing
        listenForEvent = new ButtonListener();

        //we place action listeners on each button
        upgradeButton.addActionListener(listenForEvent);
        goBackButton.addActionListener(listenForEvent);

        //without setting the visibility to true the window will
        //not load when run
        //however, setting the visibility before we have
        //built the entire application means that
        //some things could be missing so we will
        //wait until the end of the main to load up the
        //window
        //but we do not want to forget this step
        foundation.setVisible(true);
    }//end of constructor

    //when we want to have button do things in our application
    //we need to implement the ActionListener interface
    //we do not want to do this on the main class and instead
    //do it on a brand new class
    //the ActionListener interface only has one method to implement
    //this is a private class so it is allow to be inside another
    //class as it will act as a helper
    private class ButtonListener implements ActionListener {

        //when an action is performed on a button that is
        //set up to listen for events this method is
        //automatically called
        public void actionPerformed(ActionEvent event) {

            //one way to see what button is pressed is
            //to grab the text off the button
            //through the getActionCommand method
            //this is text so it needs to be stored
            //in a String
            String theButtonName = event.getActionCommand();

            //with the text off the button that has been pressed
            //we can do some checks to see which button was pressed
            //and make appropriate changes
            if ("Upgrade".equals(theButtonName)) {

                if (upgradeAmount == 0) {
                    //we can change a JLabel text through
                    //the setText method
                    //this is why we made all our components instance
                    //variables so that they could be accessed
                    //in other methods/classes if needed
                    mySpecialLabel.setText("I'm still ordinary we should upgrade again");
                    mySpecialLabel.setFont(new Font("Comic Sans", Font.BOLD, 21));
                    upgradeAmount++;
                } else {
                    mySpecialLabel.setText("I'm An OrDiNaRy LaBeL wIsH i CoUlD bE uPgRaDeD");
                }
            } else if ("Go Back".equals(theButtonName)) {
                mySpecialLabel.setText("Upgrade finished, restore points deleted");
            }
        }//end of actionPerformed method
    }//end of buttonlistener class

    public static void main(String[] args) {
        //we can then call the constructor of the
        //class from within the main here to run
        //our application
        new SimpleWindow();
    }//end of main

}//end of simplewindow class


