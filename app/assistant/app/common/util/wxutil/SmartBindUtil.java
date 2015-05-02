/**
 * @Project:assistant
 * @Title: SmartBindUtil.java
 * @date: 2015-2-16 下午10:52:48
 * @version 1.0
 */
package assistant.app.common.util.wxutil;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.cache.Cache;
import assistant.app.base.config.WechatConfig;
import assistant.app.bind.model.WechatAccountInfo;

/**
 * @ClassName SmartBindUtil
 * @Description 智能绑定工具类
 * @author minlingchao
 * @date 2015-2-16 下午10:52:48
 */
public class SmartBindUtil {

	private static final Logger LOG = LoggerFactory.getLogger(SmartBindUtil.class);

	private static HttpClient client = new HttpClient();

	/**
	 * 是否已登录
	 */
	private static boolean isLogin = false;

	/**
	 * cookie
	 */
	private static String cookieStr;

	/**
	 * token
	 */
	private static String token;

	/**
	 * 得到AppId,AppSecret
	 * 
	 * @return
	 */
	public static WechatAccountInfo getWechatDev(String userName, String passwd) {
		int staus = login(userName, passwd);
		if (staus == HttpStatus.SC_OK) {
			isLogin = true;
			return getWechatDevInfo();
		} else {
			return null;
		}
	}

