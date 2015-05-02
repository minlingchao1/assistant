/**
 * @Project:assistant
 * @Title: INewsMessageRefLogic.java
 * @date: 2015-2-26 上午8:35:00
 * @version 1.0
 */
package assistant.app.reply.logic;

import java.util.List;

import assistant.app.reply.model.NewsMessageRef;

/**
 * @ClassName INewsMessageRefLogic
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-26 上午8:35:00
 */
public interface INewsMessageRefLogic {

	public long insert(Long userId, Long wechatId, String msgType, String newsJSon);

	public List<NewsMessageRef> getNews(long userId, long wechatId);

	public NewsMessageRef findById(long id);

}
