/**
 * @Project:assistant
 * @Title: GetWXAccessTokenJob.java
 * @date: 2015-2-10 上午11:58:33
 * @version 1.0
 */
package assistant.app.base.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.jobs.Every;
import assistant.app.base.job.base.OnceJob;
import assistant.app.bind.logic.IWechatAccessTokenLogic;
import assistant.app.bind.logic.IWechatInfoLogic;
import assistant.app.bind.logic.impl.WechatAccessTokenLogicImpl;
import assistant.app.bind.logic.impl.WechatInfoLogicImpl;
import assistant.app.bind.model.WechatAccessToken;
import assistant.app.bind.model.WechatAccountInfo;
import assistant.app.common.util.DateUtils;
import assistant.app.common.util.wxutil.AccessTokenGetUntil;

/**
 * @ClassName GetWXAccessTokenJob
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-10 上午11:58:33
 */
@Every("10s")
public class GetWXAccessTokenJob extends OnceJob<WechatAccessToken> {

	public static final Logger LOG = LoggerFactory.getLogger(GetWXAccessTokenJob.class);

	private IWechatAccessTokenLogic accessTokenLogic = WechatAccessTokenLogicImpl.getInstance();

	private IWechatInfoLogic wechatInfoLogic = WechatInfoLogicImpl.getInstance();

	@Override
	public List<WechatAccessToken> getAllData() {

		return accessTokenLogic.findAll();
	}

	@Override
	public boolean dealOne(WechatAccessToken accesstoken) {
		long now=System.currentTimeMillis();

		if (now - accesstoken.getCreateTime() > DateUtils.TWO_HOUR_MILLS){
			LOG.warn("update accessToken for wechatId:{}", accesstoken.getWechatId());
			WechatAccountInfo weAccountInfo=wechatInfoLogic.findById(accesstoken.getWechatId());
			String accessToken = AccessTokenGetUntil.getAccessToken(weAccountInfo.getAppId(),
					weAccountInfo.getAppSecret());
			LOG.warn("accessToken:{}", accessToken);
			accesstoken.setCreateTime(System.currentTimeMillis());
			accesstoken.setAccessToken(accessToken);
			accessTokenLogic.update(accesstoken);
		}
		return false;
	}

	@Override
	public Logger getLog() {
		return LOG;
	}

}
