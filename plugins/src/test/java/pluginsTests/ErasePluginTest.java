package pluginsTests;

import static org.junit.Assert.*;

import plugin.Plugin;
import plugins.ErasePlugin;

public class ErasePluginTest extends PluginTest{

	@Override
	public Plugin createPlugin() {
		return new ErasePlugin();
	}

	@Override
	public void testTransform() {
		assertEquals("", plugin.transform(PluginTest.TEST));
	}

}
