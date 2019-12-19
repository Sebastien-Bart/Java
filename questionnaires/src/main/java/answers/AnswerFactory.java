package answers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * An AnswerFactory class to build answers from two Strings
 * @author Bart Sebastien and Krol Mikolai
 */
public class AnswerFactory {

	public static AnswerFactory FACTORY = new AnswerFactory();
	
	/**
	 * Private constructor because AnswerFactory is a Singleton
	 */
	private AnswerFactory() {}

	/**
	 * @param answerClassName The type of the answer to create
	 * @param answerText The text of the answer (can be a number, yes/no, etc...)
	 * @return the answer built from answerClassName and answerText
	 * @throws ClassNotFoundException shouldnt happen
	 * @throws NoSuchMethodException shouldnt happen
	 * @throws SecurityException shouldnt happen
	 * @throws InstantiationException shouldnt happen
	 * @throws IllegalAccessException shouldnt happen
	 * @throws IllegalArgumentException shouldnt happen
	 * @throws InvocationTargetException shouldnt happen
	 */
	public Answer<?> buildAnswer(String answerClassName, String answerText) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// on recupere l'objet class pour la classe de nom answerClassName
		Class<?> c = Class.forName(answerClassName);
		// On recupere le constructeur de cette classe qui a pour signature XXXXX(String)
		Constructor<?> constructor = c.getConstructor(String.class);
		// On utilise le constructeur pour construire l'objet
		Answer<?> answer = (Answer<?>) constructor.newInstance(answerText);
		return answer;
	}

}
