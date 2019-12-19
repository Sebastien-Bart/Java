package filtersTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FilenameFilter;

import org.junit.Before;
import org.junit.Test;

public abstract class FilterTest {
	
	protected String testDirName = "src/test/resources";
	
	protected FilenameFilter filter;
	protected String accepetedFilename;
	protected String notAcceptedFilename;
	
	public abstract FilenameFilter createFilter();
	
	public abstract String createAcceptedFilename();
	
	@Before
	public void init() {
		this.filter = this.createFilter();
		this.accepetedFilename = createAcceptedFilename();
		this.notAcceptedFilename = "aaaa";
	}
	
	@Test
	public void testAcceptReturnsTrue() {
		assertTrue(this.filter.accept(null, this.accepetedFilename));
	}
	
	@Test
	public void testAcceptReturnsFalse() {
		assertFalse(this.filter.accept(null, notAcceptedFilename));
	}

}
