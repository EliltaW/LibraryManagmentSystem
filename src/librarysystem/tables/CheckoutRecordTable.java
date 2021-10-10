package librarysystem.tables;

import librarysystem.AddMemberWindow;
import librarysystem.CheckOutBookWindow;
import librarysystem.LibWindow;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecordTable extends JFrame implements LibWindow {

	public static CheckoutRecordTable INSTANCE = new CheckoutRecordTable();
	private JPanel topPanel;
	private JPanel middlePanel;
	private JTable table;
	private JScrollPane scrollPane;
	private CustomTableModel model;

	private boolean isInitialized = false;

	private final String[] DEFAULT_COLUMN_HEADERS 
	   = {"ISBN", "Checkout Date", "Due Date" };
	private static final int SCREEN_WIDTH = 640;
	private static final int SCREEN_HEIGHT = 480;
	private static final int TABLE_WIDTH = (int) (0.75 * SCREEN_WIDTH);
    private static final int DEFAULT_TABLE_HEIGHT = (int) (0.75 * SCREEN_HEIGHT);

    private final float [] COL_WIDTH_PROPORTIONS =
    	{0.35f, 0.35f, 0.3f};

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	public void init() {
		initializeWindow();
		JPanel mainPanel = new JPanel();
		defineTopPanel();
		defineMiddlePanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		getContentPane().add(mainPanel);		
	}
	
	private void defineTopPanel() {
		topPanel = new JPanel();
		createTableAndTablePane();
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(scrollPane);		
	}

	private void defineMiddlePanel(){
		middlePanel=new JPanel();
		middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton backButton = new JButton("<= Back to Home");
		addBackButtonListener(backButton);
		middlePanel.add(backButton);
	}
	
	private void createTableAndTablePane() {
		updateModel(); 
		table = new JTable(model);
		createCustomColumns(table, TABLE_WIDTH,
	            COL_WIDTH_PROPORTIONS, DEFAULT_COLUMN_HEADERS);
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(
				new Dimension(TABLE_WIDTH, DEFAULT_TABLE_HEIGHT));
		scrollPane.getViewport().add(table);
	}
	
	private void createCustomColumns(JTable table, int width, float[] proportions,
		  String[] headers) {
		table.setAutoCreateColumnsFromModel(false);
        int num = headers.length;
        for(int i = 0; i < num; ++i) {
            TableColumn column = new TableColumn(i);
            column.setHeaderValue(headers[i]);
            column.setMinWidth(Math.round(proportions[i]*width));
            table.addColumn(column);
        }
	}
	
	public void setValues(CustomTableModel model, List<String[]> data) {
//		String[] row0 = {"Jim", "Jones", "100,000"};
//		String[] row1 = {"Anne", "Rand", "128,000"};
//		String[] row2 = {"Osama", "Bacchus", "110,000"};
//		List<String[]> data = new ArrayList<>();
//		data.add(row0);
//		data.add(row1);
//		data.add(row2);
		model.setTableValues(data);
		table.updateUI();
	}
	public void updateModel(List<String[]> list){
		if(model == null) {
			model = new CustomTableModel();
		}
		model.setTableValues(list);
	}
	
	private void updateModel() {
		List<String[]> theData = new ArrayList<String[]>();
		updateModel(theData);
	}
	
//	class ButtonListener implements ActionListener {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			setValues(model);
//			table.updateUI();
//		}
//	}
	
	private void initializeWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Member Checkout Record");
		setSize(SCREEN_WIDTH,SCREEN_HEIGHT); 
		setResizable(false);
	}

	private void addBackButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			CheckoutRecordTable.INSTANCE.setVisible(false);
			INSTANCE = new CheckoutRecordTable();
		});
	}

	public CustomTableModel getModel() {
		return model;
	}
	
	private static final long serialVersionUID = 3618976789175941431L;
}
