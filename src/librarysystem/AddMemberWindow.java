package librarysystem;

import business.Address;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddMemberWindow extends JFrame implements LibWindow {
    public static AddMemberWindow INSTANCE = new AddMemberWindow();

    private boolean isInitialized = false;

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;

    private JTextField nameTextFeild, streetTextField, cityTextField, stateTextField, zipTextField, phoneTexFiel,
            idTextField, lastNameTextFeild;
    private JButton button;

    public boolean isInitialized() {
        return isInitialized;
    }
    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    /* This class is a singleton */
    private AddMemberWindow () {}

    public void init() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setTitle("Add Member");
        defineTopPanel();
        defineMiddlePanel();
        defineBottomPanel();
        BorderLayout bl = new BorderLayout();
        bl.setVgap(30);
        mainPanel.setLayout(bl);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        getContentPane().add(mainPanel);
        isInitialized(true);
        //setSize(660, 500);
    }

    private void defineTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel idLabel = new JLabel("Member Id");
        idTextField = new JTextField(10);
        JPanel idPanel = createTextPanel(idLabel, idTextField);

        JLabel labelName = new JLabel("First Name");
        nameTextFeild = new JTextField(10);
        JPanel namePanel = createTextPanel(labelName, nameTextFeild);

        JLabel labelLastName = new JLabel("Last Name");
        lastNameTextFeild = new JTextField(10);
        JPanel lastNamePanel = createTextPanel(labelLastName, lastNameTextFeild);

        JLabel labelStreet = new JLabel("Street");
        streetTextField = new JTextField(10);
        JPanel streetPanel = createTextPanel(labelStreet, streetTextField);

        topPanel.add(idPanel);
        topPanel.add(namePanel);
        topPanel.add(lastNamePanel);
        topPanel.add(streetPanel);

    }

    private void defineBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        button = new JButton("Submit");
        addSubmitButtonListener(button);
        JButton backButton = new JButton("<= Back to Home");
        addBackButtonListener(backButton);

        bottomPanel.add(button);
        bottomPanel.add(backButton);
    }

    private void defineMiddlePanel() {
        middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel cityLabel = new JLabel("City");
        cityTextField = new JTextField(10);
        JPanel cityPanel = createTextPanel(cityLabel, cityTextField);

        JLabel stateLabel = new JLabel("State");
        stateTextField = new JTextField(10);
        JPanel statePanel = createTextPanel(stateLabel, stateTextField);

        JLabel zipLabel = new JLabel("Zip");
        zipTextField = new JTextField(10);
        JPanel zipPanel = createTextPanel(zipLabel, zipTextField);

        JLabel phoneLabel = new JLabel("Phone Number");
        phoneTexFiel = new JTextField(10);
        JPanel phonePanel = createTextPanel(phoneLabel, phoneTexFiel);

        middlePanel.add(cityPanel);
        middlePanel.add(statePanel);
        middlePanel.add(zipPanel);
        middlePanel.add(phonePanel);

    }

    private static JPanel createTextPanel(JLabel lab, JTextField textField) {

        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        bottom.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

        lab.setFont(Util.makeSmallFont(lab.getFont()));
        top.add(lab);
        bottom.add(textField);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.add(top, BorderLayout.NORTH);
        textPanel.add(bottom, BorderLayout.CENTER);
        return textPanel;
    }

    private void handleWindowClosing() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent w) {
                dispose();
                // other clean-up
                System.exit(0);
            }
        });
    }

    private void addBackButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
            AddMemberWindow.INSTANCE.setVisible(false);
            INSTANCE = new AddMemberWindow();
        });
    }

    private void addSubmitButtonListener(JButton butn) {
        butn.addActionListener(evt -> {

            String street = streetTextField.getText().trim();
            String city = cityTextField.getText().trim();
            String state = stateTextField.getText().trim();
            String zip = zipTextField.getText().trim();

            System.out.println("&&&");
            System.out.println(street);

            String memberId = idTextField.getText().trim();
            String fname = nameTextFeild.getText().trim();
            String lname = lastNameTextFeild.getText().trim();
            String tel = phoneTexFiel.getText().trim();

            Address address = new Address(street, city, state, zip);
            LibraryMember libraryMember  = new LibraryMember(memberId, fname, lname, tel, address);

            LibrarySystem.INSTANCE.ci.addMember(libraryMember);
            JOptionPane.showMessageDialog(this, "Member Added Successfully",
                    "Success", 1);
            clearData();
        });
    }

    public void clearData() {
        streetTextField.setText("");
        cityTextField.setText("");
        stateTextField.setText("");
        zipTextField.setText("");
        idTextField.setText("");
        nameTextFeild.setText("");
        lastNameTextFeild.setText("");
        phoneTexFiel.setText("");
    }
}

