package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class UI {
	
	private Connect connect;
	
	// pull down for "add" 
	private JComboBox<String> cb;
    private JButton OKbtn;
    
    private int selectedIndex;    
    
    public UI(Connect connect) {
		new UI(connect, true);
	}
    
	public UI(Connect connect, boolean useLogin) {
		this.connect = connect;
		selectedIndex = 0;
		if(useLogin) {
			gotoPage(new JFrame(), "Login");
		} else {
			gotoPage(new JFrame(), "Main");
		}
	}
	
	private void gotoPage(JFrame frame, String newPage) {
		switch (newPage) {
			case "Main": {
				frame.dispose();
				frame.setVisible(false);
				mainPage();
				break;
			}
			
			case "Add": {
				frame.dispose();
				frame.setVisible(false);
				addPage();
				break;
			}
			
			case "Delete": {
				frame.dispose();
				frame.setVisible(false);
				deletePage();
				break;
			}
			
			case "Update": {
				frame.dispose();
				frame.setVisible(false);
				updatePage();
				break;
			}
			
			case "Login": {
				frame.dispose();
				frame.setVisible(false);
				loginPage();
				break;
			}
		}
	}
	
	private void loginPage() {
		Login log = new Login(connect);
		
		// create the frame
		JFrame frame = new JFrame("Login or Register");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		
		// create panels
		JPanel btnPanel = new JPanel();
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		inputPanel.setBounds(25, 25, 250, 250);
		
		// add buttons
		JButton login = new JButton("Login");
		JButton reg = new JButton("Register");
		btnPanel.add(login);
		btnPanel.add(reg);
		
		// add user inputs
		JLabel userLabel = new JLabel("Username: ");
		JTextField user = new JTextField("");
		user.setMaximumSize(new Dimension(400, 25));
		JLabel passLabel = new JLabel("Password: ");
		JTextField pass = new JTextField("");
		pass.setMaximumSize(new Dimension(400, 25));
		inputPanel.add(userLabel);
		inputPanel.add(user);
		inputPanel.add(passLabel);
		inputPanel.add(pass);
		
		// add action listeners to call login methods
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(log.login(user.getText(), pass.getText())) {
					gotoPage(frame, "Main");
				}
			}
		});
		reg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				log.register(user.getText(), pass.getText());
			}
		});
		
		// get frame ready to display
		frame.getContentPane().add(BorderLayout.SOUTH, btnPanel);
		frame.getContentPane().add(inputPanel);
		frame.setVisible(true);
	}
	
	private JPanel getButtonPanel(JFrame frame) {
		JPanel btnPanel = new JPanel();
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		inputPanel.setBounds(40, 40, 20, 20);

		JButton mainButton = new JButton("Main");
		JButton addButton = new JButton("Add");
		JButton updateButton = new JButton("Update");
		JButton deleteButton = new JButton("Delete");
		btnPanel.add(mainButton);
		btnPanel.add(addButton);
		btnPanel.add(updateButton);
		btnPanel.add(deleteButton);
		
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gotoPage(frame, "Add");
			}
		});
		
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gotoPage(frame, "Update");
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gotoPage(frame, "Delete");
			}
		});
		
		mainButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gotoPage(frame, "Main");
			}
		});
		
		return btnPanel;
	}
	
	private void displaySelectedChoice(JLabel resultLabel) {
        String selectedChoice = (String) cb.getSelectedItem();
        resultLabel.setText("Selected: " + selectedChoice);
        selectedIndex = cb.getSelectedIndex();
    }
	
	private void mainPage() {
		// create the frame
		JFrame frame = new JFrame("Welcome to the EsportDataTracking App!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);
		
		// create panels
		JPanel btnPanel = getButtonPanel(frame);
		frame.add(BorderLayout.SOUTH, btnPanel);
		frame.setVisible(true);
		
		// add pull down menu
		JPanel addPDpanel = new JPanel();
	    frame.add(addPDpanel, BorderLayout.NORTH);
		JLabel addLabel = new JLabel("Select data to view: ");
		addLabel.setBounds(20, 10, 20, 20);
		addLabel.setVisible(true);

        addPDpanel.add(addLabel);
		addPDpanel.setLocation(100, 100);
        String[] choices = { "Team", "Player", "Gear", "Match", "Match Organization", 
							"Event", "Player Uses Gear", "Event Has a Match", 
							"Event Held by Organization", "Team in Match", 
							"Team Placed In Event", "Player Played in a Match", 
							"Player Plays For a Team"};
        cb = new JComboBox<String>(choices);
        cb.setSelectedIndex(selectedIndex);
        cb.setVisible(true);
	    addPDpanel.add(cb);

		final JPanel[] contentPanel = {new JPanel()};
		readTable(cb.getSelectedIndex(), contentPanel[0]);
		frame.add(contentPanel[0], BorderLayout.CENTER);
	    
	    OKbtn = new JButton("OK");
	    addPDpanel.add(OKbtn);

		JLabel resultLabel = new JLabel("");
		addPDpanel.add(resultLabel);
		
	    OKbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySelectedChoice(resultLabel);
                frame.remove(contentPanel[0]);
				contentPanel[0] = new JPanel();
                frame.add(contentPanel[0]);
                readTable(cb.getSelectedIndex(), contentPanel[0]);
            }
        });
	}
	
	private void readTable(int selectedItem, JPanel contentPanel) {
		
		Select select = new Select(connect);
		JTable dataTable = null;
		
		switch(selectedItem) {
		
			case 0: {
				//Team
				Object[] columnNames = {"ID", "Team Name", "Sponsor", "Date Founded"};
				Object[][] data = select.selectTeam("", "", "", "");
//				if(data == null) {
//					break;
//				}
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 1: {
				//Player
				Object[] columnNames = {"", "", "", "", "", "", ""};
				Object[][] data = select.selectPlayer("", "", "", "", "", "", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 2: {
				//Gear
				Object[] columnNames = {"", "", "", "", ""};
				Object[][] data = select.selectGear("", "", "", "", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 3: {
				//Match
				Object[] columnNames = {"", "", "", ""};
				Object[][] data = select.selectMatch("", "", "", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 4: {
				//Org
				Object[] columnNames = {"", "", "", ""};
				Object[][] data = select.selectMatchOrganization("", "", "", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 5: {
				//Event
				Object[] columnNames = {"", "", "", "", ""};
				Object[][] data = select.selectEvent("", "", "", "", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 6: {
				//Uses
				// TODO: make table scrollable
				Object[] columnNames = {"Player", "Gear", "Since"};
				Object[][] data = select.selectUses("", "", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 7: {
				//Has
				Object[] columnNames = {"", ""};
				Object[][] data = select.selectHas("", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 8: {
				//Held
				Object[] columnNames = {"", ""};
				Object[][] data = select.selectHeld("", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 9: {
				//ParticipatesIn
				Object[] columnNames = {"", "", ""};
				Object[][] data = select.selectParticipateIn("", "", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 10: {
				//PlacedIn
				Object[] columnNames = {"", "", ""};
				Object[][] data = select.selectPlacedIn("", "", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 11: {
				//PlayedOn
				Object[] columnNames = {"", ""};
				Object[][] data = select.selectPlayedOn("", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 12: {
				//PlaysFor
				Object[] columnNames = {"", ""};
				Object[][] data = select.selectPlaysFor("", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
		
		}
		
		if(dataTable == null) {
			Object[] columnNames = {"Error"};
			Object[][] data = {{"No Data Available"}};
			dataTable = new JTable(data, columnNames);
		}
		
		dataTable.setEnabled(false);
		contentPanel.add(dataTable);
	}

	private void addPage() {
		Add a = new Add(connect);
		
		// set up frame and add standard buttons
		JFrame frame = new JFrame("Add Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(600,600));
		JPanel btnPanel = getButtonPanel(frame);
		frame.add(BorderLayout.SOUTH, btnPanel);
		
		// set up pull down menu panel and add label
		JPanel addPDpanel = new JPanel();
	    frame.add(BorderLayout.NORTH, addPDpanel);
		JLabel addLabel = new JLabel("Select something to add: ");
		addLabel.setBounds(20, 10, 20, 20);
		addLabel.setVisible(true);
		addPDpanel.add(addLabel);
		addPDpanel.setLocation(100, 100);

		// add pull down menu
        String[] choices = { "Team", "Player", "Gear", "Match", "Match Organization", 
        					"Event", "Player Uses Gear", "Event Has a Match", 
        					"Event Held by Organization", "Team in Match", 
        					"Team Placed In Event", "Player Played in a Match", 
        					"Player Plays For a Team"};
        cb = new JComboBox<String>(choices);
        cb.setSelectedIndex(selectedIndex);
        cb.setVisible(true);
	    addPDpanel.add(cb);
	    
	    OKbtn = new JButton("OK");
	    addPDpanel.add(OKbtn);

		JLabel resultLabel = new JLabel("");
		addPDpanel.add(resultLabel);
		
	    OKbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySelectedChoice(resultLabel);
                // TODO: Code for what to do when table is selected
            }
        });
	    
	    // TODO: Code for add operation UI
	    
	    frame.pack();
		frame.setVisible(true);
	}
	
	private void set (JTable pointer, JTable newTable) {
		pointer = newTable;
	}
	
	private void deletePage() {
		
		Delete d = new Delete(connect);
		
		// set up frame and add standard buttons
		JFrame frame = new JFrame("Delete Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(600,600));
		JPanel btnPanel = getButtonPanel(frame);
		frame.add(BorderLayout.SOUTH, btnPanel);
		
		// set up pull down menu panel and add label
		JPanel addPDpanel = new JPanel();
	    frame.add(BorderLayout.NORTH, addPDpanel);
		JLabel addLabel = new JLabel("Select something to delete: ");
		addLabel.setBounds(20, 10, 20, 20);
		addLabel.setVisible(true);
		addPDpanel.add(addLabel);
		addPDpanel.setLocation(100, 100);

		// add pull down menu
        String[] choices = { "Team", "Player", "Gear", "Match", "Match Organization", 
        					"Event", "Player Uses Gear", "Event Has a Match", 
        					"Event Held by Organization", "Team in Match", 
        					"Team Placed In Event", "Player Played in a Match", 
        					"Player Plays For a Team"};
        cb = new JComboBox<String>(choices);
        cb.setSelectedIndex(selectedIndex);
        cb.setVisible(true);
	    addPDpanel.add(cb);
	    
	    JPanel contentPanel = new JPanel();
		JTable dataTable = deleteTable(cb.getSelectedIndex(), contentPanel);
		frame.add(contentPanel, BorderLayout.CENTER);
	    
	    OKbtn = new JButton("OK");
	    addPDpanel.add(OKbtn);

		JLabel resultLabel = new JLabel("");
		addPDpanel.add(resultLabel);
		
	    OKbtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				displaySelectedChoice(resultLabel);
                contentPanel.getComponent(0).setEnabled(false);
                contentPanel.getComponent(0).setVisible(false);
                contentPanel.removeAll();
                set(dataTable, deleteTable(cb.getSelectedIndex(), contentPanel));
                
			}
	    });
	    addPDpanel.add(OKbtn);
	    
	    JButton submit = new JButton("Submit");
	    submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JTable dataTable = ((JTable) contentPanel.getComponent(0));
				
				// call delete code
				switch(cb.getSelectedIndex()) {
				
					case 0: {
						// Team
						for(Object id : getSingleIDRows(dataTable)) {
							d.deleteTeam((int) id);
						}
						break;
					}
					
					case 1: {
						// Player
						for(Object id : getSingleIDRows(dataTable)) {
							d.deletePlayer((int) id);
						}
						break;
					}
					
					case 2: {
						// Gear
						for(Object model : getSingleIDRows(dataTable)) {
							d.deleteGear((String) model);
						}
						break;
					}
					
					case 3: {
						// Match
						for(Object id : getSingleIDRows(dataTable)) {
							d.deleteMatch((int) id);
						}
						break;
					}
					
					case 4: {
						// Org
						for(Object id : getSingleIDRows(dataTable)) {
							d.deleteOrg((int) id);
						}
						break;
					}
					
					case 5: {
						// Event
						for(Object id : getSingleIDRows(dataTable)) {
							d.deleteEvent((int) id);
						}
						break;
					}
					
					case 6: {
						// Uses
						for(Object[] i : getDoubleIDRows(dataTable)) {
							d.deleteUses((int) i[0], (String) i[1]);
						}
						break;
					}
					
					case 7: {
						// Has
						for(Object[] i : getDoubleIDRows(dataTable)) {
							d.deleteHas((int) i[0], (int) i[1]);
						}
						break;
					}
					
					case 8: {
						// Held
						for(Object[] i : getDoubleIDRows(dataTable)) {
							d.deleteHeld((int) i[1], (int) i[0]);
						}
						break;
					}
					
					case 9: {
						// PlayedOn
						for(Object[] i : getDoubleIDRows(dataTable)) {
							d.deletePlayedOn((int) i[0], (int) i[1]);
						}
						break;
					}
					
					case 10: {
						// PlacedIn
						for(Object[] i : getDoubleIDRows(dataTable)) {
							d.deletePlacedIn((int) i[0], (int) i[1]);
						}
						break;
					}
					
					case 11: {
						// ParticipateIn
						for(Object[] i : getDoubleIDRows(dataTable)) {
							d.deleteParticipateIn((int) i[0], (int) i[1]);
						}
						break;
					}
					
					case 12: {
						// PlaysFor
						for(Object[] i : getDoubleIDRows(dataTable)) {
							d.deletePlaysFor((int) i[0], (int) i[1]);
						}
						break;
					}
				
				}
				
				// refresh table output
				displaySelectedChoice(resultLabel);
                contentPanel.getComponent(0).setEnabled(false);
                contentPanel.getComponent(0).setVisible(false);
                contentPanel.removeAll();
                set(dataTable, deleteTable(cb.getSelectedIndex(), contentPanel));
				
			}
			
	    });
	    
	    btnPanel.add(submit, BorderLayout.NORTH);

	    frame.pack();
		frame.setVisible(true);
	}
	
	private List<Object> getSingleIDRows(JTable dataTable){
		List<Object> checkedRowIDs = new ArrayList<Object>();
		
		for (int i = 0; i < dataTable.getRowCount(); i++) {

			if((boolean) dataTable.getValueAt(i, 0)) {
				checkedRowIDs.add(dataTable.getValueAt(i, 1));
			}
			
	     }
		
		return checkedRowIDs;
	}
	
	private List<Object[]> getDoubleIDRows(JTable dataTable){
		List<Object[]> checkedRowIDs = new ArrayList<Object[]>();
		
		for (int i = 0; i < dataTable.getRowCount(); i++) {

			if((boolean) dataTable.getValueAt(i, 0)) {
				checkedRowIDs.add(new Object[] {dataTable.getValueAt(i, 1), dataTable.getValueAt(i, 2)});
			}
			
	     }
		
		return checkedRowIDs;
	}
	
	@SuppressWarnings("serial")
	private JTable deleteTable(int selectedItem, JPanel contentPanel) {
		
		Select select = new Select(connect);
		JTable dataTable = null;
		
		Object[][] data = null;
		Object[] columnNames = null;
		
		switch(selectedItem) {
		
			case 0: {
				//Team
				Object[] specificColumns = {"", "ID", "Team Name", "Sponsor", "Date Founded"};
				data = select.selectTeam("", "", "", "");
				columnNames = specificColumns;
				break;
			}
			
			case 1: {
				//Player
				Object[] specificColumns = {"", "", "", "", "", "", "", ""};
				data = select.selectPlayer("", "", "", "", "", "", "");
				columnNames = specificColumns;
				break;
			}
			
			case 2: {
				//Gear
				Object[] specificColumns = {"", "", "", "", "", ""};
				data = select.selectGear("", "", "", "", "");
				columnNames = specificColumns;
				break;
			}
			
			case 3: {
				//Match
				Object[] specificColumns = {"", "", "", "", ""};
				data = select.selectMatch("", "", "", "");
				columnNames = specificColumns;
				break;
			}
			
			case 4: {
				//Org
				Object[] specificColumns = {"", "", "", "", ""};
				data = select.selectMatchOrganization("", "", "", "");
				columnNames = specificColumns;
				break;
			}
			
			case 5: {
				//Event
				Object[] specificColumns = {"", "", "", "", "", ""};
				data = select.selectEvent("", "", "", "", "");
				columnNames = specificColumns;
				break;
			}
			
			case 6: {
				//Uses
				// TODO: make table scrollable
				Object[] specificColumns = {"", "Player", "Gear", "Since"};
				data = select.selectUses("", "", "");
				columnNames = specificColumns;
				break;
			}
			
			case 7: {
				//Has
				Object[] specificColumns = {"", "", ""};
				data = select.selectHas("", "");
				columnNames = specificColumns;
				break;
			}
			
			case 8: {
				//Held
				Object[] specificColumns = {"", "", ""};
				data = select.selectHeld("", "");
				columnNames = specificColumns;
				break;
			}
			
			case 9: {
				//ParticipatesIn
				Object[] specificColumns = {"", "", "", ""};
				data = select.selectParticipateIn("", "", "");
				columnNames = specificColumns;
				break;
			}
			
			case 10: {
				//PlacedIn
				Object[] specificColumns = {"", "", "", ""};
				data = select.selectPlacedIn("", "", "");
				columnNames = specificColumns;
				break;
			}
			
			case 11: {
				//PlayedOn
				Object[] specificColumns = {"", "", ""};
				data = select.selectPlayedOn("", "");
				columnNames = specificColumns;
				break;
			}
			
			case 12: {
				//PlaysFor
				Object[] specificColumns = {"", "", ""};
				data = select.selectPlaysFor("", "");
				columnNames = specificColumns;
				break;
			}
		
		}
		
		if(data == null) {
			Object[] specificColumns = {"Error"};
			columnNames = specificColumns;
			Object[][] noData = {{"No Data Available"}};
			data = noData;
		}
		
		Object[][] withExtraColumn = new Object[data.length][data[0].length + 1];
		
		for(int i = 0; i < data.length; i++) {
			withExtraColumn[i][0] = false;
			for(int j = 1; j <= data[0].length; j++) {
				withExtraColumn[i][j] = data[i][j - 1];
			}
		}
		
		dataTable = new JTable(withExtraColumn, columnNames) {
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int column) {
				if(column == 0) {
					return Boolean.class;
				}
				
				return String.class;
			}
			
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex){
				return columnIndex == 0;
			}
			
		};
		
		dataTable.setPreferredScrollableViewportSize(dataTable.getPreferredSize());
		contentPanel.add(dataTable, BorderLayout.CENTER);
		
		return dataTable;
	}

	private void updatePage() {
		
		// set up frame and add standard buttons
		JFrame frame = new JFrame("Add Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(600,600));
		JPanel btnPanel = getButtonPanel(frame);
		frame.add(BorderLayout.SOUTH, btnPanel);
		
		// set up pull down menu panel and add label
		JPanel addPDpanel = new JPanel();
	    frame.add(BorderLayout.NORTH, addPDpanel);
		JLabel addLabel = new JLabel("Select something to add: ");
		addLabel.setBounds(20, 10, 20, 20);
		addLabel.setVisible(true);
		addPDpanel.add(addLabel);
		addPDpanel.setLocation(100, 100);

		// add pull down menu
        String[] choices = { "Team", "Player", "Gear", "Match", "Match Organization", 
        					"Event", "Player Uses Gear", "Event Has a Match", 
        					"Event Held by Organization", "Team in Match", 
        					"Team Placed In Event", "Player Played in a Match", 
        					"Player Plays For a Team"};
        cb = new JComboBox<String>(choices);
        cb.setSelectedIndex(selectedIndex);
        cb.setVisible(true);
	    addPDpanel.add(cb);
	    frame.pack();
		frame.setVisible(true);
		
		OKbtn = new JButton("OK");
	    addPDpanel.add(OKbtn);

		JLabel resultLabel = new JLabel("");
		addPDpanel.add(resultLabel);
		
	    OKbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySelectedChoice(resultLabel);
                // TODO: Code for what to do when table is selected
            }
        });
		
		// TODO Code for Update Page
	}
}
