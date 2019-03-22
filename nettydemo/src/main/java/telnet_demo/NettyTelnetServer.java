package telnet_demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 创建一个telnet服务
 */
public class NettyTelnetServer {

  private static final int PORT = 8088;
  private ServerBootstrap serverBootstrap;

  // 负责收集客户端连接, 都是循环不断的监听
  private EventLoopGroup bossGroup = new NioEventLoopGroup(1);
  // 负责处理对每个连接的IO读写
  private EventLoopGroup workerGroup = new NioEventLoopGroup();

  public void open() throws InterruptedException {
    // Socket服务端启动类, 通过偶这个类的实例, 用户可以创建对应的服务端程序
    serverBootstrap = new ServerBootstrap();
    // 指定socket 的属性
    serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
    serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
        .handler(new LoggingHandler(
            LogLevel.INFO)).childHandler(new NettyTelnetInitializer());

    // 绑定对应的端口号, 并启动开始监听端口上的连接
    Channel channel = serverBootstrap.bind(PORT).sync().channel();

    // 等待关闭, 同步端口
    channel.closeFuture().sync();
  }

  public void close() {
    bossGroup.shutdownGracefully();
    workerGroup.shutdownGracefully();
  }


}
