/**
 * @Project:assistant
 * @Title: IWechatInfoLogic.java
 * @date: 2015-2-1 下午6:38:54
 * @version 1.0
 */
package assistant.app.bind.logic;

import java.io.File;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import play.mvc.Http.Request;
import assistant.app.bind.model.WechatAccountInfo;

/**
 * @ClassName IWechatInfoLogic
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-1 下午6:38:54
 */
public interface IWechatInfoLogic {

    /**
     * 添加相关微信账户信息
     */
    public long addWechatInfo(Long userId, String accountName, String type, String authenticate, String appId,
            String appSecret, String wechatNumber, String accountId, String qrCode, String headImage, String url,
            String token);

    /**
     * 自动绑定
     */
    public long autoBind(Long userId, WechatAccountInfo wechatAccountInfo);

    /**
     * 删除微信账户信息
     */
    public long delete(Long wechatId);

    /**
     * 自动生成URl
     */
    public String createUrl(Request request, String appId);

    /**
     * 保存图片
     */
    public String saveImage(Request request, File file);

    /**
     * 保存语音
     */
    public String saveVoice(Request request, File file);

    /**
     * 保存视频
     */
    public String saveVideo(Request request, File file);

    /**
     * 查找相关微信账户信息
     */
    public WechatAccountInfo findById(Long id);

    /**
     * 更新微信账户信息
     */
    public long update(WechatAccountInfo wechatAccountInfo);

    /**
     * 查找WechatId
     */
    public Long findWechatId(Long userId);

    /**
     * 根据AppId查找
     */
    public WechatAccountInfo findByAppId(String appId);

    /**
     * 根据userId获取微信账号信息
     */
    public List<WechatAccountInfo> getWechatInfo(Long userId);

    /**
     * 根据微信号和用户查找
     */
    public WechatAccountInfo findByUserIdAndWechatId(@Param("userId") Long userId, @Param("wechatId") long wechatId);

}
