package s4InterThreadComm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Simple demo to illustrate the use of Reentrant locks, and the .await() and .signal() interaction.
 * Here, thread 1 executes the initial print operation then 'awaits' the 'signal' from thread 2 to complete
 * its execution.
 */

class Worker {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void producer() throws InterruptedException {
		lock.lock();
		System.out.println("Producer method...");
		condition.await();
		Thread.sleep(2000);
		System.out.println("Producer again...");
		lock.unlock();
	}
	
	public void consumer() throws InterruptedException {
		lock.lock();
		Thread.sleep(2000);
		//condition.signal();
		//lock.unlock();
		System.out.println("Consumer method...");
		condition.signal();
		lock.unlock();
	}
}

public class ReentrantLockDemo {

	public static void main(String[] args) {
		Worker worker = new Worker();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					worker.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					worker.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
		});
		
		t1.start();
		t2.start();
		
		
	}

}
