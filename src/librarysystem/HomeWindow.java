package librarysystem;

import dataaccess.Auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomeWindow extends JFrame implements LibWindow {

    public static HomeWindow INSTANCE = new HomeWindow();
    private boolean isInitialized = false;

    private Auth currentAuth;
    private String userName;

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel topFirstPanel;
    private JPanel topLowerPanel;
    private JPanel middlePanel;
    private JPanel middleTopHalf;
    private JPanel middleLowerHalf;
    private JPanel bottomPanel;

    public boolean isInitialized() {
        return isInitialized;
    }

    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    /* This class is a singleton */
    private HomeWindow() {
    }

    public void init() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setTitle(userName+" ("+currentAuth+")");
        defineTopPanel();
        defineMiddlePanel();
        defineBottomPanel();
        BorderLayout bl = new BorderLayout();
        bl.setVgap(30);
        handleWindowClosing();
        mainPanel.setLayout(bl);
        if(currentAuth.equals(Auth.LIBRARIAN) || currentAuth.equals(Auth.BOTH)) {
            mainPanel.add(topPanel, BorderLayout.NORTH);
        }
        if(currentAuth.equals(Auth.ADMIN) || currentAuth.equals(Auth.BOTH)) {
            mainPanel.add(middlePanel, BorderLayout.CENTER);
        }
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        getContentPane().add(mainPanel);
        isInitialized(true);
    }

    private void defineTopPanel() {
        defineTopFirstPanel();
        defineTopLowerPanel();
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(topFirstPanel, BorderLayout.NORTH);
        topPanel.add(topLowerPanel, BorderLayout.SOUTH);
    }

    private void defineTopFirstPanel() {
        topFirstPanel = new JPanel();
        JPanel intPanel = new JPanel(new BorderLayout());
        intPanel.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.NORTH);
        topFirstPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topFirstPanel.add(intPanel);
    }

    private void defineTopLowerPanel() {
        topLowerPanel = new JPanel();
        topLowerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton checkout = new JButton("Checkout Book");
        checkout.addActionListener(new ChekoutButtonListener());
        topLowerPanel.add(checkout);
    }

    private void defineMiddlePanel() {
        defineMiddleTopHalf();
        defineMiddleLowerHalf();
        middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());
        middlePanel.add(middleTopHalf, BorderLayout.NORTH);
        middlePanel.add(middleLowerHalf, BorderLayout.SOUTH);
    }

    private void defineMiddleTopHalf() {
        middleTopHalf = new JPanel();
        middleTopHalf.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton addMember = new JButton("Add Member");
        addMember.addActionListener(new AddMemberButtonListener());
        JButton addBookCopy = new JButton("Add Book Copy");
        JButton addNewBook = new JButton("Add New Book");
        addNewBook.addActionListener(new AddNewBookButtonListener());
        middleTopHalf.add(addMember);
        middleTopHalf.add(addBookCopy);
        middleTopHalf.add(addNewBook);
    }

    private void defineMiddleLowerHalf() {
        middleLowerHalf = new JPanel();
        middleLowerHalf.setLayout(new BorderLayout());
        JSeparator s = new JSeparator();
        s.setOrientation(SwingConstants.HORIZONTAL);
        middleLowerHalf.add(s, BorderLayout.SOUTH);
    }


    private void defineBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton logout = new JButton("Logout");
        logout.addActionListener(new LogoutButtonListener());
        bottomPanel.add(logout);
    }


    private void handleWindowClosing() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent w) {
                dispose();
                System.exit(0);
            }
        });
    }

    public void setAuth(Auth auth) {
       this.currentAuth = auth;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    class AddMemberButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AddMemberWindow.INSTANCE.init();
            Util.centerFrameOnDesktop(AddMemberWindow.INSTANCE);
            AddMemberWindow.INSTANCE.setVisible(true);
            AddMemberWindow.INSTANCE.pack();
        }
    }

    class AddNewBookButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
               AddNewBookWindow.INSTANCE.init();
               AddNewBookWindow.INSTANCE.setSize(500, 300);
               Util.centerFrameOnDesktop(AddNewBookWindow.INSTANCE);
               AddNewBookWindow.INSTANCE.setVisible(true);
               AddMemberWindow.INSTANCE.pack();
        }
    }

    class LogoutButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginWindow.INSTANCE.setVisible(false);
            INSTANCE.setVisible(false);
            LoginWindow.INSTANCE = new LoginWindow();
            INSTANCE = new HomeWindow();
            LoginWindow.INSTANCE.setVisible(false);
            INSTANCE.setVisible(false);
            LibrarySystem.INSTANCE.setVisible(true);
        }
    }

    class ChekoutButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CheckOutBookWindow.INSTANCE.init();
            Util.centerFrameOnDesktop(CheckOutBookWindow.INSTANCE);
            CheckOutBookWindow.INSTANCE.setVisible(true);
            CheckOutBookWindow.INSTANCE.pack();
        }
    }
}
