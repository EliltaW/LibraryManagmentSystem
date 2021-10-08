package librarysystem;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class AddBookWindow extends JFrame implements LibWindow{
	
	public static final AddBookWindow INSTANCE = new AddBookWindow();
    private boolean isInitialized = false;
    
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel outerMiddle;
    private JPanel lowerHalf;


    private JTextField authFirstNameField;
    private JTextField authLastNameField;
    private JTextField titleField;

    @Override
    public void init() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        defineTopPanel();
        defineOuterMiddle();
        defineLowerHalf();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(outerMiddle, BorderLayout.CENTER);
        mainPanel.add(lowerHalf, BorderLayout.SOUTH);
        getContentPane().add(mainPanel);
        isInitialized(true);
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }
    
    public void isInitialized(boolean val) {
        isInitialized = val;
    }


    @Override
	public void init() {

		 initializeWindow();
	        mainPanel = new JPanel();
	        defineTopPanel();
	        mainPanel.setLayout(new BorderLayout());
	        mainPanel.add(topPanel, BorderLayout.CENTER);
	        getContentPane().add(mainPanel);
	        isInitialized(true);
	        pack();
	}
    
  private void defineTopPanel() {
    	
	  topPanel = new JPanel();
	  topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        button1 = new JButton("Add Existing Book");
        button2 = new JButton("Add New Book");
        // addSubmitButtonListener(button);
        JButton backButton = new JButton("<= Back to Main");
       // addBackButtonListener(backButton);

        topPanel.add(button1, BorderLayout.NORTH);
        topPanel.add(button2, BorderLayout.CENTER);
       topPanel.add(backButton, BorderLayout.SOUTH);
       
    }


    private void initializeWindow() {
        setTitle("Add Book");
        setSize(300, 250);
        handleWindowClosing();
        centerFrameOnDesktop(this);
        setResizable(false);
    }


    private void defineLowerHalf() {
        lowerHalf = new JPanel();
        lowerHalf.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton backButton = new JButton("<= Back to Main");
        addBackButtonListener(backButton);
        lowerHalf.add(backButton);
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
            System.out.println(authFirstNameField.getText().trim());
            System.out.println(titleField.getText().trim());

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

    private void addBackButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
            LibrarySystem.hideAllWindows();
            LibrarySystem.INSTANCE.setVisible(true);
        });
    }
}
