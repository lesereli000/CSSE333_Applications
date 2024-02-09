package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public UI(Connect connect) {
		new UI(connect, true);
	}
    
	public UI(Connect connect, boolean useLogin) {
		this.connect = connect;
		if(useLogin) {
			loginPage();
		} else {
			mainPage();
		}
	}
	
	public void loginPage() {
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
					frame.dispose();
					mainPage();
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
				
				//TODO: call add functions
			}
		});
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gotoPage(frame, "Delete");
				
				//TODO: call delete functions
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
	
	public void mainPage() {
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
							"Event Held by Organization", "Player in Event", 
							"Team Placed In Event", "Player Played in a Match", 
							"Player Plays For a Team"};
        cb = new JComboBox<String>(choices);
        cb.setVisible(true);
	    addPDpanel.add(cb);
	    
	    OKbtn = new JButton("OK");
	    addPDpanel.add(OKbtn);

		JLabel resultLabel = new JLabel("");
		addPDpanel.add(resultLabel);
		
		JPanel contentPanel = new JPanel();
		readTable(cb.getSelectedIndex(), contentPanel);
		frame.add(contentPanel, BorderLayout.CENTER);
		
	    OKbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySelectedChoice(resultLabel);
                frame.remove(contentPanel);
                JPanel contentPanel = new JPanel();
                frame.add(contentPanel);
                readTable(cb.getSelectedIndex(), contentPanel);
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
				if(data == null) {
					break;
				}
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
			// TODO: Display no data message
			Object[] columnNames = {};
			Object[][] data = {};
			dataTable = new JTable(data, columnNames);
		}
		
		dataTable.setEnabled(false);
		contentPanel.add(dataTable);
	}

	private void deleteTable(int selectedItem, JPanel contentPanel) {
		
		Select select = new Select(connect);
		JTable dataTable = null;
		
		switch(selectedItem) {
		
			case 0: {
				//Team
				Object[] columnNames = {"ID", "Team Name", "Sponsor", "Date Founded"};
				Object[][] data = select.selectTeam("", "", "", "");
				if(data == null) {
					break;
				}
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
			// TODO: Display no data message
			Object[] columnNames = {};
			Object[][] data = {};
			dataTable = new JTable(data, columnNames);
		}
		
		dataTable.setEnabled(false);
		contentPanel.add(dataTable);
	}
	
	private void displaySelectedChoice(JLabel resultLabel) {
        String selectedChoice = (String) cb.getSelectedItem();
        resultLabel.setText("Selected: " + selectedChoice);
    }
	
	
	
	
	public void addPage() {
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
        					"Event Held by Organization", "Player in Event", 
        					"Team Placed In Event", "Player Played in a Match", 
        					"Player Plays For a Team"};
        cb = new JComboBox<String>(choices);
        cb.setVisible(true);
	    addPDpanel.add(cb);
	    frame.pack();
		frame.setVisible(true);
	}
	
	public void deletePage() {
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
        					"Event Held by Organization", "Player in Event", 
        					"Team Placed In Event", "Player Played in a Match", 
        					"Player Plays For a Team"};
        cb = new JComboBox<String>(choices);
        cb.setVisible(true);
	    addPDpanel.add(cb);
	    
	    JPanel inputPanel = new JPanel();
	    inputPanel.setMaximumSize(new Dimension(500, 800));
	    inputPanel.setLayout(new BorderLayout());
	    inputPanel.setVisible(false);
		
		JPanel infoPanel1 = new JPanel();
		infoPanel1.setLayout(new GridBagLayout());
		JLabel infoLabel1 = new JLabel("Team Name: ");
		infoPanel1.add(infoLabel1);

		JTextField info1 = new JTextField();
		info1.setPreferredSize(new Dimension(500, 25));
		infoPanel1.add(info1);

		inputPanel.add(infoPanel1, BorderLayout.NORTH);
	    
		JPanel infoPanel2 = new JPanel();
		infoPanel2.setLayout(new GridBagLayout());
		JLabel infoLabel2 = new JLabel("Team Name: ");
		infoPanel2.add(infoLabel2);

		JTextField info2 = new JTextField();
		info2.setPreferredSize(new Dimension(500, 25));
		infoPanel2.add(info2);

		infoPanel2.setVisible(false);
		inputPanel.add(infoPanel2, BorderLayout.CENTER);
	    
	    JButton submit = new JButton("Submit");
	    submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				switch(cb.getSelectedIndex()) {
				
					case 0: {
						//Team
						d.deleteTeam(info1.getText());
						break;
					}
					
					case 1: {
						//Player
						d.deletePlayer(info1.getText());
						break;
					}
					
					case 2: {
						//Gear
						d.deleteGear(info1.getText());
						break;
					}
					
					case 3: {
						//Match
						d.deleteMatch(info1.getText());
						break;
					}
					
					case 4: {
						//Org
						d.deleteOrg(info1.getText());
						break;
					}
					
					case 5: {
						//Event
						d.deleteEvent(info1.getText());
						break;
					}
					
					case 6: {
						//Uses
						d.deleteUses(info1.getText(), info2.getText());
						break;
					}
					
					case 7: {
						//Has
						d.deleteHas(info1.getText(), info2.getText());
						break;
					}
					
					case 8: {
						//Held
						d.deleteHeld(info1.getText(), info2.getText());
						break;
					}
					
					case 9: {
						//ParticipatesIn
						d.deleteParticipateIn(info1.getText(), info2.getText());
						break;
					}
					
					case 10: {
						//PlacedIn
						d.deletePlacedIn(info1.getText(), info2.getText());
						break;
					}
					
					case 11: {
						//PlayedIn
						d.deletePlayedOn(info1.getText(), info2.getText());
						break;
					}
					
					case 12: {
						//PlaysFor
						d.deletePlaysFor(info1.getText(), info2.getText());
						break;
					}
				
				}
			}
	    });
	    
	    inputPanel.add(submit, BorderLayout.SOUTH);
	    frame.add(inputPanel, BorderLayout.CENTER);
	    
	    OKbtn = new JButton("OK");
	    OKbtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				switch(cb.getSelectedIndex()) {
				
					case 0: {
						//Team
						infoLabel1.setText("Team Name: ");
						break;
					}
					
					case 1: {
						//Player
						infoLabel1.setText("Player Name: ");
						break;
					}
					
					case 2: {
						//Gear
						infoLabel1.setText("Gear Model: ");
						break;
					}
					
					case 3: {
						//Match
						infoLabel1.setText("Match Time: ");
						break;
					}
					
					case 4: {
						//Org
						infoLabel1.setText("Org Name: ");
						break;
					}
					
					case 5: {
						//Event
						infoLabel1.setText("Event Name: ");
						break;
					}
					
					case 6: {
						//Uses
						infoLabel1.setText("Player Name: ");
						infoLabel2.setText("Gear Model: ");
						infoPanel2.setVisible(true);
						break;
					}
					
					case 7: {
						//Has
						infoLabel1.setText("Event Name: ");
						infoLabel2.setText("Match Time: ");
						infoPanel2.setVisible(true);
						break;
					}
					
					case 8: {
						//Held
						infoLabel1.setText("Event Name: ");
						infoLabel2.setText("Org Name: ");
						infoPanel2.setVisible(true);
						break;
					}
					
					case 9: {
						//ParticipatesIn
						infoLabel1.setText("Player Name: ");
						infoLabel2.setText("Event Name: ");
						infoPanel2.setVisible(true);
						break;
					}
					
					case 10: {
						//PlacedIn
						infoLabel1.setText("Team Name: ");
						infoLabel2.setText("Event Name: ");
						infoPanel2.setVisible(true);
						break;
					}
					
					case 11: {
						//PlayedIn
						infoLabel1.setText("Player Name: ");
						infoLabel2.setText("Match Time: ");
						infoPanel2.setVisible(true);
						break;
					}
					
					case 12: {
						//PlaysFor
						infoLabel1.setText("Player Name: ");
						infoLabel2.setText("Team Name: ");
						infoPanel2.setVisible(true);
						break;
					}
				
				}
				inputPanel.setVisible(true);
			}
	    	
	    });
	    addPDpanel.add(OKbtn);

	    frame.pack();
		frame.setVisible(true);
	}
	
	private void gotoPage(JFrame frame, String selectedChoice) {
		switch (selectedChoice) {
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

		}
	}
}
