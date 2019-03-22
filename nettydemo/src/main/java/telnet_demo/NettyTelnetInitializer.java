package telnet_demo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 初始化配置类
 */
public class NettyTelnetInitializer extends ChannelInitializer<SocketChannel> {

  private static final StringDecoder DECODER = new StringDecoder();
  private static final StringEncoder ENCODER = new StringEncoder();

  @Override
  protected void initChannel(SocketChannel channel) throws Exception {
    ChannelPipeline pipeline = channel.pipeline();

    pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));

    // 添加编码和解码的类
    pipeline.addLast(DECODER);
    pipeline.addLast(ENCODER);

    // 添加业务处理的类
    pipeline.addLast(new NettyTelnetHandler());
  }
}
