package answersTests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import answers.Answer;
import answers.AnswerFactory;
import answers.NumericAnswer;

public class AnswerFactoryTest {

	@Test
	public void testBuildAnswerIsOk() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int n = 5;
		Answer<?> toTest = AnswerFactory.FACTORY.buildAnswer("answers.NumericAnswer", Integer.toString(n));
		assertEquals(new NumericAnswer(n), toTest);
	}

}
