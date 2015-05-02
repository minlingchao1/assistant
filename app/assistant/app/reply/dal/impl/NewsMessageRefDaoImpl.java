/**
 * @Project:assistant
 * @Title: NewsMessageRefDaoImpl.java
 * @date: 2015-2-26 上午8:32:52
 * @version 1.0
 */
package assistant.app.reply.dal.impl;

import java.util.List;

import mybatisplay.IbatisSessionFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.reply.dal.INewsMessageRefDao;
import assistant.app.reply.dao.mapper.IArticleMapper;
import assistant.app.reply.dao.mapper.INewsMessageRefMapper;
import assistant.app.reply.model.Article;
import assistant.app.reply.model.NewsMessageRef;

/**
 * @ClassName NewsMessageRefDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-26 上午8:32:52
 */
public class NewsMessageRefDaoImpl implements INewsMessageRefDao {

	private static final Logger LOG = LogCongfig.MENULOG;

	private static NewsMessageRefDaoImpl instance = new NewsMessageRefDaoImpl();

	private NewsMessageRefDaoImpl() {

	}

	public static NewsMessageRefDaoImpl getInstance() {
		return instance;
	}

	@Override
	public long insert(Long userId, Long wechatId, String msgType, String newsJSon) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			String msgId = "";

			JSONArray newspaper = JSONArray.fromObject(newsJSon);
			
			for(int i=0;i<newspaper.size();i++){
				JSONObject obj = newspaper.getJSONObject(i);
				String title = obj.getString("tit");
				String imgUrl = obj.getString("img");
				String desc = obj.getString("desc");
				String url = obj.getString("url");
				String content = obj.getString("con");
				
				Article article=new Article();
				article.setTitle(title);
				article.setImgUrl(imgUrl);
				article.setContent(content);
				article.setAbstracts(desc);
				article.setUrl(url);
				
				IArticleMapper articleMapper = session.getMapper(IArticleMapper.class);
				articleMapper.insert(article);

				Long articleId = article.getId();
				msgId += articleId + ",";
			}

			NewsMessageRef messageRef = new NewsMessageRef();
			messageRef.setMsgId(msgId);
			messageRef.setWechatId(wechatId);
			messageRef.setUserId(userId);
			messageRef.setMsgType(msgType);
			INewsMessageRefMapper newsMapper = session.getMapper(INewsMessageRefMapper.class);
			result = newsMapper.insert(messageRef);
			session.commit();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<NewsMessageRef> getNews(long userId, long wechatId) {
		List<NewsMessageRef> result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			INewsMessageRefMapper newsMapper = session.getMapper(INewsMessageRefMapper.class);
			result = newsMapper.getNews(userId, wechatId);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public NewsMessageRef findById(long id) {
		NewsMessageRef result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			INewsMessageRefMapper newsMapper = session.getMapper(INewsMessageRefMapper.class);
			result = newsMapper.findById(id);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

}
