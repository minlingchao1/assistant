/**
 * @Project:assistant
 * @Title: ICustomLocationMapper.java
 * @date: 2015-2-14 上午10:34:09
 * @version 1.0
 */
package assistant.app.custom.dao.mapper;

import assistant.app.custom.model.CustomLocation;

/**
 * @ClassName ICustomLocationMapper
 * @Description 用户地理位置
 * @author minlingchao
 * @date 2015-2-14 上午10:34:09
 */
public interface ICustomLocationMapper {

	public long insert(CustomLocation customLocation);

	public CustomLocation findByWechatId(long wedchatId);
}
