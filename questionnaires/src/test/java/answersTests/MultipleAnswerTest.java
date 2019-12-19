package answersTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import answers.Answer;
import answers.ChoiceAnswer;
import answers.MultipleAnswer;
import answers.TextualAnswer;
import util.BadFormatStringException;
import util.StringIsEqualToTheAnswerThrownButNotAcceptedException;

public class MultipleAnswerTest extends AnswerTest<List<TextualAnswer>>{

	@Override
	public Answer<List<TextualAnswer>> createAnswer(List<TextualAnswer> content) {
		return new MultipleAnswer(createGoodAnswerAttribute());
	}

	@Override
	public List<TextualAnswer> createGoodAnswerAttribute() {
		ArrayList<TextualAnswer> res = new ArrayList<TextualAnswer>();
		res.add(new TextualAnswer("a"));
		res.add(new TextualAnswer("b"));
		res.add(new TextualAnswer("c"));
		return res;
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
		for (TextualAnswer textualAnswer : goodAnswer) {
			String toTest = textualAnswer.getTheAnswer();
			assertTrue(this.answer.stringIsEqualToTheAnswer(toTest));
		}
	}

	@Override
	public void testStringIsEqualToTheAnswerReturnsFalse()
			throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		assertFalse(this.answer.stringIsEqualToTheAnswer(new String()));
	}

	@Override
	public void testEqualsReturnsTrue() {
		MultipleAnswer toTest = new MultipleAnswer(createGoodAnswerAttribute());
		assertTrue(this.answer.equals(toTest));
	}

	@Override
	public void testEqualsReturnsFalse() {
		ArrayList<TextualAnswer> l = new ArrayList<TextualAnswer>();
		l.add(new TextualAnswer("a"));
		l.add(new TextualAnswer("b"));
		MultipleAnswer toTest = new MultipleAnswer(l);
		assertFalse(this.answer.equals(toTest));
	}

	@Override
	public void testConstructorWithStringAsParamIsEqualToAnswerAttribute() throws BadFormatStringException {
		assertEquals(this.answer, new MultipleAnswer("a;b;c"));
	}
	
	@Test(expected=BadFormatStringException.class)
	public void testConstructorThrowsException() throws BadFormatStringException {
		new ChoiceAnswer("yes no maybe i don't know");
	}

}
