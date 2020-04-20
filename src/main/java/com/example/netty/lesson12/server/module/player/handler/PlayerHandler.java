package com.example.netty.lesson12.server.module.player.handler;


import com.example.netty.lesson12.common.core.annotion.SocketCommand;
import com.example.netty.lesson12.common.core.annotion.SocketModule;
import com.example.netty.lesson12.common.core.model.Result;
import com.example.netty.lesson12.common.core.session.Session;
import com.example.netty.lesson12.common.module.ModuleId;
import com.example.netty.lesson12.common.module.player.PlayerCmd;
import com.example.netty.lesson12.common.module.player.request.LoginRequest;
import com.example.netty.lesson12.common.module.player.request.RegisterRequest;
import com.example.netty.lesson12.common.module.player.response.PlayerResponse;

/**
 * 玩家模块
 * @author -琴兽-
 *
 */
@SocketModule(module = ModuleId.PLAYER)
public interface PlayerHandler {
	
	
	/**
	 * 创建并登录帐号
	 * @param session
	 * @param data {@link RegisterRequest}
	 * @return
	 */
	@SocketCommand(cmd = PlayerCmd.REGISTER_AND_LOGIN)
	public Result<PlayerResponse> registerAndLogin(Session session, byte[] data);
	

	/**
	 * 登录帐号
	 * @param session
	 * @param data {@link LoginRequest}
	 * @return
	 */
	@SocketCommand(cmd = PlayerCmd.LOGIN)
	public Result<PlayerResponse> login(Session session, byte[] data);

}
