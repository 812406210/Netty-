package com.example.netty.lesson12.client.module.player.handler;
import com.example.netty.lesson12.client.swing.ResultCodeTip;
import com.example.netty.lesson12.client.swing.Swingclient;
import com.example.netty.lesson12.common.core.model.ResultCode;
import com.example.netty.lesson12.common.module.player.response.PlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 玩家模块
 * @author -琴兽-
 *
 */
@Component
public class PlayerHandlerImpl implements PlayerHandler{
	
	@Autowired
	private Swingclient swingclient;
	@Autowired
	private ResultCodeTip resultCodeTip;

	@Override
	public void registerAndLogin(int resultCode, byte[] data) {
		if(resultCode == ResultCode.SUCCESS){
			PlayerResponse playerResponse = new PlayerResponse();
			playerResponse.readFromBytes(data);
			
			swingclient.setPlayerResponse(playerResponse);
			swingclient.getTips().setText("注册登录成功");
		}else{
			swingclient.getTips().setText(resultCodeTip.getTipContent(resultCode));
		}
	}

	@Override
	public void login(int resultCode, byte[] data) {
		if(resultCode == ResultCode.SUCCESS){
			PlayerResponse playerResponse = new PlayerResponse();
			playerResponse.readFromBytes(data);
			
			swingclient.setPlayerResponse(playerResponse);
			swingclient.getTips().setText("登录成功");
		}else{
			swingclient.getTips().setText(resultCodeTip.getTipContent(resultCode));
		}
	}
}
