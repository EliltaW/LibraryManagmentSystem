package librarysystem;

import javax.swing.*;
import java.awt.*;

public class AddCopyBookWindow extends JFrame implements LibWindow {

	public static final AddCopyBookWindow INSTANCE = new AddCopyBookWindow();
	private boolean isInitialized = false;

	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel bottomPanel;

	private JTextField isbnField;
	JButton addButton;

	@Override
	public void init() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		defineTopPanel();
		defineBottomPanel();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		getContentPane().add(mainPanel);
		isInitialized(true);
	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	public void defineTopPanel() {
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel isbnLabel = new JLabel("ISBN");
		Util.adjustLabelFont(isbnLabel, Util.DARK_BLUE, true);
		isbnField = new JTextField(10);

		topPanel.add(isbnField);
		topPanel.add(isbnLabel);

	}

	public void defineBottomPanel() {

		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		addButton = new JButton("Add Copy");
		JButton backButton = new JButton("<= Back to Main");

		bottomPanel.add(addButton, BorderLayout.NORTH);
		bottomPanel.add(backButton, BorderLayout.SOUTH);

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

		});
	}

	public void updateData() {
		// nothing to do

	}

}
