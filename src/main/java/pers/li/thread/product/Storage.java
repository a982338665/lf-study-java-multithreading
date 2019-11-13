package pers.li.thread.product;

/**
 *仓库
 */
class Storage {
	// 仓库容量为10
	private Product[] products = new Product[10];
	private int top = 0;

	// 生产者往仓库中放入产品
	public synchronized void push(Product product) {
		while (top == products.length) {
			try {
				System.out.println("producer wait");
				wait();//仓库已满，等待
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        //把产品放入仓库
		products[top++] = product;
		System.out.println(Thread.currentThread().getName() + " 生产了产品"
				+ product);
		System.out.println("producer notifyAll");
		notifyAll();//唤醒等待线程
		

	}

	// 消费者从仓库中取出产品
	public synchronized Product pop() {
		while (top == 0) {
			try {
				System.out.println("consumer wait");
				wait();//仓库空，等待
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		//从仓库中取产品
		--top;
		Product p = new Product(products[top].getId(), products[top].getName());
		products[top] = null;
		System.out.println(Thread.currentThread().getName() + " 消费了产品" + p);
		System.out.println("comsumer notifyAll");
		notifyAll();//唤醒等待线程
		return p;
	}
}
