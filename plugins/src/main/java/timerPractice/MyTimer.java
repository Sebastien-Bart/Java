package timerPractice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * a class to pratice with timers
 * @author sebastien bart and krol mikolai
 */
public class MyTimer {

	private static int delay = 1000;
	private Timer timer;
	
	/**
	 * Constructor for MyTimer
	 */
	public MyTimer() {
		this.timer = new Timer(delay, new MyListener());
	}
	
	/**
	 * Starts the timer
	 */
	public void start() {
		this.timer.start();
	}
	
	/**
	 * An ActionListener class to print actual time when fired
	 * @author sebastien Bart and Krol Mikolai
	 */
	public class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(java.util.Calendar.getInstance().getTime());
		}

	}
	
	// MAIN
	public static void main(String[] args) {
		MyTimer timer = new MyTimer();
		timer.start();
		
		// Boucle infinie
		while(true);
	}

}
