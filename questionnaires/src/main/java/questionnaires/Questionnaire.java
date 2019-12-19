package questionnaires;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JPanel;

import questions.Question;
import util.StringIsEqualToTheAnswerThrownButNotAcceptedException;

/**
 * A class to create and manage questionnaire
 * @author Bart Sebastien and Krol Mikolai
 */
public class Questionnaire {
	
	private List<Question> questions;
	private int points;
	
	/**
	 * Create a questionnaire with no questions and initialize the points at 0
	 */
	public Questionnaire() {
		questions = new ArrayList<>();
		points = 0;
	}

	/**
	 * @return the nb of points
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * @return the sum of the points of the questionnaire
	 */
	public int getTotalPoints() {
		int res = 0;
		for (Question q : questions) {
			res += q.getPoints();
		}
		return res;
	}
	
	/**
	 * @param points points to add
	 */
	public void addPoints(int points) {
		this.points += points;
	}
	
	/**
	 * Set the points to 0
	 */
	public void resetPoints() {
		this.points = 0;
	}
	
	/**
	 * @param q the question to add
	 */
	public void addQuestion(Question q) {
		questions.add(q);
	}

	/**
	 * @return the questions of the questionnaire
	 */
	public List<Question> getQuestions() {
		return questions;
	}
	
	/**
	 * Ask all the questions
	 * @throws StringIsEqualToTheAnswerThrownButNotAcceptedException shouldnt happen
	 */
	public void askAll() throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		Scanner myScan = new Scanner(System.in);
		for (Question q : questions) {
			System.out.println("---> "+ q.getText());
			String userAnswer = askForUserAnswer(myScan, q);
			// DEBUG
			System.out.println(userAnswer);
			// FIN DEBUG
			useUserAnswer(q, userAnswer);
		}
		System.out.println("\nYou have : "+ points + " points.");
	}

	/**
	 * Try the answer of the user and use the result of the question
	 * @param q the question asked
	 * @param userAnswer the user's answer
	 * @throws StringIsEqualToTheAnswerThrownButNotAcceptedException shouldnt happen
	 */
	private void useUserAnswer(Question q, String userAnswer) throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		boolean result = q.tryToAnswer(userAnswer);
		if (result) {
			points += q.getPoints();
			System.out.println("correct ! (" + q.getPoints() + " points)\n");
		}
		else {
			System.out.println("wrong ! correct answer was : " + q.getAnswer()+"\n");
		}
	}

	/**
	 * @param myScan the used scanner
	 * @param q the question asked
	 * @return the user answer in upper case
	 */
	protected String askForUserAnswer(Scanner myScan, Question q) {
		boolean isGoodAnswerType = false;
		String userAnswer = null;
		while (!isGoodAnswerType) {
			System.out.println(q.getAnswer().getTypeAsString());
			userAnswer = myScan.next().toUpperCase();
			isGoodAnswerType = q.getAnswer().isAccepted(userAnswer);
		}
		return userAnswer;
	}
	
	/**
	 * @return a list of all the panels corresponding to each question of the questionnaire
	 */
	public Map<Question, JPanel> generateJPanels(){
		HashMap<Question, JPanel> panels = new HashMap<>();
		for(Question q : questions) {
			panels.put(q, q.createPanel());
		}
		return panels;
	}
	
	
}
