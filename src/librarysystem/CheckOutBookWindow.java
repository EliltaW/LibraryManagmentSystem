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

public class CheckOutBookWindow extends JFrame implements LibWindow{
	
    public static final CheckOutBookWindow INSTANCE = new CheckOutBookWindow();
    private boolean isInitialized = false;

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;
    
    private JTextField memberIdTextFeild, isbntTextField;
    private JButton button;
    
    /* This class is a singleton */
    private CheckOutBookWindow () {}
    
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
	        defineMiddlePanel();
	        defineBottomPanel();
	        mainPanel.setLayout(new BorderLayout());
	        mainPanel.add(topPanel, BorderLayout.NORTH);
	        mainPanel.add(middlePanel, BorderLayout.CENTER);
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

          topPanel.add(idPanel);
       
    }
    
    private void defineMiddlePanel() {
    	
    	middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel isbnLabel = new JLabel("ISBN");
        isbntTextField = new JTextField(10);
        JPanel isbnPanel = createTextPanel(isbnLabel, isbntTextField);
    }
    
    private void defineBottomPanel() {
    	
    	bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        button = new JButton("Check Out");
      //  addSubmitButtonListener(button);
        JButton backButton = new JButton("<= Back to Main");
       // addBackButtonListener(backButton);

        bottomPanel.add(button);
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

	
	 private void initializeWindow() {
	        setTitle("Check Out Book");
	        setSize(300, 250);
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

