package nettydemo.provider;

import nettydemo.netty.NettyServer;

public class ServerBootstrap {

  public static void main(String[] args) {
    NettyServer.startServer("localhost", 8088);
  }
}
