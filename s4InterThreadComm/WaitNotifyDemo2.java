package s4InterThreadComm;

import java.util.ArrayList;
import java.util.List;

/*
 * This program shows a more complex interaction between wait and notify. In this program both the producer and consumer operate 
 * in infinite loops [while(true)]. To break these loops, the program uses the behaviour of wait and notify. In the producer, once the 
 * upper limit is reached, the thread is put in a waiting state which switches to the consumer thread. The consumer deletes all the elements 
 * from the array then puts itself into its own waiting state. 
 * NOTE: notify() method will be called either by the current thread completing all execution tasks (see previous WaitNotifyDemo) 
 * or the current thread being put into a waiting state. 
 */

class Processor {
	private List<Integer> list = new ArrayList<>();
	private static final int UPPER_LIMIT = 5;
	private static final int LOWER_LIMIT = 0;
	private final Object lock = new Object();
	private int value = 0;
	
	public void producer() throws InterruptedException {
		synchronized(lock) {
			while(true) {
				if(list.size() == UPPER_LIMIT) {
					System.out.println("Waiting for item removal...");
					lock.wait();
				}
				else {
					System.out.println("Adding: " + value);
					list.add(value);
					value++;
					lock.notify();
				}
				Thread.sleep(500);
			}			
		}
	}
	
	public void consumer() throws InterruptedException {
		synchronized(lock) {
			while(true) {
				if(list.size() == LOWER_LIMIT) {
					System.out.println("Waiting for item addition...");
					value = 0;
					lock.wait();
				}
				else {
					System.out.println("Removing: " + list.remove(list.size()-1));
					lock.notify();
				}
				Thread.sleep(500);
			}
		}
	}
	
	
}

public class WaitNotifyDemo2 {

	public static void main(String[] args) {
		Processor processor = new Processor();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					processor.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					processor.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
		});
		
		t1.start();
		t2.start();

	}

}
