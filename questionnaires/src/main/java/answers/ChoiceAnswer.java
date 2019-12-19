package answers;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import util.BadFormatStringException;
import util.StringIsEqualToTheAnswerThrownButNotAcceptedException;

/**
 * A class to have questions that proposes answers.
 * @author Bart Sebastien and Krol Mikolai
 */
public class ChoiceAnswer extends Answer<String> { // ou extends Answer ? Jai mis Textual parce que c'est un TextualAnswer sauf qu'on a des choix
// answer finalement pour interface (mik)
	
	protected ArrayList<String> choices;

	/**
	 * @return the choices
	 */
	public ArrayList<String> getChoices() {
		return choices;
	}

	/**
	 * @param theAnswer the right answer
	 * @param choices a list of choices
	 */
	public ChoiceAnswer(String theAnswer, ArrayList<String> choices) {
		super(theAnswer);
		this.choices = choices;
	}
	
	/**
	 * @param txt a string in the good format (ex: "Seb|Mik|Loulou") Seb being the right answer 
	 * @throws BadFormatStringException if txt has bad format
	 */
	public ChoiceAnswer(String txt) throws BadFormatStringException {
		super(null);
		if (!txt.contains("|")) {
			throw new BadFormatStringException();
		}
		try {
			this.choices = new ArrayList<String>();
			String[] allStrings = txt.split("\\|");
			for (int i = 0; i < allStrings.length; i++) {
				if (i == 0) {
					this.theAnswer = allStrings[i];
				}
				this.choices.add(allStrings[i]);
			}
		} catch (Exception e) {
			throw new BadFormatStringException();
		}
	}
	
	@Override
	public boolean isAccepted(String s) {
		for (String string : choices) {
			if (string.toUpperCase().equals(s))
				return true;
		}
		return false;
	}

	@Override
	public String getTypeAsString() {
		String res = "(choice :";
		ArrayList<String> elements = new ArrayList<String>(choices);
		Collections.shuffle(elements);
		for (String choice : elements) {
			res += " "+choice;
		}
		res += ")";
		return res;
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
		if (o instanceof ChoiceAnswer) {
			ChoiceAnswer toTest = (ChoiceAnswer) o;
			return theAnswer.equals(toTest.getTheAnswer()) &&
					choices.equals(toTest.getChoices());
		}
		return false;
	}
	
	@Override
	public void addSwingComponent(JPanel p) {
		ButtonGroup g = new ButtonGroup();
		JRadioButton b;
		Collections.shuffle(choices);
		for (String s: choices) {
			b = new JRadioButton(s);
			g.add(b);
			p.add(b);
		}
	}

	@Override
	public String getData(JPanel j) {
		List<JRadioButton> buttons = new ArrayList<>();
		for(Component c : j.getComponents()) {
			if(c instanceof JRadioButton) {
				buttons.add((JRadioButton) c);
			}
		}
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
		for(Component c : j.getComponents()) {
			if(c instanceof JRadioButton) {
				buttons.add((JRadioButton) c);
			}
		}
		for(JRadioButton b : buttons) {
			g.add(b);
		}
		g.clearSelection();
	}

}
