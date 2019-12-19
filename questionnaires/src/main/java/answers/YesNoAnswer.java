package answers;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import util.BadFormatStringException;
import util.StringIsEqualToTheAnswerThrownButNotAcceptedException;

/**
 * A class to create and manage Yes/No answers
 * @author Bart Sebastien and Krol Mikolai
 */
public class YesNoAnswer extends Answer<YesNo> {

	/**
	 * @param theAnswer either YES or NO from YesNo.java enum
	 */
	public YesNoAnswer(YesNo theAnswer) {
		super(theAnswer);
	}
	
	/**
	 * @param txt YES or NO but as a String
	 * @throws BadFormatStringException if txt has bad format
	 */
	public YesNoAnswer(String txt) throws BadFormatStringException {
		super(null);
		try {
			this.theAnswer = YesNo.valueOf(txt);
		} catch (Exception e) {
			throw new BadFormatStringException();
		}
	}

	@Override
	public boolean isAccepted(String s) {
		try {
			YesNo.valueOf(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String getTypeAsString() {
		return "(YES/NO) ";
	}

	@Override
	public boolean stringIsEqualToTheAnswer(String s) throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
		if (isAccepted(s)) {
			return YesNo.valueOf(s).equals(theAnswer);
		}
		throw new StringIsEqualToTheAnswerThrownButNotAcceptedException();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof YesNoAnswer) {
			YesNoAnswer toTest = (YesNoAnswer) o;
			return theAnswer == toTest.getTheAnswer();
		}
		return false;
	}

	@Override
	public void addSwingComponent(JPanel p) {
		ButtonGroup g = new ButtonGroup();
		JRadioButton yes = new JRadioButton("YES");
		JRadioButton no = new JRadioButton("NO");
		g.add(yes);
		g.add(no);
		p.add(yes,BorderLayout.EAST);
		p.add(no,BorderLayout.EAST);
	}
	
	@Override
	public String getData(JPanel j) {
		List<JRadioButton> buttons = new ArrayList<>();
		buttons.add((JRadioButton)j.getComponent(j.getComponentCount()-1));
		buttons.add((JRadioButton)j.getComponent(j.getComponentCount()-2));
		for(JRadioButton b : buttons) {
			if (b.isSelected()) {
				return b.getText();
			}
		}
		return "";
	}
	
	@Override
	public void resetData(JPanel j) {
		List<JRadioButton> buttons = new ArrayList<>();
		ButtonGroup g = new ButtonGroup();
		JRadioButton yes = (JRadioButton)j.getComponent(j.getComponentCount()-1);
		buttons.add(yes);
		g.add(yes);
		JRadioButton no = (JRadioButton)j.getComponent(j.getComponentCount()-2);
		buttons.add(no);
		g.add(no);
		g.clearSelection();
	}

}
