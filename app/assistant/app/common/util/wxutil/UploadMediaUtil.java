/**
 * @Project:assistant
 * @Title: UploadMediaUtil.java
 * @date: 2015-2-10 下午1:08:04
 * @version 1.0
 */
package assistant.app.common.util.wxutil;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import assistant.app.base.config.WechatConfig;
import assistant.app.bind.logic.IWechatInfoLogic;
import assistant.app.bind.logic.impl.WechatInfoLogicImpl;
import assistant.app.common.util.CommonUtils;

/**
 * @ClassName UploadMediaUtil
 * @Description 上传下载媒体文件到微信服务器
 * @author minlingchao
 * @date 2015-2-10 下午1:08:04
 */
public class UploadMediaUtil {

	public static final Logger LOG = LoggerFactory.getLogger(UploadMediaUtil.class);
	public static String uploadMedia(String accessToken, String type, String mediaFileUrl) {

		String mediaId=null;
		
		String uploadMediaUrl = WechatConfig.UPLOAD_MEDIA_URL;
		LOG.warn("accessToken:{},type:{}", accessToken, type);
		uploadMediaUrl = uploadMediaUrl.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);

		// 定义数据分割符
		String boundary = "------------7da2e536604c8";
		try {
			URL uploadUrl = new URL(uploadMediaUrl);
			HttpURLConnection uploadConnection = (HttpURLConnection) uploadUrl.openConnection();
			uploadConnection.setDoOutput(true);
			uploadConnection.setDoInput(true);
			uploadConnection.setRequestMethod("POST");
			// 设置请求头
			uploadConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			// 获取媒体文件上传的输出流
			OutputStream outputStream = uploadConnection.getOutputStream();

			URL mediaUrl = new URL(mediaFileUrl);
			HttpURLConnection mediaConnection = (HttpURLConnection) mediaUrl.openConnection();
			mediaConnection.setDoOutput(true);
			mediaConnection.setRequestMethod("GET");

			// 从请求头中获取内容类型
			String contentType = mediaConnection.getHeaderField("Content-Type");
			// 根据内容类型判断文件扩展名
			String fileExt = CommonUtils.getFileExt(contentType);
			
			// 请求体.
			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream.write(String.format("Content-Disposition:form-data;name=\"media\";filename=\"file1%s\"\r\n",
					fileExt).getBytes());
			outputStream.write(String.format("Content-Type:%s\r\n\r\n", contentType).getBytes());

			// 获取媒体文件输入流
			BufferedInputStream bis = new BufferedInputStream(mediaConnection.getInputStream());
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				// 将媒体文件写到输出流
				outputStream.write(buf, 0, size);
			}
			// 请求体结束
			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			outputStream.close();
			bis.close();
			mediaConnection.disconnect();

			// 获取媒体文件上传的输入流
			InputStream inputStream = uploadConnection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();

			// 释放资源
			inputStream.close();
			inputStream = null;
			uploadConnection.disconnect();

			// 解析返回结果
			JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
			if (jsonObject.get("errcode") != null) {
				LOG.error("uploadMediaUntil error:{}", jsonObject.toString());
				return mediaId;
			}
			mediaId=jsonObject.getString("media_id");
			// LOG.warn("upload media return result:{}", jsonObject);
			System.out.println(jsonObject.toString());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOG.error(e.getMessage(), e);
		}

		return mediaId;
	}

	public static void main(String[] args) {
		IWechatInfoLogic infoLogic = WechatInfoLogicImpl.getInstance();
		// String accessToken = infoLogic.getAccessToken("wx4d288fbd3b62713b",
		// "37be1377d7bb4878591c395a2eea0346");
		UploadMediaUtil
				.uploadMedia(
						"OMTaF8DfdZxbGlRxzD_vl7ZhcoeKycP1QYaUJv4z78kcHeAzcf3NfzIIW1LRFizvBgpVLVMcRA1woe7sonTdI_kqSDvZdZfYTzox6zip5U4",
						"image", "http://127.0.0.1/public/images/background.jpg");
	}
}
