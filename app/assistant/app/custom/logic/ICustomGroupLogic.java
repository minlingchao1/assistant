/**
 * @Project:assistant
 * @Title: ICustomGroupLogic.java
 * @date: 2015-2-13 下午5:41:40
 * @version 1.0
 */
package assistant.app.custom.logic;

import assistant.app.custom.model.CustomGroup;

/**
 * @ClassName ICustomGroupLogic
 * @Description 用户分组管理
 * @author minlingchao
 * @date 2015-2-13 下午5:41:40
 */
public interface ICustomGroupLogic {

	public long insert(CustomGroup group);

	/**
	 * 获取用户分组
	 */
	public long getGroup(long wechatId, String accessToken);

	/**
	 * 创建用户分组
	 */

	public long createGroup(long wechatId, String groupName);
	
	/**
	 * 修改用户分组
	 */
	public boolean updateGroup(long wechatId, int groupId, String groupName);

	/**
	 * 更新
	 */
	public long update(CustomGroup group);

	/**
	 * 查找分组
	 */
	public CustomGroup findById(long wechatId, long id);

}
