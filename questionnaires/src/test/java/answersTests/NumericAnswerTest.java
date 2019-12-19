package answersTests;

import static org.junit.Assert.*;

import org.junit.Test;

import answers.Answer;
import answers.ChoiceAnswer;
import answers.NumericAnswer;
import util.BadFormatStringException;
import util.StringIsEqualToTheAnswerThrownButNotAcceptedException;

public class NumericAnswerTest extends AnswerTest<Integer> {

	@Override
	public Answer<Integer> createAnswer(Integer content) {
		return new NumericAnswer(createGoodAnswerAttribute());
	}

	@Override
	public Integer createGoodAnswerAttribute() {
		return 5;
	}

	@Override
	public void isAcceptedReturnsTrue() {
		assertTrue(this.answer.isAccepted("6"));
	}

	@Override
	public void isAcceptedReturnsFalse() {
		assertFalse(this.answer.isAccepted("salut beau thé"));
	}

	@Override
	public void testStringIsEqualToTheAnswerReturnsTrue() throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		assertTrue(this.answer.stringIsEqualToTheAnswer(this.goodAnswer.toString()));
	}

	@Override
	public void testStringIsEqualToTheAnswerReturnsFalse() throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		Integer badAnswer = this.goodAnswer - 1;
		assertFalse(this.answer.stringIsEqualToTheAnswer(badAnswer.toString()));
	}
	
	@Override
	public void testEqualsReturnsTrue() {
		NumericAnswer same = new NumericAnswer(this.goodAnswer);
		assertTrue(same.equals(this.answer));
	}
	
	@Override
	public void testEqualsReturnsFalse() {
		NumericAnswer notSame = new NumericAnswer(this.goodAnswer-1);
		assertFalse(notSame.equals(this.answer));
	}

	@Override
	public void testConstructorWithStringAsParamIsEqualToAnswerAttribute() throws BadFormatStringException {
		assertEquals(this.answer, new NumericAnswer(this.goodAnswer.toString()));
	}
	
	@Test(expected=BadFormatStringException.class)
	public void testConstructorThrowsException() throws BadFormatStringException {
		new ChoiceAnswer("yes no maybe i don't know");
	}

}
