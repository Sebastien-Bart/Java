import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import gui.Gui;
import questionnaires.Questionnaire;
import questionnaires.QuestionnaireFactory;
import util.StringIsEqualToTheAnswerThrownButNotAcceptedException;

/**
 * ptit main pour tester
 * @author Bart Sebastien and Krol Mikolai
 */
public class MainQuestionnaire {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, StringIsEqualToTheAnswerThrownButNotAcceptedException {
		Scanner myScan = new Scanner(System.in);
		
		// choix type questionnaire
		System.out.println("Quel type de questionnaire ?");
		int usrInputInt = 0;
		while(usrInputInt != 1 && usrInputInt != 2) {
			System.out.println("1 : Questionnaire console");
			System.out.println("2 : Questionnaire graphique");
			try {
				usrInputInt = myScan.nextInt();
			} catch (Exception	 e){
				myScan.skip(".*");
			}
		}
		
		// nom du fichier
		Questionnaire q = null;
		while(q == null) {
			System.out.println("Entre le nom du du questionnaire (fichier txt) :");
			String usrInputString = myScan.nextLine();
			try {
				q = QuestionnaireFactory.FACTORY.createQuestionnaire(usrInputString);
			} catch(Exception e) {
				System.out.println("--->[!] Erreur creation du questionnaire avec ce nom de fichier.");
			}
		}
		
		// process
		if (usrInputInt == 1) {
			q.askAll();
		}
		else if (usrInputInt == 2) {
			Gui myGui = new Gui(q);
			myGui.run();
		}
		else {
			System.out.println("Erreur if usrInput");
		}
		myScan.close();
	}

}
