package s6Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * This program demonstrates single thread executors. Single thread executors, by themselves, are not really concurrency or multi-threading
 * because it is just one thread executing tasks sequentially (see program output). Note: this program will run indefinitely until stopped
 * by the user because the executor has not been terminated (executor termination was not covered at this point in the course).
 */

class Task implements Runnable {
	private int id;
	
	public Task(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Task with id " + id + " in is work - thread id: " + Thread.currentThread().getName());
		long duration = (long) (Math.random()*5);
		
		try {
			Thread.sleep(duration*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class SingleThreadExectuorDemo {

	public static void main(String[] args) {
		// Single thread executor is just one thread that executes tasks one after another
		ExecutorService executor = Executors.newSingleThreadExecutor(); 
		
		for (int i = 0; i<5; i++) {
			executor.execute(new Task(i));
		}
		
	}

}
