package com.universe.neutron.star.netty.practice.buffer;

import java.nio.IntBuffer;

/**
 * @author gaohongming
 * @version 1.0.0
 * @ClassName IntBufferDemo.java
 * @Description TODO
 * @createTime 2020年12月27日 20:29:00
 * 整数缓冲区实例
 */
public class IntBufferDemo {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(8);
        for (int i = 0; i < buffer.capacity(); i++) {
            int j =2 *(i+1);
            buffer.put(j);
        }

        //重设此缓冲区，将限制为止设置为当前位置，当前位置设置为0  也就是此buffer可读
        buffer.flip();
        // 查看当前位置和限制之间是否存在元素
        while (buffer.hasRemaining()){
            int j = buffer.get();
            System.out.println(j);

        }
    }
}
