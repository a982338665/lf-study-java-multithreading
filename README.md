# multithreading
### java多线程和多进程

**1.多进程：**
    
    1.当前的操作系统都是多任务的OS(Operating System)
    2.每隔独立执行的任务就是一个进程
    3.OS即操作系统将时间划分为多个时间片-时间很短
    4.每个时间片内将CPU分配给某一个任务，时间片结束，CPU将自动回收，再分配给另外任务
        ·【单核cpu】：在外部看，所有任务都在同时执行，但是实际上，在cpu上，任务是按照串行依次运行
        ·【多核cpu】：多个进程任务可以并行，但是单核，多进程只能串行
        ·边听音乐，边工作其实也是cpu在不断切换
    
    5.多进程优点：
        -1.可以同时运行多个任务
        -2.程序因IO堵塞时，cpu处于空闲状态（idle），可以释放cpu，让cpu为其他程序服务
        -3.当系统有多个cpu时，可以为多个程序同时服务
            ·cpu不再提高频率，而是提高核数：  
                由于受到晶体管数量和系统运行时产生的热量的限制，cpu频率已经无法提高了，提高核数
                多核和并行程序才是提高程序性能的唯一办法
    6.多进程缺点：
        1.太笨重，不好管理：打开十多个程序，可能会耗光内存
        2.不好切换：当io阻塞时，释放cpu给其他程序用的时候，不好切换

**2.多线程：**
    
    1.一个程序可包含多个子任务，可串行或并行
    2.每个子任务可以称为一个线程
    3.如果一个子任务阻塞，程序可以将cpu调度到另外一个子任务进行工作，这样能保证cpu还留在本程序中，而不会被调度到别的程序（进程）中
        这样提高了本程序所获得的cpu时间和利用率
        
**3.多进程和多线程对比：**

    -线程共享数据
    -线程通讯更高效
    -线程更轻量级，更容易切换
    -多个线程更容易管理
    -线程之间更容易通信，进程之间很难通信
   
 **4.多线程：**
    
    wait()是属于Object类的方法，从源码给出的解释来看，wait()方法可以做到如下几点：
    	（1）首先，调用了wait()之后会引起当前线程处于等待状状态。
    	（2）其次，每个线程必须持有该对象的monitor。
    		  如果在当前线程中调用wait()方法之后，该线程就会释放monitor的持有对象并让自己处于等待状态。
    	（3）如果想唤醒一个正在等待的线程，那么需要开启一个线程通过notify()或者notifyAll()方法
    		 去通知正在等待的线程获取monitor对象。如此，该线程即可打破等待的状态继续执行代码。
    		 -------------------------------
    （1）在线程的运行过程中，调用该线程持有monitor对象的wait()方法时，该线程首先会进入等待状态，并将自己持有的monitor对象释放。
    （2）如果一个线程正处于等待状态时，那么唤醒它的办法就是开启一个新的线程，通过notify()或者notifyAll()的方式去唤醒。当然，需要注意的一点就是，必须是同一个monitor对象。
    （3）sleep()方法虽然会使线程中断，但是不会将自己的monitor对象释放，在中断结束后，依然能够保持代码继续执行。
    http://blog.csdn.net/kaka534/article/details/51849285
    -------------------------------------------------------------
    --BlockingQueue:使用可阻塞队列来实现互斥，通信(需要几个就新建几个)
    		/**可存放三个数据的可阻塞队列**/
    		final BlockingQueue<Integer> block=new ArrayBlockingQueue<Integer>(3);
    		one.put(1);//阻塞队列可放一个数据，已装满，阻塞不执行
    		one.take();
    --CyclicBarrier：集合--分散--集合--分散
    		final CyclicBarrier cb=new 	CyclicBarrier(3);//等待三条线程集合完毕
    		cb.getNumberWaiting();
    		cb.await();//当三个线程都到集合点时，继续向下执行
    --CountDownLatch:计数线程--线程准备(n)--发布命令一起执行--等待都执行完--报告结果
    		final CountDownLatch start=new 	CountDownLatch(1);//表示countDown一次后等待失效
    		final CountDownLatch end=new 	CountDownLatch(3);//表示countDown三次后等待失效
    		start.countDown();//表示countDown一次
    		for循环3次或者三个线程每个countDown一次即表示三次
    		start.await();//计数完成后，等待失效向下执行
    --Semphore:用于控制并发线程个数
    		final Semaphore sp=new 	Semaphore(3,true);//true保证顺序执行
    		sp.acquire();//开始控制并发个数，超过则等待
    		(3-sp.availablePermits())//当前已存在并发数
    --Exchanger:线程之间的数据交换:两个数据之间，若有其中一个数据未查出则等待
    		final Exchanger start=new 	Exchanger();//一定是作为一个共享变量，等同于容器
    		String datachange=(String)start.exchange(data);//作为一个交换机，当两边均有数据时交换成功，单方面有数据时阻塞
    		//因此需要启动至少两个线程
    ---------------------------------------------------------------

