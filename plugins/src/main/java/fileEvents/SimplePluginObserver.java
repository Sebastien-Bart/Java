package fileEvents;

import java.io.File;

import fileFilters.PluginFilter;

/**
 * A Listener class to print messages when plugins added/removed
 * @author sebastien Baty and mikolai Krol
 */
public class SimplePluginObserver implements FileListener {

	@Override
	public void fileAdded(FileEvent e) {
		System.out.println("New plugin detected : " + e.getFilename().substring(0, e.getFilename().lastIndexOf(".")));
	}

	@Override
	public void fileRemoved(FileEvent e) {
		System.out.println(e.getFilename().substring(0, e.getFilename().lastIndexOf(".")) + "plugin removed.");
	}
	
	public static void main(String[] args) {
		// Main Q4 Ex4
		File dir = new File("extensions/plugins");
		FileChecker checker = new FileChecker(new PluginFilter(), dir);
		checker.addFileListener(new SimplePluginObserver());
		checker.startAnalysing();
		
		while(true);

	}

}
