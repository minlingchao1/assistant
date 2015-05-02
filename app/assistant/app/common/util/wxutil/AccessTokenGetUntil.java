/**
 * @Project:assistant
 * @Title: AccessTokenGetUntil.java
 * @date: 2015-2-10 下午4:03:39
 * @version 1.0
 */
package assistant.app.common.util.wxutil;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.config.WechatConfig;
import assistant.app.common.util.WebUtil;

/**
 * @ClassName AccessTokenGetUntil
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-10 下午4:03:39
 */
public class AccessTokenGetUntil {

	public static final Logger LOG = LogCongfig.WECHATINFOLOG;

	public static String getAccessToken(String appId, String appSecret) {

		// ?grant_type=client_credential&appid=%s&secret=%s
		NameValuePair[] data = new NameValuePair[] { new NameValuePair("grant_type", "client_credential"),
				new NameValuePair("appid", appId), new NameValuePair("secret", appSecret) };
		String accessToken = null;
		try {
			String result = WebUtil.doPost(WechatConfig.ACCESS_TOKEN_URL, data);
			JSONObject tokenJson = JSONObject.fromObject(result);
			if (tokenJson.get("errorcode") != null) {
				LOG.error("appId:{} get accesstoken error,errcode:{}", appId, tokenJson.getString("errcode"));

			} else {
				accessToken = tokenJson.getString("access_token");
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accessToken;
	}

}
