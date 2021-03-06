package hangmanController;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import hangmanModel.HangManModel;
import hangmanView.HangManView;

public class HangManController implements ActionListener {
	private HangManModel model;
	private HangManView view;
	
	public HangManController(HangManModel model, HangManView view){
		this.model = model;
		this.view = view;
	}
	
	public void setUsedLetter(String letter){
		model.guessLetter(letter);
	}
	
	public void updateLetterGuess(){
		ArrayList<String> letterList = model.getLettersGuessed();
		String usedLetters = letterList.toString();
		view.getUsedLettersField().setText(usedLetters.substring(1, usedLetters.length() - 1));
	}
	
	public void updateViewCanvas(){
		int guesses = model.getGuessesRemaining();
		
		if(guesses == 10){
			view.getHangmanCanvas().drawStandBase(view.getHangmanCanvas().getGraphics());
		}
		else if(guesses == 9){
			view.getHangmanCanvas().drawStandLeft(view.getHangmanCanvas().getGraphics());
			
		}
		else if(guesses == 8){
			view.getHangmanCanvas().drawStandTop(view.getHangmanCanvas().getGraphics());
			
		}
		else if(guesses == 7){
			view.getHangmanCanvas().drawNoose(view.getHangmanCanvas().getGraphics());
			
		}
		else if(guesses == 6){
			view.getHangmanCanvas().drawHead(view.getHangmanCanvas().getGraphics());
			
		}
		else if(guesses == 5){
			view.getHangmanCanvas().drawBody(view.getHangmanCanvas().getGraphics());
			
		}
		else if(guesses == 4){
			view.getHangmanCanvas().drawLegLeft(view.getHangmanCanvas().getGraphics());
			
		}
		else if(guesses == 3){
			view.getHangmanCanvas().drawLegRight(view.getHangmanCanvas().getGraphics());
			
		}
		else if(guesses == 2){
			view.getHangmanCanvas().drawArmLeft(view.getHangmanCanvas().getGraphics());
			
		}
		else if(guesses == 1){
			view.getHangmanCanvas().drawArmRight(view.getHangmanCanvas().getGraphics());
		}
		else if(guesses == 0){
			view.getHangmanCanvas().drawEyes(view.getHangmanCanvas().getGraphics());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("New Game")){
			newGame();
		}
		else if(e.getActionCommand().equals("Open Dictionary")){
			JFileChooser fileChoose = new JFileChooser();
			fileChoose.setDialogTitle("Choose a new dictionary text file");
			int returnVal = fileChoose.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String filePath = fileChoose.getSelectedFile().getAbsolutePath();
				if (!filePath.contains("txt")) {
					JOptionPane.showMessageDialog(null, "Invalid text file!");
					return;
				}
				model = new HangManModel(filePath);
				newGame();
			}
			
			else{
				return;
			}
		}
		else if(e.getActionCommand().equals("Exit")){
			exitGame();
		}
		else{
			//get the letter that the player guessed
			String letter = view.getGuessText().getText();
			
			//clear the guess field to make guessing again easier
			view.getGuessText().setText("");
			
			//if there was more than one char or wasn't a letter, stop executing
			if(letter.length() > 1 || !letter.matches("[a-zA-Z]+")){
				return;
			}
			
			setUsedLetter(letter);
			updateLetterGuess();
			
			updateViewCanvas();

			view.getWordsDisplayed().setText(model.getWordDisplay());
			if(model.hasWon()){
				JOptionPane.showMessageDialog(null, "Congrats, you have won!");
			}
			
			else if(model.hasLost()){
				JOptionPane.showMessageDialog(null, "You lost!");
				view.getWordsDisplayed().setText(model.getGoalWord());
			}
		}
		view.getWordsDisplayed().setText(model.getWordDisplay());
	}
	
	public void initView(){
		view.getWordsDisplayed().setText(model.getWordDisplay());
	}
	
	/**
	 * Starts a new game
	 */
	public void newGame(){
		//set a new goal word
		model.setNewGoalWord();
		view.getHangmanCanvas().getGraphics().clearRect(0, 0, 345, 360);
		updateLetterGuess();
		initView();
	}
	
	/**
	 * Exits the game
	 */
	public void exitGame(){
		System.exit(0);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HangManModel hangModel = new HangManModel();
					HangManView hangView = new HangManView();
					HangManController controller = new HangManController(hangModel, hangView);
					hangView.addBtnGuessController(controller);
					hangView.addNewGameController(controller);
					hangView.addOpenDictionaryController(controller);
					hangView.addExitGameController(controller);
					controller.initView();
					
					hangView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
}
