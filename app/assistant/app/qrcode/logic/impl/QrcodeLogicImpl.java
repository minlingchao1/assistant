/**
 * @Project:assistant
 * @Title: QrcodeLogicImpl.java
 * @date: 2015-2-12 下午6:07:14
 * @version 1.0
 */
package assistant.app.qrcode.logic.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;

import play.Play;
import play.mvc.Http.Request;
import assistant.app.base.config.LogCongfig;
import assistant.app.base.config.WechatConfig;
import assistant.app.bind.dal.IWechatAccessTokenDao;
import assistant.app.bind.dal.impl.WechatAccessTokenDaoImpl;
import assistant.app.bind.model.WechatAccessToken;
import assistant.app.common.util.CommonUtils;
import assistant.app.common.util.FIleUrlUtil;
import assistant.app.common.util.WebUtil;
import assistant.app.qrcode.dal.IQrcodeDao;
import assistant.app.qrcode.dal.impl.QrcodeDaoImpl;
import assistant.app.qrcode.dto.ActionInfo;
import assistant.app.qrcode.dto.PermanentQrcodeDto;
import assistant.app.qrcode.dto.Scene;
import assistant.app.qrcode.dto.TempQrcodeDto;
import assistant.app.qrcode.logic.IQrcodeLogic;
import assistant.app.qrcode.model.Qrcode;

/**
 * @ClassName QrcodeLogicImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-12 下午6:07:14
 */
public class QrcodeLogicImpl implements IQrcodeLogic {

	private static final Logger LOG = LogCongfig.QRCODELOG;

	private static QrcodeLogicImpl instance = new QrcodeLogicImpl();

	private QrcodeLogicImpl() {

	}

	public static QrcodeLogicImpl getInstance() {
		return instance;
	}

	private IQrcodeDao qrcodeDao = QrcodeDaoImpl.getInstance();

	private IWechatAccessTokenDao accessTokenDao = WechatAccessTokenDaoImpl.getInstance();
	@Override
	public long insert(Qrcode qrcode) {
		return qrcodeDao.insert(qrcode);
	}

	@Override
	public boolean createQrcode(Request request, long wechatId, int expireSeconds, String sceneName, String actionName,
			int sceneId) {
		boolean isSuccess = false;
		Scene scene = new Scene(sceneId);
		ActionInfo actionInfo = new ActionInfo(scene);
		if(actionName.equals(WechatConfig.QR_SCENE)){
			TempQrcodeDto qrcodeDto = new TempQrcodeDto(expireSeconds, actionName, actionInfo);
			JSONObject qrcodeJson = JSONObject.fromObject(qrcodeDto);
			String ticket = sendQrInfo(wechatId, actionName, qrcodeJson.toString());
			if (ticket != null) {
				Qrcode qrcode = new Qrcode(wechatId, expireSeconds, actionName, ticket, sceneName, sceneId);
				String qrImg = getQrImg(request, ticket);
				if(qrImg!=null){
					qrcode.setQrImg(qrImg);
				}
				if (insert(qrcode) > 0) {
					isSuccess = true;
				}

			}
		} else {
			PermanentQrcodeDto permanentQrcodeDto = new PermanentQrcodeDto(actionName, actionInfo);
			JSONObject qrcodeJson = JSONObject.fromObject(permanentQrcodeDto);
			String ticket = sendQrInfo(wechatId, actionName, qrcodeJson.toString());
			if (ticket != null) {
				Qrcode qrcode = new Qrcode(wechatId, expireSeconds, actionName, ticket, sceneName, sceneId);
				String qrImg = getQrImg(request, ticket);
				if (qrImg != null) {
					qrcode.setQrImg(qrImg);
				}
				if (insert(qrcode) > 0) {
					isSuccess = true;
				}
			}

		}

		return isSuccess;
	}

	@Override
	public String sendQrInfo(long wechatId, String actionName, String qrJson) {

		String ticket = null;
		WechatAccessToken accessToken = accessTokenDao.findByWechatId(wechatId);
		NameValuePair[] data = new NameValuePair[] { new NameValuePair("access_token", accessToken.getAccessToken()) };
		String result=null;
		if(actionName.equals("QR_SCENE")){
			String url = WechatConfig.QR_TEMPOARY_URL;
			url = url.replace("TOKEN", accessToken.getAccessToken());
			result = WebUtil.httpRequest(url, "POST", qrJson);
			if (result != null) {
				JSONObject resJson = JSONObject.fromObject(result);
				if (resJson.get("errcode") == null) {
					ticket = resJson.getString("ticket");
					LOG.warn("创建临时二维码成功!");
				} else {
					LOG.error("创建临时二维码--errcode:{},errMsg:{}", resJson.get("errcode"), resJson.get("errmsg"));
				}
			}
		}else{
			String url = WechatConfig.QR_TEMPOARY_URL;
			url = url.replace("TOKEN", accessToken.getAccessToken());
			result = WebUtil.httpRequest(url, "POST", qrJson);
			if (result != null) {
				JSONObject resJson = JSONObject.fromObject(result);
				if (resJson.get("errcode") == null) {
					ticket = resJson.getString("ticket");
					LOG.warn("创建永久二维码成功!");
				} else {
					LOG.error("创建永久二维码--errcode:{},errMsg:{}", resJson.get("errcode"), resJson.get("errmsg"));
				}
			}
		}
		
		return ticket;
	}

	@Override
	public List<Qrcode> findByWechatId(long wechatId) {
		return qrcodeDao.findByWechatId(wechatId);
	}
	@Override
	public String getQrImg(Request request, String ticket) {

		String filePath = null;
		String realPath=null;
		String getQrUrl = WechatConfig.QR_GET_URL;
		getQrUrl = getQrUrl.replace("TICKET", CommonUtils.urlEncodeUTF8(ticket));
		
		try {
			URL url = new URL(getQrUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			
			String savePath = getSavePath(request);
			if(!savePath.endsWith("/")){
				savePath += "/";
				
			}

			// ticket作为文件名
			filePath = savePath + ticket + ".jpg";
			realPath = getQrRealPath(request) + ticket + ".jpg";
			// 将微信服务器返回的输入流写入文件
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			FileUtils.copyInputStreamToFile(bis, new File(filePath));
			bis.close();
			
			conn.disconnect();
			
			LOG.info("换取二维码成功!,filePath:{}", filePath);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return realPath;
	}

	@Override
	public String getSavePath(Request request) {
		String qrUrl = FIleUrlUtil.getQrUrl();
		// String savePath = request.getBase() + qrUrl + File.separatorChar;
		String savePath = Play.applicationPath + qrUrl + File.separatorChar;
		LOG.warn("saveQrPath:{}", savePath);
		return savePath;
	}

	@Override
	public String getQrRealPath(Request request) {
		String qrUrl = FIleUrlUtil.getQrUrl();
		String savePath = request.getBase() + qrUrl + File.separatorChar;
		// String savePath = Play.applicationPath + qrUrl + File.separatorChar;
		LOG.warn("realPath:{}", savePath);
		return savePath;
	}

}
