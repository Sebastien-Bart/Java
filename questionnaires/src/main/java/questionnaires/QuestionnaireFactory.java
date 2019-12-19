package questionnaires;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import answers.AnswerFactory;
import questions.Question;

/**
 * A class to create questionnaires from a file
 * @author JC Routier
 */
public class QuestionnaireFactory {
	
	// singleton
	public static final QuestionnaireFactory FACTORY = new QuestionnaireFactory();
	
	private QuestionnaireFactory() {};
	
	/**
	 * Create a Question from three Strings
	 * @param text the text of the question
	 * @param answerType the name of the class expected
	 * @param answer the answer of the question
	 * @param points the points of the question
	 * @return a question build with the params
	 * @throws IOException if the file is not correctly formatted
	 * @throws InvocationTargetException shouldnt happen
	 * @throws IllegalArgumentException shouldnt happen
	 * @throws IllegalAccessException shouldnt happen
	 * @throws InstantiationException shouldnt happen
	 * @throws SecurityException shouldnt happen
	 * @throws NoSuchMethodException shouldnt happen
	 * @throws ClassNotFoundException shouldnt happen
	 */
	public Question createQuestion(String text, String answerType, String answer, String points) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
			int nbPoints = Integer.parseInt(points);
			return new Question(text, AnswerFactory.FACTORY.buildAnswer(answerType ,answer), nbPoints);
		}
		catch(NumberFormatException e) {
			throw new IOException("Le fichier a un mauvais format");
		}
	}
	
	/**
	 * Create a questionnaire from a formatted file
	 * @param fileName name of the file
	 * @return a questionnaire object
	 * @throws IOException if the file is not correctly formatted
	 * @throws InvocationTargetException shouldnt happen
	 * @throws IllegalArgumentException shouldnt happen
	 * @throws IllegalAccessException shouldnt happen
	 * @throws InstantiationException shouldnt happen
	 * @throws SecurityException shouldnt happen
	 * @throws NoSuchMethodException shouldnt happen
	 * @throws ClassNotFoundException shouldnt happen
	 */
	public Questionnaire createQuestionnaire(String fileName) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Questionnaire questionnaire = new Questionnaire();
		File source = new File(fileName);
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(source));
			String text;
			// lit par blocs de 3 lignes : text, answer, points
			while((text = in.readLine()) != null) {
				String answerType = in.readLine();
				String answer = in.readLine();
				String nbPoints = in.readLine();
				if (answer == null || nbPoints == null || answerType == null) {
					throw new IOException("Le fichier a un mauvais format");
				}
				questionnaire.addQuestion(this.createQuestion(text, answerType, answer, nbPoints));
			}
		} catch(FileNotFoundException e) {
			throw new IOException(e);
		} finally {
			in.close();
		}
		return questionnaire;
	}

}
