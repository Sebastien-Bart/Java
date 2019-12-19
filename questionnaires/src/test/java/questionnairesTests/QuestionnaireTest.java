package questionnairesTests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import answers.NumericAnswer;
import answers.TextualAnswer;
import answers.YesNo;
import answers.YesNoAnswer;
import questionnaires.Questionnaire;
import questions.Question;
import util.StringIsEqualToTheAnswerThrownButNotAcceptedException;

public class QuestionnaireTest {
	
	protected MockQuestionnaire questionnaire = new MockQuestionnaire();
	
	@Before
	public void init() {
		Question q1 = new Question("Q textuelle : txt", new TextualAnswer("txt"), 1);
		Question q2 = new Question("Q numerique : 5", new NumericAnswer(5), 1);
		Question q3 = new Question("Q yesNo : NO", new YesNoAnswer(YesNo.NO), 1);
		questionnaire.addQuestion(q1);
		questionnaire.addQuestion(q2);
		questionnaire.addQuestion(q3);
	}

	@Test
	public void testAskAllDoPauseWhenUserInputNotGoodType() throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		String mockInput = "txt\ncinq\n5\noui\nYES";
		InputStream stdin = System.in;
		try {
		  System.setIn(new ByteArrayInputStream(mockInput.getBytes()));
		  questionnaire.askAll();
		} finally {
		  System.setIn(stdin);
		}
		assertEquals(2, questionnaire.getNbPause());
	}
	
	@Test
	public void testAskAllGivesGoodAmountOfPoints() throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		String mockInput = "";
		int expected = 0;
		for (Question q : questionnaire.getQuestions()) {
			mockInput += q.getAnswer().toString().toUpperCase() + "\n";
			expected += q.getPoints();
		}
		InputStream stdin = System.in;
		try {
		  System.setIn(new ByteArrayInputStream(mockInput.getBytes()));
		  questionnaire.askAll();
		} finally {
		  System.setIn(stdin);
		}
		assertEquals(expected, questionnaire.getPoints());
	}
	
	
	
	
	/**
	 * Mock class to test pauses
	 * @author Bart Sebastien and Krol Mikolai
	 */
	public class MockQuestionnaire extends Questionnaire {
		
		protected int nbPause;
		
		/**
		 * @return the nbPause
		 */
		public int getNbPause() {
			return nbPause;
		}

		public MockQuestionnaire() {
			super();
			nbPause = 0;
		}
		
		@Override
		public String askForUserAnswer(Scanner myScan, Question q) {
			boolean isGoodAnswerType = false;
			String userAnswer = null;
			while (!isGoodAnswerType) {
				userAnswer = myScan.nextLine();
				System.out.println(q.getAnswer().getTypeAsString() + userAnswer);
				isGoodAnswerType = q.getAnswer().isAccepted(userAnswer);
				if (!isGoodAnswerType) {
					this.nbPause++;
				}
			}
			return userAnswer;
		}
	}

}
