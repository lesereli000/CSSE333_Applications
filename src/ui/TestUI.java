package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestUI {
	 private JFrame frame;
	    private JPanel panel;
	    private JButton button;
	    private JLabel label;

	    public TestUI() {
	        frame = new JFrame();
	        panel = new JPanel();
	        button = new JButton("Click me");
	        label = new JLabel("Button not clicked");

	        // Set up the frame
	        frame.setSize(300, 200);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.add(panel);

	        panel.setLayout(null);

	        // Set up the button
	        button.setBounds(100, 20, 100, 25);
	        button.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                label.setText("Button clicked!");
	            }
	        });
	        panel.add(button);

	        // Set up the label
	        label.setBounds(10, 50, 200, 25);
	        panel.add(label);

	        // Make the frame visible
	        frame.setVisible(true);
	    }
}
