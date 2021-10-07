package librarysystem;

import javax.swing.*;
import java.awt.*;

public class AddBookWindow extends JFrame implements LibWindow {

    public static final AddBookWindow INSTANCE = new AddBookWindow();
    private boolean isInitialized = false;

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel outerMiddle;


    private JTextField authFirstNameField;
    private JTextField authLastNameField;
    private JTextField titleField;

    @Override
    public void init() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        defineTopPanel();
        defineOuterMiddle();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(outerMiddle, BorderLayout.CENTER);
        getContentPane().add(mainPanel);
        isInitialized(true);
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    public void defineTopPanel() {
        topPanel = new JPanel();
        JLabel AddBookLabel = new JLabel("Add Book Title");
        Util.adjustLabelFont(AddBookLabel, Util.DARK_BLUE, true);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(AddBookLabel);
    }

    public void defineOuterMiddle() {
        outerMiddle = new JPanel();
        outerMiddle.setLayout(new BorderLayout());

        //set up left and right panels
        JPanel middlePanel = new JPanel();
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
        middlePanel.setLayout(fl);
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JLabel authFirstNameLabel = new JLabel("Author First Name");
        JLabel authLastNameLabel = new JLabel("Author Last Name");
        JLabel titleLabel = new JLabel("Book Title");

        authFirstNameField = new JTextField(10);
        authLastNameField = new JTextField(10);
        titleField = new JTextField(10);

        leftPanel.add(authFirstNameLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
        leftPanel.add(authLastNameLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
        leftPanel.add(titleLabel);


        rightPanel.add(authFirstNameField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
        rightPanel.add(authLastNameField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
        rightPanel.add(titleField);

        middlePanel.add(leftPanel);
        middlePanel.add(rightPanel);
        outerMiddle.add(middlePanel, BorderLayout.NORTH);

        //add button at bottom
        JButton addBookButton = new JButton("Add Book");
        attachAddBookButtonListener(addBookButton);
        JPanel addBookButtonPanel = new JPanel();
        addBookButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        addBookButtonPanel.add(addBookButton);
        outerMiddle.add(addBookButtonPanel, BorderLayout.CENTER);

    }
    private void attachAddBookButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
//            List<Author> authors = new ArrayList<Author>();
//            String fname = authFirstNameField.getText();
//            String lname = authLastNameField.getText();
//
//            String title = titleField.getText();
//            authors.add(new Author(fname, lname));
//
//            Data.addBookTitle(title);
//            displayInfo("The book " + title + " has been added " +
//                    "to the collection!");

        });
    }
    public void updateData() {
        // nothing to do

    }
}
