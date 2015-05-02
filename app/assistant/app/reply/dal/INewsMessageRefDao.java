/**
 * @Project:assistant
 * @Title: INewsMessageRefDao.java
 * @date: 2015-2-26 上午8:32:14
 * @version 1.0
 */
package assistant.app.reply.dal;

import java.util.List;

import assistant.app.reply.model.NewsMessageRef;

/**
 * @ClassName INewsMessageRefDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-26 上午8:32:14
 */
public interface INewsMessageRefDao {
	
	public long insert(Long userId, Long wechatId, String msgType, String newsJSon);

	public List<NewsMessageRef> getNews(long userId, long wechatId);

	public NewsMessageRef findById(long id);
}
