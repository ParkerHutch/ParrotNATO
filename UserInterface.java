import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserInterface {
	
	JFrame frame;
	JTextField inputField;
	JButton submitButton;
	
	public UserInterface(int w, int h) {
		
		// Frame initialization
		setFrame(new JFrame("ParrotNATO"));
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().setSize(w, h);
		
		// Menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu settings = new JMenu("Settings");
		JMenuItem settingsOption1 = new JMenuItem("Setting 1");
		settings.add(settingsOption1);
		menuBar.add(settings);
		
		getFrame().getContentPane().add(BorderLayout.NORTH, menuBar);
		getFrame().getContentPane().add(BorderLayout.SOUTH, getInputPanel());

	}
	
	private JPanel getInputPanel() {

		// Input Panel
		JPanel panel = new JPanel();
		JLabel textLabel = new JLabel("Answer:");
		setSubmitButton(createSubmitButton());
		setInputField(createInputField());
		
		panel.add(textLabel);
		panel.add(getInputField());
		panel.add(getSubmitButton());
		
		return panel;
		
	}
	
	private Action getSubmitAction() {
		
		return new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getInputField().setText("");

			}

		};
		
	}
	
	public JTextField createInputField() {
		
		JTextField inputField = new JTextField(10);
		
		KeyAdapter uppercaseConverter = new KeyAdapter() {

			public void keyTyped(KeyEvent e) {

				if (Character.isLowerCase(e.getKeyChar())) {

					e.setKeyChar(Character.toUpperCase(e.getKeyChar()));

				}
			}

		};
		
		inputField.addKeyListener(uppercaseConverter);
		
		return inputField;
		
	}
	
	public JButton createSubmitButton() {
		
		JButton submitButton = new JButton("Check");
		
		submitButton.addActionListener(getSubmitAction());
		
		return submitButton;
		
	}

	public JTextField getInputField() {
		return inputField;
	}

	public void setInputField(JTextField inputField) {
		this.inputField = inputField;
	}

	public JButton getSubmitButton() {
		return submitButton;
	}

	public void setSubmitButton(JButton submitButton) {
		this.submitButton = submitButton;
	}

	public JFrame getFrame() {
		
		return frame;
		
	}
	
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
}
