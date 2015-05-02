/**
 * @Project:assistant
 * @Title: CustomLocationLogicImpl.java
 * @date: 2015-2-14 上午10:50:22
 * @version 1.0
 */
package assistant.app.custom.logic.impl;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.custom.dal.ICustomLocationDao;
import assistant.app.custom.dal.impl.CustomLocationDaoImpl;
import assistant.app.custom.logic.ICustomLocationLogic;
import assistant.app.custom.model.CustomLocation;

/**
 * @ClassName CustomLocationLogicImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-14 上午10:50:22
 */
public class CustomLocationLogicImpl implements ICustomLocationLogic {

	public static final Logger LOG = LogCongfig.CUSTOMLOG;

	private static CustomLocationLogicImpl instance = new CustomLocationLogicImpl();

	private CustomLocationLogicImpl() {

	}

	public static CustomLocationLogicImpl getInstance() {
		return instance;
	}

	private ICustomLocationDao customLocationDao = CustomLocationDaoImpl.getInstance();

	@Override
	public long insert(CustomLocation customLocation) {
		return customLocationDao.insert(customLocation);
	}

	@Override
	public CustomLocation findByWechatId(long wedchatId) {
		return customLocationDao.findByWechatId(wedchatId);
	}

	@Override
	public long createLocation(long wechatId, String createTime,String fromUserName, String toUserName, String lng, String lat,
			String precision) {
		CustomLocation customLocation = new CustomLocation();
		customLocation.setFromUserName(fromUserName);
		customLocation.setToUserName(toUserName);
		customLocation.setLng(lng);
		customLocation.setLat(lat);
		customLocation.setPrecisions(precision);
		customLocation.setWechatId(wechatId);
		customLocation.setOpenId(fromUserName);
		customLocation.setCreateTime(createTime);
		customLocationDao.insert(customLocation);
		return 0;
	}

}
