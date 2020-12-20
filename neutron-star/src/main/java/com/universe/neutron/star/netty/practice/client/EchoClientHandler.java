package com.universe.neutron.star.netty.practice.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author gaohongming
 * @version 1.0.0
 * @ClassName EchoClientHandler.java
 * @Description netty测试
 * @createTime 2020年12月19日 16:20:00
 */
//这个注解表示类的实例可以被多个Channel共享
@ChannelHandler.Sharable
public class EchoClientHandler  extends SimpleChannelInboundHandler<ByteBuf> {

    /**
     * 当被通知Channel是活跃的时候发送一条消息
     * 这个会在一个连接被建立的时候调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello netty", CharsetUtil.UTF_8));
    }

    /**
     * 记录已经接受的消息转储
     * 这个函数接受服务器发送的消息，当服务器发送的消息过多的时候，这个方法会被调用多次，不会一次性接受
     * 但是会根据TCP的保证，字节数组会按照服务器发送它们的顺序接收
     * 当这个方法返回时候，SimpleChannelInnoundHandler 负责释放指向保存该消息ByteBuf的内存引用
     * @param channelHandlerContext
     * @param byteBuf
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("Client received: " + byteBuf.toString(CharsetUtil.UTF_8));
    }

    //发生异常时记录错误并关闭Channel
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
