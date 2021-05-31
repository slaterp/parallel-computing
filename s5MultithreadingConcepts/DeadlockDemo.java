package s5MultithreadingConcepts;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * This program demonstrates how deadlocking can occur. Deadlocking is when two or more threads rely on other threads to finish executing
 * before they can also terminate their execution BUT the other threads also rely on these threads to complete there execution, thus
 * creating an impassable block where the threads will wait forever for the other threads to complete.
 * 
 * In the demo, initially worker1 acquires lock1 and worker2 lock2, after both sleeping for 300ms they attempt to acquire the other's
 * lock which is impossible because there are still engaged. This causes the deadlocking where they both wait forever for the other's lock
 * to be freed up for them.
 */

public class DeadlockDemo {
	private Lock lock1 = new ReentrantLock(true);
	private Lock lock2 = new ReentrantLock(true);


	public static void main(String[] args) {
		DeadlockDemo deadlockDemo = new DeadlockDemo();
		
		// Instantiating threads using lambda functions
		new Thread(deadlockDemo::worker1,"worker1").start();
		new Thread(deadlockDemo::worker2,"Worker2").start();
		
	}
	
	public void worker1() {
		lock1.lock();
		System.out.println("Worker1 acquires lock1...");
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock2.lock();
		System.out.println("Worker1 acquires lock2...");
		
		lock1.unlock();
		lock2.unlock();
	}
	
	public void worker2() {
		lock2.lock();
		System.out.println("Worker2 acquires lock2...");
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock1.lock();
		System.out.println("Worker2 acquires lock1...");
		
		lock1.unlock();
		lock2.unlock();
	}

}
