package s5MultithreadingConcepts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/*
 * This program demonstrates the use of semaphores in multithreading. The first argument in the semaphore constructor tells the
 * semaphore how many threads it can run concurrently (try 2 or 6 as well). The executor service is a convenience measure
 * that generates the threads which is managed by the semaphore allowing only 3 (or whatever number is specified) to be 
 * executed, whilst the other threads wait. This approach to concurrency is useful when we want to limit load on a server, 
 * for example when trying to buy tickets on a ticket website, the engineers can limit the number of people allowed onto the
 * purchase page to ensure servers do not crash with the massive load. 
 */

enum Downloader {
	INSTANCE;
	
	private Semaphore semaphore = new Semaphore(3, true);
	
	public void downloadData() {
		try {
			semaphore.acquire();
			download();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}

	void download() {
		System.out.println("Downloading data from the web...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

public class SemaphoreDemo {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 12; i++) {
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					Downloader.INSTANCE.downloadData();
				}
			});
		}
	}

}
