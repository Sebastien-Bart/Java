package gui;

import javax.swing.JMenuItem;

import plugin.Plugin;

/**
 * A personnalized JMenuItem class to attach a plugin to it
 * @author sebastien
 */
public class JPluginMenuItem extends JMenuItem {

	protected Plugin plugin;
	
	/**
	 * @param plugin the plugin attached to the menuItem
	 */
	public JPluginMenuItem(Plugin plugin) {
		super();
		this.setLabel(plugin.getLabel());
		this.plugin = plugin;
	}
	
	/**
	 * @return the attached plugin
	 */
	public Plugin getPlugin() {
		return plugin;
	}

}
