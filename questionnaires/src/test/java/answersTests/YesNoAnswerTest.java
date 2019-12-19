package answersTests;

import static org.junit.Assert.*;

import org.junit.Test;

import answers.Answer;
import answers.ChoiceAnswer;
import answers.YesNo;
import answers.YesNoAnswer;
import util.BadFormatStringException;
import util.StringIsEqualToTheAnswerThrownButNotAcceptedException;

public class YesNoAnswerTest extends AnswerTest<YesNo> {

	@Override
	public Answer<YesNo> createAnswer(YesNo content) {
		return new YesNoAnswer(createGoodAnswerAttribute());
	}

	@Override
	public YesNo createGoodAnswerAttribute() {
		return YesNo.YES;
	}

	@Override
	public void isAcceptedReturnsTrue() {
		assertTrue(this.answer.isAccepted("NO"));
		assertTrue(this.answer.isAccepted("YES"));
	}

	@Override
	public void isAcceptedReturnsFalse() {
		assertFalse(this.answer.isAccepted("oui"));
	}

	@Override
	public void testStringIsEqualToTheAnswerReturnsTrue() throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		assertTrue(this.answer.stringIsEqualToTheAnswer("YES"));
	}

	@Override
	public void testStringIsEqualToTheAnswerReturnsFalse() throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		assertFalse(this.answer.stringIsEqualToTheAnswer("NO"));
	}

	@Override
	public void testEqualsReturnsTrue() {
		YesNoAnswer toTest = new YesNoAnswer(createGoodAnswerAttribute());
		assertTrue(this.answer.equals(toTest));
	}

	@Override
	public void testEqualsReturnsFalse() {
		YesNoAnswer toTest = new YesNoAnswer(YesNo.NO);
		assertFalse(this.answer.equals(toTest));
	}

	@Override
	public void testConstructorWithStringAsParamIsEqualToAnswerAttribute() throws BadFormatStringException {
		assertEquals(this.answer, new YesNoAnswer(goodAnswer.toString()));
	}
	
	@Test(expected=BadFormatStringException.class)
	public void testConstructorThrowsException() throws BadFormatStringException {
		new ChoiceAnswer("yes no maybe i don't know");
	}

}
