/**
 * @Project:assistant
 * @Title: OverallDaoImpl.java
 * @date: 2015-3-5 下午4:48:45
 * @version 1.0
 */
package assistant.app.overall.dal.impl;

import java.util.List;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.config.WechatConfig;
import assistant.app.overall.dal.IOverallDao;
import assistant.app.overall.dao.mapper.IOverallMapper;
import assistant.app.overall.dao.mapper.IOverallMessageRefMapper;
import assistant.app.overall.model.Overall;
import assistant.app.overall.model.OverallMessageRef;
import assistant.app.reply.dao.mapper.IArticleMapper;
import assistant.app.reply.dao.mapper.IKeywordReplyRuleMapper;
import assistant.app.reply.model.Article;
import assistant.app.reply.model.KeywordReplyRule;
import assistant.app.subscribe.config.ReplyMsgType;

/**
 * @ClassName OverallDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-5 下午4:48:45
 */
public class OverallDaoImpl implements IOverallDao {

	private static final Logger LOG = LogCongfig.OVERALLLOG;

	private static OverallDaoImpl instance = new OverallDaoImpl();

	private OverallDaoImpl() {

	}

	public static OverallDaoImpl getInstance() {
		return instance;
	}

	@Override
	public boolean addOverall(long userId, long wechatId, String name, String keyword, String descr, String pageImg,
			String left, String right, String top, String bottom, String front, String behind, String musicSet,
			String backMusic) {

		boolean isSuccess = false;
		SqlSession session = IbatisSessionFactory.get().openSession(false);

		try {
			// 添加全景图信息
			Overall overall = new Overall();
			overall.setName(name);
			overall.setDescr(descr);
			overall.setBehindUrl(behind);
			overall.setFrontUrl(front);
			overall.setTopUrl(top);
			overall.setBottomUrl(bottom);
			overall.setLeftUrl(left);
			overall.setRightUrl(right);
			overall.setWechatId(wechatId);
			overall.setMusicSet(musicSet);
			overall.setBackMusic(backMusic);

			IOverallMapper overallMapper = session.getMapper(IOverallMapper.class);
			overallMapper.insert(overall);
			Long overallId = overall.getId();

			// 添加图文消息信息
			Article article = new Article();
			article.setAbstracts(descr);
			article.setImgUrl(pageImg);
			article.setTitle(name);
			// 设置全景图URL
			String url = WechatConfig.OVERALL_URL;
			url = url.replace("OVERALL_ID", overallId.toString());
			article.setUrl(url);
			IArticleMapper articleMapper = session.getMapper(IArticleMapper.class);
			articleMapper.insert(article);
			Long msgId = article.getId();

			// 添加关联
			OverallMessageRef overallMessageRef = new OverallMessageRef();
			overallMessageRef.setMsgId(msgId);
			overallMessageRef.setOverallId(overallId);
			IOverallMessageRefMapper messageRefMapper = session.getMapper(IOverallMessageRefMapper.class);
			messageRefMapper.insert(overallMessageRef);

			// 添加关键词信息
			KeywordReplyRule keywordReplyRule = new KeywordReplyRule();
			keywordReplyRule.setKeyword(keyword);
			keywordReplyRule.setMsgId(msgId);
			keywordReplyRule.setIsAllMatch(0);
			keywordReplyRule.setRuleName(name);
			keywordReplyRule.setUserId(userId);
			keywordReplyRule.setWechatId(wechatId);
			keywordReplyRule.setType(ReplyMsgType.OVERALL);

			IKeywordReplyRuleMapper ruleMapper = session.getMapper(IKeywordReplyRuleMapper.class);
			ruleMapper.insert(keywordReplyRule);
			isSuccess = true;
			session.commit();

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			session.rollback();
		}

		return isSuccess;
	}

	@Override
	public List<Overall> getList(long wechatId) {
		List<Overall> result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			IOverallMapper overallMapper = session.getMapper(IOverallMapper.class);
			result = overallMapper.getList(wechatId);
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
	public Overall findById(long id) {
		Overall result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			IOverallMapper overallMapper = session.getMapper(IOverallMapper.class);
			result = overallMapper.findById(id);
			session.commit();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

}
