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
    private JButton button1, button2;
    
    private AddBookWindow() {};
    
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
  
  public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable()
		{
			public void run() {
				AddBookWindow mf = new AddBookWindow();				
				mf.setVisible(true);
			}
		});
	}


	

}
