package pers.li.thread.product;

/**
 * 消费者
 */
class Consumer implements Runnable {
	private Storage storage;

	public Consumer(Storage storage) {
		this.storage = storage;
	}

	public void run() {
		int i = 0;
		while(i<10)
		{
			i++;
			storage.pop();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
