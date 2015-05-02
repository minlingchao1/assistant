/**
 * @Project:assistant
 * @Title: ArticleDaoImpl.java
 * @date: 2015-2-28 下午10:55:30
 * @version 1.0
 */
package assistant.app.reply.dal.impl;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.reply.dal.IArticleDao;
import assistant.app.reply.dao.mapper.IArticleMapper;
import assistant.app.reply.model.Article;

/**
 * @ClassName ArticleDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-28 下午10:55:30
 */
public class ArticleDaoImpl implements IArticleDao {

	private static final Logger LOG = LogCongfig.WECHATMSGREPLYLOG;

	private static ArticleDaoImpl instance = new ArticleDaoImpl();

	private ArticleDaoImpl() {

	}

	public static ArticleDaoImpl getInstance() {
		return instance;
	}


	@Override
	public Article findById(long id) {
		Article result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			IArticleMapper articleMapper = session.getMapper(IArticleMapper.class);
			result = articleMapper.findById(id);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

}
