/**
 * @Project:assistant
 * @Title: SceneDaoImpl.java
 * @date: 2015-3-4 上午11:30:42
 * @version 1.0
 */
package assistant.app.scene.dal.impl;

import java.util.List;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.config.WechatConfig;
import assistant.app.reply.dao.mapper.IArticleMapper;
import assistant.app.reply.dao.mapper.IKeywordReplyRuleMapper;
import assistant.app.reply.model.Article;
import assistant.app.reply.model.KeywordReplyRule;
import assistant.app.scene.config.SceneConfig;
import assistant.app.scene.dal.ISceneDao;
import assistant.app.scene.dao.mapper.ISceneImgMapper;
import assistant.app.scene.dao.mapper.ISceneMapper;
import assistant.app.scene.dao.mapper.ISceneMessageRefMapper;
import assistant.app.scene.model.Scene;
import assistant.app.scene.model.SceneImg;
import assistant.app.scene.model.SceneMessageRef;
import assistant.app.subscribe.config.ReplyMsgType;

/**
 * @ClassName SceneDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-3-4 上午11:30:42
 */
public class SceneDaoImpl implements ISceneDao {

    private static final Logger LOG = LogCongfig.SCENELOG;

    private static SceneDaoImpl instance = new SceneDaoImpl();

    private SceneDaoImpl() {

    }

    public static SceneDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addScene(long userId, long wechatId, String sceneName, long start, long end, String keyword,
            String pageImg, String descr, String shareTitle, String shareContent, int openSet, String openImg,
            int outSet, String outImg, int slide, String musicSet, String music, String openBtnImg, String outBtnImg,
            String openBtnUrl, String outBtnUrl, String openBtnPos, String outBtnPos) {

        boolean isSuccess = false;
        SqlSession session = IbatisSessionFactory.get().openSession(false);

        try {
            // 添加场景基本信息
            Scene scene = new Scene();
            scene.setWechatId(wechatId);
            scene.setSceneName(sceneName);
            scene.setStart(start);
            scene.setEnd(end);
            scene.setOpenImg(openImg);
            scene.setOpenSet(openSet);
            scene.setOutImg(outImg);
            scene.setOutSet(outSet);
            scene.setBackMusic(music);
            scene.setMusicSet(musicSet);
            scene.setSlide(slide);
            scene.setShareTitle(shareTitle);
            scene.setShareContent(shareContent);
            scene.setOpenBtnImg(openBtnImg);
            scene.setOpenBtnUrl(openBtnUrl);
            scene.setOpenBtnPos(openBtnPos);
            scene.setOutBtnImg(outBtnImg);
            scene.setOutBtnUrl(outBtnUrl);
            scene.setOutBtnPos(openBtnPos);

            if (start > System.currentTimeMillis()) {
                scene.setStatus(SceneConfig.COMING.getCode());
            } else if (start < System.currentTimeMillis() && end > System.currentTimeMillis()) {
                scene.setStatus(SceneConfig.GOING_ON.getCode());
            } else if (end < System.currentTimeMillis()) {
                scene.setStatus(SceneConfig.OUT_DATE.getCode());
            }

            // 保存场景信息
            ISceneMapper sceneMapper = session.getMapper(ISceneMapper.class);
            sceneMapper.insert(scene);
            Long sceneId = scene.getId();

            // 保存图文信息
            String url = WechatConfig.SCENE_URL;
            url = url.replace("SCENE_ID", sceneId.toString());
            Article article = new Article();
            article.setAbstracts(descr);
            article.setTitle(sceneName);
            article.setImgUrl(pageImg);
            article.setUrl(url);

            IArticleMapper articleMapper = session.getMapper(IArticleMapper.class);
            articleMapper.insert(article);
            Long msgId = article.getId();

            // 建立场景消息关联
            SceneMessageRef sceneMessageRef = new SceneMessageRef();
            sceneMessageRef.setMsgId(msgId);
            sceneMessageRef.setSceneId(sceneId);

            ISceneMessageRefMapper sceneMessageRefMapper = session.getMapper(ISceneMessageRefMapper.class);
            sceneMessageRefMapper.insert(sceneMessageRef);

            // 保存关键词信息
            KeywordReplyRule keywordReplyRule = new KeywordReplyRule();
            keywordReplyRule.setRuleName(sceneName);
            keywordReplyRule.setType(ReplyMsgType.SCENE);
            keywordReplyRule.setMsgId(msgId);
            keywordReplyRule.setUserId(userId);
            keywordReplyRule.setWechatId(wechatId);
            keywordReplyRule.setIsAllMatch(0);
            keywordReplyRule.setKeyword(keyword);

            IKeywordReplyRuleMapper ruleMapper = session.getMapper(IKeywordReplyRuleMapper.class);
            ruleMapper.insert(keywordReplyRule);

            isSuccess = true;
            session.commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }

        return isSuccess;
    }

    @Override
    public List<Scene> getList(long wechatId) {
        List<Scene> result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            ISceneMapper sceneMapper = session.getMapper(ISceneMapper.class);
            result = sceneMapper.getList(wechatId);
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
    public Scene findById(long id) {
        Scene result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            ISceneMapper sceneMapper = session.getMapper(ISceneMapper.class);
            result = sceneMapper.findById(id);
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
    public List<SceneImg> findBySceneId(long sceneId) {
        List<SceneImg> result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            ISceneImgMapper sceneMapper = session.getMapper(ISceneImgMapper.class);
            result = sceneMapper.findBySceneId(sceneId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public long update(Scene scene) {

        long result = 0l;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        try {
            ISceneMapper sceneMapper = session.getMapper(ISceneMapper.class);
            result = sceneMapper.update(scene);
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
    public List<Scene> listAll() {

        List<Scene> result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            ISceneMapper sceneMapper = session.getMapper(ISceneMapper.class);
            result = sceneMapper.listAll();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

}
