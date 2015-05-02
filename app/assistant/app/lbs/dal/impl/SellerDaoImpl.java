/**
 * @Project:assistant
 * @Title: SellerDaoImpl.java
 * @date: 2015-3-6 下午4:11:03
 * @version 1.0
 */
package assistant.app.lbs.dal.impl;

import java.util.List;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.lbs.dal.ISellerDao;
import assistant.app.lbs.dao.mapper.ISellerMapper;
import assistant.app.lbs.dao.mapper.ISellerMessageRefMapper;
import assistant.app.lbs.model.Seller;
import assistant.app.lbs.model.SellerMessageRef;
import assistant.app.reply.dao.mapper.IArticleMapper;
import assistant.app.reply.dao.mapper.IKeywordReplyRuleMapper;
import assistant.app.reply.model.Article;
import assistant.app.reply.model.KeywordReplyRule;
import assistant.app.subscribe.config.ReplyMsgType;

/**
 * @ClassName SellerDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-6 下午4:11:03
 */
public class SellerDaoImpl implements ISellerDao {

	public static final Logger LOG = LogCongfig.LBSLOG;

	private static SellerDaoImpl instance = new SellerDaoImpl();

	private SellerDaoImpl() {

	}

	public static SellerDaoImpl getInstance() {
		return instance;
	}

	@Override
	public long addLBSInfo(Long userId, Long wechatId, String sellerName, String descr, String prov, String city,
			String dist, String category, String address, String mobile, String img, String lngLat) {
		long result = 0l;
		SqlSession session = IbatisSessionFactory.get().openSession(false);
		try {
			// LBS信息
			Seller seller = new Seller();
			seller.setWechatId(wechatId);
			seller.setSellerName(sellerName);
			seller.setDescr(descr);
			seller.setProv(prov);
			seller.setCity(city);
			seller.setDist(dist);
			seller.setAddress(address);
			seller.setMobile(mobile);
			seller.setImg(img);
			seller.setLngLat(lngLat);
			seller.setAddress(address);
			seller.setCategory(category);

			ISellerMapper sellerMapper = session.getMapper(ISellerMapper.class);
			sellerMapper.insert(seller);
			Long sellerId = seller.getId();

			// 图文消息
			Article article = new Article();
			article.setAbstracts(descr);
			article.setTitle(sellerName);
			article.setUrl("");
			article.setImgUrl(img);
			
			IArticleMapper articleMapper = session.getMapper(IArticleMapper.class);
			articleMapper.insert(article);
			Long msgId = article.getId();

			// 关键词信息
			KeywordReplyRule keywordReplyRule = new KeywordReplyRule();
			keywordReplyRule.setIsAllMatch(1);
			keywordReplyRule.setMsgId(msgId);
			keywordReplyRule.setRuleName(sellerName);
			keywordReplyRule.setKeyword(category);
			keywordReplyRule.setType(ReplyMsgType.LBS);
			keywordReplyRule.setUserId(userId);
			keywordReplyRule.setWechatId(wechatId);

			IKeywordReplyRuleMapper replyRuleMapper = session.getMapper(IKeywordReplyRuleMapper.class);
			replyRuleMapper.insert(keywordReplyRule);

			// LBS 消息关联

			SellerMessageRef sellerMessageRef = new SellerMessageRef();
			sellerMessageRef.setMsgId(msgId);
			sellerMessageRef.setSellerId(sellerId);

			ISellerMessageRefMapper sellerMessageRefMapper = session.getMapper(ISellerMessageRefMapper.class);
			sellerMessageRefMapper.insert(sellerMessageRef);

			result = sellerId;
			session.commit();

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			session.rollback();
		}
		return result;
	}

	@Override
	public List<Seller> getList(long wechatId) {
		List<Seller> result = null;
		SqlSession session = IbatisSessionFactory.get().openSession();
		try {
			ISellerMapper sellerMapper = session.getMapper(ISellerMapper.class);
			result = sellerMapper.getList(wechatId);
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
