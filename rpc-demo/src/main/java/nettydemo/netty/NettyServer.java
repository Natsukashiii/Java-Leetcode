package nettydemo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 服务端
 */
public class NettyServer {

  public static void startServer(String hostName, int port) {

  }

  public static void startServer0(String hostName, int port) {
    try {
      ServerBootstrap bootstrap = new ServerBootstrap();
      NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
      bootstrap.group(eventLoopGroup)
          .channel(NioServerSocketChannel.class)
          .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel channel) throws Exception {
              ChannelPipeline pipeline = channel.pipeline();
              pipeline.addLast(new StringDecoder());
              pipeline.addLast(new StringEncoder());
              pipeline.addLast(new ServerHandler());
            }
          });
      bootstrap.bind(hostName, port).sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}
