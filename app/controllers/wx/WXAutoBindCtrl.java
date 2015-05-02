/**
 * @Project:assistant
 * @Title: WXAutoBindCtrl.java
 * @date: 2015-2-20 上午10:50:15
 * @version 1.0
 */
package controllers.wx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import assistant.app.common.util.wxutil.SmartBindUtil;
import controllers.base.BaseController;

/**
 * @ClassName WXAutoBindCtrl
 * @Description 微信号自动绑定
 * @author minlingchao
 * @date 2015-2-20 上午10:50:15
 */
public class WXAutoBindCtrl extends BaseController {

	public static final Logger LOG = LoggerFactory.getLogger(WXAutoBindCtrl.class);

	// 登录验证
	public static void login(String userName, String passwd) {

		if (SmartBindUtil.login(userName, passwd) != -1) {
			// WechatAccountInfo accountInfo =
			// SmartBindUtil.getWechatDev(userName, passwd);
			// WechatAccountInfo accountInfo2 = SmartBindUtil.getAccount();
			// LOG.info("token", Cache.get("token"));
			renderJsonSuccess();
		} else {
			renderJsonFail();
		}
	}

	// 数据同步
	public static void syncData(String userName,String passwd) {
		// WechatAccountInfo accountInfo = new WechatAccountInfo();
		// WechatAccountInfo accountInfo2 =SmartBindUtil.getAccount();
		// WechatAccountInfo accountInfo3 = SmartBindUtil.getWechatDev(userName,
		// passwd);
		// accountInfo.setAppId(accountInfo3.getAppId());
		// accountInfo.setAppSecret("");
		// accountInfo.set

	}

}
