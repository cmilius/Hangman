package hangmanController;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	
	
	public void guess(){
		
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
		String letter = view.getGuessText().getText();
		if(letter.length() > 1){
			return;
		}
		setUsedLetter(letter);
		updateLetterGuess();
		view.getWordsDisplayed().setText(model.getWordDisplay());
		
		updateViewCanvas();
		
		if(model.hasWon()){
			JOptionPane.showMessageDialog(null, "Congrats, you have won!");
		}
		
		else if(model.hasLost()){
			JOptionPane.showMessageDialog(null, "You lost!");
			view.getWordsDisplayed().setText(model.getGoalWord());
		}
	}
	
	public void initView(){
		view.getWordsDisplayed().setText(model.getWordDisplay());
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HangManModel hangModel = new HangManModel();
					HangManView hangView = new HangManView();
					HangManController controller = new HangManController(hangModel, hangView);
					hangView.addBtnGuessController(controller);
					controller.initView();
					
					hangView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
}
