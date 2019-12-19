package answers;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;

import util.StringIsEqualToTheAnswerThrownButNotAcceptedException;

/**
 * A class to create and manage Textual answers
 * @author Bart Sebastien and Krol Mikolai
 */
public class TextualAnswer extends Answer<String> {

	/**
	 * @param theAnswer the right answer
	 */
	public TextualAnswer(String theAnswer) {
		super(theAnswer);
	}

	@Override
	public boolean isAccepted(String s) {
		return true;
	}

	@Override
	public String getTypeAsString() {
		return "(text) ";
	}

	@Override
	public boolean stringIsEqualToTheAnswer(String s) throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		if (isAccepted(s)) {
			return s.equals(theAnswer.toUpperCase());
		}
		throw new StringIsEqualToTheAnswerThrownButNotAcceptedException();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof TextualAnswer) {
			TextualAnswer toTest = (TextualAnswer) o;
			return toTest.getTheAnswer().toUpperCase().equals(this.theAnswer.toUpperCase());
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
