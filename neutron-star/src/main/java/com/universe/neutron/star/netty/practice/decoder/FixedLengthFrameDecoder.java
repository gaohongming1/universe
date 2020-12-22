package com.universe.neutron.star.netty.practice.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * 固定长度解码器
 * 传入的桢会被传出固定长度的桢传出
 * @author gaohongming3
 * @date 2020-12-21 11:31
 */
public class FixedLengthFrameDecoder extends ByteToMessageCodec {
    private final int frameLength;

    public FixedLengthFrameDecoder(int frameLength) {
        if (frameLength <= 0 ){
            throw new IllegalArgumentException(
                    "frameLength must be a positive integer" +frameLength
            );
        }
        this.frameLength = frameLength;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {

    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
        //如果有足够的字节可以读取则读取
        while (byteBuf.readableBytes() >=frameLength){
            //从ByteBuf中读取一个新桢
            ByteBuf buf = byteBuf.readBytes(frameLength);
            //将该桢添加到已经被解码的消息之中
            list.add(buf);
        }
    }
}
