/**
 * @Project:assistant
 * @Title: ReplyMessageLogicImpl.java
 * @date: 2015-2-7 下午3:48:38
 * @version 1.0
 */
package assistant.app.reply.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.config.WechatConfig;
import assistant.app.common.util.wxutil.AMapUntil;
import assistant.app.common.util.wxutil.ConvertXml;
import assistant.app.common.util.wxutil.MessageUtil;
import assistant.app.lbs.dal.ISellerMessageRefDao;
import assistant.app.lbs.dal.impl.SellerMessageRefDaoImpl;
import assistant.app.lbs.logic.ISellerLogic;
import assistant.app.lbs.logic.impl.SellerLogicImpl;
import assistant.app.lbs.model.SellerMessageRef;
import assistant.app.reply.dal.IArticleDao;
import assistant.app.reply.dal.IImageMessageDao;
import assistant.app.reply.dal.INewsMessageRefDao;
import assistant.app.reply.dal.ITextMessageDao;
import assistant.app.reply.dal.IVideoMessageDao;
import assistant.app.reply.dal.IVoiceMessageDao;
import assistant.app.reply.dal.impl.ArticleDaoImpl;
import assistant.app.reply.dal.impl.ImageMessageDaoImpl;
import assistant.app.reply.dal.impl.NewsMessageRefDaoImpl;
import assistant.app.reply.dal.impl.TextMessageDaoImpl;
import assistant.app.reply.dal.impl.VideoMessageDaoImpl;
import assistant.app.reply.dal.impl.VoiceMessageDaoImpl;
import assistant.app.reply.dto.ArticleDto;
import assistant.app.reply.dto.Image;
import assistant.app.reply.dto.ImageMessageDto;
import assistant.app.reply.dto.NewsMessageDto;
import assistant.app.reply.dto.TextMessageDto;
import assistant.app.reply.dto.VideoMessageDto;
import assistant.app.reply.dto.Voice;
import assistant.app.reply.dto.VoiceMessageDto;
import assistant.app.reply.logic.IReplyMessageLogic;
import assistant.app.reply.model.Article;
import assistant.app.reply.model.ImageMessage;
import assistant.app.reply.model.NewsMessageRef;
import assistant.app.reply.model.TextMessage;
import assistant.app.reply.model.VoiceMessage;
import assistant.app.subscribe.config.ReplyMsgType;

/**
 * @ClassName ReplyMessageLogicImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-7 下午3:48:38
 */
public class ReplyMessageLogicImpl implements IReplyMessageLogic {

	private static final Logger LOG = LogCongfig.WECHATMSGREPLYLOG;

	private static ReplyMessageLogicImpl instance = new ReplyMessageLogicImpl();

	private ReplyMessageLogicImpl() {

	}

	public static ReplyMessageLogicImpl getInstance() {
		return instance;
	}

	private ITextMessageDao textMessageDao = TextMessageDaoImpl.getInstance();

	private IImageMessageDao imageMessageDao = ImageMessageDaoImpl.getInstance();

	private IVoiceMessageDao voiceMessageDao = VoiceMessageDaoImpl.getInstance();
	
	private IVideoMessageDao videoMessageDao = VideoMessageDaoImpl.getInstance();

	private INewsMessageRefDao newssMessageRefDao = NewsMessageRefDaoImpl.getInstance();

	private IArticleDao articleDao = ArticleDaoImpl.getInstance();

	private ISellerLogic sellerLogic = SellerLogicImpl.getInstance();

	private ISellerMessageRefDao sellerRefDao = SellerMessageRefDaoImpl.getInstance();

	@Override
	public long saveTextMessage(String fromUserName, String toUserName, String content) {
		TextMessage textMessage = new TextMessage();
		textMessage.setContent(content);
		textMessage.setFromUserName(toUserName);
		textMessage.setToUserName(fromUserName);
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setCreateTime(String.valueOf(new Date().getTime()));
		return textMessageDao.insert(textMessage);
	}

	@Override
	public long saveImageMessage(String fromUserName, String toUserName, String mediaId) {

		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setFromUserName(toUserName);
		imageMessage.setToUserName(fromUserName);
		imageMessage.setMediaId(mediaId);
		imageMessage.setCreateTime(String.valueOf(new Date().getTime()));
		imageMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
		return imageMessageDao.insert(imageMessage);
	}

