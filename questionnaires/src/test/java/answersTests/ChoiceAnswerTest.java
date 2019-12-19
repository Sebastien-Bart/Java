package answersTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import answers.Answer;
import answers.ChoiceAnswer;
import util.BadFormatStringException;
import util.StringIsEqualToTheAnswerThrownButNotAcceptedException;

public class ChoiceAnswerTest extends AnswerTest<String>{

	protected ArrayList<String> choices;
	
	@Override
	public Answer<String> createAnswer(String content) {
		return new ChoiceAnswer(createGoodAnswerAttribute(), createChoices());
	}
	
	@Override
	public String createGoodAnswerAttribute() {
		return "ok";
	}
	
	public ArrayList<String> createChoices() {
		ArrayList<String> res = new ArrayList<String>();
		res.add(createGoodAnswerAttribute());
		res.add("notOk");
		return res;
	}

	@Override
	public void isAcceptedReturnsTrue() {
		assertTrue(this.answer.isAccepted("OK"));
		assertTrue(this.answer.isAccepted("NOTOK"));
	}

	@Override
	public void isAcceptedReturnsFalse() {
		assertFalse(this.answer.isAccepted(new String()));
	}
	
	@Override
	public void testStringIsEqualToTheAnswerReturnsFalse() throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		assertFalse(this.answer.stringIsEqualToTheAnswer("NOTOK"));
	}
	
	@Override
	public void testStringIsEqualToTheAnswerReturnsTrue() throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		assertTrue(this.answer.stringIsEqualToTheAnswer(this.goodAnswer.toUpperCase()));
	}
	
	@Override
	public void testConstructorWithStringAsParamIsEqualToAnswerAttribute() throws BadFormatStringException {
		assertEquals(this.answer, new ChoiceAnswer("ok|notOk"));
	}
	
	@Override
	public void testEqualsReturnsTrue() {
		ChoiceAnswer toTest = new ChoiceAnswer(createGoodAnswerAttribute(), createChoices());
		assertTrue(this.answer.equals(toTest));
	}
	
	@Override
	public void testEqualsReturnsFalse() {
		ChoiceAnswer toTest = new ChoiceAnswer("notOk", createChoices());
		assertFalse(this.answer.equals(toTest));
	}
	
	@Test(expected=BadFormatStringException.class)
	public void testConstructorThrowsException() throws BadFormatStringException {
		new ChoiceAnswer("yes no maybe i don't know");
	}

	
	
}
