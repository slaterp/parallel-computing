package s3ThreadManipulation;

/*
 * This program is an illustrative example of the difference between a Daemon thread and Main (worker) thread.
 * In short, Daemon threads will continue execution until the program has finished running. In this program, completion
 * brought on by the end of the NormalWorker thread (which simply waits for 3 seconds before terminating).
 */

class DaemonWorker implements Runnable {

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Daemon is running");
		}
	}
	
}

class NormalWorker implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Normal worker had completed execution (thread done)");
	}
	
}

public class DaemonExample {

	public static void main(String[] args) {
		Thread t1 = new Thread(new DaemonWorker());
		Thread t2 = new Thread(new NormalWorker());
		
		t1.setDaemon(true);
		
		t1.start();
		t2.start();
	}

}
