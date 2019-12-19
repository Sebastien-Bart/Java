package fileFilters;

import java.io.File;
import java.io.FilenameFilter;

/**
 * A filter to check if filename starts with parameter string
 * @author sebastien Bart and Mikolai Krol
 */
public class StartsWithFilter implements FilenameFilter {

	private String start; // different de ce qu'on a vu en td mais mieux non ?
	
	/**
	 * Constructor for StartsWithFilter
	 * @param s the string to check if file starts with
	 */
	public StartsWithFilter(String s){
		this.start = s;
	}

	@Override
	public boolean accept(File dir, String filename) {
		return filename.startsWith(this.start);
	}

}
