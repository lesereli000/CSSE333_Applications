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
import javax.swing.JTextField;

public class UI {
	
	private Connect connect;
	
	
	// pull down for "add" 
	private JComboBox<String> cb;
    private JButton OKbtn;
    private JLabel resultLabel;

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
	public void mainPage() {
		//JOptionPane.showMessageDialog(null, "Login Success. Page not implemented yet.");
		// create the frame
		JFrame frame = new JFrame("Welcome to the EsportDataTracking App!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);
		
		// create panels
		JPanel btnPanel = getButtonPanel(frame);
		frame.getContentPane().add(BorderLayout.SOUTH, btnPanel);
		// literally no idea what this is for and it obscures some of the buttons so it has been commented out until further evaluation
		// frame.getContentPane().add(inputPanel);
		frame.setVisible(true);
		
		// add pull down menu
		JPanel addPDpanel = new JPanel();
	    frame.add(addPDpanel);
		JLabel addLabel = new JLabel("Add a new item in the table: ");
		addLabel.setBounds(20, 10, 20, 20);
		addLabel.setVisible(true);

        addPDpanel.add(addLabel);
		addPDpanel.setLocation(100, 100);
        String[] choices = { "Team","Player", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};
        cb = new JComboBox<String>(choices);
        cb.setVisible(true);
	    addPDpanel.add(cb);
	    
	    OKbtn = new JButton("OK");
	    addPDpanel.add(OKbtn);

		resultLabel = new JLabel();
		addPDpanel.add(resultLabel);
	    OKbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySelectedChoice();
            }
        });

		
	}
	
	public void deletePage() {
		Delete d = new Delete(connect);
		
		JFrame frame = new JFrame("Delete Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(600,600));
//		frame.setSize(600, 600);
		JPanel btnPanel = getButtonPanel(frame);
		frame.add(BorderLayout.SOUTH, btnPanel);
		
		// add pull down menu
		JPanel addPDpanel = new JPanel();
	    frame.add(BorderLayout.NORTH, addPDpanel);
		JLabel addLabel = new JLabel("Select something to delete: ");
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
	    
	    JPanel inputPanel = new JPanel();
	    inputPanel.setMaximumSize(new Dimension(500, 800)); // Increase the height
	    inputPanel.setLayout(new BorderLayout());
	    inputPanel.setVisible(false);
		
		JPanel infoPanel1 = new JPanel();
		infoPanel1.setLayout(new GridBagLayout()); // Use FlowLayout
		JLabel infoLabel1 = new JLabel("Team Name: ");
		infoPanel1.add(infoLabel1);

		JTextField info1 = new JTextField();
		info1.setPreferredSize(new Dimension(500, 25)); // Set preferred size
		infoPanel1.add(info1);

		inputPanel.add(infoPanel1, BorderLayout.NORTH);
	    
		JPanel infoPanel2 = new JPanel();
		infoPanel2.setLayout(new GridBagLayout()); // Use FlowLayout
		JLabel infoLabel2 = new JLabel("Team Name: ");
		infoPanel2.add(infoLabel2);

		JTextField info2 = new JTextField();
		info2.setPreferredSize(new Dimension(500, 25)); // Set preferred size
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
	
	private void displaySelectedChoice() {
        String selectedChoice = (String) cb.getSelectedItem();
        resultLabel.setText("Selected: " + selectedChoice);
    }
	
	private void gotoPage(JFrame frame, String selectedChoice) {
		switch (selectedChoice) {
			case "Main": {
				frame.dispose();
				frame.setVisible(false);
				mainPage();
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
