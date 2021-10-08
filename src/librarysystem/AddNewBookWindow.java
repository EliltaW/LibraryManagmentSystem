package librarysystem;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddNewBookWindow extends JFrame implements LibWindow{

	public static final AddNewBookWindow INSTANCE = new AddNewBookWindow();

	private boolean isInitialized = false;

	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel bottomPanel;

	private JTextField authNameTextFeild, isbnTextField, titleTextFeild, maxCheckOutTextField, numOfCopyTextField;
	private JButton addButton;

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	/* This class is a singleton */
	private AddNewBookWindow () {}

	public void init() {
		initializeWindow();
		mainPanel = new JPanel();
		defineTopPanel();
		defineMiddlePanel();
		defineBottomPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		getContentPane().add(mainPanel);
		isInitialized(true);
		pack();
		// setSize(660, 500);
	}
	
	 private void defineTopPanel() {
	        topPanel = new JPanel();
	        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

	        JLabel authNameLable = new JLabel("Author Name");
	        authNameTextFeild = new JTextField(10);
	        JPanel authNamePanel = createTextPanel(authNameLable, authNameTextFeild);

	        JLabel isbnLable = new JLabel("ISBN");
	        isbnTextField = new JTextField(10);
	        JPanel isbnPanel = createTextPanel(isbnLable, isbnTextField);

	        JLabel titleLable = new JLabel("Title");
	        titleTextFeild = new JTextField(10);
	        JPanel titelPanel = createTextPanel(titleLable, titleTextFeild);

	      
	        topPanel.add(isbnPanel);
	        topPanel.add(authNamePanel);
	        topPanel.add(titelPanel);

	    }
	 
	 private void defineMiddlePanel() {
		 
		 middlePanel = new JPanel();
	        middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

	        JLabel maxCheckOutLable = new JLabel("Max Checkout Length");
	        maxCheckOutTextField = new JTextField(10);
	        JPanel maxPanel = createTextPanel(maxCheckOutLable, maxCheckOutTextField);

	        JLabel numCopyLable = new JLabel("Number Of Copies");
	        numOfCopyTextField = new JTextField(10);
	        JPanel numCopyPanel = createTextPanel(numCopyLable, numOfCopyTextField);

	        middlePanel.add(maxPanel);
	        middlePanel.add(numCopyPanel);
	      

	 }
	 
	 private void defineBottomPanel() {
		 
		 bottomPanel = new JPanel();
	        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	        addButton = new JButton("Add Book");
	       // addSubmitButtonListener(button1);
	        JButton backButton = new JButton("<= Back to Main");
	       // addBackButtonListener(backButton);

	        bottomPanel.add(addButton, BorderLayout.NORTH);
	        bottomPanel.add(backButton, BorderLayout.SOUTH);
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


	 private void initializeWindow() {
	        setTitle("Add New Book");
	        setSize(500, 1000);
	        handleWindowClosing();
	        centerFrameOnDesktop(this);
	        setResizable(false);
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

	 public static void centerFrameOnDesktop(Component f) {
	        final int SHIFT_AMOUNT = 0;
	        Toolkit toolkit = Toolkit.getDefaultToolkit();
	        int height = toolkit.getScreenSize().height;
	        int width = toolkit.getScreenSize().width;
	        int frameHeight = f.getSize().height;
	        int frameWidth = f.getSize().width;
	        f.setLocation(((width - frameWidth) / 2) - SHIFT_AMOUNT, (height - frameHeight) / 3);
	    }

	
}
