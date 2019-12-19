package fileFilters;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.*;

/**
 * A filter to check if a file is a plugin
 * @author sebastien Baty and mikolai Krol
 */
public class PluginFilter implements FilenameFilter {
	
	public PluginFilter() {	}

	@Override
	public boolean accept(File dir, String name) {
		// condition .class
		boolean endWithClass = name.endsWith(".class");
		
		try {
			Class<?> c = Class.forName("plugins."+name.substring(0, name.lastIndexOf(".")));
			// condtion dans package plugin
			String packageName = c.getPackage().getName();
			// condition constructeur sans parametres
			Constructor<?> constructor = c.getConstructor();
			//condition implemente l'interface Plugin
			Type[] types = c.getGenericInterfaces();
			boolean implemPlugin = false;
			for (Type type : types) {
				if (type.getTypeName().equals("plugin.Plugin"))
					implemPlugin = true;
			}
			
			return (implemPlugin && endWithClass && packageName.equals("plugins"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
