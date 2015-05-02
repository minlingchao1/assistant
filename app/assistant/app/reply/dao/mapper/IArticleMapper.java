/**
 * @Project:assistant
 * @Title: IArticleMapper.java
 * @date: 2015-2-28 下午10:47:11
 * @version 1.0
 */
package assistant.app.reply.dao.mapper;

import assistant.app.reply.model.Article;

/**
 * @ClassName IArticleMapper
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-28 下午10:47:11
 */
public interface IArticleMapper {

	public long insert(Article article);

	public Article findById(Long id);

}
