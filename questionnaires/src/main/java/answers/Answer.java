package answers;

import javax.swing.JPanel;

import util.StringIsEqualToTheAnswerThrownButNotAcceptedException;

/**
 * A class to create and manage Answers
 * @author Bart Sebastien and Krol Mikolai
 */
public abstract class Answer<T> {
	
	protected T theAnswer;
	
	/**
	 * @param theAnswer the answer to set
	 */
	public Answer(T theAnswer) {
		this.theAnswer = theAnswer;
	}

	/**
	 * @return the theAnswer
	 */
	public T getTheAnswer() {
		return theAnswer;
	}
	
	/**
	 * @param s the tested string
	 * @return true if s is allowed as an answer of this type
	 */
	public abstract boolean isAccepted(String s);
	
	/**
	 * @return the String corresponding to the type of the answer
	 */
	public abstract String getTypeAsString();
	
	/**
	 * @param s the user input
	 * @return true if s is theAnswer false otherwise
	 * @throws StringIsEqualToTheAnswerThrownButNotAcceptedException if s is not accepted
	 */
	public abstract boolean stringIsEqualToTheAnswer(String s) throws StringIsEqualToTheAnswerThrownButNotAcceptedException;
	
	public String toString() {
		return ""+theAnswer;
	}
	
	/**
	 * @param p the jPanel to be modified
	 */
	public abstract void addSwingComponent(JPanel p);
	
	/**
	 * @param j panel which contains the question corresponding to the answer
	 * @return the answer given by the user
	 */
	public abstract String getData(JPanel j);
	
	/**
	 * reset the data given by the user
	 * @param j panel containing the answer component
	 */
	public abstract void resetData(JPanel j);
	
	
}
