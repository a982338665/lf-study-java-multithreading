package pers.li.thread.timetask.threadpool;

public class ThreadPoolTest {

	
}

/**
 *  Executors创建的4种线程池的使用
 *  Java通过Executors提供四种线程池，分别为：
 *  newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 *  newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 *  newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
 *  newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 * @author 
 *=======================================
 *Executors.newFixedThreadPool(3);在线程池中保持三个线程可以同时执行，
 *但是注意，并不是说线程池中永远都是这三个线程，只是说可以同时存在的线程数，
 *当某个线程执行结束后，会有新的线程进来。
 *newFixedThreadPool.execute(new ThreadForpools());
 *这句话的含义并不是添加新的线程，而是添加新的处理业务请求进来。至少我当前是这么理解的，没有发现线程可以重复使用。
 */
