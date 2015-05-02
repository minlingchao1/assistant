/**
 * @Project:assistant
 * @Title: ICustomLocationDao.java
 * @date: 2015-2-14 上午10:45:36
 * @version 1.0
 */
package assistant.app.custom.dal;

import assistant.app.custom.model.CustomLocation;

/**
 * @ClassName ICustomLocationDao
 * @Description 用户地理位置处理
 * @author minlingchao
 * @date 2015-2-14 上午10:45:36
 */
public interface ICustomLocationDao {

	public long insert(CustomLocation customLocation);

	public CustomLocation findByWechatId(long wedchatId);
}
