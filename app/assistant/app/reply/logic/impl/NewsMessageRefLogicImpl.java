/**
 * @Project:assistant
 * @Title: NewsMessageRefLogicImpl.java
 * @date: 2015-2-26 上午8:35:16
 * @version 1.0
 */
package assistant.app.reply.logic.impl;

import java.util.List;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.reply.dal.INewsMessageRefDao;
import assistant.app.reply.dal.impl.NewsMessageRefDaoImpl;
import assistant.app.reply.logic.INewsMessageRefLogic;
import assistant.app.reply.model.NewsMessageRef;

/**
 * @ClassName NewsMessageRefLogicImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-26 上午8:35:16
 */
public class NewsMessageRefLogicImpl implements INewsMessageRefLogic {

	private static final Logger LOG = LogCongfig.MENULOG;

	private static NewsMessageRefLogicImpl instance = new NewsMessageRefLogicImpl();

	private NewsMessageRefLogicImpl() {

	}

	public static NewsMessageRefLogicImpl getInstance() {
		return instance;
	}

	private INewsMessageRefDao newsMessageRefDao = NewsMessageRefDaoImpl.getInstance();

	@Override
	public long insert(Long userId,Long wechatId,String msgType,String newsJSon) {
		return newsMessageRefDao.insert(userId, wechatId, msgType, newsJSon);
	}

	@Override
	public List<NewsMessageRef> getNews(long userId, long wechatId) {
		return newsMessageRefDao.getNews(userId, wechatId);
	}

	@Override
	public NewsMessageRef findById(long id) {
		return newsMessageRefDao.findById(id);
	}

}
