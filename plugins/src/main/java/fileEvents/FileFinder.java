package fileEvents;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

/**
 * A class to find file from a dir
 * @author sebastien Bart and Mikolai Krol
 */
public class FileFinder {
	
	private File dir;
	
	/**
	 * Constructor for FileFinder
	 * @param dir the dir to search the files in
	 */
	public FileFinder(File dir) {
		this.dir = dir;
	}
	
	/**
	 * Different de ce qu'on a vu en TD: on etait censé avoir 2 methodes (listFilesStartsWithC / listFilesEndsWithClass)
	 * Mais je trouve que c'est mieux comme ca.
	 * @param f The filenameFilter
	 * @return the list of the names of the files that passed the filter f in the dir
	 */
	public List<String> listFilesWithFilter(FilenameFilter f) {
		return Arrays.asList(dir.list(f));
	}
	
}
