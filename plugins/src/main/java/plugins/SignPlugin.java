package plugins;

import plugin.Plugin;

/**
 * A plugin that sign the text with our names
 * @author sebastien bart and mikolai krol
 */
public class SignPlugin implements Plugin {

	@Override
	public String transform(String s) {
		return s += "\n Sébastien Bart and Mikolai Krol";
	}

	@Override
	public String getLabel() {
		return "Sign";
	}

	@Override
	public String helpMessage() {
		return "Sign the document";
	}

}
