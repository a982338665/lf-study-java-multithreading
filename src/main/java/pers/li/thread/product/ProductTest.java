package pers.li.thread.product;
/**
 * 经典生产者与消费者问题
 * 生产者不断的往仓库中存放产品，消费者从仓库中消费产品。
 * 其中生产者和消费者都可以有若干个。
 * 仓库规则：容量有限，库满时不能存放，库空时不能取产品 。
 */

public class ProductTest {
	public static void main(String[] args) throws InterruptedException {
		Storage storage = new Storage();
		
		Thread consumer1 = new Thread(new Consumer(storage));
		consumer1.setName("消费者1");
		Thread consumer2 = new Thread(new Consumer(storage));
		consumer2.setName("消费者2");
		Thread producer1 = new Thread(new Producer(storage));
		producer1.setName("生产者1");
		Thread producer2 = new Thread(new Producer(storage));
		producer2.setName("生产者2");
		
		producer1.start();
		producer2.start();
		Thread.sleep(1000);		
		consumer1.start();
		consumer2.start();		
	}
}








