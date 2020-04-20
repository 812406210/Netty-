package com.example.netty.lesson12.common.module.chat.request;


import com.example.netty.lesson12.common.core.serial.Serializer;

/**
 * 广播消息
 * @author -琴兽-
 *
 */
public class PublicChatRequest extends Serializer {
	
	/**
	 * 内容
	 */
	private String context;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	protected void read() {
		this.context = readString();
	}

	@Override
	protected void write() {
		writeString(context);
	}
}
