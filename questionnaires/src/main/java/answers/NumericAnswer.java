package answers;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import util.BadFormatStringException;
import util.StringIsEqualToTheAnswerThrownButNotAcceptedException;

/**
 * A class to create and manage Numeric answers
 * @author Bart Sebastien and Krol Mikolai
 */
public class NumericAnswer extends Answer<Integer> {

	/**
	 * @param theAnswer an int
	 */
	public NumericAnswer(int theAnswer) {
		super(theAnswer);
	}
	
	/**
	 * Used when building from a file
	 * @param txt the int as a String
	 * @throws BadFormatStringException if txt has bad format
	 */
	public NumericAnswer(String txt) throws BadFormatStringException {
		super(null);
		try {
			this.theAnswer = Integer.parseInt(txt);
		} catch (Exception e) {
			throw new BadFormatStringException();
		}	
	}

	@Override
	public boolean isAccepted(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public String getTypeAsString() {
		return "(numeric) ";
	}

	@Override
	public boolean stringIsEqualToTheAnswer(String s) throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		if (isAccepted(s)) {
			return Integer.parseInt(s) == theAnswer;
		}
		throw new StringIsEqualToTheAnswerThrownButNotAcceptedException();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof NumericAnswer) {
			NumericAnswer toTest = (NumericAnswer) o;
			return toTest.getTheAnswer() == this.theAnswer;
		}
		return false;
	}

	@Override
	public void addSwingComponent(JPanel p) {
		JSpinner s = new JSpinner(new SpinnerNumberModel(0, -theAnswer -100, theAnswer +100, 1));
		p.add(s, BorderLayout.EAST);
	}
	
	@Override
	public String getData(JPanel j) {
		JSpinner spinner = (JSpinner) j.getComponent(j.getComponentCount()-1);
		return spinner.getValue().toString();
	}
	
	@Override
	public void resetData(JPanel j) {
		JSpinner spinner = (JSpinner) j.getComponent(j.getComponentCount()-1);
		spinner.setValue(0);
	}

}
