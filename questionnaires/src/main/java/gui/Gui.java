package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import questionnaires.Questionnaire;
import questionnaires.QuestionnaireFactory;
import questions.Question;
import util.StringIsEqualToTheAnswerThrownButNotAcceptedException;

/**
 * @author Bart Sébastien / Krol Mikolaï
 * A class to generate graphical interface for questionnaire
 */
public class Gui {
	
	public static void main(String args[]) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		Gui g = new Gui(QuestionnaireFactory.FACTORY.createQuestionnaire("questionnaire.txt"));
		g.run();
	}
	
	private Questionnaire q;
	private JFrame f;
	private Map<Question, JPanel> panels;
	
	/**
	 * Create a graphical interface for the questionnaire q
	 * @param q questionnaire to answer
	 */
	public Gui(Questionnaire q) {
		this.q = q;
		this.f = new JFrame("Questionnaire");
		f.addWindowListener(new CloseWindowEvent());
		f.setLocation(200, 100);
		f.setSize(1000, 500);
		f.setLayout(new GridLayout(q.getQuestions().size()+1, 1));
		panels = q.generateJPanels();
		
	}
	
	/**
	 * Add the panels corresponding to each question to the main JFrame
	 */
	public void addPanels() {
		for(Question q : panels.keySet()) {
			f.add(panels.get(q), BorderLayout.WEST);
		}
	}
	
	/**
	 * Add the validate button to the main frame
	 */
	public void addValidateButton() {
		JButton b = new JButton("Validate");
		b.addActionListener(new ValidateButtonActionListener());
		f.add(b);
		b.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	/**
	 * Set the frame visible
	 */
	public void display() {
		f.setVisible(true);
	}
	
	/**
	 * to launch the graphical interface
	 */
	public void run() {
		addPanels();
		addValidateButton();
		display();
	}
	
	/**
	 * @author krol bart
	 * Closing event
	 */
	private class CloseWindowEvent extends WindowAdapter {
		public void windowClosing(java.awt.event.WindowEvent e) {
			System.exit(0);
		}
	}
	
	
	/**
	 * @author krol bart
	 * Event performed when the user click on the validate button
	 */
	private class ValidateButtonActionListener implements ActionListener {
		
		private HashMap<Question, String> data;
		
		ValidateButtonActionListener(){
			data = new HashMap<>();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			getData(Gui.this.f);
			try {
				verifyData();
			} catch (StringIsEqualToTheAnswerThrownButNotAcceptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			showPopup();
		}
		
		/**
		 * @param f main frame of the interface
		 * get the answers given by the user
		 */
		public void getData(JFrame f) {
			for(Question q : Gui.this.panels.keySet()) {
				String userAnswer = q.getAnswer().getData(Gui.this.panels.get(q));
				data.put(q, userAnswer);
			}
		}
		
		public void resetData() {
			for(Question q : Gui.this.panels.keySet()) {
				q.getAnswer().resetData(Gui.this.panels.get(q));
			}
		}
		
		/**
		 * Verify for each question if the answer given by the user is correct
		 * @throws StringIsEqualToTheAnswerThrownButNotAcceptedException should not happen
		 */
		public void verifyData() throws StringIsEqualToTheAnswerThrownButNotAcceptedException {
			for(Question q : data.keySet()) {
				String answer = data.get(q);
				boolean result;
				if (!answer.equals("")) {result = q.tryToAnswer(data.get(q).toUpperCase());}
				else result = false;
				if(result) {
					Gui.this.q.addPoints(q.getPoints());
				}
			}
		}
		
		/**
		 * Show a popup with the user's score
		 */
		public void showPopup() {
			String[] choix = {"Recommencer", "Quitter"};
			int rang = JOptionPane.showOptionDialog(Gui.this.f, 
				      "Score : " + Gui.this.q.getPoints() + " / " + Gui.this.q.getTotalPoints(),
				      "Résultats",
				      JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				      null,
				      choix, choix[1]);
			if(rang == 1) {System.exit(0);}
			else resetData();
			Gui.this.q.resetPoints();
		}
		
	}

}
