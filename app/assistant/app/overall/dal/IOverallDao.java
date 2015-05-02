/**
 * @Project:assistant
 * @Title: IOverallDao.java
 * @date: 2015-3-5 下午4:48:35
 * @version 1.0
 */
package assistant.app.overall.dal;

import java.util.List;

import assistant.app.overall.model.Overall;

/**
 * @ClassName IOverallDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-5 下午4:48:35
 */
public interface IOverallDao {

	public boolean addOverall(long userId, long wechatId, String name, String keyword, String descr, String pageImg,
			String left, String right, String top, String bottom, String front, String behind, String musicSet,
			String backMusic);

	public List<Overall> getList(long wechatId);

	public Overall findById(long id);
}