**5.多线程信息共享：**
    
    1.线程类：
        -通过集成Thread或事项Runnable创建线程
        -通过start方法，调用run方法，run方法工作
        -线程run结束后，线程退出
    2.粗粒度：子线程与子线程，和main线程之间缺乏交流
    3.细粒度：线程之间有信息交流通讯
        -通过共享变量达到信息共享
        -jdk原生库暂不支持点对点发送消息，类似mpi并行库直接发送消息
            不支持线程0到线程1发送消息
            不支持线程0向所有其他线程群发消息
    4.通过共享变量在多个线程中共享消息
        -static变量
        -同一个Runnable类的成员变量
    5.多线程信息共享：
        -工作缓存副本：
            java多线程内存模型：
                每个线程都有自己的工作缓存，当他需要数据的时候，他会从主存RAM中加载完数据，放到自己的工作缓存中，然后再开始运算
                假设，有三个线程，共享一个变量，其中有一个线程修改了数据，另外两个没有收到通知，继续使用它本身的工作缓存中的数据
                进行运算，就会导致每个人的工作缓存中都无法显示最新的数据
        -关键步骤缺乏加锁限制：--> 一次仅允许一个人修改
            例如：i++;并非原子操作
                -读取主存i(正本)到工作缓存副本中
                -每个cpu执行副本i++操作
                -cpu将结果写入到缓存副本中
                -数据从工作缓存副本刷到主存正本中
    6.变量副本问题的解决方法：
        -采用volatile关键字修饰变量：即使通知更新缓存
            -保证不同线程对共享变量操作时的可见性
        -保证内存可见性及防止指令重排序
        -不能保证原子性
    7.关键步骤加锁限制：
        -互斥：同步代码块，一次只能有一个线程运行
        -synchronized：加大性能负担，但是使用简便
        
        
**6.多线程管理：**
    
    1.线程状态：
        NEW         新建new
        RUNNABLE    就绪start，调用start时处于就绪状态，等待cpu调度器分配
        RUNNING     运行run
        BLOCK       阻塞sleep
        TERMINATED  结束    
    2.Thread的部分api已废弃：
        -暂停和恢复suspend、resume
        -消亡 stop和destory
    3.线程阻塞和唤醒：
        -sleep  到时继续执行
        -wait，notify，notifyAll  等待需要别人来唤醒
        -join       等待另外一个线程结束
        -interrupt  向另外一个线程发送中断信号，该线程收到信号，会触发InterruptedException（可解除阻塞），并进行下一步处理
    4.生产者消费者示例：multithreading\src\main\java\pers\li\thread\product
    5.线程被动的暂停和停止：
        -依靠别的线程来拯救自己
        -没有及时释放资源
    6.线程主动暂停和停止：--> 更好，可以保证资源释放
        -定期监测共享变量
        -如果需要暂停或终止，先释放资源，在主动动作
        -暂停：Thread.sleep(),休眠
        -终止：run方法结束，线程终止
    7.多线程死锁：multithreading\src\main\java\pers\li\thread\deadlock
        -每个线程互相持有别人需要的锁
        -预防死锁，对资源进行等级排序
    8.守护线程：multithreading\src\main\java\pers\li\thread\daemon
        -普通线程的结束，是run方法运行结束
        -守护线程的结束，是run方法运行结束，或mian函数结束
        -守护线程永远不要访问资源，如文件，数据库等，因为main函数，结束，他就会被强制结束，可能造成资源来不及释放
    9.线程查看工具jvisualvm：java自带的分析工具
        -打开cmd --> 输入jvisualvm进行线程监测
    10.总结：
        -了解线程多个状态
        -了解线程协作机制
        -线程协作尽量简单化，采用粗粒度协作
        -了解死锁和后台线程（守护线程）概念
        -使用jvisualvm查看线程执行情况
        -尽量采用共享变量监控方式控制线程状态

**7.java并发框架：**

    1.并行计算：
        ·业务特点：任务多，数据量大
        ·串行&并行：
            -串行编程容易，并行编程困难
            -单个计算核频率下降，计算核数增多，整体性能变高（单核可能2G左右，可能有8核，16核，32核，所以整体性能提高）
        ·多核编程既要涉及并行：任务分配和执行过程高度耦合
            -如何控制粒度,切割任务
            -如何分配任务给线程，监督线程执行过程
    2.并行模式：
        -主从模式   Master-Slave  主线程分配从线程工作
        -worker模式 Worker-Worker 所有线程执行相同任务
    3.并发编程：
        -Thread/Runnable/Thread组管理
        -Excutor - 重点
        -Fork-Join框架

