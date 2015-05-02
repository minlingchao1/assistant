/**
 * @Project:assistant
 * @Title: IArticleLogicImpl.java
 * @date: 2015-2-28 下午11:32:04
 * @version 1.0
 */
package assistant.app.reply.logic.impl;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.reply.dal.IArticleDao;
import assistant.app.reply.dal.impl.ArticleDaoImpl;
import assistant.app.reply.logic.IArticleLogic;
import assistant.app.reply.model.Article;

/**
 * @ClassName IArticleLogicImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-28 下午11:32:04
 */
public class ArticleLogicImpl implements IArticleLogic {

	private static final Logger LOG = LogCongfig.WECHATMSGREPLYLOG;

	private static ArticleLogicImpl instance = new ArticleLogicImpl();

	private ArticleLogicImpl() {

	}

	public static ArticleLogicImpl getInstance() {
		return instance;
	}

	private IArticleDao articleDao = ArticleDaoImpl.getInstance();

	@Override
	public Article findById(long id) {
		return articleDao.findById(id);
	}

}
