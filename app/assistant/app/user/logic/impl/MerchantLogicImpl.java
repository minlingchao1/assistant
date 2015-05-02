/**
 * @Project:assistant
 * @Title: MerchantLogicImpl.java
 * @date: 2015-1-17 下午10:33:07
 * @version 1.0
 */
package assistant.app.user.logic.impl;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.common.util.MD5Util;
import assistant.app.user.dal.IMerchantDao;
import assistant.app.user.dal.impl.MerchantDaoImpl;
import assistant.app.user.logic.IMerchantLogic;
import assistant.app.user.model.Merchant;

/**
 * @ClassName MerchantLogicImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-1-17 下午10:33:07
 */
public class MerchantLogicImpl implements IMerchantLogic {

	public static final Logger LOG=LogCongfig.MERCHANTLOG;
	
    private static MerchantLogicImpl instance=new MerchantLogicImpl();
	
	private MerchantLogicImpl(){
		
	}
	public static MerchantLogicImpl getInstance(){
		return instance;
	}
	
	private IMerchantDao merchantDao=MerchantDaoImpl.getInstance();
	
	@Override
	public long register(String address, String email, String mobile, String passwd, String qq, String userName) {
		String password=MD5Util.MD5(passwd);
		Merchant merchant=new Merchant(address, email, mobile, password, userName,qq);
		return merchantDao.insert(merchant);
	}
	@Override
	public Merchant login(String userName, String password) {
		String passwd=MD5Util.MD5(password);
		return merchantDao.findByUserNameAndPasswd(userName, passwd);
	}
	@Override
	public Merchant getInfo(Long id) {
		return merchantDao.findMerchantInfo(id);
	}
	@Override
	public long edit(Merchant merchant) {
		return merchantDao.update(merchant);
	}
	@Override
	public long changePasswd(Long id,String oldPasswd,String newPasswd) {
		Merchant merchant=merchantDao.findPasswdById(id);
		if(merchant.getPasswd().equals(MD5Util.MD5(oldPasswd))){
			//Merchant merchant=merchantDao.findMerchantInfo(id);
			merchant.setPasswd(MD5Util.MD5(newPasswd));
			return merchantDao.updatePasswd(merchant);
		}
		return 0;
	}

}
