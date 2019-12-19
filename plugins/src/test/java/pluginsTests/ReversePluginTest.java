package pluginsTests;

import static org.junit.Assert.*;

import plugin.Plugin;
import plugins.ReversePlugin;

public class ReversePluginTest extends PluginTest{

	@Override
	public Plugin createPlugin() {
		return new ReversePlugin();
	}

	@Override
	public void testTransform() {
		assertEquals("edcba", plugin.transform(PluginTest.TEST));
	}

}
