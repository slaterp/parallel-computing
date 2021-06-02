package s6Executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class StockMarketUpdater implements Runnable {

	@Override
	public void run() {
		System.out.println("Updating and downloading stock related data from the web...");
	}
	
}

public class ScheduledThreadPoolDemo {

	public static void main(String[] args) {
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		
		executor.scheduleAtFixedRate(new StockMarketUpdater(), 1000, 2000, TimeUnit.MILLISECONDS);
		
		
	}

}
