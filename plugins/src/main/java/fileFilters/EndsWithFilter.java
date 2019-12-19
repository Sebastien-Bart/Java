package fileFilters;

import java.io.File;
import java.io.FilenameFilter;

/**
 * A filter to check if filename ends with parameter string
 * @author sebastien Bart and Mikolai Krol
 */
public class EndsWithFilter implements FilenameFilter {
	
	private String end; // different de ce qu'on a vu en td mais mieux non ?
	
	/**
	 * Constructor for EndsWithFilter
	 * @param s the string to check if file ends with
	 */
	public EndsWithFilter(String s) {
		this.end = s;
	}

	@Override
	public boolean accept(File dir, String filename) {
		return filename.endsWith(end);
	}

}
