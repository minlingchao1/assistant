/**
 * @Project:assistant
 * @Title: KeywordReplyRuleLogicImpl.java
 * @date: 2015-2-11 下午12:48:01
 * @version 1.0
 */
package assistant.app.reply.logic.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Random;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.slf4j.Logger;
import org.wltea.analyzer.lucene.IKAnalyzer;

import assistant.app.base.config.LogCongfig;
import assistant.app.reply.dal.IKeywordReplyRuleDao;
import assistant.app.reply.dal.impl.KeywordReplyRuleDaoImpl;
import assistant.app.reply.dto.KeywordReplyInfoDto;
import assistant.app.reply.logic.IKeywordReplyRuleLogic;
import assistant.app.reply.logic.IReplyMessageLogic;
import assistant.app.reply.logic.IReplyMsgTempleteLogic;
import assistant.app.reply.model.KeywordReplyRule;
import assistant.app.reply.model.ReplyMsgTemplete;

/**
 * @ClassName KeywordReplyRuleLogicImpl
 * @Description 关键字回复
 * @author minlingchao
 * @date 2015-2-11 下午12:48:01
 */
public class KeywordReplyRuleLogicImpl implements IKeywordReplyRuleLogic {

    private static final Logger LOG = LogCongfig.WECHATMSGREPLYLOG;

    private static KeywordReplyRuleLogicImpl instance = new KeywordReplyRuleLogicImpl();

    private KeywordReplyRuleLogicImpl() {

    }

    public static KeywordReplyRuleLogicImpl getInstance() {
        return instance;
    }

    private IKeywordReplyRuleDao ruleDao = KeywordReplyRuleDaoImpl.getInstance();
    private IReplyMessageLogic replyMessageLogic = ReplyMessageLogicImpl.getInstance();
    private IReplyMsgTempleteLogic templeteLogic = ReplyMsgTempleteLogicImpl.getInstance();

    @Override
    public boolean addRule(Long userId, Long wechatId, String ruleName, String keyword, int type, int isAllMatch,
            String replyMsg) {
        return ruleDao.addRule(userId, wechatId, ruleName, keyword, type, isAllMatch, replyMsg);
    }

    @Override
    public List<KeywordReplyRule> fuzzyQuery(String keyword) {
        return ruleDao.fuzzyQuery(keyword);
    }

    @Override
    public List<KeywordReplyRule> exactMatch(String keyword) {
        return ruleDao.exactMatch(keyword);
    }

    @Override
    public List<KeywordReplyInfoDto> findAll(long wechatId) {
        return ruleDao.findAll(wechatId);
    }

    @Override
    public boolean addNewsRule(Long userId, Long wechatId, String ruleName, String keyword, int type, int isAllMatch,
            Long msgId) {
        return ruleDao.addNewsRule(userId, wechatId, ruleName, keyword, type, isAllMatch, msgId);
    }

    @Override
    public String dealKeyword(Long wechatId, String fromUserName, String toUserName, String keyword) {
        String reqXml = null;

        // 查找规则：先精确查询再模糊查询，从查询结果中随机选取一条回复.查询不到选择无关键字时的回复
        List<KeywordReplyRule> keywordReplyRules = exactMatch(keyword);
        LOG.warn("keywordReplyRules isEmpty：{}", keywordReplyRules.isEmpty());
        if (!keywordReplyRules.isEmpty()) {
            LOG.warn("精确查询");
            reqXml = createKeywordReply(keywordReplyRules, fromUserName, toUserName);
        } else {
            LOG.warn("模糊查询");
            // 创建分词对象
            IKAnalyzer anal = new IKAnalyzer(true);
            StringReader reader = new StringReader(keyword);
            // 分词
            TokenStream ts = anal.tokenStream("", reader);
            CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
            // 遍历分词数据
            try {
                while (ts.incrementToken()) {
                    keywordReplyRules = fuzzyQuery(term.toString());
                    if (!keywordReplyRules.isEmpty()) {
                        reqXml = createKeywordReply(keywordReplyRules, fromUserName, toUserName);
                        break;
                    }
                    LOG.warn("分词:{}", term.toString());
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (reqXml == null) {
                LOG.warn("无关键字时的回复！");
            }
        }

        return reqXml;
    }

    @Override
    public String createKeywordReply(List<KeywordReplyRule> keywordReplyRules, String fromUserName, String toUserName) {
        String reqXml = null;
        KeywordReplyRule rule = new KeywordReplyRule();
        Random random = new Random();
        rule = keywordReplyRules.get(random.nextInt(keywordReplyRules.size()));
        int type = rule.getType();

        String replyMsg = null;
        String mediaId = null;
        if (type < 4) {
            LOG.warn("keywordReplyRule:{}", rule.getId());
            ReplyMsgTemplete templete = templeteLogic.findById(rule.getMsgId());
            replyMsg = templete.getReplyMsg();
            mediaId = templete.getMediaId();
        }

        reqXml = replyMessageLogic.createReplyMessage(rule.getMsgId(), type, fromUserName, toUserName, replyMsg,
                mediaId);

        return reqXml;
    }

    @Override
    public KeywordReplyInfoDto findById(Long id) {

        return ruleDao.findById(id);
    }

    @Override
    public boolean delete(Long id) {

        return ruleDao.delete(id);
    }

}
