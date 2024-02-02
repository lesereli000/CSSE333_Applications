package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
		this.connect = connect;
		loginPage();
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
		JFrame frame = new JFrame("DeletePage");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);
		frame.setVisible(true);
		JPanel btnPanel = getButtonPanel(frame);
		frame.getContentPane().add(BorderLayout.SOUTH, btnPanel);
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
