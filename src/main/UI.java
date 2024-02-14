package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class UI {
	
	private Connect connect;
	
	// pull down for "add" 
	private JComboBox<String> cb;
    private JButton OKbtn;
    
    private int selectedIndex;    
    
    private final int FRAME_WIDTH = 900;
    private final int FRAME_HEIGHT = 900;
    
    private String userPerms;
    
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
		frame.dispose();
		frame.setVisible(false);
		switch (newPage) {
			case "Main": {
				mainPage();
				break;
			}
			
			case "Add": {
				addPage();
				break;
			}
			
			case "Delete": {
				deletePage();
				break;
			}
			
			case "Update": {
				updatePage();
				break;
			}
			
			case "Login": {
				loginPage();
				break;
			}
			
			case "Admin": {
				adminPage();
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
					userPerms = log.getPerms();
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

		if(userPerms.contains("r")) {
			JButton mainButton = new JButton("Main");
			btnPanel.add(mainButton, BorderLayout.CENTER);
			mainButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gotoPage(frame, "Main");
				}
			});
		}
		
		if(userPerms.contains("w")) {
			JButton addButton = new JButton("Add");
			btnPanel.add(addButton, BorderLayout.CENTER);
			addButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gotoPage(frame, "Add");
				}
			});
		}

		if(userPerms.contains("w")) {
			JButton updateButton = new JButton("Update");
			btnPanel.add(updateButton);
			updateButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gotoPage(frame, "Update");
				}
			});
		}

		if(userPerms.contains("d")) {
			JButton deleteButton = new JButton("Delete");
			btnPanel.add(deleteButton, BorderLayout.CENTER);
			deleteButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gotoPage(frame, "Delete");
				}
			});
		}
		
		if(userPerms.contains("a")) {
			JButton adminButton = new JButton("Admin");
			btnPanel.add(adminButton, BorderLayout.CENTER);
			adminButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gotoPage(frame, "Admin");
				}
			});
		}
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
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
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
							"Event Held by Organization", "Player in Match", 
							"Team Placed In Event", "Team Played in a Match", 
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
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 1: {
				//Player
				Object[] columnNames = {"ID", "Nation", "Player Name", "Username", "DOB", "Experience", "Role"};
				Object[][] data = select.selectPlayer("", "", "", "", "", "", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 2: {
				//Gear
				Object[] columnNames = {"Model Number", "Manufacturer", "Price", "Link", "Type"};
				Object[][] data = select.selectGear("", "", "", "", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 3: {
				//Match
				Object[] columnNames = {"ID", "Time", "Score", "Watch Hours"};
				Object[][] data = select.selectMatch("", "", "", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 4: {
				//Org
				Object[] columnNames = {"ID", "Contact Email", "Sponsor", "Organization Name"};
				Object[][] data = select.selectMatchOrganization("", "", "", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 5: {
				//Event
				Object[] columnNames = {"ID", "Live Link", "Location", "Game Name", "Event Name"};
				Object[][] data = select.selectEvent("", "", "", "", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 6: {
				//Uses
				Object[] columnNames = {"Player", "Gear", "Since"};
				Object[][] data = select.selectUses("", "", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 7: {
				//Has
				Object[] columnNames = {"Event ID", "Match ID"};
				Object[][] data = select.selectHas("", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 8: {
				//Held
				Object[] columnNames = {"Organization ID", "Event ID"};
				Object[][] data = select.selectHeld("", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 9: {
				//ParticipatesIn
				Object[] columnNames = {"Player ID", "Match ID", "Stats / Notes"};
				Object[][] data = select.selectParticipateIn("", "", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 10: {
				//PlacedIn
				Object[] columnNames = {"Team ID", "Event ID", "Rank"};
				Object[][] data = select.selectPlacedIn("", "", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 11: {
				//PlayedOn
				Object[] columnNames = {"Team ID", "Match ID"};
				Object[][] data = select.selectPlayedOn("", "");
				dataTable = new JTable(data, columnNames);
				break;
			}
			
			case 12: {
				//PlaysFor
				Object[] columnNames = {"Player ID", "Team ID"};
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
		
		dataTable.setPreferredScrollableViewportSize(new Dimension(FRAME_WIDTH - 150, FRAME_HEIGHT - 150));
        dataTable.setFillsViewportHeight(true);
		dataTable.setEnabled(false);
		JScrollPane js = new JScrollPane(dataTable);
		contentPanel.add(js);
	}

 	private void addPage() {
		Add a = new Add(connect);
		
		// set up frame and add standard buttons
		JFrame frame = new JFrame("Add Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		JPanel btnPanel = getButtonPanel(frame);
		frame.add(BorderLayout.SOUTH, btnPanel);
		frame.setVisible(true);
		
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
        					"Event Held by Organization", "Player in Match", 
        					"Team Placed In Event", "Team Played in a Match", 
        					"Player Plays For a Team"};
        cb = new JComboBox<String>(choices);
        cb.setSelectedIndex(selectedIndex);
        cb.setVisible(true);
	    addPDpanel.add(cb);
	    
	    JPanel inputPanel = new JPanel();
	    inputPanel.setMaximumSize(new Dimension(500, 800)); // Increase the height
	    inputPanel.setLayout(new BorderLayout());
	    inputPanel.setVisible(false);
		
	    
	    JPanel infoPanel1 = new JPanel();
	    infoPanel1.setLayout(new BoxLayout(infoPanel1, BoxLayout.Y_AXIS));
	    frame.add(infoPanel1, BorderLayout.CENTER);
	    infoPanel1.setVisible(true);
	    
	    // Create multiple JTextFields
        JTextField textField1 = new JTextField(5);
        JTextField textField2 = new JTextField(5);
        JTextField textField3 = new JTextField(5);
        JTextField textField4 = new JTextField(5);
        JTextField textField5 = new JTextField(5);
        JTextField textField6 = new JTextField(5);
        
        JLabel label1 = new JLabel("Text Field 1:");
        JLabel label2 = new JLabel("Text Field 2:");
        JLabel label3 = new JLabel("Text Field 3:");
        JLabel label4 = new JLabel("Text Field 4:");
        JLabel label5 = new JLabel("Text Field 5:");
        JLabel label6 = new JLabel("Text Field 6:");

        // Add JTextFields to the JPanel
        
        infoPanel1.add(label1);
        infoPanel1.add(textField1);
        infoPanel1.add(label2);
        infoPanel1.add(textField2);
        infoPanel1.add(label3);
        infoPanel1.add(textField3);
        infoPanel1.add(label4);
        infoPanel1.add(textField4);
        infoPanel1.add(label5);
        infoPanel1.add(textField5);
        infoPanel1.add(label6);
        infoPanel1.add(textField6);

        
        textField1.setVisible(false);
		textField2.setVisible(false);
		textField3.setVisible(false);
	    textField4.setVisible(false);
		textField5.setVisible(false);
		textField6.setVisible(false);
		label1.setVisible(false);
		label2.setVisible(false);
		label3.setVisible(false);
		label4.setVisible(false);
		label5.setVisible(false);
		label6.setVisible(false);
        
        
	    JButton submit = new JButton("Submit");
	    inputPanel.add(submit, BorderLayout.EAST);
	    frame.add(inputPanel, BorderLayout.EAST);
	    
	    
	    submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				switch(cb.getSelectedIndex()) {
				
					case 0: {
						//Team
						a.addTeam(textField1.getText(), textField2.getText(), textField3.getText());
						break;
					}
					
					case 1: {
						//Player
						a.addPlayer(textField1.getText(), textField2.getText(), textField3.getText(),
								textField4.getText(), textField5.getText(), textField6.getText());
						break;
					}
					
					case 2: {
						//Gear
						a.addGear(textField1.getText(), textField2.getText(), textField3.getText(),
								textField4.getText(), textField5.getText());
						
						break;
					}
					
					case 3: {
						//Match
						a.addMatch(textField1.getText(), textField2.getText(), textField3.getText());
						
						break;
					}
					
					case 4: {
						//Org
						a.addOrg(textField1.getText(), textField2.getText(), textField3.getText());
						
						break;
					}
					
					case 5: {
						//Event
						a.addEvent(textField1.getText(), textField2.getText(), textField3.getText(),
								textField4.getText());
						
						break;
					}
					
					case 6: {
						//Uses
						a.addUses(textField1.getText(), textField2.getText(), textField3.getText());
						
						break;
					}
					
					case 7: {
						//Has
						a.addHas(textField1.getText(), textField2.getText());
						
						break;
					}
					
					case 8: {
						//Held
						a.addHeld(textField1.getText(), textField2.getText());
						
						break;
					}
					
					case 9: {
						//ParticipatesIn
						a.addParticipateIn(textField1.getText(), textField2.getText(), textField3.getText());
						break;
					}
					
					case 10: {
						//PlacedIn
						a.addPlacedIn(textField1.getText(), textField2.getText(), textField3.getText());
						break;
					}
					
					case 11: {
						//PlayedOn
						a.addPlayedOn(textField1.getText(), textField2.getText());
						break;
					}
					
					case 12: {
						//PlaysFor
						a.addPlaysFor(textField1.getText(), textField2.getText());
						break;
					}
				
					
				}
			}
	    });
	    
	    
	    OKbtn = new JButton("OK");
	    OKbtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				switch(cb.getSelectedIndex()) {
				
					case 0: {
						//Team
						label1.setText("Team Name: ");
						label2.setText("Sponser Name: ");
						label3.setText("Date Found: ");
						textField1.setVisible(true);
						textField2.setVisible(true);
						textField3.setVisible(true);
						textField4.setVisible(false);
						textField5.setVisible(false);
						textField6.setVisible(false);
						label1.setVisible(true);
						label2.setVisible(true);
						label3.setVisible(true);
						label4.setVisible(false);
						label5.setVisible(false);
						label6.setVisible(false);
						System.out.println("Add Team");
						break;
					}
					
					case 1: {
						//Player
						label1.setText("Nation: ");
						label2.setText("Player Name: ");
						label3.setText("Username: ");
						label4.setText("Bate of Birth: ");
						label5.setText("Experience: ");
						label6.setText("Role: ");
						textField1.setVisible(true);
						textField2.setVisible(true);
						textField3.setVisible(true);
						textField4.setVisible(true);
						textField5.setVisible(true);
						textField6.setVisible(true);
						label1.setVisible(true);
						label2.setVisible(true);
						label3.setVisible(true);
						label4.setVisible(true);
						label5.setVisible(true);
						label6.setVisible(true);
						System.out.println("Add Player");
						break;
					}
					
					case 2: {
						//Gear
						label1.setText("Model Number: ");
						label2.setText("Manufature: ");
						label3.setText("Starting Price: ");
						label4.setText("Link: ");
						label5.setText("Type: ");
						
						textField1.setVisible(true);
						textField2.setVisible(true);
						textField3.setVisible(true);
						textField4.setVisible(true);
						textField5.setVisible(true);
						textField6.setVisible(false);
						label1.setVisible(true);
						label2.setVisible(true);
						label3.setVisible(true);
						label4.setVisible(true);
						label5.setVisible(true);
						label6.setVisible(false);
						System.out.println("Add Gear");
						break;
					}
					
					case 3: {
						//Match
						label1.setText("Date and Time: ");
						label2.setText("Score: ");
						label3.setText("Watching Hours: ");
						
						textField1.setVisible(true);
						textField2.setVisible(true);
						textField3.setVisible(true);
						textField4.setVisible(false);
						textField5.setVisible(false);
						textField6.setVisible(false);
						label1.setVisible(true);
						label2.setVisible(true);
						label3.setVisible(true);
						label4.setVisible(false);
						label5.setVisible(false);
						label6.setVisible(false);
						System.out.println("Add Match");
						break;
					}
					
					case 4: {
						//Org
						label1.setText("Contact Info: ");
						label2.setText("Sponsor Name: ");
						label3.setText("Organization Name: ");
						
						textField1.setVisible(true);
						textField2.setVisible(true);
						textField3.setVisible(true);
						textField4.setVisible(false);
						textField5.setVisible(false);
						textField6.setVisible(false);
						label1.setVisible(true);
						label2.setVisible(true);
						label3.setVisible(true);
						label4.setVisible(false);
						label5.setVisible(false);
						label6.setVisible(false);
						System.out.println("Add Organization");
						break;
					}
					
					case 5: {
						//Event
						label1.setText("Online Live Address: ");
						label2.setText("Location: ");
						label3.setText("Game Name: ");
						label4.setText("Event Name: ");
						
						textField1.setVisible(true);
						textField2.setVisible(true);
						textField3.setVisible(true);
						textField4.setVisible(true);
						textField5.setVisible(false);
						textField6.setVisible(false);
						label1.setVisible(true);
						label2.setVisible(true);
						label3.setVisible(true);
						label4.setVisible(true);
						label5.setVisible(false);
						label6.setVisible(false);
						System.out.println("Add Event");
						break;
					}
					
					case 6: {
						//Uses
						label1.setText("Player ID: ");
						label2.setText("Gear: ");
						label3.setText("Since: ");
						
						textField1.setVisible(true);
						textField2.setVisible(true);
						textField3.setVisible(true);
						textField4.setVisible(false);
						textField5.setVisible(false);
						textField6.setVisible(false);
						label1.setVisible(true);
						label2.setVisible(true);
						label3.setVisible(true);
						label4.setVisible(false);
						label5.setVisible(false);
						label6.setVisible(false);
						System.out.println("Add Uses");
						break;
					}
					
					case 7: {
						//Has
						label1.setText("Event ID: ");
						label2.setText("MatchID: ");
						
						
						textField1.setVisible(true);
						textField2.setVisible(true);
						textField3.setVisible(false);
						textField4.setVisible(false);
						textField5.setVisible(false);
						textField6.setVisible(false);
						label1.setVisible(true);
						label2.setVisible(true);
						label3.setVisible(false);
						label4.setVisible(false);
						label5.setVisible(false);
						label6.setVisible(false);
						System.out.println("Add Has");
						break;
					}
					
					case 8: {
						//Held
						label1.setText("Match Organization ID: ");
						label2.setText("Event ID: ");
						
						textField1.setVisible(true);
						textField2.setVisible(true);
						textField3.setVisible(false);
						textField4.setVisible(false);
						textField5.setVisible(false);
						textField6.setVisible(false);
						label1.setVisible(true);
						label2.setVisible(true);
						label3.setVisible(false);
						label4.setVisible(false);
						label5.setVisible(false);
						label6.setVisible(false);
						System.out.println("Add Held");
						break;
					}
					
					case 9: {
						//ParticipateIn
						label1.setText("PlayerID: ");
						label2.setText("MatchID: ");
						label3.setText("Statistics: ");
						
						textField1.setVisible(true);
						textField2.setVisible(true);
						textField3.setVisible(true);
						textField4.setVisible(false);
						textField5.setVisible(false);
						textField6.setVisible(false);
						label1.setVisible(true);
						label2.setVisible(true);
						label3.setVisible(true);
						label4.setVisible(false);
						label5.setVisible(false);
						label6.setVisible(false);
						System.out.println("Add ParticipateIn");
						break;
					}
					
					case 10: {
						//PlacedIn
						label1.setText("Team ID: ");
						label2.setText("Event ID: ");
						label3.setText("Rank: ");
						
						textField1.setVisible(true);
						textField2.setVisible(true);
						textField3.setVisible(true);
						textField4.setVisible(false);
						textField5.setVisible(false);
						textField6.setVisible(false);
						label1.setVisible(true);
						label2.setVisible(true);
						label3.setVisible(true);
						label4.setVisible(false);
						label5.setVisible(false);
						label6.setVisible(false);
						System.out.println("Add PlacedIn");
						break;
					}
					
					case 11: {
						//PlayedOn
						label1.setText("Team ID: ");
						label2.setText("Match ID: ");
						
						textField1.setVisible(true);
						textField2.setVisible(true);
						textField3.setVisible(false);
						textField4.setVisible(false);
						textField5.setVisible(false);
						textField6.setVisible(false);
						label1.setVisible(true);
						label2.setVisible(true);
						label3.setVisible(false);
						label4.setVisible(false);
						label5.setVisible(false);
						label6.setVisible(false);
						System.out.println("Add PlayedOn");
						break;
					}
					
					case 12: {
						//PlaysFor
						label1.setText("Player ID: ");
						label2.setText("Team ID: ");
						
						textField1.setVisible(true);
						textField2.setVisible(true);
						textField3.setVisible(false);
						textField4.setVisible(false);
						textField5.setVisible(false);
						textField6.setVisible(false);
						label1.setVisible(true);
						label2.setVisible(true);
						label3.setVisible(false);
						label4.setVisible(false);
						label5.setVisible(false);
						label6.setVisible(false);
						System.out.println("Add PlaysFor");
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
	
	private <T> void set (T pointer, T newTable) {
		pointer = newTable;
	}
	
	private void deletePage() {
		
		Delete d = new Delete(connect);
		
		// set up frame and add standard buttons
		JFrame frame = new JFrame("Delete Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
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
        					"Event Held by Organization", "Player in Match", 
        					"Team Placed In Event", "Team Played in a Match", 
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
				Object[] specificColumns = {"Select", "ID", "Team Name", "Sponsor", "Date Founded"};
				data = select.selectTeam("", "", "", "");
				columnNames = specificColumns;
				break;
			}
			
			case 1: {
				//Player
				Object[] specificColumns = {"Select", "ID", "Nation", "Player Name", "Username", "DOB", "Experience", "Role"};
				data = select.selectPlayer("", "", "", "", "", "", "");
				columnNames = specificColumns;
				break;
			}
			
			case 2: {
				//Gear
				Object[] specificColumns = {"Select", "Model Number", "Manufacturer", "Price", "Link", "Type"};
				data = select.selectGear("", "", "", "", "");
				columnNames = specificColumns;
				break;
			}
			
			case 3: {
				//Match
				Object[] specificColumns = {"Select", "ID", "Time", "Score", "Watch Hours"};
				data = select.selectMatch("", "", "", "");
				columnNames = specificColumns;
				break;
			}
			
			case 4: {
				//Org
				Object[] specificColumns = {"Select", "ID", "Contact Email", "Sponsor", "Organization Name"};
				data = select.selectMatchOrganization("", "", "", "");
				columnNames = specificColumns;
				break;
			}
			
			case 5: {
				//Event
				Object[] specificColumns = {"Select", "ID", "Live Link", "Location", "Game Name", "Event Name"};
				data = select.selectEvent("", "", "", "", "");
				columnNames = specificColumns;
				break;
			}
			
			case 6: {
				//Uses
				Object[] specificColumns = {"Select", "Player", "Gear", "Since"};
				data = select.selectUses("", "", "");
				columnNames = specificColumns;
				break;
			}
			
			case 7: {
				//Has
				Object[] specificColumns = {"Select", "Event ID", "Match ID"};
				data = select.selectHas("", "");
				columnNames = specificColumns;
				break;
			}
			
			case 8: {
				//Held
				Object[] specificColumns = {"Select", "Organization ID", "Event ID"};
				data = select.selectHeld("", "");
				columnNames = specificColumns;
				break;
			}
			
			case 9: {
				//ParticipatesIn
				Object[] specificColumns = {"Select", "Player ID", "Match ID", "Stats / Notes"};
				data = select.selectParticipateIn("", "", "");
				columnNames = specificColumns;
				break;
			}
			
			case 10: {
				//PlacedIn
				Object[] specificColumns = {"Select", "Team ID", "Event ID", "Rank"};
				data = select.selectPlacedIn("", "", "");
				columnNames = specificColumns;
				break;
			}
			
			case 11: {
				//PlayedOn
				Object[] specificColumns = {"Select", "Team ID", "Match ID"};
				data = select.selectPlayedOn("", "");
				columnNames = specificColumns;
				break;
			}
			
			case 12: {
				//PlaysFor
				Object[] specificColumns = {"Select", "Player ID", "Team ID"};
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
		
		dataTable.setPreferredScrollableViewportSize(new Dimension(FRAME_WIDTH - 150, FRAME_HEIGHT - 150));
    	dataTable.setFillsViewportHeight(true);
		JScrollPane js = new JScrollPane(dataTable);
		contentPanel.add(js);
		
		return dataTable;
	}

	private void updatePage() {
		
		// set up frame and add standard buttons
		JFrame frame = new JFrame("Update Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		JPanel btnPanel = getButtonPanel(frame);
		frame.add(BorderLayout.SOUTH, btnPanel);
		
		// set up pull down menu panel and add label
		JPanel addPDpanel = new JPanel();
	    frame.add(BorderLayout.NORTH, addPDpanel);
		JLabel addLabel = new JLabel("Select something to update: ");
		addLabel.setBounds(20, 10, 20, 20);
		addLabel.setVisible(true);
		addPDpanel.add(addLabel);
		addPDpanel.setLocation(100, 100);

		// add pull down menu
        String[] choices = { "Team", "Player", "Gear", "Match", "Match Organization", 
        					"Event", "Player Uses Gear", "Player in Match", "Team Placed In Event"
		};
        cb = new JComboBox<String>(choices);
		if (selectedIndex > 8) {
			selectedIndex = 8;
		}
        cb.setSelectedIndex(selectedIndex);
        cb.setVisible(true);
	    addPDpanel.add(cb);
	    frame.pack();
		frame.setVisible(true);
		
		OKbtn = new JButton("OK");
	    addPDpanel.add(OKbtn);

		final JPanel[] contentPanel = {new JPanel()};
		updateTable(cb.getSelectedIndex(), contentPanel[0]);
		frame.add(contentPanel[0], BorderLayout.CENTER);

		JLabel resultLabel = new JLabel("");
		addPDpanel.add(resultLabel);
		
	    OKbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				displaySelectedChoice(resultLabel);
				frame.remove(contentPanel[0]);
				contentPanel[0] = new JPanel();
				frame.add(contentPanel[0]);
				updateTable(cb.getSelectedIndex(), contentPanel[0]);
			}
        });
	}
	
	private void updateTable(int selectedItem, JPanel contentPanel) {

		Select select = new Select(connect);
		Update update = new Update(connect);
		JTable dataTable = null;

		switch(selectedItem) {

			case 0: {
				//Team
				Object[] columnNames = {"ID", "Team Name", "Sponsor", "Date Founded"};
				Object[][] data = select.selectTeam("", "", "", "");
				dataTable = getUpdateTable(data, columnNames, 1);
				TableModel model = dataTable.getModel();
				model.addTableModelListener(new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent e) {
						if (e.getType() == TableModelEvent.UPDATE) {
							int row = e.getFirstRow();
							String id = model.getValueAt(row, 0).toString();
							String teamname = model.getValueAt(row, 1).toString();
							String sponsor = model.getValueAt(row, 2).toString();
							String dateFound = model.getValueAt(row, 3).toString();
							update.updateTeam(id, teamname, sponsor, dateFound);
						}
					}
				});
				break;
			}

			case 1: {
				//Player
				Object[] columnNames = {"", "", "", "", "", "", ""};
				Object[][] data = select.selectPlayer("", "", "", "", "", "", "");
				dataTable = getUpdateTable(data, columnNames, 1);
				TableModel model = dataTable.getModel();
				model.addTableModelListener(new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent e) {
						if (e.getType() == TableModelEvent.UPDATE) {
							int row = e.getFirstRow();
							String id = model.getValueAt(row, 0).toString();
							String nation = model.getValueAt(row, 1).toString();
							String playerName = model.getValueAt(row, 2).toString();
							String username = model.getValueAt(row, 3).toString();
							String dob = model.getValueAt(row, 4).toString();
							String experience = model.getValueAt(row, 5).toString();
							String role = model.getValueAt(row, 6).toString();
							update.updatePlayer(id, nation, playerName, username, dob, experience, role);
						}
					}
				});
				break;
			}

			case 2: {
				//Gear
				Object[] columnNames = {"", "", "", "", ""};
				Object[][] data = select.selectGear("", "", "", "", "");
					dataTable = getUpdateTable(data, columnNames, 1);
					TableModel model = dataTable.getModel();
					model.addTableModelListener(new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent e) {
						if (e.getType() == TableModelEvent.UPDATE) {
							int row = e.getFirstRow();
							String modelNumber = model.getValueAt(row, 0).toString();
							String manf = model.getValueAt(row, 1).toString();
							String startingPrice = model.getValueAt(row, 2).toString();
							String link = model.getValueAt(row, 3).toString();
							String type = model.getValueAt(row, 4).toString();
							update.updateGear(modelNumber, manf, type, startingPrice, link);
						}
					}
				});
				break;
			}

			case 3: {
				//Match
				Object[] columnNames = {"", "", "", ""};
				Object[][] data = select.selectMatch("", "", "", "");
				dataTable = getUpdateTable(data, columnNames, 1);
				TableModel model = dataTable.getModel();
				model.addTableModelListener(new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent e) {
						if (e.getType() == TableModelEvent.UPDATE) {
							int row = e.getFirstRow();
							String id = model.getValueAt(row, 0).toString();
							String dateandtime = model.getValueAt(row, 1).toString();
							String score = model.getValueAt(row, 2).toString();
							String watchinghours = model.getValueAt(row, 3).toString();
							update.updateMatch(id, dateandtime, score, watchinghours);
						}
					}
				});
				break;
			}

			case 4: {
				//Org
				Object[] columnNames = {"", "", "", ""};
				Object[][] data = select.selectMatchOrganization("", "", "", "");
				dataTable = getUpdateTable(data, columnNames, 1);
				TableModel model = dataTable.getModel();
				model.addTableModelListener(new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent e) {
						if (e.getType() == TableModelEvent.UPDATE) {
							int row = e.getFirstRow();
							String id = model.getValueAt(row, 0).toString();
							String contact = model.getValueAt(row, 1).toString();
							String sponsor = model.getValueAt(row, 2).toString();
							String organizationname = model.getValueAt(row, 3).toString();
							update.updateMatchOrganization(id, contact, sponsor, organizationname);
						}
					}
				});
				break;
			}

			case 5: {
				//Event
				Object[] columnNames = {"", "", "", "", ""};
				Object[][] data = select.selectEvent("", "", "", "", "");
				dataTable = getUpdateTable(data, columnNames, 1);
				TableModel model = dataTable.getModel();
				model.addTableModelListener(new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent e) {
						if (e.getType() == TableModelEvent.UPDATE) {
							int row = e.getFirstRow();
							String id = model.getValueAt(row, 0).toString();
							String eventname = model.getValueAt(row, 1).toString();
							String gamename = model.getValueAt(row, 2).toString();
							String location = model.getValueAt(row, 3).toString();
							String onlineliveaddress = model.getValueAt(row, 4).toString();
							update.updateEvent(id, eventname, gamename, location, onlineliveaddress);
						}
					}
				});
				break;
			}

			case 6: {
				//Uses
				Object[] columnNames = {"Player", "Gear", "Since"};
				Object[][] data = select.selectUses("", "", "");
				dataTable = getUpdateTable(data, columnNames, 2);
				TableModel model = dataTable.getModel();
				model.addTableModelListener(new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent e) {
						if (e.getType() == TableModelEvent.UPDATE) {
							int row = e.getFirstRow();
							String playerid = model.getValueAt(row, 0).toString();
							String gear = model.getValueAt(row, 1).toString();
							String since = model.getValueAt(row, 2).toString();
							update.updateUses(playerid, gear, since);
						}
					}
				});
				break;
			}
			case 7: {
				//ParticipateIn
				Object[] columnNames = {"", "", ""};
				Object[][] data = select.selectParticipateIn("", "", "");
				dataTable = getUpdateTable(data, columnNames, 2);
				TableModel model = dataTable.getModel();
				model.addTableModelListener(new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent e) {
						if (e.getType() == TableModelEvent.UPDATE) {
							int row = e.getFirstRow();
							String playerid = model.getValueAt(row, 0).toString();
							String matchid = model.getValueAt(row, 1).toString();
							String stats = model.getValueAt(row, 2).toString();
							update.updateParticipateIn(playerid, matchid, stats);
						}
					}
				});
				break;
			}
			case 8: {
				//PlacedIn
				Object[] columnNames = {"", "", ""};
				Object[][] data = select.selectPlacedIn("", "", "");
				dataTable = getUpdateTable(data, columnNames, 2);
				TableModel model = dataTable.getModel();
				model.addTableModelListener(new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent e) {
						if (e.getType() == TableModelEvent.UPDATE) {
							int row = e.getFirstRow();
							String teamid = model.getValueAt(row, 0).toString();
							String eventid = model.getValueAt(row, 1).toString();
							String rank = model.getValueAt(row, 2).toString();
							update.updatePlacedIn(teamid, eventid, rank);
						}
					}
				});
				break;
			}

		}

		if(dataTable == null) {
			Object[] columnNames = {"Error"};
			Object[][] data = {{"No Data Available"}};
			dataTable = new JTable(data, columnNames);
		}
		
		dataTable.setPreferredScrollableViewportSize(new Dimension(FRAME_WIDTH - 150, FRAME_HEIGHT - 150));
    	dataTable.setFillsViewportHeight(true);
		JScrollPane js = new JScrollPane(dataTable);
		contentPanel.add(js);
	}
	
	@SuppressWarnings("serial")
	private JTable getUpdateTable(Object[][] data, Object[] columnNames, int numKeys) {
		JTable dataTable = new JTable(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Make all cells editable
				return column >= numKeys;
			}

		};
		return dataTable;
	}
	
	private void adminPage() {
		Admin admin = new Admin(connect);
		
		// set up frame and add standard buttons
		JFrame frame = new JFrame("Manage Permissions");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		JPanel btnPanel = getButtonPanel(frame);
		frame.add(BorderLayout.SOUTH, btnPanel);
	    
		JPanel contentPanel = new JPanel();
		frame.add(contentPanel, BorderLayout.CENTER);
		
		JTable dataTable = adminTable();

		JScrollPane js = new JScrollPane(dataTable);
		contentPanel.add(js, BorderLayout.CENTER);
		
		// submit button for deletions
		JButton submit = new JButton("Delete Users");
		contentPanel.add(submit, BorderLayout.SOUTH);
		submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < dataTable.getRowCount(); i++) {
					if((boolean) dataTable.getValueAt(i, 0)) {
						admin.removeUser((String) dataTable.getValueAt(i,  1));
						js.setEnabled(false);
		                js.setVisible(false);
		                contentPanel.remove(js);
						set(dataTable, adminTable());

						set(js, new JScrollPane(dataTable));
						js.setVisible(true);
						js.setEnabled(true);
						contentPanel.add(js, BorderLayout.CENTER);
					}
			     }
			}
			
		});
		
	    frame.pack();
		frame.setVisible(true);
	}
	
	private JTable adminTable() {
		Admin admin = new Admin(connect);
		
		String[] columnNames = {"Select", "Username", "Read", "Write", "Delete", "Admin"};
		
		Object[][] data = admin.getPermsTableData();
		
		@SuppressWarnings("serial")
		JTable dataTable = new JTable(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Make all cells editable
				return column != 1;
			}
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int column) {
				return column == 1 ? String.class : Boolean.class;
			}
		};
		
		TableModel tableModel = dataTable.getModel();
		tableModel.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int row = e.getFirstRow();
					String username = tableModel.getValueAt(row, 1).toString();
					String newPerms = new String("");
					if((boolean) tableModel.getValueAt(row, 2)) {
						newPerms = newPerms.concat("r");
					}
					if((boolean) tableModel.getValueAt(row, 3)) {
						newPerms = newPerms.concat("w");
					}
					if((boolean) tableModel.getValueAt(row, 4)) {
						newPerms = newPerms.concat("d");
					}
					if((boolean) tableModel.getValueAt(row, 5)) {
						newPerms = newPerms.concat("a");
					}
					admin.updatePermsTable(username, newPerms);
				}
				
			}
			
		});
		
		dataTable.setPreferredScrollableViewportSize(new Dimension(FRAME_WIDTH - 150, FRAME_HEIGHT - 150));
    	dataTable.setFillsViewportHeight(true);
		
		return dataTable;
	}
}


