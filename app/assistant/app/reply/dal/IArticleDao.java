/**
 * @Project:assistant
 * @Title: IArticleDao.java
 * @date: 2015-2-28 下午10:55:20
 * @version 1.0
 */
package assistant.app.reply.dal;

import assistant.app.reply.model.Article;

/**
 * @ClassName IArticleDao
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-28 下午10:55:20
 */
public interface IArticleDao {

	public Article findById(long id);

}
