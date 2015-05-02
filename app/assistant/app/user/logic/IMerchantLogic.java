/**
 * @Project:assistant
 * @Title: IMerchantLogic.java
 * @date: 2015-1-17 下午10:30:40
 * @version 1.0
 */
package assistant.app.user.logic;

import assistant.app.user.model.Merchant;

/**
 * @ClassName IMerchantLogic
 * @Description TODO
 * @author minlingchao
 * @date 2015-1-17 下午10:30:40
 */
public interface IMerchantLogic {
	
	/**
	 * 注册
	 */
	public long register(String address,String email,String mobile,String passwd,String qq,String userName);

	
	/**
	 * 登录
	 */
	public Merchant login(String userName,String password);
	
	/**
	 * 根据ID查找用户相关信息
	 */
	public Merchant getInfo(Long id);
	
	/**
	 * 更新账户信息
	 */
	public long edit(Merchant merchant);
	
	/**
	 * 修改用户密码
	 */
	public long changePasswd(Long id,String oldPasswd,String newPasswd);
}
