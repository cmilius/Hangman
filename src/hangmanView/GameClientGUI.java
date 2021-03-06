package hangmanView;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameClientGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldGuess;
	private String letterGuess;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameClientGUI frame = new GameClientGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameClientGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenDictionary = new JMenuItem("Open Dictionary");
		mnFile.add(mntmOpenDictionary);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_WordWindow = new JPanel();
		panel_WordWindow.setBounds(10, 382, 674, 57);
		contentPane.add(panel_WordWindow);
		panel_WordWindow.setLayout(null);
		
		JTextPane textPaneWordView = new JTextPane();
		textPaneWordView.setBounds(10, 11, 654, 35);
		panel_WordWindow.add(textPaneWordView);
		
		JPanel panel_Graphic = new JPanel();
		panel_Graphic.setBounds(339, 11, 345, 360);
		contentPane.add(panel_Graphic);
		panel_Graphic.setLayout(null);
		
		Canvas canvas = new Canvas();
		canvas.setBounds(10, 10, 325, 340);
		panel_Graphic.add(canvas);
		
		JPanel panel_Guess = new JPanel();
		panel_Guess.setBounds(10, 11, 319, 65);
		contentPane.add(panel_Guess);
		panel_Guess.setLayout(null);
		
		JLabel lblGuessALetter = new JLabel("Guess a Letter:");
		lblGuessALetter.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGuessALetter.setBounds(10, 21, 131, 21);
		panel_Guess.add(lblGuessALetter);
		
		textFieldGuess = new JTextField();
		textFieldGuess.setBounds(140, 23, 67, 20);
		panel_Guess.add(textFieldGuess);
		textFieldGuess.setColumns(10);
		
		JPanel panel_UsedLetters = new JPanel();
		panel_UsedLetters.setBounds(10, 87, 319, 284);
		contentPane.add(panel_UsedLetters);
		panel_UsedLetters.setLayout(null);
		
		JTextArea textAreaGuesses = new JTextArea();
		textAreaGuesses.setBounds(10, 11, 299, 262);
		panel_UsedLetters.add(textAreaGuesses);
		
		//guess button code
		JButton btnGuess = new JButton("Guess!");
		btnGuess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//check the input field of textFieldGuess
				
				//if there is more or less than one character
				if((textFieldGuess.getText().length() > 1) || (textFieldGuess.getText().length()) <= 0){
					System.out.println("Please only enter one character");
					textFieldGuess.setText("");
				}
				//otherwise see if it is valid input
				else{
					//if the input is alphabetical
					if(textFieldGuess.getText().matches("[a-zA-Z]+")){
						System.out.println("Correct");
						textFieldGuess.setText("");
						
						//add it to the guessed letters
						textAreaGuesses.append(textFieldGuess.getText());
						
					}
					//display error
					else{
						System.out.println("Please only enter the alphabet");
						textFieldGuess.setText("");
					}
				}
				
			}
		});
		btnGuess.setBounds(220, 22, 89, 23);
		panel_Guess.add(btnGuess);
		
		
	}
}