	@Override
	public long saveVoiceMessage(String fromUserName, String toUserName, String mediaId) {
		VoiceMessage voiceMessage = new VoiceMessage();
		voiceMessage.setCreateTime(String.valueOf(new Date().getTime()));
		voiceMessage.setFromUserName(toUserName);
		voiceMessage.setToUserName(fromUserName);
		voiceMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VOICE);
		voiceMessage.setMediaId(mediaId);
		return voiceMessageDao.insert(voiceMessage);
	}

	@Override
	public long saveVideoMessage(String fromUserName, String toUserName, String mediaId, String thumbMediaId) {
		return 0;
	}

	@Override
	public TextMessageDto createTextMessage(String fromUserName, String toUserName, String content) {
		TextMessageDto textMessageDto = new TextMessageDto();
		textMessageDto.setFromUserName(toUserName);
		textMessageDto.setToUserName(fromUserName);
		textMessageDto.setContent(content);
		textMessageDto.setCreateTime(new Date().getTime());
		textMessageDto.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		return textMessageDto;
	}

	@Override
	public ImageMessageDto createImageMessage(String fromUserName, String toUserName, String mediaId) {
		Image image = new Image();
		image.setMediaId(mediaId);
		LOG.warn("Image mediaId:", mediaId);
		ImageMessageDto imageMessageDto = new ImageMessageDto();
		imageMessageDto.setImage(image);
		imageMessageDto.setFromUserName(toUserName);
		imageMessageDto.setToUserName(fromUserName);
		imageMessageDto.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
		imageMessageDto.setCreateTime(new Date().getTime());
		return imageMessageDto;
	}

	@Override
	public VoiceMessageDto createVoiceMessage(String fromUserName, String toUserName, String mediaId) {
		Voice voice = new Voice();
		voice.setMediaId(mediaId);

		VoiceMessageDto voiceMessageDto = new VoiceMessageDto();
		voiceMessageDto.setCreateTime(new Date().getTime());
		voiceMessageDto.setFromUserName(toUserName);
		voiceMessageDto.setToUserName(fromUserName);
		voiceMessageDto.setVoice(voice);
		voiceMessageDto.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VOICE);
		return voiceMessageDto;
	}

	@Override
	public VideoMessageDto createVideoMessage(String fromUserName, String toUserName, String mediaId,
			String thumbMediaId) {
		return null;
	}

	@Override
	public NewsMessageDto createNewsMessage(String fromUserName, String toUserName, String picUrl, String url,
			String description, String title) {
		ArticleDto article = new ArticleDto();
		article.setTitle(title);
		article.setUrl(url);
		article.setPicUrl(picUrl);
		article.setDescription(description);
		
		List<ArticleDto> articles = new ArrayList<ArticleDto>();
		articles.add(article);

		NewsMessageDto newsMessageDto = new NewsMessageDto();
		newsMessageDto.setArticleCount(articles.size());
		newsMessageDto.setArticles(articles);
		return newsMessageDto;
	}



	@Override
	public String createNewsMessage(Long msgId, String fromUserName, String toUserName) {
		String respXml = null;

		NewsMessageRef newsMessageRef = newssMessageRefDao.findById(msgId);
		String[] msgIds = newsMessageRef.getMsgId().split(",");
		List<ArticleDto> articles = new ArrayList<ArticleDto>();
		NewsMessageDto newsMessage = new NewsMessageDto();
		for (int i = 0; i < msgIds.length; i++) {
			Article article = articleDao.findById(Long.valueOf(msgIds[i]));
			String url;
			LOG.warn("url{}", article.getUrl().isEmpty());
			if (article.getUrl().isEmpty()) {
				url = WechatConfig.NEWS_URL;
				url = url.replace("ARTICLE_ID", article.getId().toString());
				LOG.warn("realURL:{}", url);
			} else {
				url = article.getUrl();
			}

			ArticleDto articleDto = new ArticleDto();
			articleDto.setDescription(article.getAbstracts());
			articleDto.setUrl(url);
			articleDto.setTitle(article.getTitle());
			articleDto.setPicUrl(article.getImgUrl());
			articleDto.setUrl(url);
			articles.add(articleDto);

			LOG.warn("title:{}", article.getTitle());
		}
		newsMessage.setArticles(articles);
		newsMessage.setArticleCount(msgIds.length);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setToUserName(fromUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		respXml = ConvertXml.newsMessageToXml(newsMessage);
		LOG.warn("respXml:{}", respXml);
		return respXml;
	}

	@Override
	public String createExtendNewsMessage(Long msgId, String fromUserName, String toUserName) {
		String respXml = null;
		Article article = articleDao.findById(msgId);

		List<ArticleDto> articleDtos = new ArrayList<ArticleDto>();
		ArticleDto articleDto = new ArticleDto();
		articleDto.setTitle(article.getTitle());
		articleDto.setUrl(article.getUrl());
		articleDto.setPicUrl(article.getImgUrl());
		articleDto.setDescription(article.getAbstracts());
		articleDtos.add(articleDto);

		NewsMessageDto newsMessage = new NewsMessageDto();
		newsMessage.setArticleCount(articleDtos.size());
		newsMessage.setArticles(articleDtos);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setToUserName(fromUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

		respXml = ConvertXml.newsMessageToXml(newsMessage);
		LOG.warn("respXml:{}", respXml);

		return respXml;
	}


	@Override
	public String createReplyMessage(long msgId, int type, String fromUserName, String toUserName, String replyMsg,
			String mediaId) {
		String reqXml = null;
		if (type == ReplyMsgType.TEXT) {
			// 创建文本信息
			TextMessageDto textMessageDto = createTextMessage(fromUserName, toUserName, replyMsg);
			// 保存文本信息发送记录
			saveTextMessage(fromUserName, toUserName, replyMsg);
			reqXml = ConvertXml.messageToXml(textMessageDto);
			LOG.warn("subscribe reply for {},msgType:text", fromUserName);
		} else if (type == ReplyMsgType.IMAGE) {
			// 创建图片消息
			ImageMessageDto imageMessageDto = createImageMessage(fromUserName, toUserName, mediaId);
			LOG.warn("imageMessageDto mediaID:{}", mediaId);
			// 保存图片信息发送记录
			saveImageMessage(fromUserName, toUserName, mediaId);
			reqXml = ConvertXml.messageToXml(imageMessageDto);
			LOG.warn("subscribe reply for {},msgType:image", fromUserName);
		} else if (type == ReplyMsgType.VOICE) {
			// 创建语音消息
			VoiceMessageDto voiceMessageDto = createVoiceMessage(fromUserName, toUserName, mediaId);
			// 保存语音消息发送记录
			saveVoiceMessage(fromUserName, toUserName, mediaId);
			reqXml = ConvertXml.messageToXml(voiceMessageDto);
			LOG.warn("subscribe reply for {},msgType:image", fromUserName);
		} else if (type == ReplyMsgType.SINGLE_NEWS_MESSAGE || type == ReplyMsgType.MULTI_NEWS_MESSAGW) {
			reqXml = createNewsMessage(msgId, fromUserName, toUserName);
			LOG.warn("subscribe reply for {},msgType:news", fromUserName);
		} else if (type == ReplyMsgType.SCENE || type == ReplyMsgType.OVERALL) {
			reqXml = createExtendNewsMessage(msgId, fromUserName, toUserName);
			LOG.warn("subscribe reply for {},msgType:news-scene", fromUserName);
		}

		return reqXml;
	}

	@Override
	public String createSellerNewsMessage(String center, String fromUserName, String toUserName) {
		
		String searchResult = sellerLogic.aroundSearch(center, 10000, 10, 1);

		List<ArticleDto> articleDtos = new ArrayList<ArticleDto>();
		NewsMessageDto newsMessageDto = new NewsMessageDto();
		JSONObject obj = JSONObject.fromObject(searchResult);
		String data = obj.getString("datas");
		String resXml = null;
		if (data != null) {
			LOG.warn("data:{}", data);
			JSONArray sellerArr = JSONArray.fromObject(data);
			for (int i = 0; i < sellerArr.size(); i++) {
				JSONObject o = sellerArr.getJSONObject(i);
				Long sellerId = o.getLong("sellerId");
				SellerMessageRef sellerRef = sellerRefDao.findBySellerId(sellerId);
				Article article = articleDao.findById(sellerRef.getMsgId());
				ArticleDto articleDto = new ArticleDto();
				articleDto.setDescription(article.getAbstracts());
				// start=116.403124,39.940693&dest=116.481488,39.990464&destName=%E4%B8%80%E6%9D%A1%E9%A9%BE%E8%BD%A6%E8%B7%AF%E7%BA%BF&key=03138e10227098542d2af2efd58e30e9
				String url = AMapUntil.ROUTE_PLANING + "?&start=" + center + "&dest=" + o.getString("_location")
						+ "&destName=" + article.getAbstracts() + "&key=" + AMapUntil.KEY;
				LOG.warn("url:{}", url);
				articleDto.setUrl(url);
				articleDto.setTitle(article.getTitle());
				articleDto.setPicUrl(article.getImgUrl());
				articleDtos.add(articleDto);
			}
			newsMessageDto.setArticleCount(articleDtos.size());
			newsMessageDto.setArticles(articleDtos);
			newsMessageDto.setFromUserName(toUserName);
			newsMessageDto.setToUserName(fromUserName);
			newsMessageDto.setCreateTime(new Date().getTime());
			newsMessageDto.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			resXml = ConvertXml.newsMessageToXml(newsMessageDto);
			LOG.warn("resXml:{}", resXml);
		}

		return resXml;
	}



}
