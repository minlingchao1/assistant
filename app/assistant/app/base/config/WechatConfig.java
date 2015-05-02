/**
 * @Project:assistant
 * @Title: WechatConfig.java
 * @date: 2015-1-22 下午9:56:36
 * @version 1.0
 */
package assistant.app.base.config;

/**
 * @ClassName WechatConfig
 * @Description 微信基础配置
 * @author minlingchao
 * @date 2015-1-22 下午9:56:36
 */
public class WechatConfig {
	
	
	// ////////////////////////微信基础URL///////////////////////////////////////////////////
	/**
	 * 微信URL
	 */
	public static final String HOST_URL="https://mp.weixin.qq.com";
	
	/**
	 * 微信登录地址
	 */
	public static final String LOGIN_URL="https://mp.weixin.qq.com/cgi-bin/login?lang=zh_CN";
	
	/**
	 * 微信退出地址
	 */
	public static final String LOGOUT_URL="http://mp.weixin.qq.com/cgi-bin/logout?t=wxm-logout&lang=zh_CN&token=";
	
	/**
	 * 微信主页地址
	 */
	public static final String INDEX_URL="http://mp.weixin.qq.com/cgi-bin/home?t=home/index&lang=zh_CN&token=";
	
	/**
	 * 微信账号信息
	 */
	public static final String ACCOUNT_INFO_URL="http://mp.weixin.qq.com/cgi-bin/settingpage?t=setting/index&action=index&lang=zh_CN&token=";
	
	/**
	 * 开发者页面地址
	 */
	public static final String DEV_URL="https://mp.weixin.qq.com/advanced/advanced?action=dev&t=advanced/dev&lang=zh_CN&token=";
	
	/**
	 * 切换开发模式
	 */
	public static final String DEV_UPDATE_URL="https://mp.weixin.qq.com/misc/skeyform?form=advancedswitchform&lang=zh_CN&flag=1&type=2";
	
	/**
	 * 服务器配置提交地址
	 */
	public static final String DEV_SERVICE_URL="https://mp.weixin.qq.com/advanced/callbackprofile?t=ajax-response&lang=zh_CN&token=";
	
	// 模拟请求所用的参数设置//////////////////////////////////////////////////
	public final static String COOKIE_H = "Cookie";

	public final static String CONNECTION_H = "Connection";

	public final static String CONNECTION = "keep-alive";

	public final static String HOST_H = "Host";

	public final static String HOST = "mp.weixin.qq.com";

	public final static String REFERER_H = "Referer";

	public final static String REFERER = "https://mp.weixin.qq.com/";

	public final static String USER_AGENT_H = "User-Agent";

	public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36";

	public final static String ACCEPT_H = "Accept";

	public final static String ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";

	public final static String ACCEPT_ENCODEING_H = "Accept-Encoding";

	public final static String ACCEPT_ENCODEING = "gzip,deflate,sdch";

	public final static String ACCEPT_LANGUAGE_H = "Accept-Language";

	public final static String ACCEPT_LANGUAGE = "zh-CN,zh;q=0.8";

	public final static String CONTENT_TYPE_H = "Content-Type";

	public final static String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";

	public final static String XMLHTTP_REQUEST_H = "X-Requested-With";

	public final static String XMLHTTP_REQUEST = "XMLHttpRequest";

	public final static String UTF_8 = "UTF-8";

	// ////////////////////////微信API接口地址///////////////////////////////////////////
	/**
	 * 获取ACCESS_TOKEN接口
	 */
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
	/**
	 * 上传媒体文件地址
	 */
	public static final String UPLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";


	/**
	 * 下载媒体文件地址
	 */
	public static final String DOWNLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get";
	
	/**
	 * 创建临时二维码地址
	 */
	public static final String QR_TEMPOARY_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
	
	/**
	 * 创建永久二维码地址
	 */
	public static final String QR_PERMANENT_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
	
	/**
	 * 换取二维码地址
	 */
	public static final String QR_GET_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";

	/**
	 * 创建菜单接口地址
	 */
	public static final String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * 菜单删除
	 */
	public static final String MENU_DEL_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	/**
	 * 菜单查询
	 */
	public static final String MENU_SEARCH_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	// ////////////////////////////接口配置////////////////////////////////////////////////////////////////////
	/**
	 * 二维码类型:临时二维码
	 */
	public static final String QR_SCENE = "QR_SCENE";

	/**
	 * 二维码类型:永久二维码
	 */
	public static final String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";

	/**
	 * 二维码状态:有效
	 */
	public static final int QR_STATUS_EFFECT = 1;
	
	/**
	 * 二维码状态：无效
	 */
	public static final int QR_STATUS_OUT = 0;
	
	// ////////////////////////////////用户信息/////////////////////////////////////////
	
	/**
	 * 用户：获取用户基本信息地址
	 */
	public static final String CUSTOM_INGO_GET_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";

	/**
	 * 用户:获取用户分组地址
	 */
	public static final String CUSTOM_GROUP_GET_URL = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
	
	/**
	 * 用户:创建用户分组地址
	 */
	public static final String CUSTOM_GROUP_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";

	/**
	 * 用户:修改用户分组
	 */
	public static final String CUSTOM_GROUP_UPDATE_URL = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
	
	/**
	 * 用户:查询用户所在分组
	 */
	public static final String CUSTOM_GROUP_SEARCH_URL = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";

	/**
	 * 用户:获取关注着列表
	 */
	public static final String CUSTOM_LIST_GET_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";

	/**
	 * 图文消息地址
	 */
	public static final String NEWS_URL = "http://assistant.ciaosir.com/reply/NewsTemplCtrl/newsTempl?articleId=ARTICLE_ID";

	/**
	 * 微场景地址
	 */
	public static final String SCENE_URL = "http://assistant.ciaosir.com/scene/SceneTemplCtrl/scene?sceneId=SCENE_ID";

	/**
	 * 全景图地址
	 */
	public static final String OVERALL_URL = "http://assistant.ciaosir.com/overall/OverallTemplCtrl/overrallTempl?overallId=OVERALL_ID";

}