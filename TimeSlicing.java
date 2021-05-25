/*
 * Basic example of Time-Slicing algorithm
 * 
 * What is time-slicing? Time-slicing is an approach to multi-threading where processing time of multiple threads 
 * is shared. In this example, the CPU alternates between processing operations for thread 1 (Runner 1) and thread 2 (Runner 2).
 * 
 * Note: Time-slicing is not an example of parallel execution in that they are not executed simultaneously.
 */

class Runner1 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Runner 1: " + i);
		}
	}
	
}

class Runner2 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Runner 2: " + i);
		}
	}
	
}

public class TimeSlicing {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runner1());
        Thread t2 = new Thread(new Runner2());
        
        t1.start();
        t2.start();
        
    }

}