package telnet_demo;

public class NettyTest {

  public static void main(String[] args) {
    NettyTelnetServer server = new NettyTelnetServer();
    try {
      server.open();
    } catch (InterruptedException e) {
      server.close();
      e.printStackTrace();
    }
  }

}
