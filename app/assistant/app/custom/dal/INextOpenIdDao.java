/**
 * @Project:assistant
 * @Title: INextOpenIdDao.java
 * @date: 2015-2-13 下午6:43:06
 * @version 1.0
 */
package assistant.app.custom.dal;

import java.util.List;

import assistant.app.custom.model.NextOpenId;

/**
 * @ClassName INextOpenIdDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-13 下午6:43:06
 */
public interface INextOpenIdDao {

	public long insert(NextOpenId nextOpenId);

	public long update(NextOpenId nextOpenId);

	public List<NextOpenId> getAll();

}
