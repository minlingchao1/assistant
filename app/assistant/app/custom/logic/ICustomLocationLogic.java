/**
 * @Project:assistant
 * @Title: ICustomLocationLogic.java
 * @date: 2015-2-14 上午10:49:25
 * @version 1.0
 */
package assistant.app.custom.logic;

import assistant.app.custom.model.CustomLocation;

/**
 * @ClassName ICustomLocationLogic
 * @Description 用户地理位置管理逻辑
 * @author minlingchao
 * @date 2015-2-14 上午10:49:25
 */
public interface ICustomLocationLogic {

	public long insert(CustomLocation customLocation);

	public CustomLocation findByWechatId(long wedchatId);

	public long createLocation(long wechatId, String createTime, String fromUserName, String toUserName, String lng,
			String lat,
			String precision);

}
