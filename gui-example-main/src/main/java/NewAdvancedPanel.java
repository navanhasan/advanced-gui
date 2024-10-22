import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//PLEASE READ THROUGH THE COMMENTS IN THE
//SIMPLEWINDOW AND ADVANCEDWINDOW FILES
//TO FULLY UNDERSTAND
//WHAT COMPONENTS IN HERE ARE DOING

//NOTE: Only new components not used
//in the SimpleWindow and AdvancedWindow files
//will be explained in here

//JPanel is a class which means we have the
//ability to make ourselves a subclass of JPanel
//inheriting all its methods and fields
public class NewAdvancedPanel extends JPanel {
    //this class will have the purpose of
    //only setting up a panel that can
    //then be added to another class
    //which will implement a JFrame

    //we may want access to these components
    private JTextArea leftTextArea;
    private JTextArea rightTextArea;
    private JButton addShowButton;
    private JButton removeShowButton;

    private ArrayList<String> showOne;
    private ArrayList<String> showTwo;

    public NewAdvancedPanel(String title) {
        //because we are a subclass of
        //JPanel we can call our super
        //constructor and pass in a new
        //layout for the Window
        super(new GridLayout(1, 4));

        showOne = new ArrayList<>();
        showTwo = new ArrayList<>();

        JLabel panelTitle = new JLabel(title);
        panelTitle.setFont(new Font("Serif", Font.PLAIN, 17));

        //we can set the default row and column
        //amount of the JTextArea via its constructor
        leftTextArea = new JTextArea(7, 7);
        rightTextArea = new JTextArea(7, 7);

        //if we add some data to our JTextArea
        //there is a chance it could overflow either
        //horizontally or vertically
        //we want to be able to scroll vertically always
        //but we only want to be able to scroll horizontally
        //if the text requires it
        //we make our TextAreas scrollable by passing them
        //in as the first argument to the constructor
        //we then add these new scrollable areas to
        //ourself later
        JScrollPane leftScroll = new JScrollPane(leftTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane rightScroll = new JScrollPane(rightTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        addShowButton = new JButton("Add Show");
        removeShowButton = new JButton("Remove Show");

        ButtonListener listener = new ButtonListener();
        addShowButton.addActionListener(listener);
        removeShowButton.addActionListener(listener);

        //even though we extend the JPanel class
        //we can still make another JPanel via
        //composition
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.X_AXIS));

        innerPanel.add(addShowButton);
        innerPanel.add(removeShowButton);

        //we want to have a way to tell the difference
        //between our two JTextAreas
        //one way to do this is with a Border
        //we will apply a Border to only one
        //JTextArea so that it can be separated
        //visually from the other
        //it is a solid line border that is black
        //in color
        Border textAreaBorder = BorderFactory.createLineBorder(Color.BLACK);
        leftTextArea.setBorder(textAreaBorder);

        //since we are a child of JPanel we can
        //call public methods that exist in the
        //parent class
        //this means that we can call the
        //add method without an instance needed
        //and it will call our parent version
        //since we are not overwriting it
        //we are add our JTextArea and the
        //other JPanel we made to this
        //object
        //therefore, whenever a new instance
        //of NewAdvancedPanel is ever made
        //it will have an JTextArea and
        //then another JPanel with two
        //buttons in it
        add(panelTitle);
        add(leftScroll);
        add(rightScroll);
        add(innerPanel);

    }

    //we take in the textArea as a parameter so that we know which
    //panel to clear before we write back into it
    private String listPrint (ArrayList<String> list, JTextArea textArea) {
        StringBuilder fullStr = new StringBuilder();

        //clear out the textArea that we were passed
        //before we right back into it with
        //the info from the ArrayList
        textArea.setText("");
        for (String str: list) {
            //since we could be continually
            //building a String in this
            //loop it is better to use
            //StringBuilder to continually
            //make this call so we are not
            //copying the whole String each time
            //through the loop
            fullStr.append(str);
        }
        return fullStr.toString();
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //we are making a String array to hold options
            //in a dropdown menu that is what a JComboBox is
            //we are passing in the String array so that it
            //is full of the options we list
            String[] options = {"Left Panel", "Right Panel"};
            JComboBox<String> comboBox = new JComboBox<>(options);
            int option;

            if(addShowButton == e.getSource()) {
                JLabel titleLabel = new JLabel("Title:");
                JLabel channelLabel = new JLabel("Channel:");

                JTextField titleField = new JTextField();
                JTextField channelField = new JTextField();

                Object[] optionPane = {titleLabel, titleField, channelLabel, channelField, comboBox};
                option = JOptionPane.showConfirmDialog(null, optionPane,
                        "Add Show Form", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    String title = titleField.getText();
                    String channel = channelField.getText();
                    String fullInfo = "Title: " + title + "\nChannel: " + channel + "\n";

                    //we want to get whatever selection the user made
                    //from the dropdown menu this will return the option
                    //the user selected in a String format
                    String comboSelection = comboBox.getSelectedItem().toString();

                    //after we capture the selection the user made
                    //we can then test it to see what option
                    //the user selected so that we can add
                    //the data the user wants into the panel
                    //that the user wants
                    if (comboSelection.equals(options[0])) {
                        showOne.add(fullInfo);
                        leftTextArea.append(listPrint(showOne, leftTextArea));
                    } else if (comboSelection.equals(options[1])) {
                        showTwo.add(fullInfo);
                        rightTextArea.append(listPrint(showTwo, rightTextArea));
                    }
                }

            } else if (removeShowButton == e.getSource()) {
                //we only pass in the comboBox to this JOptionPane
                //because we only want the user to select
                //which panel they want to remove the last show
                //added from
                option = JOptionPane.showConfirmDialog(null, comboBox, "Remove Show Form",
                        JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    String comboSelection = comboBox.getSelectedItem().toString();
                    if (comboSelection.equals(options[0])) {
                        showOne.remove(showOne.get(showOne.size() - 1));
                        leftTextArea.append(listPrint(showOne, leftTextArea));
                    } else if (comboSelection.equals(options[1])) {
                        showTwo.remove(showTwo.get(showTwo.size() - 1));
                        rightTextArea.append(listPrint(showTwo, rightTextArea));
                    }
                }
            }
        }
    }

}
