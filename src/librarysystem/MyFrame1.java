package librarysystem;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame1 extends JFrame implements ActionListener {

	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel bottomPanel;

	private JTextField nameTextFeild, streetTextField, cityTextField, stateTextField, zipTextField, phoneTexFiel,
			idTextField, lastNameTextFeild;
	private JButton button;

	public MyFrame1() {
		initializeWindow();
		JPanel mainPanel = new JPanel();
		defineTopPanel();
		defineMiddlePanel();
		defineBottomPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		getContentPane().add(mainPanel);
	}

	private void defineTopPanel() {
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel idLable = new JLabel("Member Id");
		idLable.setFont(makeSmallFont(idLable.getFont()));
		idTextField = new JTextField(10);
		JPanel idPanel = createTextPanel(idLable, idTextField);

		JLabel labelName = new JLabel("First Name");
		labelName.setFont(makeSmallFont(labelName.getFont()));
		nameTextFeild = new JTextField(10);
		JPanel namePanel = createTextPanel(labelName, nameTextFeild);

		JLabel labelLastName = new JLabel("Last Name");
		labelLastName.setFont(makeSmallFont(labelLastName.getFont()));
		lastNameTextFeild = new JTextField(10);
		JPanel lastNamePanel = createTextPanel(labelLastName, lastNameTextFeild);

		JLabel labelStreet = new JLabel("Street");
		labelStreet.setFont(makeSmallFont(labelStreet.getFont()));
		streetTextField = new JTextField(10);
		JPanel streetPanel = createTextPanel(labelStreet, streetTextField);

		JLabel cityLabel = new JLabel("City");
		cityLabel.setFont(makeSmallFont(cityLabel.getFont()));
		cityTextField = new JTextField(10);
		JPanel cityPanel = createTextPanel(cityLabel, cityTextField);

		topPanel.add(idPanel);
		topPanel.add(namePanel);
		topPanel.add(lastNamePanel);
		topPanel.add(streetPanel);
		topPanel.add(cityPanel);

	}

	private void defineBottomPanel() {
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		button = new JButton("Submit");
		button.addActionListener(this);
		bottomPanel.add(button);

	}

	private void defineMiddlePanel() {
		middlePanel = new JPanel();
		middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel stateLabel = new JLabel("State");
		stateLabel.setFont(makeSmallFont(stateLabel.getFont()));
		stateTextField = new JTextField(10);
		JPanel statePanel = createTextPanel(stateLabel, stateTextField);

		JLabel zipLabel = new JLabel("Zip");
		zipLabel.setFont(makeSmallFont(zipLabel.getFont()));
		zipTextField = new JTextField(10);
		JPanel zipPanel = createTextPanel(zipLabel, zipTextField);

		JLabel phoneLabel = new JLabel("phone number");
		phoneLabel.setFont(makeSmallFont(phoneLabel.getFont()));
		phoneTexFiel = new JTextField(10);
		JPanel phonePanel = createTextPanel(phoneLabel, phoneTexFiel);

		

		middlePanel.add(statePanel);
		middlePanel.add(zipPanel);
		middlePanel.add(phonePanel);

	}

	private static JPanel createTextPanel(JLabel lab, JTextField textField) {

		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		bottom.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

		top.add(lab);
		bottom.add(textField);

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BorderLayout());
		textPanel.add(top, BorderLayout.NORTH);
		textPanel.add(bottom, BorderLayout.CENTER);
		return textPanel;
	}

	private void initializeWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Register_Member");
		setSize(600, 300);
		handleWindowClosing();
		centerFrameOnDesktop(this);
		setResizable(false);
	}

	public static Font makeSmallFont(Font f) {
		return new Font(f.getName(), f.getStyle(), (f.getSize() - 2));
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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MyFrame1 mf = new MyFrame1();
				mf.setVisible(true);
			}
		});
	}

	void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);

	}

	private static final long serialVersionUID = 3618976789175941431L;

	@Override
	public void actionPerformed(ActionEvent e) {

		String name = nameTextFeild.getText();
		String street = streetTextField.getText();
		String city = cityTextField.getText();
		String state = stateTextField.getText();
		String zip = zipTextField.getText();
		System.out.println(name + "\n" + street + "\n" + city + ", " + state + " " + zip);
	}
}
