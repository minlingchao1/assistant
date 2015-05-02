/**
 * @Project:assistant
 * @Title: SubScribeCtrl.java
 * @date: 2015-2-6 下午6:32:51
 * @version 1.0
 */
package controllers.subscribe;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.dto.ProcessStatus;
import assistant.app.bind.logic.IWechatAccessTokenLogic;
import assistant.app.bind.logic.IWechatInfoLogic;
import assistant.app.bind.logic.impl.WechatAccessTokenLogicImpl;
import assistant.app.bind.logic.impl.WechatInfoLogicImpl;
import assistant.app.bind.model.WechatAccessToken;
import assistant.app.bind.model.WechatAccountInfo;
import assistant.app.common.util.wxutil.MessageUtil;
import assistant.app.common.util.wxutil.UploadMediaUtil;
import assistant.app.reply.logic.IReplyMsgTempleteLogic;
import assistant.app.reply.logic.impl.ReplyMsgTempleteLogicImpl;
import assistant.app.reply.model.ReplyMsgTemplete;
import assistant.app.subscribe.logic.ISubScribeLogic;
import assistant.app.subscribe.logic.impl.SubscribeLogicImpl;
import assistant.app.subscribe.model.Subscribe;
import controllers.base.BaseController;

/**
 * @ClassName SubScribeCtrl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-6 下午6:32:51
 */
public class SubScribeCtrl extends BaseController {

    private static final Logger LOG = LogCongfig.WECHATMSGDEALLOG;

    private static ISubScribeLogic subScribeLogic = SubscribeLogicImpl.getInstance();

    private static IReplyMsgTempleteLogic templeteLogic = ReplyMsgTempleteLogicImpl.getInstance();

    private static IWechatInfoLogic wechatInfoLogic = WechatInfoLogicImpl.getInstance();

    private static IWechatAccessTokenLogic wechatAccessTokenLogic = WechatAccessTokenLogicImpl.getInstance();

    public static void add(long wechatId, int type, String replyMsg) {
        Long userId = getMerchantId();
        long isExist = subScribeLogic.countByWechatId(wechatId);
        String mediaId = "0";
        Long merchatId = getMerchantId();
        ProcessStatus processStatus = validWechatInfo(merchatId, wechatId);
        if (!processStatus.isSuccess()) {
            renderJsonFail(5000, processStatus.getMessage());
        }

        if (isExist != 0) {

            Subscribe subscribe = subScribeLogic.findByWechatId(wechatId);
            WechatAccountInfo wechatAccountInfo = wechatInfoLogic.findById(Long.valueOf(wechatId));
            WechatAccessToken wechatAccessToken = wechatAccessTokenLogic.findByWechatId(wechatAccountInfo.getId());
            ReplyMsgTemplete templete = templeteLogic.findById(subscribe.getMsgId());

            if (type != 0) {
                if (type == 1) {
                    mediaId = UploadMediaUtil.uploadMedia(wechatAccessToken.getAccessToken(),
                            MessageUtil.RESP_MESSAGE_TYPE_IMAGE, replyMsg);
                } else if (type == 2) {
                    mediaId = UploadMediaUtil.uploadMedia(wechatAccessToken.getAccessToken(),
                            MessageUtil.RESP_MESSAGE_TYPE_VOICE, replyMsg);
                } else {
                    mediaId = UploadMediaUtil.uploadMedia(wechatAccessToken.getAccessToken(),
                            MessageUtil.RESP_MESSAGE_TYPE_VIDEO, replyMsg);
                }
            }

            templete.setReplyMsg(replyMsg);
            templete.setMediaId(mediaId);
            templete.setCreateTime(System.currentTimeMillis());

            if (templeteLogic.update(templete, type) > 0) {
                renderJsonSuccess();
            } else {
                renderJsonFail();
            }

        } else {
            if (subScribeLogic.addSubReply(wechatId, userId, type, replyMsg)) {
                renderJsonSuccess();
            } else {
                renderJsonFail();
            }
        }

    }

}
