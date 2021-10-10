package librarysystem;

import business.CheckoutRecordEntry;
import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;
import librarysystem.rulesets.RuleException;
import librarysystem.rulesets.RuleSet;
import librarysystem.rulesets.RuleSetFactory;
import librarysystem.tables.CheckoutRecordTable;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class CheckOutBookWindow extends JFrame implements LibWindow {

    public static CheckOutBookWindow INSTANCE = new CheckOutBookWindow();
    private boolean isInitialized = false;

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;

    private JTextField memberIdTextFeild, isbntTextField;

    /* This class is a singleton */
    private CheckOutBookWindow() {
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    @Override
    public void init() {
        mainPanel = new JPanel();
        setTitle("Check out book");
        handleWindowClosing();
        defineTopPanel();
        defineBottomPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        getContentPane().add(mainPanel);
        isInitialized(true);
        pack();
    }

    private void defineTopPanel() {

        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel idLable = new JLabel("Member Id");
        memberIdTextFeild = new JTextField(10);
        JPanel idPanel = createTextPanel(idLable, memberIdTextFeild);

        JLabel isbnLabel = new JLabel("ISBN");
        isbntTextField = new JTextField(10);
        JPanel isbnPanel = createTextPanel(isbnLabel, isbntTextField);

        topPanel.add(idPanel);
        topPanel.add(isbnPanel);

    }

    private void defineBottomPanel() {

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton checkout = new JButton("Check Out");
        checkoutButtonListener(checkout);
        JButton backButton = new JButton("<= Back to Home");
        addBackButtonListener(backButton);

        bottomPanel.add(checkout);
        bottomPanel.add(backButton);
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
            CheckOutBookWindow.INSTANCE.setVisible(false);
            INSTANCE = new CheckOutBookWindow();
        });
    }

    private void checkoutButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
            try {
                RuleSet rules = RuleSetFactory.getRuleSet(CheckOutBookWindow.this);
                rules.applyRules(CheckOutBookWindow.this);
                ControllerInterface controllerInterface = new SystemController();
                List<String[]> data = controllerInterface.checkout(memberIdTextFeild.getText(), isbntTextField.getText());

                CheckoutRecordTable.INSTANCE.init();
                Util.centerFrameOnDesktop(CheckoutRecordTable.INSTANCE);
                CheckoutRecordTable.INSTANCE.setValues( CheckoutRecordTable.INSTANCE.getModel(), data);
                CheckoutRecordTable.INSTANCE.setVisible(true);
                CheckoutRecordTable.INSTANCE.pack();

            } catch (RuleException | LibrarySystemException e) {
                JOptionPane.showMessageDialog(CheckOutBookWindow.this, e.getMessage(), "Error", 0);
            }

        });
    }

    public String getMemberIdTextFeild () {
        return memberIdTextFeild.getText();
    }

    public String getiIbntTextField () {
        return isbntTextField.getText();
    }

}

