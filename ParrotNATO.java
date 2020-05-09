import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class ParrotNATO {

	public static void main(String[] args) {
		
		String correct = "Correct";
		// Frame initialization
		JFrame frame = new JFrame("ParrotNATO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		
		// Menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu settings = new JMenu("Settings");
		JMenuItem settingsOption1 = new JMenuItem("Setting 1");
		settings.add(settingsOption1);
		menuBar.add(settings);
		
		// Input Panel
		JPanel panel = new JPanel();
        JLabel textLabel = new JLabel("Answer:");
        JTextField inputField = new JTextField(10);
        JButton submitButton = new JButton("Check");
        
        KeyAdapter uppercaseConverter = new KeyAdapter() {
        	
        	public void keyTyped(KeyEvent e) {
        		
        		if (Character.isLowerCase(e.getKeyChar())) {
        			
        			e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
        			
        		}
        	  }
        	
		};
        
        Action submitAnswer = new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				inputField.setText("");
				
			}
		};
		
		inputField.addKeyListener(uppercaseConverter);
		inputField.addActionListener(submitAnswer);
		submitButton.addActionListener(submitAnswer);
        panel.add(textLabel); 
        panel.add(inputField);
        panel.add(submitButton);
		
		frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setVisible(true);
	}

}
