import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

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
	JButton playAgainButton;
	
	String spokenString = "TEST";
	String evaluationString = "";

	SoundPlayer soundPlayer = new SoundPlayer();
	
	boolean alphabeticOnly = true;
	int wordLength = 5;
	
	public UserInterface(int w, int h) {
		
		// Frame initialization
		setFrame(new JFrame("ParrotNATO"));
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().setSize(w, h);

		getFrame().getContentPane().setLayout(new BorderLayout());
		
		setSummaryLabel(createSummaryLabel());
		setNextButton(createNextButton());
		setPlayAgainButton(createPlayAgainButton());
		
		getFrame().getContentPane().add(BorderLayout.NORTH, getMenuBar());
		getFrame().getContentPane().add(getSummaryLabel(), BorderLayout.CENTER);
		getFrame().getContentPane().add(getNextButton(), BorderLayout.EAST);
		getFrame().getContentPane().add(getPlayAgainButton(), BorderLayout.WEST);
		getFrame().getContentPane().add(BorderLayout.SOUTH, getInputPanel());
		
		
	}
	
	private void doSample() {
		
		getSummaryLabel().setText("Speaking");
		setSpokenString(getRandomString());
		getSoundPlayer().speakWord(getSpokenString());
		
		
	}
	
	private String getRandomString() {
		
		String str = "";
		for (int i = 0; i < getWordLength(); i++) {
			
			char c = (char)((new Random()).nextInt(26) + 'A');
			str += c;
		}
		System.out.println(str);
		return str;
		
	}

	
	private JButton createPlayAgainButton() {

		JButton playAgainButton = new JButton("<");

		Action playAgainAction = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (getSoundPlayer().getClip() != null && 
						!getSoundPlayer().getClip().isActive()) {
					getSoundPlayer().speakWord(getSpokenString());
				}
				
			}

		};
		
		playAgainButton.addActionListener(playAgainAction);
		
		return playAgainButton;
		
	}
	
	private JButton createNextButton() {
		
		JButton nextButton = new JButton(">");
		
		Action nextAction = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				getInputField().setText(""); // clear input field
				doSample();
				
			}

		};
		
		nextButton.addActionListener(nextAction);
		return nextButton;
	}
	
	private JLabel createSummaryLabel() {
		JLabel summaryLabel = new JLabel(
				"<html>Press the right button to begin<br/>Press the left button to play sound again</html>\"");
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

	public JButton getPlayAgainButton() {
		return playAgainButton;
	}

	public void setPlayAgainButton(JButton playAgainButton) {
		this.playAgainButton = playAgainButton;
	}

	public SoundPlayer getSoundPlayer() {
		return soundPlayer;
	}

	public void setSoundPlayer(SoundPlayer soundPlayer) {
		this.soundPlayer = soundPlayer;
	}

	public String getSpokenString() {
		return spokenString;
	}

	public void setSpokenString(String spokenString) {
		this.spokenString = spokenString;
	}

	public String getEvaluationString() {
		return evaluationString;
	}

	public void setEvaluationString(String evaluationString) {
		this.evaluationString = evaluationString;
	}

	public boolean alphabeticOnly() {
		return alphabeticOnly;
	}

	public void setAlphabeticOnly(boolean alphabeticOnly) {
		this.alphabeticOnly = alphabeticOnly;
	}

	public int getWordLength() {
		return wordLength;
	}

	public void setWordLength(int wordLength) {
		this.wordLength = wordLength;
	}

	public JFrame getFrame() {

		return frame;

	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
