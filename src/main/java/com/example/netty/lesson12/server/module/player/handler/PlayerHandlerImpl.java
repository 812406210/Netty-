package com.example.netty.lesson12.server.module.player.handler;

import com.example.netty.lesson12.common.core.exception.ErrorCodeException;
import com.example.netty.lesson12.common.core.model.Result;
import com.example.netty.lesson12.common.core.model.ResultCode;
import com.example.netty.lesson12.common.core.session.Session;
import com.example.netty.lesson12.common.module.player.request.LoginRequest;
import com.example.netty.lesson12.common.module.player.request.RegisterRequest;
import com.example.netty.lesson12.common.module.player.response.PlayerResponse;
import com.example.netty.lesson12.server.module.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 玩家模块
 * @author -琴兽-
 *
 */
@Component
public class PlayerHandlerImpl implements PlayerHandler{
	
	@Autowired
	private PlayerService playerService;

	@Override
	public Result<PlayerResponse> registerAndLogin(Session session, byte[] data) {
		
		PlayerResponse result = null;
		try {
			//反序列化
			RegisterRequest registerRequest = new RegisterRequest();
			registerRequest.readFromBytes(data);
			
			//参数判空
			if(StringUtils.isEmpty(registerRequest.getPlayerName()) || StringUtils.isEmpty(registerRequest.getPassward())){
				return Result.ERROR(ResultCode.PLAYERNAME_NULL);
			}
			
			//执行业务
			result = playerService.registerAndLogin(session, registerRequest.getPlayerName(), registerRequest.getPassward());
		} catch (ErrorCodeException e) {
			return Result.ERROR(e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			return Result.ERROR(ResultCode.UNKOWN_EXCEPTION);
		}
		return Result.SUCCESS(result);
	}

	@Override
	public Result<PlayerResponse> login(Session session, byte[] data) {
		PlayerResponse result = null;
		try {
			//反序列化
			LoginRequest loginRequest = new LoginRequest();
			loginRequest.readFromBytes(data);
			
			//参数判空
			if(StringUtils.isEmpty(loginRequest.getPlayerName()) || StringUtils.isEmpty(loginRequest.getPassward())){
				return Result.ERROR(ResultCode.PLAYERNAME_NULL);
			}
			
			//执行业务
			result = playerService.login(session, loginRequest.getPlayerName(), loginRequest.getPassward());
		} catch (ErrorCodeException e) {
			return Result.ERROR(e.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
			return Result.ERROR(ResultCode.UNKOWN_EXCEPTION);
		}
		return Result.SUCCESS(result);
	}

}
