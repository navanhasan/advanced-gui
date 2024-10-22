import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//PLEASE READ THROUGH THE COMMENTS IN THE
//SIMPLEWINDOW FILE TO FULLY UNDERSTAND
//WHAT COMPONENTS IN HERE ARE DOING

//NOTE: Only new components not used
//in the SimpleWindow file will be explained
//in here
public class AdvancedWindow {

    private JFrame foundation;
    private JPanel buttonsPanel;
    private JButton greetingButton;
    private JButton formEntryButton;
    private JButton formEntry2Button;
    private JPanel displayPanel;
    private JTextArea dataArea;
    private Container theHolder;
    private ButtonListener listenForEvent;

    public AdvancedWindow() {

        foundation = new JFrame("My Advanced GUI App");
        foundation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,2));

        greetingButton = new JButton("Greeting");
        formEntryButton = new JButton("Form Entry");
        formEntry2Button = new JButton("Form 2 Entry");

        greetingButton.setFont(new Font("Arial", Font.BOLD, 17));
        formEntryButton.setFont(new Font("Serif", Font.PLAIN, 17));
        formEntry2Button.setFont(new Font("Serif", Font.PLAIN, 17));

        buttonsPanel.add(greetingButton);
        buttonsPanel.add(formEntryButton);
        buttonsPanel.add(formEntry2Button);

        listenForEvent = new ButtonListener();
        greetingButton.addActionListener(listenForEvent);
        formEntryButton.addActionListener(listenForEvent);
        formEntry2Button.addActionListener(listenForEvent);

        displayPanel = new JPanel(new FlowLayout());

        //a JTextArea is a multiline area that can display
        //text
        //it is also used to edit text this is create
        //as we type in more text the JTextArea can grow
        //to have more rows when we hit enter
        dataArea = new JTextArea();

        //we will only set the columns of the
        //textArea an have the rows default to one
        dataArea.setColumns(35);

        //we want any text that is longer than
        //our setColumn size to wrap for us automatically
        //without setting the line to wrap than the
        //text area will grow horizontally each append we do
        //this can cause some words to be cut in half
        dataArea.setLineWrap(true);

        displayPanel.add(dataArea);

        theHolder = foundation.getContentPane();
        theHolder.add(buttonsPanel, BorderLayout.NORTH);
        theHolder.add(displayPanel, BorderLayout.SOUTH);

        //the pack() method takes the sizes
        //of the components and layout structure
        //it then determines the size the frame needs
        //to be and fits everything into that size
        foundation.pack();

        foundation.setVisible(true);
    }//end of constructor

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            //the getSource method returns to us the
            //object that the action was performed on
            //we can typecast this Object to be any
            //component we need in this case
            //we will typecast it to be a JButton
            //so the getSource will allow us to identify
            //which button object was pressed
            JButton theButton = (JButton) event.getSource();

            //we can test the object we just got against
            //objects on our application
            //we will use the == for this equality test
            //this will tell us if they are the same object
            //stored in memory
            if (theButton == greetingButton) {
                //we use the append method to write into
                //the JTextArea, but also keep anything
                //that is currently in there as well
                dataArea.append("Hello I am an TextArea");
            } else if (theButton == formEntryButton) {

                JLabel nameLabel = new JLabel("Name:");
                JLabel ageLabel = new JLabel("Age:");
                JLabel quoteLabel = new JLabel("Quote:");

                //a JTextField is similar to a JTextArea
                //but it is not multiline the user can
                //still type in it and we can then
                //get the entered information
                //from it
                JTextField nameField = new JTextField();
                JTextField ageField = new JTextField();
                JTextField quoteField = new JTextField();

                //we are building an object array to hold
                //all the information
                Object[] optionPane = {nameLabel, nameField, ageLabel, ageField, quoteLabel, quoteField};

                //a JOptionPane brings up a new window that
                //we are using the showMessageDialog to show
                //all the fields for the user to enter information
                //in we are also showing two buttons
                //Okay and Cancel for the user to
                //click on and then we will make a decision
                //based on that
                //we are storing the user's click option in
                //an int because the Okay button (a 0) and Cancel button (a 2)
                //each have an int associated with them
                int option = JOptionPane.showConfirmDialog(null, optionPane,
                        "Form", JOptionPane.OK_CANCEL_OPTION);

                //we will test to see which option the user selected
                //we do not need to know the int associated with
                //each button option as we can test them against
                //the enum constant this is abstraction
                if (option == JOptionPane.OK_OPTION) {
                    //we want to store the user info so we can
                    //append it to the text area
                    String name = nameField.getText();
                    String age = ageField.getText();
                    String quote = quoteField.getText();

                    //when we are appending it is important to
                    //remember that it is putting it
                    //on the end of the text area so we are using
                    //new line escapes to give ourselves some room in
                    //the JTextArea and since that area grows as we need
                    //there is no worries about having extra space
                    String output = "\n" + quote + " is such an interesting quote " + name + " " +
                            "especially for somebody who is only " + age + "\n";

                    dataArea.append(output);

                } else if (option == JOptionPane.CANCEL_OPTION) {
                    dataArea.append("\nThe user canceled out of the form entry no data collected" +
                            ".\n");
                }//end of if-else-if seeing user okay or cancel selection

            } else if (theButton == formEntry2Button) {
                //instead of building a "form" for the
                //user to enter all information in at once
                //we could present the user with a series of
                //input prompts one at a time using
                //the showInputDialog method of the JOptionPane
                //we do not need to provide a JTextField for the user
                //as one is given to them as part of the input dialog
                //we can store the answers right into a String without
                //testing which button the user clicks as we will trust
                //the user to hit okay, if the user does hit cancel
                //we still get the other prompts but null is stored in
                //the variable
                String name = JOptionPane.showInputDialog("What is your name?");
                String age = JOptionPane.showInputDialog("What is your age?");
                String quote = JOptionPane.showInputDialog("What is a cool quote?");

                String output = "\nThis was all captured from a series of input dialogs\n"
                        + quote + " is" + " such an interesting quote " + name + " " +
                        "especially for somebody who is only " + age + "\n";

                dataArea.append(output);

            }//end of if-else-if check to see which button the user pressed

        }//end of actionPerformed method
    }//end of buttonlistener class

    public static void main(String[] args) {
        new AdvancedWindow();
    }
}
