/**
 * @Project:assistant
 * @Title: SmartBindLogicImpl.java
 * @date: 2015-2-16 下午10:04:34
 * @version 1.0
 */
package assistant.app.bind.logic.impl;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.config.WechatConfig;
import assistant.app.bind.logic.ISmartBindLogic;
import assistant.app.common.util.MD5Util;
import assistant.app.common.util.WebUtil;

/**
 * @ClassName SmartBindLogicImpl
 * @Description 智能绑定逻辑实现
 * @author minlingchao
 * @date 2015-2-16 下午10:04:34
 */
public class SmartBindLogicImpl implements ISmartBindLogic {

	public static final Logger LOG = LogCongfig.WECHATBINDLOG;

	@Override
	public long login(String userName, String passwd) {
		NameValuePair[] data = new NameValuePair[] { new NameValuePair("username", userName),
				new NameValuePair("pwd", MD5Util.MD5(passwd)), new NameValuePair("f", "json"),
				new NameValuePair("imgcode", "")
		};
		try {
			String result = WebUtil.doPostForWechat(WechatConfig.LOGIN_URL, data);
			JSONObject resjson = JSONObject.fromObject(result);
			int ret = resjson.getJSONObject("base_resp").getInt("ret");
			if (ret == 0) {
				LOG.warn("userName:{}验证成功", userName);
				String redirectUrl = resjson.getString("redirect_url");
				LOG.warn("userName:{},正在获取token", userName);
				if (redirectUrl != null && redirectUrl.trim().length() > 0) {
					String[] ss = StringUtils.split(redirectUrl, "?");
					String[] ps = null;
				}
			}
		} catch (HttpException e) {
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return 0;
	}

	@Override
	public long index() {
		return 0;
	}

	@Override
	public long enableDev(int flag, int type) {
		return 0;
	}

	@Override
	public long setDevServiceUrl(String url, String callbackToken) {
		return 0;
	}

	@Override
	public long getWechatAccountInfo() {
		return 0;
	}

	@Override
	public long getDevInfo() {
		return 0;
	}

	@Override
	public long logout() {
		return 0;
	}

}
