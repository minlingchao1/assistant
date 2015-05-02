/**
 * @Project:assistant
 * @Title: IMerchantMapper.java
 * @date: 2015-1-17 下午9:14:09
 * @version 1.0
 */
package assistant.app.user.dao.mapper;

import java.util.HashMap;

import assistant.app.user.model.Merchant;

/**
 * @ClassName IMerchantMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-1-17 下午9:14:09
 */
public interface IMerchantMapper {
	
	public long insert(Merchant merchant);
	
	public long update(Merchant merchant);
	
	public long updatePasswd(Merchant merchant);
	
	public Merchant findPasswdById(Long id);
	
	public Merchant findMerchantInfo(Long id);
	
	public Merchant findByUserNameAndPasswd(HashMap map);

}
