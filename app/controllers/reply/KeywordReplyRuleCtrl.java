/**
 * @Project:assistant
 * @Title: KeywordReplyRuleCtrl.java
 * @date: 2015-2-11 下午12:51:37
 * @version 1.0
 */
package controllers.reply;

import java.util.List;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.dto.ProcessStatus;
import assistant.app.reply.dto.KeywordReplyInfoDto;
import assistant.app.reply.logic.IKeywordReplyRuleLogic;
import assistant.app.reply.logic.INewsMessageRefLogic;
import assistant.app.reply.logic.IReplyMsgTempleteLogic;
import assistant.app.reply.logic.impl.KeywordReplyRuleLogicImpl;
import assistant.app.reply.logic.impl.NewsMessageRefLogicImpl;
import assistant.app.reply.logic.impl.ReplyMsgTempleteLogicImpl;
import controllers.base.BaseController;

/**
 * @ClassName KeywordReplyRuleCtrl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-11 下午12:51:37
 */
public class KeywordReplyRuleCtrl extends BaseController {

    private static final Logger LOG = LogCongfig.WECHATMSGREPLYLOG;

    private static IKeywordReplyRuleLogic ruleLogic = KeywordReplyRuleLogicImpl.getInstance();

    private static IReplyMsgTempleteLogic templeteLogic = ReplyMsgTempleteLogicImpl.getInstance();

    private static INewsMessageRefLogic newsMessageRefLogic = NewsMessageRefLogicImpl.getInstance();

    // 添加
    public static void add(Long wechatId, String ruleName, String keyword, int type, int isAllMatch, String replyMsg) {
        Long userId = getMerchantId();
        Long merchatId = getMerchantId();
        ProcessStatus processStatus = validWechatInfo(merchatId, wechatId);
        if (!processStatus.isSuccess()) {
            renderJsonFail(5000, processStatus.getMessage());
        }
        if (ruleLogic.addRule(userId, wechatId, ruleName, keyword, type, isAllMatch, replyMsg)) {
            renderJsonSuccess();
        } else {
            renderJsonFail();
        }
    }

    // 编辑
    public static void edit(Long id) {
        KeywordReplyInfoDto rule = ruleLogic.findById(id);
        renderJsonAjaxResult(rule);
    }

    // 删除
    public static void delete(Long id) {
        if (ruleLogic.delete(id)) {
            renderJsonSuccess();
        } else {
            renderJsonFail();
        }
    }

    // 添加图文回复
    public static void addNews(Long wechatId, String ruleName, String keyword, int type, int isAllMatch, Long msgId) {
        Long userId = getMerchantId();
        ProcessStatus processStatus = validWechatInfo(userId, wechatId);
        if (!processStatus.isSuccess()) {
            renderJsonFail(5000, processStatus.getMessage());
        }
        if (ruleLogic.addNewsRule(userId, wechatId, ruleName, keyword, type, isAllMatch, msgId)) {
            renderJsonSuccess();
        } else {
            renderJsonFail();
        }
    }

    // 关键词列表
    public static void getKeywordList(long wechatId) {
        Long userId = getMerchantId();
        ProcessStatus processStatus = validWechatInfo(userId, wechatId);
        if (!processStatus.isSuccess()) {
            renderJsonFail(5000, processStatus.getMessage());
        }
        List<KeywordReplyInfoDto> list = ruleLogic.findAll(wechatId);
        renderJsonAjaxResult(list);
    }

    // 保存图文消息
    public static void saveNewsMessage(long wechatId, String msgType, String newsJson) {
        Long userId = getMerchantId();
        ProcessStatus processStatus = validWechatInfo(userId, wechatId);
        if (!processStatus.isSuccess()) {
            renderJsonFail(5000, processStatus.getMessage());
        }
        if (newsMessageRefLogic.insert(userId, wechatId, msgType, newsJson) > 0) {
            renderJsonSuccess();
        } else {
            renderJsonFail();
        }

    }

}