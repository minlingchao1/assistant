package assistant.app.wxmsgdeal.logic.impl;

import java.util.Map;

import org.slf4j.Logger;

import play.mvc.Http.Request;
import assistant.app.base.config.LogCongfig;
import assistant.app.bind.logic.IWechatInfoLogic;
import assistant.app.bind.logic.impl.WechatInfoLogicImpl;
import assistant.app.bind.model.WechatAccountInfo;
import assistant.app.common.util.wxutil.ConvertXml;
import assistant.app.common.util.wxutil.MessageUtil;
import assistant.app.custom.logic.ICustomLocationLogic;
import assistant.app.custom.logic.impl.CustomLocationLogicImpl;
import assistant.app.menu.logic.IMenuLogic;
import assistant.app.menu.logic.impl.MenuLogicImpl;
import assistant.app.reply.config.ClickMsgConfig;
import assistant.app.reply.logic.IKeywordReplyRuleLogic;
import assistant.app.reply.logic.IReplyMessageLogic;
import assistant.app.reply.logic.IScanCountLogic;
import assistant.app.reply.logic.impl.KeywordReplyRuleLogicImpl;
import assistant.app.reply.logic.impl.ReplyMessageLogicImpl;
import assistant.app.reply.logic.impl.ScanCountLogicImpl;
import assistant.app.subscribe.logic.ISubScribeLogic;
import assistant.app.subscribe.logic.impl.SubscribeLogicImpl;
import assistant.app.wxmsgdeal.logic.IWXMsgDealLogic;

/**
 * @ClassName WXMsgDealLogicImpl
 * @Description 微信消息处理
 * @author minlingchao
 * @date 2015-2-6 下午12:35:06
 */
public class WXMsgDealLogicImpl implements IWXMsgDealLogic {

    public static final Logger LOG = LogCongfig.WECHATMSGDEALLOG;

    private static WXMsgDealLogicImpl instance = new WXMsgDealLogicImpl();

    private WXMsgDealLogicImpl() {

    }

    public static WXMsgDealLogicImpl getInstance() {
        return instance;
    }

    private IWechatInfoLogic wechatInfoLogic = WechatInfoLogicImpl.getInstance();
    private ISubScribeLogic subScribeLogic = SubscribeLogicImpl.getInstance();
    private IKeywordReplyRuleLogic ruleLogic = KeywordReplyRuleLogicImpl.getInstance();
    private ICustomLocationLogic locationLogic = CustomLocationLogicImpl.getInstance();
    private IMenuLogic menuLogic = MenuLogicImpl.getInstance();
    private IReplyMessageLogic replyMessageLogic = ReplyMessageLogicImpl.getInstance();
    private IScanCountLogic scanLogic = ScanCountLogicImpl.getInstance();

    @Override
    public String processRequest(Request request) {
        String reqXml = null;
        LOG.warn("request body:{}", request.params.get("body"));

        Map<String, String> requestMap = ConvertXml.parseXml(request);

        // 获取发送者
        String fromUserName = requestMap.get("FromUserName");
        String toUserName = requestMap.get("ToUserName");
        String msgType = requestMap.get("MsgType");
        String createTime = requestMap.get("CreateTime");

        String appId = request.params.get("appId");
        WechatAccountInfo wechatAccountInfo = wechatInfoLogic.findByAppId(appId);
        Long wechatId = wechatAccountInfo.getId();
        // 关注
        if (msgType.equals(MessageUtil.REQ_MSG_TYPE_EVENT)) {
            String eventType = requestMap.get("Event");
            // 订阅
            if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                reqXml = subScribeLogic.dealSubscribe(wechatId, fromUserName, toUserName);
                // 二维码扫描
            } else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {

                // 地理位置信息
            } else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                LOG.warn("location：{}", MessageUtil.EVENT_TYPE_LOCATION);
                String lng = requestMap.get("Longitude");
                String lat = requestMap.get("Latitude");
                String center = lng + "," + lat;
                String precision = requestMap.get("Precision");

                locationLogic.createLocation(wechatId, createTime, fromUserName, toUserName, lng, lat, precision);
                scanLogic.insert(wechatId, ClickMsgConfig.LOCATION, createTime);
                reqXml = replyMessageLogic.createSellerNewsMessage(center, fromUserName, toUserName);
                // 菜单点击事件
            } else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                String eventKey = requestMap.get("EventKey");

                scanLogic.insert(wechatId, ClickMsgConfig.MENU, createTime);
                reqXml = menuLogic.dealMenuClick(eventKey, fromUserName, toUserName, wechatId);
            }
            // 文本消息
        } else if (msgType.equals(MessageUtil.RESP_MESSAGE_TYPE_TEXT)) {
            String content = requestMap.get("Content");
            LOG.warn("reply text,content:{}", content);
            scanLogic.insert(wechatId, ClickMsgConfig.TEXT, createTime);
            reqXml = ruleLogic.dealKeyword(wechatId, fromUserName, toUserName, content);
        } else if (msgType.equals(MessageUtil.EVENT_TYPE_UPLOAD_LOCATION)) {
            String lng = requestMap.get("Location_Y");
            String lat = requestMap.get("Location_X");
            String center = lng + "," + lat;
            String precision = requestMap.get("Precision");
            LOG.warn("location：{}", MessageUtil.EVENT_TYPE_UPLOAD_LOCATION);
            locationLogic.createLocation(wechatId, createTime, fromUserName, toUserName, lng, lat, precision);
            scanLogic.insert(wechatId, ClickMsgConfig.LOCATION, createTime);
            reqXml = replyMessageLogic.createSellerNewsMessage(center, fromUserName, toUserName);

        }
        return reqXml;
    }
}
