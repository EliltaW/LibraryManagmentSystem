package librarysystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import business.ControllerInterface;

import business.SystemController;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

import static javax.swing.JOptionPane.ERROR_MESSAGE;


public class LoginWindow extends JFrame implements LibWindow {
    public static final LoginWindow INSTANCE = new LoginWindow();
	
	private boolean isInitialized = false;
	
	private JPanel mainPanel;
	private JPanel upperHalf;
	private JPanel middleHalf;
	private JPanel lowerHalf;
	private JPanel container;
	
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;
	private JPanel leftTextPanel;
	private JPanel rightTextPanel;
	
	private JTextField username;
	private JTextField password;
	private JLabel label;
	private JButton loginButton;
	private JButton logoutButton;
	
	
	
	
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
    private LoginWindow () {}
    
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
    		defineMiddlePanel();
    		defineLowerPanel();
    		upperHalf.add(topPanel, BorderLayout.NORTH);
    		upperHalf.add(middlePanel, BorderLayout.CENTER);
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
    		JLabel loginLabel = new JLabel("Login");
    		Util.adjustLabelFont(loginLabel, Color.BLUE.darker(), true);
    		intPanel.add(loginLabel, BorderLayout.CENTER);
    		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    		topPanel.add(intPanel);
    		
    	}
    	
    	
    	
    	private void defineMiddlePanel() {
    		middlePanel=new JPanel();
    		middlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    		defineLeftTextPanel();
    		defineRightTextPanel();
    		middlePanel.add(leftTextPanel);
    		middlePanel.add(rightTextPanel);
    	}
    	private void defineLowerPanel() {
    		lowerPanel = new JPanel();
    		loginButton = new JButton("Login");
    		addLoginButtonListener(loginButton);
    		lowerPanel.add(loginButton);
    	}

    	private void defineLeftTextPanel() {
    		
    		JPanel topText = new JPanel();
    		JPanel bottomText = new JPanel();
    		topText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
    		bottomText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));		
    		
    		username = new JTextField(10);
    		label = new JLabel("Username");
    		label.setFont(Util.makeSmallFont(label.getFont()));
    		topText.add(username);
    		bottomText.add(label);
    		
    		leftTextPanel = new JPanel();
    		leftTextPanel.setLayout(new BorderLayout());
    		leftTextPanel.add(topText,BorderLayout.NORTH);
    		leftTextPanel.add(bottomText,BorderLayout.CENTER);
    	}
    	private void defineRightTextPanel() {
    		
    		JPanel topText = new JPanel();
    		JPanel bottomText = new JPanel();
    		topText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
    		bottomText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));		
    		
    		password = new JPasswordField(10);
    		label = new JLabel("Password");
    		label.setFont(Util.makeSmallFont(label.getFont()));
    		topText.add(password);
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
				String userName = username.getText().trim();
				String pwd = password.getText().trim();
				if(userName.length() == 0 || pwd.length() == 0) {
					JOptionPane.showMessageDialog(this,"Id and Password fields must be nonempty",
							"Empty Fields", 0);
				} else {
					DataAccess dataAccess = new DataAccessFacade();
					User user = dataAccess.readUserMap().get(userName);
					if(user == null) {
						JOptionPane.showMessageDialog(this,"User Id not recognized",
								"Wrong User Id", 0);
					}
					else if (!user.getPassword().equals(pwd)) {
						JOptionPane.showMessageDialog(this,"Wrong password provided",
								"Wrong Password", 0);
					}
					else {
						System.out.println(user.getAuthorization());

						LibrarySystem.hideAllWindows();
						LibrarySystem.INSTANCE.options.remove(0);
						JMenuItem addBook = new JMenuItem("Add Book");
						addBook.addActionListener(new AddBookWindowListener());
						JMenuItem addMember = new JMenuItem("Add Member");
						addMember.addActionListener(new AddMemberWindowListener());
						LibrarySystem.INSTANCE.options.add(addMember);
						LibrarySystem.INSTANCE.options.add(addBook);
						LibrarySystem.INSTANCE.setVisible(true);


						//Data.currentAuth = u.authorization;
						//displayInfo("Login successful");
						//updateLeftPanel(Data.currentAuth);
						//bookClub.repaint();
						JOptionPane.showMessageDialog(this,"Login Successful",
								"Success", 1);
					}

				}


    				
    		});
    	}
}

class AddBookWindowListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		LibrarySystem.hideAllWindows();
		AddBookWindow.INSTANCE.init();
		Util.centerFrameOnDesktop(AddBookWindow.INSTANCE);
		AddBookWindow.INSTANCE.setVisible(true);
		AddBookWindow.INSTANCE.pack();

	}

}

class AddMemberWindowListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		LibrarySystem.hideAllWindows();
		AddMemberWindow.INSTANCE.init();
		Util.centerFrameOnDesktop(AddMemberWindow.INSTANCE);
		AddMemberWindow.INSTANCE.setVisible(true);
		AddMemberWindow.INSTANCE.pack();

	}

}
