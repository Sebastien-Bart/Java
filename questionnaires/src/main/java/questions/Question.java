package questions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import answers.Answer;
import util.StringIsEqualToTheAnswerThrownButNotAcceptedException;

/**
 * Class to represents questions of a quiz
 * @author Bart Sébastien and Krol Mikolaï
 */
public class Question {
	
	private String text;
	private Answer<?> answer;
	private int points;
	
	/**
	 * @param text text of the question
	 * @param answer answer of the question
	 * @param points number of points to win by answering right this question
	 */
	public Question(String text, Answer<?> answer, int points) {
		this.answer = answer;
		this.text = text;
		this.points = points;
	}

	/**
	 * @return the text of the question
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the answer of the question
	 */
	public Answer<?> getAnswer() {
		return answer;
	}

	/**
	 * @return number of points to win by answering right this question
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * @param s the answer as a string to try
	 * @return true if s is the correct answer, false otherwise
	 * @throws StringIsEqualToTheAnswerThrownButNotAcceptedException shouldnt happen
	 */
	public boolean tryToAnswer(String s) throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		return answer.stringIsEqualToTheAnswer(s);
	}
	
	/**
	 * Return a JPanel with the text of the question, its number of points and the component corresponding to its answer
	 * @return JPanel corresponding to the question
	 */
	public JPanel createPanel() {
		JPanel p = new JPanel();
		JLabel text = new JLabel(this.text);
		Font font1 = new Font("Arial",Font.BOLD,16);
		Font font2 = new Font("Arial",Font.ITALIC,16);
		JLabel points = new JLabel("("+Integer.toString(this.points)+ (this.points > 1 ? " points)":" point)"));
		p.add(text, BorderLayout.WEST);
		p.add(points, BorderLayout.CENTER);
		this.answer.addSwingComponent(p);
		p.setBorder(BorderFactory.createLineBorder(Color.black));
		text.setFont(font1);
		points.setFont(font2);
		return p;
	}
	
}
