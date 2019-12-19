package filtersTests;

import java.io.FilenameFilter;
import fileFilters.EndsWithFilter;

public class EndsWithFilterTest extends FilterTest {

	@Override
	public FilenameFilter createFilter() {
		return new EndsWithFilter(".class");
	}

	@Override
	public String createAcceptedFilename() {
		return "MyClass.class";
	}

}
