package designpattern.reactorPattern;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * reactor模式中Dispatcher类，负责event的分发和eventHandler的维护
 */
public class Dispatcher {

  // 通过ConcurrentHashMap来维护不同事件处理器
  Map<EventType, EventHandler> eventHandlerMap = new ConcurrentHashMap<EventType, EventHandler>();
  // 只维护一个selector负责事件选择(netty实现了多个selector)
  Selector selector;

  Dispatcher(Selector selector) {
    this.selector = selector;
  }

  // 在Dispatcher中注册eventHandler
  public void registEventHandler(EventType eventType, EventHandler eventHandler) {
    eventHandlerMap.put(eventType, eventHandler);
  }

  public void removeEventHandler(EventType eventType) {
    eventHandlerMap.remove(eventType);
  }

  public void handleEvents() {
    dispatch();
  }

  // 实现了简单的事件分发个相应的处理器,以同步方式,(Reactor在NIO中多用handle异步处理,保证非阻塞)
  public void dispatch() {
    while (true) {
      List<Event> events = selector.select();
      for (Event event:events
      ) {
        EventHandler eventHandler = eventHandlerMap.get(event.getType());
        eventHandler.handle(event);
      }
    }
  }

}
