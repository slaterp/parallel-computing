package s5MultithreadingConcepts;

/*
 * The volatile keyword can be used to force the computer to store the variable in RAM as opposed to the CPU cache.
 * Why is this important to concurrency? Using the volatile keyword forces the computer to perform any write operation on that variable
 * BEFORE any subsequent read operation. This means if one thread is to read a volatile variable, it must first see if there is any write
 * operations waiting to be performed on it in another thread.
 * What are the downsides to 'volatile'? Storing the variable in the CPU cache makes operations that involve the variable 
 * significantly faster thus it should be used only when necessary.
 */

class Worker implements Runnable {
	
	private volatile boolean terminated;

	public boolean isTerminated() {
		return terminated;
	}

	public void setTerminated(boolean terminated) {
		this.terminated = terminated;
	}

	@Override
	public void run() {
		while(!terminated) {
			System.out.println("Working class is running...");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

public class VolatileDemo {

	public static void main(String[] args) {
		Worker worker = new Worker();
		Thread t1 = new Thread(worker);
		
		t1.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		worker.setTerminated(true);
		System.out.println("Algorithm is terminated...");
		
	}

}
