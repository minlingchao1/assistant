/**
 * @Project:assistant
 * @Title: ISmartBindLogic.java
 * @date: 2015-2-16 下午10:04:04
 * @version 1.0
 */
package assistant.app.bind.logic;

/**
 * @ClassName ISmartBindLogic
 * @Description 智能绑定逻辑
 * @author minlingchao
 * @date 2015-2-16 下午10:04:04
 */
public interface ISmartBindLogic {

	/**
	 * 登录
	 */
	public long login(String userName, String passwd);

	/**
	 * 进入微信首页
	 */
	public long index();

	/**
	 * 切换开发模式/编辑模式
	 */
	public long enableDev(int flag, int type);

	/**
	 * 设置开发模式，服务器回调
	 */
	public long setDevServiceUrl(String url, String callbackToken);

	/**
	 * 得到微信公众账号信息
	 */
	public long getWechatAccountInfo();

	/**
	 * 得到AppId,AppSecret
	 */
	public long getDevInfo();

	/**
	 * 退出操作
	 */
	public long logout();

}
