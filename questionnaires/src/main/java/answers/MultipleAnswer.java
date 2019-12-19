package answers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextField;

import util.BadFormatStringException;
import util.StringIsEqualToTheAnswerThrownButNotAcceptedException;

/**
 * A class to have multiple possible answers for 1 question
 * @author Bart Sebastien and Krol Mikolai
 */
public class MultipleAnswer extends Answer<List<TextualAnswer>> {

	/**
	 * @param theAnswer a list of TextualAnswer
	 */
	public MultipleAnswer(List<TextualAnswer> theAnswer) {
		super(theAnswer);
	}
	
	/**
	 * @param txt the string in the good format (ex: "Seb;Mik;Loulou")
	 * @throws BadFormatStringException if txt has bad format
	 */
	public MultipleAnswer(String txt) throws BadFormatStringException {
		super(new ArrayList<TextualAnswer>());
		try {
			String[] possibleAnswersStrings = txt.split(";");
			for (int i = 0; i < possibleAnswersStrings.length; i++) {
				String answerString = possibleAnswersStrings[i];
				this.theAnswer.add(new TextualAnswer(answerString));
			}
		} catch (Exception e) {
			throw new BadFormatStringException();
		}
	}

	@Override
	public boolean isAccepted(String s) {
		return true;
	}

	@Override
	public String getTypeAsString() {
		return "(multiple) ";
	}

	@Override
	public boolean stringIsEqualToTheAnswer(String s) throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		if (isAccepted(s)) {
			TextualAnswer test = new TextualAnswer(s);
			return this.theAnswer.contains(test);
		}
		throw new StringIsEqualToTheAnswerThrownButNotAcceptedException();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof MultipleAnswer) {
			MultipleAnswer toTest = (MultipleAnswer) o;
			return theAnswer.equals(toTest.getTheAnswer());
		}
		return false;
	}

	@Override
	public void addSwingComponent(JPanel p) {
		JTextField t = new JTextField();
		t.setPreferredSize(new Dimension(200,30));
		p.add(t, BorderLayout.EAST);	
	}
	
	@Override
	public String getData(JPanel j) {
		JTextField textArea = (JTextField) j.getComponent(j.getComponentCount()-1);
		return textArea.getText();
	}
	
	@Override
	public void resetData(JPanel j) {
		JTextField textArea = (JTextField) j.getComponent(j.getComponentCount()-1);
		textArea.setText("");
	}

}
