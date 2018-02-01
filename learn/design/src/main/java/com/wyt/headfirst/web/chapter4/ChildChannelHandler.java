package com.wyt.headfirst.web.chapter4;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;


/**
 * LineBasedFrameDecoder+StringDecoder组合就是按照行切换的文本解码器,它被设计用来支持TCP的粘包和拆包的
 *
 * @author Darcy
 * Created By Darcy on 2018/2/1 下午3:23
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        socketChannel.pipeline().addLast(new StringDecoder());
        socketChannel.pipeline().addLast(new TimeServerHandler());

    }
}
