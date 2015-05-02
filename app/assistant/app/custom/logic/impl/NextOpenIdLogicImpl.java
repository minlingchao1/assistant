/**
 * @Project:assistant
 * @Title: NextOpenIdLogicImpl.java
 * @date: 2015-2-13 下午6:46:19
 * @version 1.0
 */
package assistant.app.custom.logic.impl;

import java.util.List;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.custom.dal.INextOpenIdDao;
import assistant.app.custom.dal.impl.NextOpenIdDaoImpl;
import assistant.app.custom.logic.INextOpenIdLogic;
import assistant.app.custom.model.NextOpenId;

/**
 * @ClassName NextOpenIdLogicImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-13 下午6:46:19
 */
public class NextOpenIdLogicImpl implements INextOpenIdLogic {

	public static final Logger LOG = LogCongfig.CUSTOMLOG;

	private static NextOpenIdLogicImpl instance = new NextOpenIdLogicImpl();

	private NextOpenIdLogicImpl() {

	}

	public static NextOpenIdLogicImpl getInstance() {
		return instance;
	}

	private INextOpenIdDao nextOpenIdDao = NextOpenIdDaoImpl.getInstance();

	@Override
	public long insert(NextOpenId nextOpenId) {
		return nextOpenIdDao.insert(nextOpenId);
	}

	@Override
	public long update(NextOpenId nextOpenId) {
		return nextOpenIdDao.update(nextOpenId);
	}

	@Override
	public List<NextOpenId> getAll() {
		return nextOpenIdDao.getAll();
	}

}
