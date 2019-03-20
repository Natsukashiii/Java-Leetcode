package Pro000.timeCountUtil;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 定时工具类
 * 根据传入的时间延迟任务执行
 * eg: 第一次传入8, 延迟8小时后执行; 执行4小时后,传入5, 则计算已经执行的时间, 只延迟一小时执行
 * eg: 第一次传入8, 延迟8小时后执行; 执行4小时后,传入3, 则计算已经执行的时间, 由于3比剩余执行时间少, 则立即执行
 *
 * 要修改成秒的话, 需要修改日期格式"yyyy-MM-dd HH:mm:ss", 并修改所有单位为1000, 以及任务定时的时间
 */
public class TimeTaskUtil {

  private static SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
  private static ScheduledExecutorService threadPool;
  private static Runnable task_Runnable;
  private static String currentTime;
  private static int timeInterval;
  private static ScheduledFuture scheduledFuture;

  public static void delay(int time) {
    System.out.println("=============== start =================");
    int new_delay;
    // 初始化线程池
    init_ThreadPool();
    // 获取上次的时间
    String last_currentTime = currentTime;
    int last_timeInterval = timeInterval;
    System.out.println("上次任务时间: " + last_currentTime + ", 上次任务延迟时间: " + last_timeInterval);
    // 获取当前时间并保存
    currentTime = getCurrentTime();
    timeInterval = time;
    System.out.println("当前时间: " + currentTime + ", 当前任务延迟时间:" + timeInterval);
    // 获取新的delay时间
    new_delay = getTimeDelay(timeInterval, last_timeInterval, last_currentTime, currentTime);
    System.out.println("新的延迟时间为: " + new_delay);
    // 构造新的任务线程
    init_RunnableTask();
    // 非首次执行, 取消该定时任务
    if (scheduledFuture != null) {
      scheduledFuture.cancel(true);
      System.out.println("定时任务取消状态: " + scheduledFuture.isCancelled());
    }
    // 执行新任务(只执行一次)
    scheduledFuture = threadPool
        .schedule(task_Runnable, new_delay, TimeUnit.HOURS);
  }

  /**
   * 获取新的延迟时间
   */
  private static int getTimeDelay(int timeInterval, int last_timeInterval,
      String last_currentTime, String currentTime) {
    // 如果是第一次执行, 时间间隔即为延迟时间, 直接返回
    if (last_currentTime == null) {
      System.out.println("首次执行");
      return timeInterval;
    }
    // 判断上一次任务是否结束, 如果结束, 新的延迟时间为当前传入的时间延迟时间
    if (isFinished(last_currentTime, currentTime,
        last_timeInterval)) {
      return timeInterval;
    }
    // 计算距离第一次执行任务已经过去多久时间
    int passTime = getTimePlus(last_currentTime, currentTime);
    // 如果新传入的延迟时间小于剩余时间, 则立即执行
    if (timeInterval < (last_timeInterval - passTime)) {
      System.out.println("新传入的延迟时间小于剩余时间,立即执行.");
      return 0;
    }
    // 计算新的delay时间, timeinterval-passTime
    int new_delay = timeInterval - passTime;
    return new_delay;
  }

  /**
   * 判断任务是否已经执行结束
   */
  private static boolean isFinished(String last_currentTime, String currentTime, int last_delay) {
    boolean res = false;
    try {
      long last = simpleFormat.parse(last_currentTime).getTime();
      long now = simpleFormat.parse(currentTime).getTime();
      // 如果当前时间和过去时间差大于上一次的delay时间, 则说明任务已经结束了
      if ((now - last) > last_delay * ((1000 * 60 * 60))) {
        res = true;
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    System.out.println("任务是否结束: " + res);
    return res;
  }


  /**
   * 计算两次时间的小时差
   */
  private static int getTimePlus(String fromTime, String toTime) {
    int hours = 0;
    try {
      long from = simpleFormat.parse(fromTime).getTime();
      long to = simpleFormat.parse(toTime).getTime();
      hours = (int) ((to - from) / (1000 * 60 * 60));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return hours;
  }

  /**
   * 初始化任务线程
   */
  private static Runnable init_RunnableTask() {
    if (task_Runnable == null) {
      task_Runnable = new Runnable() {
        @Override
        public void run() {
          System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>runnable");
          System.out.println("执行时间: " + getCurrentTime());
        }
      };
    }
    return task_Runnable;
  }

  /**
   * 初始化线程池
   */
  private static ScheduledExecutorService init_ThreadPool() {
    if (threadPool == null) {
      threadPool = Executors.newScheduledThreadPool(1);
    }
    return threadPool;
  }

  /**
   * 获取当前时间
   */
  private static String getCurrentTime() {
    return simpleFormat.format(new Date());
  }


}
