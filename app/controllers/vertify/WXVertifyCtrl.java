/**
 * @Project:assistant
 * @Title: WXVertifyCtrl.java
 * @date: 2015-2-4 上午11:00:37
 * @version 1.0
 */
package controllers.vertify;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.bind.logic.IWechatInfoLogic;
import assistant.app.bind.logic.impl.WechatInfoLogicImpl;
import assistant.app.bind.model.WechatAccountInfo;
import assistant.app.common.util.wxutil.SignUtil;
import assistant.app.wxmsgdeal.logic.IWXMsgDealLogic;
import assistant.app.wxmsgdeal.logic.impl.WXMsgDealLogicImpl;
import controllers.base.BaseController;

/**
 * @ClassName WXVertifyCtrl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-4 上午11:00:37
 */
public class WXVertifyCtrl extends BaseController {

	private static final Logger LOG = LogCongfig.WECHATBINDLOG;
	
	private static IWXMsgDealLogic dealLogic = WXMsgDealLogicImpl.getInstance();
	private static IWechatInfoLogic wechatInfoLogic = WechatInfoLogicImpl.getInstance();

	public static void vertify() {
		String appId = request.params.get("appId");
		WechatAccountInfo wechatAccountInfo = wechatInfoLogic.findByAppId(appId);
		if (wechatAccountInfo != null) {
			String signature = request.params.get("signature");
			String timestamp = request.params.get("timestamp");
			String nonce = request.params.get("nonce");
			String echostr = request.params.get("echostr");
			String token = wechatAccountInfo.getToken();
			LOG.warn("vertify for appId:{},token:{}", appId, token);
			if (SignUtil.vaildSign(signature, token, timestamp, nonce)) {
				renderText(echostr);
			}
			renderJsonFail();
		}
		renderJsonFail();
	}

	public static void dealMsg() {
		LOG.warn("dealMsg~~~~~~~~~~~");
		String appId = request.params.get("appId");
		WechatAccountInfo wechatAccountInfo = wechatInfoLogic.findByAppId(appId);
		if (wechatAccountInfo != null) {
			String signature = request.params.get("signature");
			String timestamp = request.params.get("timestamp");
			String nonce = request.params.get("nonce");
			String echostr = request.params.get("echostr");
			String token = wechatAccountInfo.getToken();
			LOG.warn("dealMsg for appId:{},token:{}", appId, token);
			if (SignUtil.vaildSign(signature, token, timestamp, nonce)) {
				String respXml = dealLogic.processRequest(request);
				LOG.warn("respXml:{}", respXml);
				renderText(respXml);
			}
			renderJsonFail();
		}
		renderJsonFail();
	}

}
