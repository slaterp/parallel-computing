package s4InterThreadComm;

public class SynchronisationDemo {
	
	public static int counter = 0;
	
	// The synchronized keyword makes sure this method is only executed by a single thread at a given time.
	// This avoids the problem where incrementation does not properly occur because both threads call
	// the counter increment at the same time.
	public static synchronized void increment() {
		counter++;
	}
	
	public static void process() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {	
				for (int i = 0; i < 100; i++) {
					increment();
				}
			} 
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					increment();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(counter);
		
	}

	public static void main(String[] args) {
		process();
	}

}
