package pers.li.thread.z_hebing1.thread;

/**
 * create by lishengbo on 2018-05-23 10:39
 * 线程池介绍：
 * ThreadPoolExecutor 的构造方法
 * public ThreadPoolExecutor(
 *
 * int corePoolSize,        --提交任务时，运行线程少于corePoolSize，则创建新线程执行，即使有空闲线程
 * int maximumPoolSize,     --当运行线程S： corePoolSize<S<maximumPoolSize 当队列任务满时创建新线程
 *                            corePoolSize=maximumPoolSize  创建了固定大小的线程池
 *                            maximumPoolSize=Integer.MAX_VALUE 则允许池适应任意数量的并发任务
 * long keepAliveTime,      --如果池中当前有多于 corePoolSize 的线程，则这些多出的线程在空闲时间超过
 *                            keepAliveTime 时将会终止,直到池中的数量减少到核心数。这提供了当池处于非活动状态时减少
 *                            资源消耗的方法.注意：keepAliveTime=0表示线程永久存活
 * TimeUnit unit,           --时间单位
 * BlockingQueue<Runnable> workQueue,
 *      ·运行线程<corePoolSize Executor 始终首选添加新的线程，而不进行排队
 *      ·运行线程>=corePoolSize，Executor 始终首选将请求加入队列，而不添加新的线程。
 *      ·无法将请求加入队列，则创建新的线程
 *      ·当创建新的线程超出 maximumPoolSize，在这种情况下，任务将被拒绝。
 * 由上可知：
 *      1.用线程池来执行队列任务，最少支持corePoolSize个并发请求，最大支持的并发请求为maximumPoolSize
 *      2.当最大并发达到maximumPoolSize时，队列已满，线程池也已限制线程，此时新任务将会被拒绝
 *
 * --以下两个可自行构造，————————————————
 * ThreadFactory threadFactory,
 * RejectedExecutionHandler handler
 *
 * );
 *
 * 任务队列的策略：
 * ·直接提交：默认选项是 SynchronousQueue：它将任务直接提交给线程而不保持它们
 *      过程：
 *         1.任务直接提交给线程
 *         2.若有空闲线程   --执行任务
 *           若无空闲线程
 *              --将任务加入队列失败
 *              --构造新线程
 *         3.因为每次新任务提交，都有可能会因为没有空闲线程，而创建新线程，
 *           所以直接提交通常要求无界 maximumPoolSizes 以避免拒绝新提交的任务
 * ·无界队列：例如，不具有预定义容量的 LinkedBlockingQueue：
 *      过程：
 *         1.当corePoolSize个线程忙碌时，会优先请求加入队列
 *         2.因为此队列无界，所以新任务都会在队列中等待，故，不可能去创建新线程，因此，maximumPoolSize 的值也就无效
 * ·有界队列：有界队列（如 ArrayBlockingQueue）有助于防止资源耗尽
 *      1.队列大小和最大池大小可能需要相互折衷：
 *          1.使用大型队列和小型池可以最大限度地降低 CPU 使用率、操作系统资源和上下文切换开销
 *          2.但是可能导致人工降低吞吐量。如果任务频繁阻塞（例如，如果它们是 I/O 边界），
 *          3.则系统可能为超过您许可的更多线程安排时间。使用小型队列通常要求较大的池大小，CPU 使用率较高，
 *          4.但是可能遇到不可接受的调度开销，这样也会降低吞吐量。
 *
 *
 */
public class ThreadPoolExecutorTest {
}
