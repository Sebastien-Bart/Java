package plugin;

/**
 * An interface to define what a plugin does
 * @author sebastien Bart and mikolai Krol
 */
public interface Plugin {
	
	/**
	 * transform the String s according to the plugin function
	 * @param s the text to tranform
	 * @return the transformed text
	 */
	public String transform(String s);
	
	/**
	 * @return the label of the plugin
	 */
	public String getLabel();
	
	/**
	 * @return a help message to tell what the plugin does
	 */
	public String helpMessage();

}
