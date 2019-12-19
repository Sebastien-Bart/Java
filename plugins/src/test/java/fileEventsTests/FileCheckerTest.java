package fileEventsTests;

import static org.junit.Assert.assertTrue;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.swing.Timer;

import org.junit.Before;
import org.junit.Test;

import fileEvents.FileChecker;
import fileEvents.FileEvent;
import fileEvents.FileListener;
import fileFilters.StartsWithFilter;

public class FileCheckerTest {
	
	private MockFileChecker checker;
	private FilenameFilter filter;
	private MockFileListener listener;
	
	private static final String ANALYSED_DIR = "src/test/resources";
	
	@Before
	public void init() {
		filter = new StartsWithFilter("C");
		File analysedFile = new File(ANALYSED_DIR);
		checker = new MockFileChecker(filter, analysedFile);
		listener = new MockFileListener();
		checker.addFileListener(listener);
	}
	
	@Test
	public void testFireFileAdded() {
		checker.fireFileAdded(null);
		assertTrue(listener.fileAddedCalled);
	}
	
	@Test
	public void testFireFileRemoved() {
		checker.fireFileRemoved(null);
		assertTrue(listener.fileRemovedCalled);
	}
	
	@Test
	public void testWhenAddFileInAnalysedDir() throws IOException {
		File file = new File(ANALYSED_DIR + "/Ctest");
		file.createNewFile();
		checker.getTimer().doAction();
		file.delete();
		assertTrue(listener.fileAddedCalled);
	}
	
	@Test
	public void testWhenRemoveFileInAnalysedDir() throws IOException {
		File file = new File(ANALYSED_DIR + "/Ctest");
		file.createNewFile();
		checker.getTimer().doAction();
		assertTrue(listener.fileAddedCalled);
		file.delete();
		checker.getTimer().doAction();
		assertTrue(listener.fileRemovedCalled);
	}
	
	/**
	 * Mock FileListener to test FileChecker
	 * @author bart and krol
	 */
	private class MockFileListener implements FileListener {
		
		private boolean fileAddedCalled = false;
		private boolean fileRemovedCalled = false;
		
		public MockFileListener() {}

		@Override
		public void fileAdded(FileEvent e) {
			fileAddedCalled = true;
		}

		@Override
		public void fileRemoved(FileEvent e) {
			fileRemovedCalled = true;
		}
		
	}
	
	// pour pouvoir utiliser fireActionPerformed
	@SuppressWarnings("serial")
	private class MockTimer extends Timer {

		public MockTimer(int delay, ActionListener listener) {
			super(delay, listener);
		}
		
		public void doAction() {
			this.fireActionPerformed(null);
		}
		
	}
	
	
	/**
	 * Mock 
	 * @author sebastien bart and mikolai krol
	 */
	private class MockFileChecker extends FileChecker {

		public MockFileChecker(FilenameFilter f, File dir) {
			super(f, dir);
			this.timer = new MockTimer(this.timer.getDelay(), this.timer.getActionListeners()[0]);
		}
		
		public MockTimer getTimer() {
			return (MockTimer) this.timer;
		}
		
	}

}
