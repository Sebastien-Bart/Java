package plugins;

import plugin.Plugin;

/**
 * A plugin that reverse the text
 * @author sebastien bart and mikolai krol
 */
public class ReversePlugin implements Plugin {

	@Override
	public String transform(String s) {
		String res = "";
		for (int i = s.length()-1; i >= 0; i--) {
			res += s.charAt(i);
		}
		return res;
	}

	@Override
	public String getLabel() {
		return "Reverse";
	}

	@Override
	public String helpMessage() {
		return "Reverse the text.";
	}
	
	public static void main(String args[]) {
		new ReversePlugin().transform("abc");
	}

}
