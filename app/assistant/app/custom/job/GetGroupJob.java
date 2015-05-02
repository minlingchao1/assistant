/**
 * @Project:assistant
 * @Title: GetGroupJob.java
 * @date: 2015-2-13 下午7:55:33
 * @version 1.0
 */
package assistant.app.custom.job;

import java.util.List;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.job.base.OnceJob;
import assistant.app.bind.logic.IWechatAccessTokenLogic;
import assistant.app.bind.logic.impl.WechatAccessTokenLogicImpl;
import assistant.app.bind.model.WechatAccessToken;
import assistant.app.custom.logic.ICustomGroupLogic;
import assistant.app.custom.logic.impl.CustomGroupLogicImpl;

/**
 * @ClassName GetGroupJob
 * @Description 获取用户分组
 * @author minlingchao
 * @date 2015-2-13 下午7:55:33
 */
// @On("0 0 23 * * ?")
public class GetGroupJob extends OnceJob<WechatAccessToken> {

	public static final Logger LOG = LogCongfig.CUSTOMLOG;

	private IWechatAccessTokenLogic wechatAccessTokenLogic = WechatAccessTokenLogicImpl
			.getInstance();
	private ICustomGroupLogic customGroupLogic = CustomGroupLogicImpl
			.getInstance();

	@Override
	public List<WechatAccessToken> getAllData() {
		return wechatAccessTokenLogic.findAll();
	}

	@Override
	public boolean dealOne(WechatAccessToken accessToken) {
		customGroupLogic.getGroup(accessToken.getWechatId(),
				accessToken.getAccessToken());
		return false;
	}

	@Override
	public Logger getLog() {
		return LOG;
	}

}
