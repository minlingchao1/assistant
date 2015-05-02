/**
 * @Project:assistant
 * @Title: KeywordReplyRuleDaoImpl.java
 * @date: 2015-2-11 下午12:31:11
 * @version 1.0
 */
package assistant.app.reply.dal.impl;

import java.util.List;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.bind.dal.IWechatAccessTokenDao;
import assistant.app.bind.dal.IWechatInfoDao;
import assistant.app.bind.dal.impl.WechatAccessTokenDaoImpl;
import assistant.app.bind.dal.impl.WechatInfoDaoImpl;
import assistant.app.bind.model.WechatAccessToken;
import assistant.app.bind.model.WechatAccountInfo;
import assistant.app.common.util.wxutil.MessageUtil;
import assistant.app.common.util.wxutil.UploadMediaUtil;
import assistant.app.reply.dal.IKeywordReplyRuleDao;
import assistant.app.reply.dao.mapper.IKeywordReplyRuleMapper;
import assistant.app.reply.dao.mapper.IReplyMsgTempleteMapper;
import assistant.app.reply.dto.KeywordReplyInfoDto;
import assistant.app.reply.model.KeywordReplyRule;
import assistant.app.reply.model.ReplyMsgTemplete;
import assistant.app.subscribe.config.ReplyMsgType;

/**
 * @ClassName KeywordReplyRuleDaoImpl
 * @Description 关键字回复处理
 * @author minlingchao
 * @date 2015-2-11 下午12:31:11
 */
public class KeywordReplyRuleDaoImpl implements IKeywordReplyRuleDao {

    private static final Logger LOG = LogCongfig.WECHATMSGDEALLOG;

    private static KeywordReplyRuleDaoImpl instance = new KeywordReplyRuleDaoImpl();

    private KeywordReplyRuleDaoImpl() {

    }

    public static KeywordReplyRuleDaoImpl getInstance() {
        return instance;
    }

    private IWechatInfoDao wechatInfoDao = WechatInfoDaoImpl.getInstance();

    private IWechatAccessTokenDao wechatAccessTokenDao = WechatAccessTokenDaoImpl.getInstance();

    @Override
    public boolean addRule(Long userId, Long wechatId, String ruleName, String keyword, int type, int isAllMatch,
            String replyMsg) {
        boolean isSuccess = false;
        String mediaId = "0";
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        try {
            Long msgId = null;

            WechatAccountInfo wechatAccountInfo = wechatInfoDao.findById(Long.valueOf(wechatId));
            WechatAccessToken wechatAccessToken = wechatAccessTokenDao.findByWechatId(wechatAccountInfo.getId());
            // LOG.warn("wehcatId:{},accessToken:{}", wechatAccountInfo.getId(),
            // wechatAccountInfo.getAccessToken());
            LOG.warn("wechatId:{},accessToken:{}", wechatId, wechatAccessToken.getAccessToken());
            if (type != 0) {

                if (type == ReplyMsgType.IMAGE) {
                    mediaId = UploadMediaUtil.uploadMedia(wechatAccessToken.getAccessToken(),
                            MessageUtil.RESP_MESSAGE_TYPE_IMAGE, replyMsg);
                } else if (type == ReplyMsgType.VOICE) {
                    mediaId = UploadMediaUtil.uploadMedia(wechatAccessToken.getAccessToken(),
                            MessageUtil.RESP_MESSAGE_TYPE_VOICE, replyMsg);
                } else if (type == ReplyMsgType.VIDEO) {
                    mediaId = UploadMediaUtil.uploadMedia(wechatAccessToken.getAccessToken(),
                            MessageUtil.RESP_MESSAGE_TYPE_VIDEO, replyMsg);
                }
            }
            ReplyMsgTemplete templete = new ReplyMsgTemplete();
            templete.setReplyMsg(replyMsg);
            templete.setMediaId(mediaId);
            templete.setCreateTime(System.currentTimeMillis());
            IReplyMsgTempleteMapper templeteMapper = session.getMapper(IReplyMsgTempleteMapper.class);
            templeteMapper.insert(templete);
            msgId = templete.getId();

            String[] keywordArray = keyword.split(",");
            for (String key : keywordArray) {
                LOG.warn("添加关键字：{}", key);
                KeywordReplyRule replyRule = new KeywordReplyRule(ruleName, key, type, isAllMatch, msgId, userId,
                        wechatId);
                IKeywordReplyRuleMapper ruleMapper = session.getMapper(IKeywordReplyRuleMapper.class);
                ruleMapper.insert(replyRule);
            }

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
    public boolean addNewsRule(Long userId, Long wechatId, String ruleName, String keyword, int type, int isAllMatch,
            Long msgId) {
        boolean isSuccess = false;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        try {

            String[] keywordArray = keyword.split(",");
            for (String key : keywordArray) {
                LOG.warn("添加关键字：{}", key);
                KeywordReplyRule replyRule = new KeywordReplyRule(ruleName, key, type, isAllMatch, msgId, userId,
                        wechatId);
                IKeywordReplyRuleMapper ruleMapper = session.getMapper(IKeywordReplyRuleMapper.class);
                ruleMapper.insert(replyRule);
            }
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
    public List<KeywordReplyRule> fuzzyQuery(String keyword) {
        List<KeywordReplyRule> result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            IKeywordReplyRuleMapper ruleMapper = session.getMapper(IKeywordReplyRuleMapper.class);
            result = ruleMapper.fuzzyQuery(keyword);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<KeywordReplyRule> exactMatch(String keyword) {
        List<KeywordReplyRule> result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            IKeywordReplyRuleMapper ruleMapper = session.getMapper(IKeywordReplyRuleMapper.class);
            result = ruleMapper.exactMatch(keyword);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<KeywordReplyInfoDto> findAll(long wechatId) {
        List<KeywordReplyInfoDto> result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            IKeywordReplyRuleMapper ruleMapper = session.getMapper(IKeywordReplyRuleMapper.class);
            result = ruleMapper.findAll(wechatId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public KeywordReplyInfoDto findById(Long id) {

        KeywordReplyInfoDto result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            IKeywordReplyRuleMapper ruleMapper = session.getMapper(IKeywordReplyRuleMapper.class);
            result = ruleMapper.findById(id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean delete(Long id) {
        boolean isSuccess = false;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        try {
            IKeywordReplyRuleMapper ruleMapper = session.getMapper(IKeywordReplyRuleMapper.class);
            KeywordReplyRule rule = ruleMapper.findMsgId(id);

            IReplyMsgTempleteMapper templeteMapper = session.getMapper(IReplyMsgTempleteMapper.class);
            templeteMapper.delete(rule.getMsgId());

            ruleMapper.delete(id);
            session.commit();
            isSuccess = true;

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }

        return isSuccess;
    }
}
