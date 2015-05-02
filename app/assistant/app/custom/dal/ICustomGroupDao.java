/**
 * @Project:assistant
 * @Title: ICustomGroupDao.java
 * @date: 2015-2-13 下午5:59:22
 * @version 1.0
 */
package assistant.app.custom.dal;

import assistant.app.custom.model.CustomGroup;

/**
 * @ClassName ICustomGroupDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-13 下午5:59:22
 */
public interface ICustomGroupDao {
	
	public long insert(CustomGroup group);

	public long update(CustomGroup group);

	public CustomGroup findById(long wechatId, long id);

}
