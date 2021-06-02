package s6Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class TaskTwo implements Runnable {
	private int id;
	
	public TaskTwo(int id) {
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

public class FixedThreadPoolDemo {

	public static void main(String[] args) {
		// Single thread executor is just one thread that executes tasks one after another
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		for (int i = 0; i<5; i++) {
			executor.execute(new TaskTwo(i));
		}
		
	}

}

