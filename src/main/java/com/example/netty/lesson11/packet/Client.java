package com.example.netty.lesson11.packet;

import java.net.Socket;
import java.nio.ByteBuffer;

public class Client {

	public static void main(String[] args) throws Exception {
        /**
         * 会出现粘包现象,通过ByteBuffer解决粘包问题
         */
		Socket socket = new Socket("127.0.0.1", 51503);

		String message = "hello,ni hao ma?";
		byte[] bytes = message.getBytes();
		//4定义为包头位数
        ByteBuffer buffer = ByteBuffer.allocate(4+bytes.length);
        buffer.putInt(bytes.length);
        buffer.put(bytes);

        byte[] array = buffer.array();
        for (int i = 0; i <1000 ; i++) {
            socket.getOutputStream().write(array);
        }

		socket.close();
	}

}
