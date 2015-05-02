/**
 * @Project:assistant
 * @Title: WechatInfoCtrl.java
 * @date: 2015-2-4 下午4:26:00
 * @version 1.0
 */
package controllers.wx;

import java.util.List;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.bind.logic.IWechatInfoLogic;
import assistant.app.bind.logic.impl.WechatInfoLogicImpl;
import assistant.app.bind.model.WechatAccountInfo;
import controllers.base.BaseController;

/**
 * @ClassName WechatInfoCtrl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-4 下午4:26:00
 */
public class WechatInfoCtrl extends BaseController {

	public static final Logger LOG = LogCongfig.WECHATINFOLOG;

	private static IWechatInfoLogic wechatInfoLogic = WechatInfoLogicImpl.getInstance();

	// 获取微信账号信息列表
	public static void getInfo() {
		Long userId = getMerchantId();
		List<WechatAccountInfo> wechatAccountInfos = wechatInfoLogic.getWechatInfo(userId);
		renderJsonAjaxResult(wechatAccountInfos);
	}

	// 删除微信号
	public static void delete(long id) {

	}

}
