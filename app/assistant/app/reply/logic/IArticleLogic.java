/**
 * @Project:assistant
 * @Title: IArticleLogic.java
 * @date: 2015-2-28 下午11:31:45
 * @version 1.0
 */
package assistant.app.reply.logic;

import assistant.app.reply.model.Article;

/**
 * @ClassName IArticleLogic
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-28 下午11:31:45
 */
public interface IArticleLogic {

	public Article findById(long id);

}