	/**
	 * 登录
	 */
	public static int login(String userName, String passwd) {
		if (isLogin) {
			return HttpStatus.SC_OK;
		}
		PostMethod postMethod = new PostMethod(WechatConfig.LOGIN_URL);
		setHeader(postMethod);
		setLoginParams(postMethod, userName, passwd);
		LOG.warn("userName：{}，正在登录微信平台");

		try {
			int status = client.executeMethod(postMethod);
            if(status==HttpStatus.SC_OK){
				LOG.warn("userName:{},登录成功", userName);
				String result = postMethod.getResponseBodyAsString();
				JSONObject resJson = JSONObject.fromObject(result);
				LOG.warn("登录返回结果:{}", resJson.toString());
				int ret = resJson.getJSONObject("base_resp").getInt("ret");
				if (ret == 0) {
					LOG.info("userName：{}，微信验证成功!", userName);
					Cookie[] cookies = client.getState().getCookies();
					StringBuffer cookie = new StringBuffer();
					for (Cookie c : cookies) {
						cookie.append(c.getName()).append("=").append(c.getValue()).append(";");
					}
					cookieStr = cookie.toString();
					
					LOG.warn("userName：{}，获取TOKEN中", userName);
					String redirectUrl = resJson.getString("redirect_url");
					if(redirectUrl!=null&&redirectUrl.trim().length()>0){
						String[] ss = StringUtils.split(redirectUrl, "?");
						String[] ps = null;
						if (ss.length == 2) {
							if (!StringUtils.isBlank(ss[1]) && ss[1].indexOf("&") != -1)
								ps = StringUtils.split(ss[1], "&");
						} else if (ss.length == 1) {
							if (!StringUtils.isBlank(ss[0]) && ss[0].indexOf("&") != -1)
								ps = StringUtils.split(ss[0], "&");
						}
						if (ps != null) {
							for (String p : ps) {
								if (StringUtils.isBlank(p)) {
									continue;
								}
								String[] tk = StringUtils.split(p, "=");
								if (tk[0] != null && "token".equals(tk[0].trim().toLowerCase())) {
									LOG.error("token:", tk[1]);
									token = tk[1];
									Cache.set("token", tk[1]);
									// LOG.error("token:", tk[1]);
									// Cache.add("token", tk[1]);
									break;
								}
							}
						}
						LOG.warn("userName：{},获取token成功...", userName);
					}
					LOG.warn("userName：{},正在进入微信首页...", userName);
					return index();
				} else {
					LOG.warn("userName：{},微信端验证失败code: " + userName);
					return ret;
				}
			} else {
				System.err.println("Method failed: " + postMethod.getStatusLine());
				LOG.warn("userName：{},网络连接错误", userName);
				return -1;
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;

	}

	/**
	 * 登录微信首页
	 */
	public static int index() {
		String token = Cache.get("token").toString();
		GetMethod get = new GetMethod(WechatConfig.INDEX_URL + token);
		setHeader(get);
		int status = -1;
		try {
			status = client.executeMethod(get);
			get.releaseConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status;
	}

	/**
	 * 得到AppId,AppSecret
	 * 
	 * @return
	 */
	public static WechatAccountInfo getWechatDevInfo() {
		// TODO 得到AppId,AppSecret
		LOG.info("获取appid,appsecret信息...");
		String token = Cache.get("token").toString();
		WechatAccountInfo devInfo = null;
		GetMethod get = new GetMethod(WechatConfig.DEV_URL + token);
		setHeader(get);
		try {
			int status = client.executeMethod(get);
			if (status == HttpStatus.SC_OK) {
				LOG.info("已经连接，正在接收数据");
				String res = get.getResponseBodyAsString();
				org.jsoup.nodes.Document doc = Jsoup.parseBodyFragment(res);
				Elements eles = doc.select(".developer_info_wrp");
				if (eles != null && eles.size() > 0) {
					Elements datas = eles.select(".frm_vertical_pt");
					devInfo = new WechatAccountInfo();
					devInfo.setAppId(datas.get(0).text());
					devInfo.setAppSecret(datas.get(1).text().replaceAll("重置", "").trim());
					Cache.set("encodingAesKey", datas.get(4).text());
					LOG.info("encodingAESKEY：{}", Cache.get("encodingAesKey"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			get.releaseConnection();
		}
		return devInfo;
	}

	/**
	 * 得到微信公众平台个人信息
	 * 
	 * @return
	 */
	public static WechatAccountInfo getAccount() {
		// TODO获取微信账号基本信息
		LOG.info("获取账户信息...");
		WechatAccountInfo account = null;
		String token = Cache.get("token").toString();
		GetMethod get = new GetMethod(WechatConfig.ACCOUNT_INFO_URL + token);
		setHeader(get);
		try {
			int status = client.executeMethod(get);
			if (status == HttpStatus.SC_OK) {
				LOG.info("已经连接，正在接收数据");
				String res = get.getResponseBodyAsString();
				Document doc = Jsoup.parseBodyFragment(res);
				Elements eles = doc.select(".account_setting_area .meta_content");
				LOG.info("账户信息:{}", eles.size());
				if (eles != null && eles.size() > 0) {
					account = new WechatAccountInfo();
					account.setAccountName(eles.get(2).text());
					account.setHeadImage(WechatConfig.HOST_URL + eles.get(0).select("img").attr("src"));
					account.setAccountId(eles.get(10).text());
					account.setWechatNumber(eles.get(3).text());
					account.setType(eles.get(4).text());
					account.setAuthenticate(eles.get(6).text());
					account.setQrcode(WechatConfig.HOST_URL + eles.get(1).select("img").attr("src"));
					LOG.info("账户信息account:{}", account.getAccountName());
				}

			}
		} catch (Exception e) {
			LOG.error("网络连接发生错误", e);
		} finally {
			get.releaseConnection();
		}
		return account;
	}

	/**
	 * 切换开发模式/编辑模式
	 * 
	 * @param flag
	 *            开启1关闭0
	 * @param type
	 *            开发模式2编辑模式1
	 * @return
	 */
	public static Result<Integer> enabledDev(int flag, int type, String userName, String passwd) {
		Result<Integer> result = new Result<Integer>();
		int staus = login(userName, passwd);
		if (staus == HttpStatus.SC_OK) {
			isLogin = true;
			PostMethod post = new PostMethod(WechatConfig.DEV_UPDATE_URL);
			setHeader(post);
			String token = Cache.get("token").toString();
			NameValuePair[] params = new NameValuePair[] { new NameValuePair("flag", "" + flag),
					new NameValuePair("type", "" + type), new NameValuePair("token", token) };
			post.setRequestBody(params);
			try {
				LOG.info("正在切换开发模式/编辑模式...");
				staus = client.executeMethod(post);
				if (staus == HttpStatus.SC_OK) {
					LOG.info("服务器连接成功");
					String res = post.getResponseBodyAsString();
					JSONObject retcode = JSONObject.fromObject(res);
					JSONObject base = retcode.getJSONObject("base_resp");
					result.setObject(Integer.parseInt(base.getString("ret")));
					result.setMsg(base.getString("err_msg"));
				} else {
					result.setObject(staus);
					result.setMsg("网络连接错误，请稍后再试");
				}
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
				result.setObject(staus);
				result.setMsg("网络连接错误，请稍后再试");
			} finally {
				post.releaseConnection();
			}
		} else {
			result.setObject(staus);
			result.setMsg("服务器登陆失败，请稍后再试");
		}
		return result;
	}

	/**
	 * 设置开发模式，服务器回调
	 * 
	 * @param url
	 * @param callback_token
	 * @return
	 */
	public static Result<Integer> setDevServiceUrl(String url, String callback_token, String userName, String passwd) {
		Result<Integer> result = new Result<Integer>();
		int staus = login(userName, passwd);
		if (staus == HttpStatus.SC_OK) {
			isLogin = true;
			String token = Cache.get("token").toString();
			PostMethod post = new PostMethod(WechatConfig.DEV_SERVICE_URL + token);
			setHeader(post);
			NameValuePair[] params = new NameValuePair[] { new NameValuePair("url", "" + url),
					new NameValuePair("callback_token", callback_token),
					new NameValuePair("encoding_aeskey", Cache.get("encodingAesKey").toString()),
					new NameValuePair("callback_encrypt_mode", "0") };
			post.setRequestBody(params);
			try {
				LOG.info("正在设置公众平台回调...");
				staus = client.executeMethod(post);
				if (staus == HttpStatus.SC_OK) {
					LOG.info("服务器连接成功");
					String res = post.getResponseBodyAsString();
					// TODO............
					System.out.println("res========" + res);
					JSONObject retcode = JSONObject.fromObject(res);
					staus = Integer.parseInt(retcode.getString("ret"));
					result.setObject(Integer.parseInt(retcode.getString("ret")));
					result.setMsg(retcode.getString("msg"));
				} else {
					result.setObject(staus);
					result.setMsg("网络连接错误，请稍后再试");
				}
			} catch (Exception e) {
				LOG.info(e.getMessage(), e);
				result.setObject(staus);
				result.setMsg("网络连接错误，请稍后再试");
			} finally {
				post.releaseConnection();
			}
		} else {
			result.setObject(staus);
			result.setMsg("服务器登陆失败，请稍后再试");
		}
		return result;
	}

	private static void setHeader(PostMethod post) {

		post.setRequestHeader(WechatConfig.CONNECTION_H, WechatConfig.CONNECTION);
		post.setRequestHeader(WechatConfig.HOST_H, WechatConfig.HOST);
		post.setRequestHeader(WechatConfig.REFERER_H, WechatConfig.REFERER);
		post.setRequestHeader(WechatConfig.USER_AGENT_H, WechatConfig.USER_AGENT);
		if (cookieStr != null && cookieStr.length() > 0) {
			post.setRequestHeader(WechatConfig.COOKIE_H, cookieStr);
		}

	}

	private static void setHeader(GetMethod get) {
		get.setRequestHeader(WechatConfig.CONNECTION_H, WechatConfig.CONNECTION);
		get.setRequestHeader(WechatConfig.HOST_H, WechatConfig.HOST);
		get.setRequestHeader(WechatConfig.REFERER_H, WechatConfig.REFERER);
		get.setRequestHeader(WechatConfig.USER_AGENT_H, WechatConfig.USER_AGENT);
		if (cookieStr != null && cookieStr.length() > 0) {
			get.setRequestHeader(WechatConfig.COOKIE_H, cookieStr);
	}
	        
	}

	private static void setLoginParams(PostMethod post, String userName, String passwd) {
		post.setRequestHeader(WechatConfig.CONTENT_TYPE_H, WechatConfig.CONTENT_TYPE);
		post.setRequestHeader(WechatConfig.XMLHTTP_REQUEST_H, WechatConfig.XMLHTTP_REQUEST);
		NameValuePair[] params = new NameValuePair[] { new NameValuePair("username", userName),
				new NameValuePair("pwd", DigestUtils.md5Hex(passwd.getBytes())), new NameValuePair("f", "json"),
				new NameValuePair("imgcode", "") };
		post.setQueryString(params);
	}

	public static void main(String[] args) {
		// SmartBindUtil smartBindUtil = new
		// SmartBindUtil("18814887640@163.com", "111217");
		SmartBindUtil.login("18814887640@163.com", "111217");
		SmartBindUtil.getWechatDevInfo();
		SmartBindUtil.getWechatDev("18814887640@163.com", "111217");

	}
}
