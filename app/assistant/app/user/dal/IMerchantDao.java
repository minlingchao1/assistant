/**
 * @Project:assistant
 * @Title: IMerchantDao.java
 * @date: 2015-1-17 下午9:14:40
 * @version 1.0
 */
package assistant.app.user.dal;

import assistant.app.user.model.Merchant;

/**
 * @ClassName IMerchantDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-1-17 下午9:14:40
 */
public interface IMerchantDao {
	
	/**
	 * 添加
	 * @param merchant
	 * @return
	 */
	public long insert(Merchant merchant);
	
	/**
	 * 更新
	 * @param merchant
	 * @return
	 */
	public long update(Merchant merchant);
	
	/**
	 * 更新密码
	 * @param passwd
	 * @param id
	 * @return
	 */
	public long updatePasswd(Merchant merchant);
	
	/**
	 * 查找用户信息
	 * @param id
	 * @return
	 */
	public Merchant findMerchantInfo(Long id);
	
	/**
	 * 查找用户密码
	 * @param id
	 * @return
	 */
	public Merchant findPasswdById(Long id);
	
	/**
	 * 根据用户名或邮箱和密码查找用户
	 */
	public Merchant findByUserNameAndPasswd(String userName,String passwd);

}
