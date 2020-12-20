package com.universe.neutron.star.netty.practice.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author gaohongming
 * @version 1.0.0
 * @ClassName EchoServerHandler.java
 * @Description netty使用练习
 * @createTime 2020年12月19日 12:02:00
 */
// 表示一个ChannelHandler可以被多个Channel安全的共享
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {


    /**
     *  这个方法对于每个传入的消息都要调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in  = (ByteBuf) msg;
        System.out.println("server received:" + in.toString(CharsetUtil.UTF_8));
        //将接受到的消息写给发送者 不冲刷出站消息 注意这个消息是异步的，当channelRead方法返回之后，不一定发送消息
        ctx.write(in);
    }


    /**
     *  通知ChannelInnoundHandler最后一次对channel-Read的调用是当前批量读取的最后一条消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将未决消息冲刷到远程节点并且关闭Channel 调用writeAndFlush会释放缓存
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 在读取操作期间，有异常跑出时候会调用
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
