package s5MultithreadingConcepts;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Livelock demo shows how livelock differentiates from deadlock. Here the threads a stuck in a loop of responding to one another.
 */

public class LivelockDemo {
	private Lock lock1 = new ReentrantLock(true);
	private Lock lock2 = new ReentrantLock(true);
	
	public static void main(String[] args) {
		LivelockDemo livelockDemo = new LivelockDemo();
		
		new Thread(livelockDemo::worker1,"worker1").start();
		new Thread(livelockDemo::worker2,"Worker2").start();
	}
	
	public void worker1() {
		
		while(true) {
			try {
				lock1.tryLock(50, TimeUnit.MILLISECONDS);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Worker1 has acquired lock1...");
			System.out.println("Worker1 is now trying to acquire lock2...");
			
			if(lock2.tryLock()) {
				System.out.println("Worker1 has acquired lock 2...");
				lock2.unlock();
			} else {
				System.out.println("Worker1 cannot acquire lock 2...");
				continue;
			}
			
			break;
		}
		
		lock1.unlock();
		lock2.unlock();
	}
	
	public void worker2() {
		
		while(true) {
			try {
				lock2.tryLock(50, TimeUnit.MILLISECONDS);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Worker2 has acquired lock2...");
			System.out.println("Worker2 is now trying to acquire lock1...");
			
			if(lock1.tryLock()) {
				System.out.println("Worker2 has acquired lock 1...");
				lock1.unlock();
			} else {
				System.out.println("Worker2 cannot acquire lock 1...");
				continue;
			}
			
			break;
		}
		
		lock1.unlock();
		lock2.unlock();
	}

}
