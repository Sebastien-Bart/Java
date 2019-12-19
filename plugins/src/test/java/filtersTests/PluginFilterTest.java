package filtersTests;

import java.io.FilenameFilter;

import fileFilters.PluginFilter;

public class PluginFilterTest extends FilterTest {

	@Override
	public FilenameFilter createFilter() {
		return new PluginFilter();
	}

	@Override
	public String createAcceptedFilename() {
		return "ErasePlugin.class";
	}

}
