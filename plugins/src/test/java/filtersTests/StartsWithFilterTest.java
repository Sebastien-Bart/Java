package filtersTests;

import java.io.FilenameFilter;

import fileFilters.StartsWithFilter;

public class StartsWithFilterTest extends FilterTest {

	@Override
	public FilenameFilter createFilter() {
		return new StartsWithFilter("Start");
	}

	@Override
	public String createAcceptedFilename() {
		return "StartFile.test";
	}

}
