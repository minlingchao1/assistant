/**
 * @Project:assistant
 * @Title: IKeywordReplyRuleLogic.java
 * @date: 2015-2-11 下午12:47:47
 * @version 1.0
 */
package assistant.app.reply.logic;

import java.util.List;

import assistant.app.reply.dto.KeywordReplyInfoDto;
import assistant.app.reply.model.KeywordReplyRule;

/**
 * @ClassName IKeywordReplyRuleLogic
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-11 下午12:47:47
 */
public interface IKeywordReplyRuleLogic {

    /**
     * 添加
     */
    public boolean addRule(Long userId, Long wechatId, String ruleName, String keyword, int type, int isAllMatch,
            String replyMsg);

    /**
     * 删除
     */
    public boolean delete(Long id);

    /**
     * 添加图文回复关键字
     */
    public boolean addNewsRule(Long userId, Long wechatId, String ruleName, String keyword, int type, int isAllMatch,
            Long msgId);

    /**
     * 模糊查询
     */
    public List<KeywordReplyRule> fuzzyQuery(String keyword);

    /**
     * 精确查询
     */
    public List<KeywordReplyRule> exactMatch(String keyword);

    /**
     * 关键字回复
     */
    public String dealKeyword(Long wechatId, String fromUserName, String toUserName, String keyword);

    /**
     * 创建关键字回复消息
     */
    public String createKeywordReply(List<KeywordReplyRule> keywordReplyRules, String fromUserName, String toUserName);

    /**
     * 查找所有关键字
     */
    public List<KeywordReplyInfoDto> findAll(long wechatId);

    /**
     * 根据Id查找
     */
    public KeywordReplyInfoDto findById(Long id);
}
