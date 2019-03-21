package designpattern.reactorPattern;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Acceptor implements Runnable{

  private int port;
  private Selector selector;

  // 代表serverSocket, 通过LinkedBlockingQueue来模拟外部输入请求队列
  private BlockingQueue<InputSource> sourceQueue = new LinkedBlockingDeque<InputSource>();

  Acceptor(Selector selector, int port) {
    this.selector = selector;
    this.port = port;
  }

  // 外部有输入请求后,需要加入到请求队列中
  public void addNewConnection(InputSource source) {
    sourceQueue.offer(source);
  }

  public int getPort() {
    return port;
  }

  public void run() {
    while (true) {
      InputSource source = null;
      try {
        // 相当于serversocket.accept(). 接收输入请求, 这里从请求队列获取输入请求
        source = sourceQueue.take();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      // 接收到InputSourc后将接收到的event的type设置为ACCEPT, 并将source复制给event
      if (source != null) {
        Event acceptEvent = new Event();
        acceptEvent.setSource(source);
        acceptEvent.setType(EventType.ACCEPT);

        selector.addEvent(acceptEvent);
      }
    }
  }
}
