package librarysystem;

import javax.swing.*;
import java.awt.*;

public class AddMemberWindow extends JFrame implements LibWindow {
    public static final AddMemberWindow INSTANCE = new AddMemberWindow();

    private boolean isInitialized = false;

    private JPanel mainPanel;
    private JPanel upperHalf;
    private JPanel middleHalf;
    private JPanel lowerHalf;

    private JPanel topPanel;
    private JPanel firstMiddlePanel;
    private JPanel secondMiddlePanel;
    private JPanel lowerPanel;
    private JPanel leftTextPanel;
    private JPanel rightTextPanel;
    private JPanel anotherTextPanel;

    private JTextField memberId;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField street;
    private JTextField city;
    private JTextField state;
    private JTextField zip;
    private JTextField telephoneNumber;

    private JLabel label;
    private JButton addMember;

    public boolean isInitialized() {
        return isInitialized;
    }
    public void isInitialized(boolean val) {
        isInitialized = val;
    }
    private JTextField messageBar = new JTextField();
    public void clear() {
        messageBar.setText("");
    }

    /* This class is a singleton */
    private AddMemberWindow () {}

    public void init() {
        mainPanel = new JPanel();
        defineUpperHalf();
        defineMiddleHalf();
        defineLowerHalf();
        BorderLayout bl = new BorderLayout();
        bl.setVgap(30);
        mainPanel.setLayout(bl);

        mainPanel.add(upperHalf, BorderLayout.NORTH);
        mainPanel.add(middleHalf, BorderLayout.CENTER);
        mainPanel.add(lowerHalf, BorderLayout.SOUTH);
        getContentPane().add(mainPanel);
        isInitialized(true);
        pack();
        //setSize(660, 500);


    }
    private void defineUpperHalf() {

        upperHalf = new JPanel();
        upperHalf.setLayout(new BorderLayout());
        defineTopPanel();
        defineFirstMiddlePanel();
        defineSecondMiddlePanel();
        defineLowerPanel();
        upperHalf.add(topPanel, BorderLayout.NORTH);
        upperHalf.add(firstMiddlePanel, BorderLayout.CENTER);
        upperHalf.add(secondMiddlePanel, BorderLayout.CENTER);
        upperHalf.add(lowerPanel, BorderLayout.SOUTH);

    }
    private void defineMiddleHalf() {
        middleHalf = new JPanel();
        middleHalf.setLayout(new BorderLayout());
        JSeparator s = new JSeparator();
        s.setOrientation(SwingConstants.HORIZONTAL);
        //middleHalf.add(Box.createRigidArea(new Dimension(0,50)));
        middleHalf.add(s, BorderLayout.SOUTH);

    }
    private void defineLowerHalf() {

        lowerHalf = new JPanel();
        lowerHalf.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton backButton = new JButton("<= Back to Main");
        addBackButtonListener(backButton);
        lowerHalf.add(backButton);

    }
    private void defineTopPanel() {
        topPanel = new JPanel();
        JPanel intPanel = new JPanel(new BorderLayout());
        intPanel.add(Box.createRigidArea(new Dimension(0,20)), BorderLayout.NORTH);
        JLabel loginLabel = new JLabel("Add Member");
        Util.adjustLabelFont(loginLabel, Color.BLUE.darker(), true);
        intPanel.add(loginLabel, BorderLayout.CENTER);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(intPanel);

    }



    private void defineFirstMiddlePanel() {
        firstMiddlePanel =new JPanel();
        firstMiddlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        defineLeftTextPanel();
        defineAnotherTextPanel();
        defineRightTextPanel();
        firstMiddlePanel.add(leftTextPanel);
        firstMiddlePanel.add(rightTextPanel);
    }

    private void defineSecondMiddlePanel() {
        secondMiddlePanel =new JPanel();
        secondMiddlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        defineLeftTextPanel();
        defineAnotherTextPanel();
        defineRightTextPanel();
        secondMiddlePanel.add(leftTextPanel);
        secondMiddlePanel.add(rightTextPanel);
    }
    private void defineLowerPanel() {
        lowerPanel = new JPanel();
        addMember = new JButton("Add Member");
        addLoginButtonListener(addMember);
        lowerPanel.add(addMember);
    }

    private void defineLeftTextPanel() {

        JPanel topText = new JPanel();
        JPanel bottomText = new JPanel();
        topText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
        bottomText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));

        memberId = new JTextField(10);
        label = new JLabel("Member Id");
        label.setFont(Util.makeSmallFont(label.getFont()));
        topText.add(memberId);
        bottomText.add(label);

        leftTextPanel = new JPanel();
        leftTextPanel.setLayout(new BorderLayout());
        leftTextPanel.add(topText,BorderLayout.NORTH);
        leftTextPanel.add(bottomText,BorderLayout.CENTER);
    }
    private void defineAnotherTextPanel() {

        JPanel topText = new JPanel();
        JPanel bottomText = new JPanel();
        topText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
        bottomText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));

        memberId = new JTextField(10);
        label = new JLabel("Member Id");
        label.setFont(Util.makeSmallFont(label.getFont()));
        topText.add(memberId);
        bottomText.add(label);

        anotherTextPanel = new JPanel();
        anotherTextPanel.setLayout(new BorderLayout());
        anotherTextPanel.add(topText,BorderLayout.NORTH);
        anotherTextPanel.add(bottomText,BorderLayout.CENTER);
    }

    private void defineRightTextPanel() {

        JPanel topText = new JPanel();
        JPanel bottomText = new JPanel();
        topText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
        bottomText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));

        firstName = new JTextField(10);
        label = new JLabel("First Name");
        label.setFont(Util.makeSmallFont(label.getFont()));
        topText.add(firstName);
        bottomText.add(label);

        rightTextPanel = new JPanel();
        rightTextPanel.setLayout(new BorderLayout());
        rightTextPanel.add(topText,BorderLayout.NORTH);
        rightTextPanel.add(bottomText,BorderLayout.CENTER);
    }

    private void addBackButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
            LibrarySystem.hideAllWindows();
            LibrarySystem.INSTANCE.setVisible(true);
        });
    }

    private void addLoginButtonListener(JButton butn) {
        butn.addActionListener(evt -> {

        });
    }
}

