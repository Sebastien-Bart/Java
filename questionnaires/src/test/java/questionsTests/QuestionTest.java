package questionsTests;

import org.junit.Test;

import answers.Answer;
import answers.NumericAnswer;
import answers.TextualAnswer;
import questions.Question;
import util.StringIsEqualToTheAnswerThrownButNotAcceptedException;

public class QuestionTest {

	protected Answer<String> a = new TextualAnswer("Louis");
	protected Question q = new Question("Comment s'appelle Louis ?", a, 1);
	
	@Test
	public void testTryToAnswerReturnsTrue() throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		q.tryToAnswer(a.toString());
	}
	
	@Test
	public void testTryToAnswerReturnsFalse() throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		q.tryToAnswer(new String());
	}
	
	@Test(expected=StringIsEqualToTheAnswerThrownButNotAcceptedException.class)
	public void testTryToAnswerThrowsException() throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		NumericAnswer num = new NumericAnswer(3);
		Question q2 = new Question("c'est 3", num, 0);
		q2.tryToAnswer("non");
	}
		
}
