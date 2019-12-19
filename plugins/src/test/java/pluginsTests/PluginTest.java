package pluginsTests;

import org.junit.Before;
import org.junit.Test;

import plugin.Plugin;

public abstract class PluginTest {

	protected Plugin plugin;
	protected static final String TEST = "abcde";  
	
	@Before
	public void before() {
		this.plugin = this.createPlugin();
	}
	
	public abstract Plugin createPlugin();
	
	@Test
	public abstract void testTransform();
}
