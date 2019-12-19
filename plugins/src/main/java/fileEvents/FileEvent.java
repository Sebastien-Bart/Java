package fileEvents;

import java.util.EventObject;

/**
 * An Event class to spot new/removed Files
 * @author sebastien Bart and Krol Mikolai
 */
public class FileEvent extends EventObject {
	
	private String filename;
	
	/**
	 * Constructor for FileEvent
	 * @param source the source of the Event
	 * @param name the name of the file
	 */
	public FileEvent(Object source, String name) {
		super(source);
		this.filename = name;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

}
