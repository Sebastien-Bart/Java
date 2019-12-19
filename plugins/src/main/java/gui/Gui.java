package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fileEvents.FileChecker;
import fileEvents.FileEvent;
import fileEvents.FileListener;
import fileFilters.PluginFilter;
import plugin.Plugin;

/**
 * a class to mange the gui of the plugin project
 * @author sebastien bart and mikolai krol
 */
public class Gui implements FileListener {
	
	private JFrame myFrame;
	private JTextArea myTextArea;
	
	private JMenuBar myMenuBar;
	private JMenu tools;
	private JMenu help;
	
	private static final File ANALYSED_DIR = new File("extensions/plugins");
	
	private static int NB_GUI_RUNNING = 0;
	private static int WINDOW_SPACING = 0;
	private static ArrayList<String> actualPlugins = new ArrayList<String>();
	private static FileChecker checker = new FileChecker(new PluginFilter(), ANALYSED_DIR);
	
	/**
	 * Constructor for gui
	 */
	public Gui() {
		myFrame = new JFrame("Editeur de texte ultra cool");
		myFrame.addWindowListener(new CloseWindowEvent());
		myFrame.setSize(600, 400);
		initMenu();
		initText();
		checker.addFileListener(this);
		NB_GUI_RUNNING++;
		myFrame.setLocation(200 + WINDOW_SPACING, 200 + WINDOW_SPACING);
	}
	
	
	@Override
	public void fileAdded(FileEvent e) {
		String cutFilename = e.getFilename().substring(0, e.getFilename().lastIndexOf("."));
		try {
			buildAndAddPluginToMenu(cutFilename);
			if (!actualPlugins.contains(cutFilename)) {
				actualPlugins.add(cutFilename);
			}
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void fileRemoved(FileEvent e) {
		String cutFilename = e.getFilename().substring(0, e.getFilename().lastIndexOf("."));
		try {
			Class<?> pluginClass = Class.forName("plugins." + cutFilename);
			Constructor<?> pluginConstructor = pluginClass.getConstructor();
			Plugin plugin = (Plugin) pluginConstructor.newInstance();
			
			if (actualPlugins.contains(cutFilename)) {
				actualPlugins.remove(cutFilename);
			}
			for (Component component : tools.getMenuComponents()) {
				if (component instanceof JPluginMenuItem) {
					JPluginMenuItem item = (JPluginMenuItem) component;
					if (item.getLabel().equals(plugin.getLabel()))
						tools.remove(item);
				}
			}
			for (Component component : help.getMenuComponents()) {
				if (component instanceof JPluginMenuItem) {
					JPluginMenuItem item = (JPluginMenuItem) component;
					if (item.getLabel().equals(plugin.getLabel()))
						help.remove(item);
				}
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * @param pluginName the name of the plugin
	 */
	private void buildAndAddPluginToMenu(String pluginName) {
		try {
			Class<?> c = Class.forName("plugins."+pluginName);
			Constructor<?> construct = c.getConstructor();
			JPluginMenuItem toolMenuItem = new JPluginMenuItem((Plugin) construct.newInstance());
			toolMenuItem.addActionListener(new ApplyPlugin(toolMenuItem.getPlugin()));
			JPluginMenuItem helpMenuItem = new JPluginMenuItem(toolMenuItem.getPlugin());
			helpMenuItem.addActionListener(new ShowHelpPlugin(helpMenuItem.getPlugin()));
			
			tools.add(toolMenuItem);
			help.add(helpMenuItem);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initiate the MenuBar for gui
	 */
	private void initMenu() {
		myMenuBar = new JMenuBar();
		
		// premier menu "File"
		JMenu file = new JMenu("File");
			// New
		JMenuItem nouveau = new JMenuItem("New");
		nouveau.addActionListener(new NewGui());
		file.add(nouveau);
			// Open
		JMenuItem open = new JMenuItem("Open...");
		open.addActionListener(new askForFile());
		file.add(open);
		file.addSeparator();
			// Exit
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new CloseWhenExit());
		file.add(exit);
			// Exit all
		JMenuItem exitall = new JMenuItem("Exit all");
		exitall.addActionListener(new ExitAll());
		file.add(exitall);
		
		myMenuBar.add(file);
		
		// deuxieme Menu "Tools"
		tools = new JMenu("Tools");
		myMenuBar.add(tools);
		
		// troisieme menu "Help"
		help = new JMenu("Help");
		myMenuBar.add(help);
		
		myFrame.add(myMenuBar, BorderLayout.NORTH);
	}
	
	/**
	 * Initiate the TextArea for gui
	 */
	private void initText() {
		myTextArea = new JTextArea();
		myFrame.add(new JScrollPane(myTextArea));
	}
	
	private void initMenuForNewGui() {
		for (String pluginName : actualPlugins) {
			buildAndAddPluginToMenu(pluginName);
		}
	}
	
	/**
	 * start the listener
	 */
	public void run() { // SEUL LE 1er GUI EST RUN
		myFrame.setVisible(true);
		checker.startAnalysing();
	}
	
	// ACTION LISTENERS -------------------------------------------
	
	
	
	/**
	 * destroy when click on "X"
	 * @author sebastien and mikolai krol
	 */
	private class CloseWindowEvent extends WindowAdapter {
		public void windowClosing(java.awt.event.WindowEvent e) {
			if (NB_GUI_RUNNING == 1) {
				System.exit(0);
			}
			else {
				Gui.this.myFrame.dispose();
				WINDOW_SPACING -= 20;
				NB_GUI_RUNNING--;
			}
		}
	}
	
	/**
	 * for the "Exit" JMenuItem
	 * @author sebastien and mikolai krol
	 */
	private class CloseWhenExit implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (NB_GUI_RUNNING == 1) {
				System.exit(0);
			}
			else {
				Gui.this.myFrame.dispose();
				NB_GUI_RUNNING--;
				WINDOW_SPACING -= 20;
			}
		}
	}
	
	/**
	 * for the "New" JMenuItem
	 * @author sebastien and mikolai krol
	 */
	private class NewGui implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			WINDOW_SPACING += 20;
			Gui newGui = new Gui();
			newGui.initMenuForNewGui();
			newGui.myFrame.setVisible(true);
		}
		
	}
	
	/**
	 * for JPluginMenuItem: apply plugin effect
	 * @author sebastien and mikolai krol
	 */
	private class ApplyPlugin implements ActionListener {
		
		private Plugin plugin;
		
		public ApplyPlugin(Plugin p) {
			this.plugin = p;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Gui.this.myTextArea.setText(plugin.transform(Gui.this.myTextArea.getText()));
		}
	}
	
	/**
	 * for JPluginMenuItem: show help of the plugin
	 * @author sebastien and mikolai krol
	 */
	private class ShowHelpPlugin implements ActionListener {
		
		private Plugin plugin;
		
		public ShowHelpPlugin(Plugin p) {
			this.plugin = p;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(Gui.this.myFrame, plugin.helpMessage());
		}
	}
	
	/**
	 * For "Exit All" : quit the program
	 * @author sebastien and mikolai krol
	 */
	private class ExitAll implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
	}
	
	/**
	 * For "Open" : make the user select a file to open
	 * @author sebastien bart and mikolai krol
	 */
	private class askForFile implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser chooser = new JFileChooser(new File("."));
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			int returnVal = chooser.showOpenDialog(Gui.this.myFrame);

		    if (returnVal == JFileChooser.APPROVE_OPTION) {
		        File file = chooser.getSelectedFile();
		        String txt = "";
		        try {
					for (String string : Files.readAllLines(file.toPath())) {
						txt += string + "\n";
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		        Gui.this.myTextArea.setText(txt);
		    } 
		}
	}

}
