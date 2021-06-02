package s7ConcurrentCollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronisedListDemo {

	public static void main(String[] args) {
		
		// Add and remove methods are synchronised
		List<Integer> nums =  Collections.synchronizedList(new ArrayList<>());
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					nums.add(i);
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					nums.add(i);
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Size of list is: " + nums.size());
	}

}
