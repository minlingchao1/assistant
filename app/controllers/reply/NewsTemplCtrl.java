/**
 * @Project:assistant
 * @Title: NewsTemplCtrl.java
 * @date: 2015-2-27 上午12:03:08
 * @version 1.0
 */
package controllers.reply;

import java.util.ArrayList;
import java.util.List;

import play.mvc.Controller;
import assistant.app.base.config.BaseConfigs;
import assistant.app.reply.dto.NewsMultiDto;
import assistant.app.reply.dto.NewsTemplDto;
import assistant.app.reply.logic.IArticleLogic;
import assistant.app.reply.logic.INewsMessageRefLogic;
import assistant.app.reply.logic.IReplyMsgTempleteLogic;
import assistant.app.reply.logic.impl.ArticleLogicImpl;
import assistant.app.reply.logic.impl.NewsMessageRefLogicImpl;
import assistant.app.reply.logic.impl.ReplyMsgTempleteLogicImpl;
import assistant.app.reply.model.Article;
import assistant.app.reply.model.NewsMessageRef;

/**
 * @ClassName NewsTemplCtrl
 * @Description 图文消息渲染
 * @author minlingchao
 * @date 2015-2-27 上午12:03:08
 */
public class NewsTemplCtrl extends Controller {

    private static INewsMessageRefLogic newsMessageRefLogic = NewsMessageRefLogicImpl.getInstance();

    private static IReplyMsgTempleteLogic templeteLogic = ReplyMsgTempleteLogicImpl.getInstance();

    private static IArticleLogic articleLogic = ArticleLogicImpl.getInstance();

    /**
     * 图文消息显示模板
     */
    public static void newsTempl(Long articleId) {

        Article article = articleLogic.findById(articleId);
        NewsTemplDto news = new NewsTemplDto();
        news.setTitle(article.getTitle());
        news.setImgUrl(article.getImgUrl());
        news.setContent(article.getContent());
        render(news);
    }

    /**
     * 素材管理
     */
    public static void sourceManage(Long wechatId) {
        Long userId = Long.valueOf(session.get(BaseConfigs.SESSION.SEESION_MERCHANT_ID));

        List<NewsMultiDto> newsMulti = new ArrayList<NewsMultiDto>();
        List<NewsMessageRef> newsMessageRefs = newsMessageRefLogic.getNews(userId, wechatId);
        List<NewsTemplDto> single = new ArrayList<NewsTemplDto>();
        if (newsMessageRefs != null) {
            for (NewsMessageRef newsMessageRef : newsMessageRefs) {
                String[] msgIds = newsMessageRef.getMsgId().split(",");

                if (newsMessageRef.getMsgType().equals("singleNews")) {
                    Article article = articleLogic.findById(Long.valueOf(msgIds[0]));
                    NewsTemplDto news = new NewsTemplDto();
                    news.setContent(article.getContent());
                    news.setImgUrl(article.getImgUrl());
                    news.setTitle(article.getTitle());
                    news.setMsgId(newsMessageRef.getId());
                    single.add(news);
                } else {
                    List<NewsTemplDto> multi = new ArrayList<NewsTemplDto>();
                    NewsMultiDto newsmulti = new NewsMultiDto();
                    for (int i = 0; i < msgIds.length; i++) {
                        Article article = articleLogic.findById(Long.valueOf(msgIds[i]));
                        NewsTemplDto news = new NewsTemplDto();
                        news.setContent(article.getContent());
                        news.setImgUrl(article.getImgUrl());
                        news.setTitle(article.getTitle());
                        news.setMsgId(newsMessageRef.getId());
                        multi.add(news);
                    }
                    newsmulti.setMsgId(newsMessageRef.getId());
                    newsmulti.setMultiTempl(multi);
                    newsMulti.add(newsmulti);
                }
            }
        }

        render(single, newsMulti);

    }

    /**
     * 素材选择
     */
    public static void sourceManageSelect(Long wechatId) {
        Long userId = Long.valueOf(session.get(BaseConfigs.SESSION.SEESION_MERCHANT_ID));

        List<NewsMultiDto> newsMulti = new ArrayList<NewsMultiDto>();
        List<NewsMessageRef> newsMessageRefs = newsMessageRefLogic.getNews(userId, wechatId);
        List<NewsTemplDto> single = new ArrayList<NewsTemplDto>();

        for (NewsMessageRef newsMessageRef : newsMessageRefs) {
            String[] msgIds = newsMessageRef.getMsgId().split(",");
            if (newsMessageRef.getMsgType().equals("singleNews")) {
                Article article = articleLogic.findById(Long.valueOf(msgIds[0]));
                NewsTemplDto news = new NewsTemplDto();
                news.setContent(article.getContent());
                news.setImgUrl(article.getImgUrl());
                news.setTitle(article.getTitle());
                news.setMsgId(newsMessageRef.getId());
                single.add(news);
            } else {
                List<NewsTemplDto> multi = new ArrayList<NewsTemplDto>();
                NewsMultiDto newsmulti = new NewsMultiDto();
                for (int i = 0; i < msgIds.length; i++) {
                    Article article = articleLogic.findById(Long.valueOf(msgIds[i]));
                    NewsTemplDto news = new NewsTemplDto();
                    news.setContent(article.getContent());
                    news.setImgUrl(article.getImgUrl());
                    news.setTitle(article.getTitle());
                    news.setMsgId(newsMessageRef.getId());
                    multi.add(news);
                }
                newsmulti.setMsgId(newsMessageRef.getId());
                newsmulti.setMultiTempl(multi);
                newsMulti.add(newsmulti);
            }
        }

        render(single, newsMulti);

    }

    /**
     * 模板显示
     */
    public static void sourceShow(Long id) {

        NewsMessageRef newsMessageRef = newsMessageRefLogic.findById(id);
        String[] msgIds = newsMessageRef.getMsgId().split(",");

        List<NewsTemplDto> multi = new ArrayList<NewsTemplDto>();
        NewsMultiDto newsMulti = new NewsMultiDto();

        for (int i = 0; i < msgIds.length; i++) {
            Article article = articleLogic.findById(Long.valueOf(msgIds[i]));
            NewsTemplDto news = new NewsTemplDto();
            news.setContent(article.getContent());
            news.setImgUrl(article.getImgUrl());
            news.setTitle(article.getTitle());
            news.setMsgId(newsMessageRef.getId());
            multi.add(news);
        }
        newsMulti.setMsgId(id);
        newsMulti.setMultiTempl(multi);
        render(newsMulti);
    }
}
