package designpattern.reactorPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import javax.swing.tree.ExpandVetoException;

/**
 * reactor模式中的Demultiplexer类，提供select（）方法用于从缓冲队列中查找出符合条件的event列表
 */
public class Selector {

  // 定义一个链表阻塞queue实现换从队列, 保证线程安全
  private BlockingQueue<Event> eventQueue = new LinkedBlockingDeque<Event>();
  // 定义一个object用于synchronized上锁
  private Object lock = new Object();

  List<Event> select() {
    return select(0);
  }

  List<Event> select(long timeout) {
    if (timeout > 0) {
      if (eventQueue.isEmpty()) {
        synchronized (lock) {
          if (eventQueue.isEmpty()) {
            try {
              lock.wait(timeout);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }
    }
    // 简单的返回全部event列表, 这里可以筛选出合适的event
    List<Event> events = new ArrayList<Event>();
    eventQueue.drainTo(events);
    return events;
  }

  public void addEvent(Event event) {
    // 将event事件加入队列
    boolean success = eventQueue.offer(event);
    if (success) {
      synchronized (lock) {
        // 如果有新增时间则对lock对象解锁
        lock.notify();
      }
    }
  }


}
