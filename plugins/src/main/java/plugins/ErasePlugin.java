package plugins;

import plugin.Plugin;

/**
 * A plugin that erase everything
 * @author sebastien bart and mikolai krol
 */
public class ErasePlugin implements Plugin{
	
	@Override
	public String transform(String s) {
		return "";
	}

	@Override
	public String getLabel() {
		return "Erase";
	}

	@Override
	public String helpMessage() {
		return "Erase everything !";
	}

}
