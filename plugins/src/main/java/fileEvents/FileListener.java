package fileEvents;

import java.util.EventListener;

/**
 * A FileListener interface to spot new files
 * @author sebastien Bart and Mikolai Krol
 */
public interface FileListener extends EventListener {

	/**
	 * do something when a file is added
	 * @param e the FileEvent emitted
	 */
	public void fileAdded(FileEvent e);
	
	/**
	 * do something when the file is removed
	 * @param e the FileEvent emitted
	 */
	public void fileRemoved(FileEvent e);
	
}
