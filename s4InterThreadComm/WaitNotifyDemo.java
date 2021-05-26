package s4InterThreadComm;

class Process {
	
	public void Produce() throws InterruptedException {
		
		synchronized(this) {
			System.out.println("Running the produce method...");
			wait();
			System.out.println("Back in the produce method...");
		}
	}
	
	public void Consume() throws InterruptedException {
		
		Thread.sleep(1000);
		
		synchronized(this) {
			System.out.println("Consume method is executed...");
			notify();
		}
	}
}

public class WaitNotifyDemo {

	public static void main(String[] args) {
		Process process = new Process();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					process.Produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					process.Consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
		});
		
		t1.start();
		t2.start();
	}

}
