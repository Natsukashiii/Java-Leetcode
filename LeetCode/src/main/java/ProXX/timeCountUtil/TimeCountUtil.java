package ProXX.timeCountUtil;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeCountUtil {

    private static ExecutorService threadPool;
    private static TimerTask timerTask;
    private static Runnable task_Runnable;
    private static Callable task_Callable;
    private static ThreadLocal timeHistory;
    private static FutureTask<Integer> time_pass;
    private static AtomicInteger count = new AtomicInteger(0);

    public static void timeCount(int time) throws ExecutionException, InterruptedException {
        getThreadPool();
        init_CallableTask(1000);
        threadPool.submit(task_Callable);
        Thread.sleep(1000);
        while (true) {
            if (time_pass.isDone()&&!time_pass.isCancelled()&&threadPool.){
                System.out.println(time_pass.get());
            }
        }
    }

    private static ThreadLocal getTime() {
        if (timeHistory == null) {
            timeHistory = new ThreadLocal();
        }
        return timeHistory;
    }

    private static TimerTask init_timerTask() {
        if (timerTask == null) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("timeTask");
                }
            };
        }
        return timerTask;
    }

    private static Runnable init_RunnableTask() {
        if (task_Runnable == null) {
            task_Runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("like");
                }
            };
        }
        return task_Runnable;
    }

    private static Callable init_CallableTask(int time) {
        if (task_Callable == null) {
            task_Callable = new Callable() {
                @Override
                public Object call() throws Exception {
                    caculate();
                    return count;
                }
            };
        }
        return task_Callable;
    }

    private static ExecutorService getThreadPool() {
        if (threadPool == null) {
            threadPool = Executors.newFixedThreadPool(5);
        }
        return threadPool;
    }

    public static void caculate() {
        while ((count.get()) < 1000) {
            count.incrementAndGet();//自增1,返回更新值
            System.out.println("正在运行是线程" + Thread.currentThread().getName() + ":" + count);
        }
    }

    public static void timeAdd() {
        Timer timer = new Timer();
    }


}
