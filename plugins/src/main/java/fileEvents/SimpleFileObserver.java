package fileEvents;

import java.io.File;

import fileFilters.EndsWithFilter;

/**
 * a Listener class to print messages when .class files added/removed
 * @author sebastien Baty and mikolai Krol
 */
public class SimpleFileObserver implements FileListener {

	@Override
	public void fileAdded(FileEvent e) {
		System.out.println("New '.class' file detected : "+e.getFilename());
	}

	@Override
	public void fileRemoved(FileEvent e) {
		System.out.println("'.class' file removed : "+e.getFilename());
	}
	
	public static void main(String[] args) {
		// Main Q6 Ex3
		File dir = new File("testClassFiles");
		FileChecker checker = new FileChecker(new EndsWithFilter(".class"), dir);
		checker.addFileListener(new SimpleFileObserver());
		checker.startAnalysing();
		
		while(true);

	}

}
