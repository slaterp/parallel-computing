/*
 * Thread.join() method forces the program to wait for the thread to complete execution before continuing with the program.
 * In this example the program must wait for the full execution of both runners before printing "Threads finished!".
 */

class Runner3 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Runner 3: " + i);
		}
	}
	
}

class Runner4 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Runner 4: " + i);
		}
	}
	
}

public class JoinExample {
	public static void main(String[] args) {
        Thread t1 = new Thread(new Runner3());
        Thread t2 = new Thread(new Runner4());
        
        t1.start();
        t2.start();
        
        try {
        	t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("Threads finished!");
        
    }
}
