/**
 * @Project:assistant
 * @Title: MerchantDaoImpl.java
 * @date: 2015-1-17 下午9:14:49
 * @version 1.0
 */
package assistant.app.user.dal.impl;

import java.util.HashMap;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import assistant.app.base.config.LogCongfig;
import assistant.app.user.dal.IMerchantDao;
import assistant.app.user.dao.mapper.IMerchantMapper;
import assistant.app.user.model.Merchant;

/**
 * @ClassName MerchantDaoImpl
 * @Description 商家信息处理逻辑
 * @author minlingchao
 * @date 2015-1-17 下午9:14:49
 */
public class MerchantDaoImpl implements IMerchantDao{

	public static final Logger LOG=LogCongfig.MERCHANTLOG;
	
	private static MerchantDaoImpl instance=new MerchantDaoImpl();
	
	private MerchantDaoImpl(){
		
	}
	public static MerchantDaoImpl getInstance(){
		return instance;
	}
	@Override
	public long insert(Merchant merchant) {
		Long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        try {
        	IMerchantMapper mapper = session.getMapper(IMerchantMapper.class);
            result = mapper.insert(merchant);
            session.commit();
        } finally {
            session.close();
        }
        return result;
	}

	@Override
	public long update(Merchant merchant) {
		Long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        try {
        	IMerchantMapper mapper = session.getMapper(IMerchantMapper.class);
            result = mapper.update(merchant);
            session.commit();
        } finally {
            session.close();
        }
        return result;
	}

	@Override
	public long updatePasswd(Merchant merchant) {
		Long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        try {
        	IMerchantMapper mapper = session.getMapper(IMerchantMapper.class);
            result = mapper.updatePasswd(merchant);
            session.commit();
        } finally {
            session.close();
        }
        return result;
	}

	@Override
	public Merchant findMerchantInfo(Long id) {
		Merchant result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
        	IMerchantMapper mapper = session.getMapper(IMerchantMapper.class);
            result = mapper.findMerchantInfo(id);    
        } finally {
            session.close();
        }
        return result;
	}

	@Override
	public Merchant findPasswdById(Long id) {
		Merchant result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
        	IMerchantMapper mapper = session.getMapper(IMerchantMapper.class);
            result = mapper.findPasswdById(id);    
        } finally {
            session.close();
        }
        return result;
	}
	@Override
	public Merchant findByUserNameAndPasswd(String userName, String passwd) {
		
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("userName", userName);
		map.put("passwd", passwd);
		
		Merchant result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
        	IMerchantMapper mapper = session.getMapper(IMerchantMapper.class);
            result = mapper.findByUserNameAndPasswd(map);    
        } finally {
            session.close();
        }
        return result;
	}




}
