package com.example.netty.lesson12.client.module.player.handler;

import com.example.netty.lesson12.common.core.annotion.SocketCommand;
import com.example.netty.lesson12.common.core.annotion.SocketModule;
import com.example.netty.lesson12.common.module.ModuleId;
import com.example.netty.lesson12.common.module.player.PlayerCmd;

/**
 * 玩家模块
 * @author -琴兽-
 *
 */
@SocketModule(module = ModuleId.PLAYER)
public interface PlayerHandler {
	
	
	/**
	 * 创建并登录帐号
	 * @param resultCode
	 * @param data {@link null}
	 */
	@SocketCommand(cmd = PlayerCmd.REGISTER_AND_LOGIN)
	public void registerAndLogin(int resultCode, byte[] data);
	

	/**
	 * 创建并登录帐号
	 * @param resultCode
	 * @param data {@link null}
	 */
	@SocketCommand(cmd = PlayerCmd.LOGIN)
	public void login(int resultCode, byte[] data);

}
