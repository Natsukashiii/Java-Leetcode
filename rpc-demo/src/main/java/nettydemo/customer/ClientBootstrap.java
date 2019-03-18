package nettydemo.customer;


import nettydemo.netty.NettyClient;
import nettydemo.publicInterface.HelloService;

public class ClientBootstrap {

  public static final String providerName = "HelloService#hello#";

  public static void main(String[] args) throws InterruptedException {
    NettyClient consumer = new NettyClient();
    // 创建一个代理对象
    HelloService service = (HelloService) consumer.getBean(HelloService.class, providerName);
    for (; ; ) {
      Thread.sleep(1000);
      System.out.println(service.hello("do you get message?"));
    }
  }
}
