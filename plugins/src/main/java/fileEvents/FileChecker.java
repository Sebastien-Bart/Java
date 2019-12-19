package fileEvents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

/**
 * a checker class that check each DELAY_ANALYSE a folder
 * to spot new/removed files accordind to its attached filter
 * @author sebastien Bart and mikolai Krol
 */
public class FileChecker {

	private static final int DELAY_ANALYSE = 1000;

	private FilenameFilter filter;
	private ArrayList<FileListener> fileListeners;
	
	protected Timer timer; // protected pour les tests
	
	/**
	 * @param f the filter of the checker
	 * @param dir the analysed dir
	 */
	public FileChecker(FilenameFilter f, File dir) {
		filter = f;
		fileListeners = new ArrayList<FileListener>();
		timer = new Timer(DELAY_ANALYSE, new CheckerListener(dir));
	}
	
	/**
	 * @param listener the listener to add
	 */
	public synchronized void addFileListener(FileListener listener) {
		fileListeners.add(listener);
	}
	
	/**
	 * @param listener the listener to remove
	 */
	public synchronized void removeFileListener(FileListener listener) {
		fileListeners.remove(listener);
	}
	
	/**
	 * fire the event FileEvent and calls fileAdded
	 * @param filename the name of the added file
	 */
	public void fireFileAdded(String filename) {
		if (fileListeners.size() > 0) {
			FileEvent event = new FileEvent(this, filename);
			for (FileListener listener : fileListeners) {
				listener.fileAdded(event);
			}
		}
	}
	
	/**
	 * fire the event FileEvent and calls fileRemoved
	 * @param filename the name of the removed file
	 */
	public void fireFileRemoved(String filename) {
		if (fileListeners.size() > 0) {
			FileEvent event = new FileEvent(this, filename);
			for (FileListener listener : fileListeners) {
				listener.fileRemoved(event);
			}
		}
	}
	
	/**
	 * Start the analyse of the folder until stoped
	 */
	public void startAnalysing() {
		timer.start();
	}
	
	/**
	 * Stop the timer
	 */
	public void stopAnalysing() {
		timer.stop();
	}
	
	
	/**
	 * An ActionListener to check if new files have been added
	 * @author sebastien Bart and Krol Mikolai
	 */
	private class CheckerListener implements ActionListener {
		
		private List<String> knownFiles;
		private FileFinder finder;
		
		/**
		 * Constructor for CheckerLister
		 */
		public CheckerListener(File dir) {
			this.finder = new FileFinder(dir);
			this.knownFiles = new ArrayList<String>(); // ATTENTION : a l'execution si fichier present
		}											// ca prendra en compte les fichiers la avant le start

		@Override
		public void actionPerformed(ActionEvent e) {
			List<String> resultAnalyse = finder.listFilesWithFilter(filter);
			ArrayList<String> toRemove = new ArrayList<String>();
			for (String filename : resultAnalyse) {
				if (!knownFiles.contains(filename)) {
					fireFileAdded(filename);
					knownFiles.add(filename);
				}
			}
			for (String filename : knownFiles) {
				if (!resultAnalyse.contains(filename)) {
					fireFileRemoved(filename);
					toRemove.add(filename);
				}
			}
			knownFiles.removeAll(toRemove);
		}

	}
	
}
