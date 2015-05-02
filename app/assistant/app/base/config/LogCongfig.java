/**
 * @Project:assistant
 * @Title: LogCongfig.java
 * @date: 2015-1-17 下午10:09:19
 * @version 1.0
 */
package assistant.app.base.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName LogCongfig
 * @Description 日志打印配置
 * @author minlingchao
 * @date 2015-1-17 下午10:09:19
 */
public class LogCongfig {

    /**
     * 用户LOG
     */
    public static final Logger MERCHANTLOG = LoggerFactory.getLogger("merchatLog");

    /**
     * 微信号绑定LOG
     */
    public static final Logger WECHATBINDLOG = LoggerFactory.getLogger("wechatLog");

    /**
     * 微信号信息LOG
     */
    public static final Logger WECHATINFOLOG = LoggerFactory.getLogger("wechatInfoLog");

    /**
     * 微信消息处理LOG
     */
    public static final Logger WECHATMSGDEALLOG = LoggerFactory.getLogger("wechatMsgDealLog");

    /**
     * 微信消息回复LOG
     */
    public static final Logger WECHATMSGREPLYLOG = LoggerFactory.getLogger("textMsgLog");

    /**
     * 上传下载媒体文件LOG
     */
    public static final Logger MEDIAUPDOWNLOG = LoggerFactory.getLogger("uploadMediaLog");

    /**
     * 二维码LOG
     */
    public static final Logger QRCODELOG = LoggerFactory.getLogger("qrcodeLog");

    /**
     * 用户LOG
     */
    public static final Logger CUSTOMLOG = LoggerFactory.getLogger("customLog");

    /**
     * 菜单LOG
     */
    public static final Logger MENULOG = LoggerFactory.getLogger("menuLog");

    /**
     * 场景LOG
     */
    public static final Logger SCENELOG = LoggerFactory.getLogger("sceneLog");

    /**
     * 全景图LOG
     */

    public static final Logger OVERALLLOG = LoggerFactory.getLogger("overallLog");

    /**
     * LBS LOG
     */
    public static final Logger LBSLOG = LoggerFactory.getLogger("lbsLog");

    /**
     * 浏览量统计
     */
    public static final Logger SCANCOUNTLOG = LoggerFactory.getLogger("scanlog");
}
