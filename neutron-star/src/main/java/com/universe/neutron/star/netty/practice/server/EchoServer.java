package com.universe.neutron.star.netty.practice.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author gaohongming
 * @version 1.0.0
 * @ClassName EchoServer.java
 * @Description netty测试启动服务器
 * @createTime 2020年12月19日 13:09:00
 *
 *  192.168.39.1
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
         new EchoServer(10086).start();

    }


    /**
     * 服务启动入口
     */
    public void start()throws Exception{

        //会受到入栈消息的通知
        final EchoServerHandler serverHandler = new EchoServerHandler();
        //因为使用的是NIO传输，所以指定了NioEventLoopGroup来接受和处理新的连接
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    //指定所使用的NIO传输Channel
                    .channel(NioServerSocketChannel.class)
                    //指定端口套接字地址
                    .localAddress(new InetSocketAddress(port))
                    //添加一个EchoServerHandler到子Channel的ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //EchoServerHandler被标注为@Shareable所以可以被共享
                            socketChannel.pipeline().addLast(serverHandler);
                            System.out.println("将serverHandler 添加到 ChannelPipeline中");
                        }
                    });
            //对于Sync的调用将导致当前线程阻塞直到绑定操作完成为止
            // 异步的绑定服务器调用sync方法阻塞直到绑定完成
            // 在调用bind之后创建一个新的Channel 之后调用connect方法建立连接
            ChannelFuture future = b.bind().sync();
            //获得channel的CloseFuture直到当前线程完成
            //这里会阻塞等待直到服务器的Channel关闭 因为在Channel的closeFuture上调用了Sync方法
            future.channel().closeFuture().sync();

        } finally {
            //关闭group释放资源
            group.shutdownGracefully().sync();
        }
    }
}
