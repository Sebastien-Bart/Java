package answersTests;

import static org.junit.Assert.*;

import org.junit.Test;

import answers.Answer;
import answers.TextualAnswer;
import util.BadFormatStringException;
import util.StringIsEqualToTheAnswerThrownButNotAcceptedException;

public class TextualAnswerTest extends AnswerTest<String>{

	@Override
	public Answer<String> createAnswer(String content) {
		return new TextualAnswer(createGoodAnswerAttribute());
	}

	@Override
	public String createGoodAnswerAttribute() {
		return "ok";
	}

	@Override
	public void isAcceptedReturnsTrue() {
		assertTrue(this.answer.isAccepted(null));
	}

	@Override
	public void isAcceptedReturnsFalse() {
		// cannot happen
	}

	@Override
	public void testStringIsEqualToTheAnswerReturnsTrue() throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		assertTrue(this.answer.stringIsEqualToTheAnswer(this.goodAnswer.toUpperCase()));
	}

	@Override
	public void testStringIsEqualToTheAnswerReturnsFalse() throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		assertFalse(this.answer.stringIsEqualToTheAnswer(new String()));
	}
	
	@Test
	public void testEqualsReturnsTrue() {
		TextualAnswer same = new TextualAnswer(this.goodAnswer);
		assertTrue(same.equals(this.answer));
	}
	
	@Test
	public void testEqualsReturnsFalse() {
		TextualAnswer notSame = new TextualAnswer(new String());
		assertFalse(notSame.equals(this.answer));
	}

	@Override
	public void testConstructorWithStringAsParamIsEqualToAnswerAttribute() throws BadFormatStringException {
		assertEquals(this.answer, new TextualAnswer(this.goodAnswer));
	}

}
