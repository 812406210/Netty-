package com.example.netty.lesson12.server.module.player.service;


import com.example.netty.lesson12.common.core.session.Session;
import com.example.netty.lesson12.common.module.player.response.PlayerResponse;

/**
 * 玩家服务
 * @author -琴兽-
 *
 */
public interface PlayerService {
	
	
	/**
	 * 登录注册用户
	 * @param playerName
	 * @param passward
	 * @return
	 */
	public PlayerResponse registerAndLogin(Session session, String playerName, String passward);
	
	
	/**
	 * 登录
	 * @param playerName
	 * @param passward
	 * @return
	 */
	public PlayerResponse login(Session session, String playerName, String passward);

}
