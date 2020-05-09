import java.awt.BorderLayout;
import java.awt.Font;
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
	JLabel summaryLabel;
	JTextField inputField;
	JButton submitButton;
	JButton nextButton;
	
	String spokenString = "TEST";
	String evaluationString = "";
	
	public UserInterface(int w, int h) {

		// Frame initialization
		setFrame(new JFrame("ParrotNATO"));
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().setSize(w, h);

		getFrame().getContentPane().setLayout(new BorderLayout());
		
		setSummaryLabel(createSummaryLabel());
		setNextButton(createNextButton());
		
		getFrame().getContentPane().add(BorderLayout.NORTH, getMenuBar());
		getFrame().getContentPane().add(getSummaryLabel(), BorderLayout.CENTER);
		getFrame().getContentPane().add(getNextButton(), BorderLayout.EAST);
		getFrame().getContentPane().add(BorderLayout.SOUTH, getInputPanel());
		
		
	}

	private JButton createNextButton() {
		
		JButton nextButton = new JButton(">");
		
		Action nextAction = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Play next sound");
				getInputField().setText(""); // clear input field
				// TODO stop old sound, play new one
				
			}

		};
		
		nextButton.addActionListener(nextAction);
		return nextButton;
	}
	
	private JLabel createSummaryLabel() {
		
		JLabel summaryLabel = new JLabel("Press the > button to begin");
		summaryLabel.setHorizontalAlignment(JLabel.CENTER);
		summaryLabel.setFont(new Font("Consolas", Font.PLAIN, 32));
		return summaryLabel;
		
	}
	private String generateEvaluationString(String userInput, String spoken) {
		return "<html>ParrotNATO<br/>Spoken: "+ spoken + "<br/>Answer: " 
				+ userInput + "</html>";
		
	}
	
	private JMenuBar getMenuBar() {

		JMenuBar menuBar = new JMenuBar();
		JMenu settings = new JMenu("Settings");
		JMenuItem settingsOption1 = new JMenuItem("Setting 1");
		settings.add(settingsOption1);
		menuBar.add(settings);
		return menuBar;

	}

	private JPanel getInputPanel() {

		// Input Panel
		JPanel panel = new JPanel();
		JLabel textLabel = new JLabel("Answer:");
		textLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
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

				displayEvaluation(getInputField().getText(), getSpokenString());
				getInputField().setText(""); // Clear text field
				// TODO turn off sound
			}

		};

	}

	public void displayEvaluation(String userInput, String spoken) {

		getSummaryLabel().setText(generateEvaluationString(userInput, spoken));

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
		inputField.addActionListener(getSubmitAction());
		return inputField;

	}

	public JButton createSubmitButton() {

		JButton submitButton = new JButton("Check");
		submitButton.setFont(new Font("Consolas", Font.PLAIN, 14));
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

	public JLabel getSummaryLabel() {
		return summaryLabel;
	}

	public void setSummaryLabel(JLabel summaryLabel) {
		this.summaryLabel = summaryLabel;
	}

	public JButton getNextButton() {
		return nextButton;
	}

	public void setNextButton(JButton nextButton) {
		this.nextButton = nextButton;
	}

	public String getSpokenString() {
		return spokenString;
	}

	public void setSpokenString(String spokenString) {
		this.spokenString = spokenString;
	}

	public JFrame getFrame() {

		return frame;

	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