**8.线程组管理：**

    1.线程组ThreadGroup: --解决不了任务分配和执行过程中的监督高度耦合问题
        -线程的集合
        -树形结构，大线程组可以包括线程组
        -可以通过enumerate方法遍历组内的线程，执行操作
        -能够有效管理多个线程，但是管理效率低
        -任务分配和执行过程高度耦合
        -重复创建线程，关闭线程操作，无法重用线程
        -注意：
            线程和线程组内的线程都是new产生出来，但是start一次以后
            就不能再次使用，即再次start
            new的代价很昂贵，只允许一次，性价比过低
    2.Executor：
        1.从jdk5开始提供Executor FrameWork（java.util.concurrent.*）
            -分离任务的创建和执行者的创建
            -线程重复利用（new线程代价很大）
        2.理解共享线程池的概念：
            -预设好的多个Thread，可弹性增加
            -多次执行很多很小的任务
            -任务创建和执行过程解耦
            -程序员无需关心线程池执行任务过程
        3.主要类：ExecutorService,ThreadPoolExecutor,Future
            -Executors.newCachedThreadPool/newFixedThreadPool创建线程池
            -ExecutorService线程池服务
            -Callable具体的逻辑对象-线程类
                和Runnable接口等价，可以用来执行一个任务
                Runnable的闰方法没有返回值
                Callable的call方法可以有返回值
            -Future返回结果
        4.建议：在进行多线程编程时，尽量使用该框架，提高多线程执行效率
    3.fork-join：--> 分治求和
        ·java7提供了另一中并行框架：分解，治理，合并（分治编程）
        ·适合用于整体任务量不好确定的场合（最小任务可确定），例如求和一个不知道多长的数组内容：二分法分成小单位，然后求和，最后合并结果
        -关键类：
            -ForkJoinPool任务池
            -RecursiveAction
            -RecursiveTask

**8.并发数据结构：**
    
    1.常用的数据结构是线程不安全的
        -ArrayList，HashMap，HashSet非同步的
        -多个线程同时读写，可能会抛出异常或数据错误
    2.传统Vector，Hashtable等同步集合性能过差
    3.并发数据结构：数据添加和删除
        -阻塞式集合：当集合为空或者满时，等待
        -非阻塞式集合：当集合为空或者满时，不等待，返回null或异常
    4.集合：
        1.List
            ·Vector同步安全，适合环境：写多读少
            ·ArrayList不安全
            ·Collections.synchronizedList(List list)基于synchronized，效率差
            ·CopyOnWriteArrayList 读多写少，基于复制机制，非阻塞
        2.Set
            `HashSet不安全
            `Collections.synchronizedSet(Set set)基于synchronized，效率差
            `CopyOnWriteArraySet，基于CopyOnWriteArrayList 读多写少，非阻塞
        3.Map
            ·Hashtable同步安全，写多读少
            ·HashMap不安全
            ·Collections.synchronizedMap(Map map)基于synchronized，效率差
            ·ConcurrentHashMap读多写少，非阻塞
        4.Queue % Deque(队列，jdk1.5提出)
            ·ConcurrentLinkedQueue 非阻塞
            ·ArrayBlockingQueue/LinkedBlockingQueue阻塞

**9.并发协作控制：multithreading\src\main\java\pers\li\thread\lock**
    
    1.Thread/Executor/Fork-Join
        -线程启动运行结束
        -线程之间缺少协作
    2.synchronized同步：
        -限定只有一个线程才能进入关键区
        -简单粗暴，性能损失有点大
    3.Lock可以实现同步效果 ———> java5后提供，性能比synchronized好
        -实现更复杂的临界区结构
        -tryLock方法可以预判锁是否空闲
        -允许分离读写的操作，多个读，一个写
        -性能更好
    4.ReentrantLock类，可重入的互斥锁
    5.ReentrantReadWriteLock类，可重入的读写锁
    6.lock和unlock函数
    7.semaphore
        ·信号量，由1965年dijkstra提出的
        ·信号量：本质是个计数器
        ·计数器大于0，可以使用，等于0不能使用
        ·可以设置多个并发量，例如限制10个访问
        ·Semaphore
            -acquire获取
            -release释放
        ·比Lock更进一步，可以控制多个同时访问关键区
    
**10.定时任务：multithreading\src\main\java\pers\li\thread\schedule**
    
        
    1.简单定时器机制：
        -TimerTask封装任务
        -Timer类 定时器
    2.Executor+定时器机制
        -ScheduledExecutorService
            -定时任务
            -周期任务
    3.Quartz
        -是一个较为完善的任务调度框架
        -解决程序中timer零散管理的问题
        -功能更加强大
            ·Timer执行周期任务，如果中间有一次异常，整个任务将终止
            ·Quartz执行周期任务，如果中间有一次异常，不影响下次执行任务
