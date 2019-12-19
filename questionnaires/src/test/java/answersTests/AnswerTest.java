package answersTests;

import org.junit.Before;
import org.junit.Test;

import answers.Answer;
import util.BadFormatStringException;
import util.StringIsEqualToTheAnswerThrownButNotAcceptedException;

public abstract class AnswerTest<T> {

	protected T goodAnswer;
	protected Answer<T> answer;
	
	@Before
	public void init() {
		this.answer = this.createAnswer(goodAnswer);
		this.goodAnswer = this.createGoodAnswerAttribute();
	}
	
	public abstract Answer<T> createAnswer(T content);
	
	public abstract T createGoodAnswerAttribute();
	
	@Test
	public abstract void isAcceptedReturnsTrue();
	
	@Test
	public abstract void isAcceptedReturnsFalse();
	
	@Test
	public abstract void testStringIsEqualToTheAnswerReturnsTrue() throws StringIsEqualToTheAnswerThrownButNotAcceptedException;
	
	@Test
	public abstract void testStringIsEqualToTheAnswerReturnsFalse() throws StringIsEqualToTheAnswerThrownButNotAcceptedException;

	@Test
	public abstract void testEqualsReturnsTrue();
	
	@Test
	public abstract void testEqualsReturnsFalse();
	
	@Test
	public abstract void testConstructorWithStringAsParamIsEqualToAnswerAttribute() throws BadFormatStringException;
	
}
