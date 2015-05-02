/**
 * @Project:assistant
 * @Title: MessageUtil.java
 * @date: 2015-2-6 下午12:41:31
 * @version 1.0
 */
package assistant.app.common.util.wxutil;

/**
 * @ClassName MessageUtil
 * @Description 消息处理工具类
 * @author minlingchao
 * @date 2015-2-6 下午12:41:31
 */
public class MessageUtil {

    // /////////////////////////请求消息类型/////////////////////////////////////
    // /////////////////////////事件消息类型/////////////////////////////////////

    /**
     * 请求消息类型：事件推送
     */
    public static final String REQ_MSG_TYPE_EVENT = "event";

    /**
     * 事件类型：订阅/关注
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：取消订阅/取消关注
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：扫描二维码
     */
    public static final String EVENT_TYPE_SCAN = "scan";

    /**
     * 事件类型:上报地理位置
     */
    public static final String EVENT_TYPE_LOCATION = "LOCATION";

    /**
     * 事件类型:上传地理位置
     */
    public static final String EVENT_TYPE_UPLOAD_LOCATION = "location";
    /**
     * 事件类型：自定义菜单(CLICK)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";

    // /////////////////////////响应消息类型/////////////////////////////////////

    /**
     * 文本消息
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 图片消息
     */
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";

    /**
     * 语音消息
     */
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";

    /**
     * 视频消息
     */
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";

    /**
     * 图文消息
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

}
