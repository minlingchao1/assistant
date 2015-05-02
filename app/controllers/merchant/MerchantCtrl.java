/**
 * @Project:assistant
 * @Title: MerchantCtrl.java
 * @date: 2015-1-17 下午10:26:57
 * @version 1.0
 */
package controllers.merchant;

import java.io.File;
import java.util.UUID;

import org.slf4j.Logger;

import assistant.app.base.config.BaseConfigs;
import assistant.app.base.config.LogCongfig;
import assistant.app.bind.logic.IWechatInfoLogic;
import assistant.app.bind.logic.impl.WechatInfoLogicImpl;
import assistant.app.bind.model.WechatAccountInfo;
import assistant.app.common.util.wxutil.AccessTokenGetUntil;
import assistant.app.user.logic.IMerchantLogic;
import assistant.app.user.logic.impl.MerchantLogicImpl;
import assistant.app.user.model.Merchant;
import controllers.base.BaseController;

/**
 * @ClassName MerchantCtrl
 * @Description 用户信息处理
 * @author minlingchao
 * @date 2015-1-17 下午10:26:57
 */
public class MerchantCtrl extends BaseController {

    public static final Logger LOG = LogCongfig.MERCHANTLOG;

    private static IMerchantLogic merchantLogic = MerchantLogicImpl.getInstance();

    private static IWechatInfoLogic wechatInfoLogic = WechatInfoLogicImpl.getInstance();

    // 注册
    public static void register(String address, String email, String mobile, String passwd, String qq, String userName) {
        // TODO 检查用户名是否存在，或者邮箱是否已经注册

        long result = merchantLogic.register(address, email, mobile, passwd, qq, userName);
        if (result > 0) {
            renderJsonSuccess();
        } else {
            renderJsonFail();
        }
    }

    // 登录
    public static void login(String userName, String passwd) {
        Merchant merchant = merchantLogic.login(userName, passwd);
        if (merchant != null) {
            session.put(BaseConfigs.SESSION.SEESION_MERCHANT_ID, merchant.getId());
            session.put(BaseConfigs.SESSION.SEESION_MERCHANT_NAME, merchant.getUserName());
            renderJsonSuccess();
        } else {
            renderJsonFail(590, "用户不存在！");
        }
    }

    // 获取用户信息
    public static void getInfo() {
        Long id = Long.valueOf(session.get(BaseConfigs.SESSION.SEESION_MERCHANT_ID));
        Merchant merchant = merchantLogic.getInfo(id);
        renderJsonAjaxResult(merchant);
    }

    // 编辑
    public static void edit(String userName, String mobile, String qq, String email) {
        Long id = Long.valueOf(session.get(BaseConfigs.SESSION.SEESION_MERCHANT_ID));
        Merchant merchant = merchantLogic.getInfo(id);
        merchant.setUserName(userName);
        merchant.setMobile(mobile);
        merchant.setEmail(email);
        merchant.setQq(qq);
        if (merchantLogic.edit(merchant) > 0) {
            renderJsonSuccess();
        } else {
            renderJsonFail();
        }
    }

    // 修改密码
    public static void changePasswd(String oldPasswd, String newPasswd) {
        Long id = Long.valueOf(session.get(BaseConfigs.SESSION.SEESION_MERCHANT_ID));
        if (merchantLogic.changePasswd(id, oldPasswd, newPasswd) > 0) {
            renderJsonSuccess();
        } else {
            renderJsonFail();
        }

    }

    // 手动绑定微信号
    public static void manualBind(Long wechatId, String accountName, String type, String authenticate, String appId,
            String appSecret, String wechatNumber, String accountId, String qrCode, String headImage) {
        Long userId = Long.valueOf(session.get(BaseConfigs.SESSION.SEESION_MERCHANT_ID));
        String url = wechatInfoLogic.createUrl(request, appId);
        String token = UUID.randomUUID().toString().replace("-", "");
        // 更新
        if (wechatId != null) {
            String accessToken = AccessTokenGetUntil.getAccessToken(appId, appSecret);
            WechatAccountInfo wechatAccountInfo = new WechatAccountInfo(accountName, type, authenticate, wechatNumber,
                    accountId, appId, appSecret, url, token, qrCode, headImage, accessToken);
            wechatAccountInfo.setId(wechatId);
            if (wechatInfoLogic.update(wechatAccountInfo) > 0) {
                renderJsonSuccess(200, wechatId.toString());
            } else {
                renderJsonFail();
            }
        } else {
            // 添加
            Long wechatIdInsert = wechatInfoLogic.addWechatInfo(userId, accountName, type, authenticate, appId,
                    appSecret, wechatNumber, accountId, qrCode, headImage, url, token);
            if (wechatIdInsert > 0) {
                renderJsonSuccess(200, wechatIdInsert.toString());
            } else {
                renderJsonFail();
            }
        }
    }

    public static void getWXInfo(Long wechatId) {
        Long merchatId = getMerchantId();
        validWechatInfo(merchatId, wechatId);
        WechatAccountInfo wechatAccountInfo = wechatInfoLogic.findById(wechatId);
        renderJsonAjaxResult(wechatAccountInfo);

    }

    // 保存图片
    public static void saveImg(File file) {
        String realImgUrl = wechatInfoLogic.saveImage(request, file);
        if (realImgUrl != null) {
            renderJsonUploadResult(0, realImgUrl);
        } else {
            renderJsonUploadResult(1, realImgUrl);
        }

    }

    // 保存语音
    public static void saveVoice(File file) {
        String realVoiceUrl = wechatInfoLogic.saveVoice(request, file);
        if (realVoiceUrl != null) {
            renderJsonUploadResult(0, realVoiceUrl);
        } else {
            renderJsonUploadResult(1, realVoiceUrl);
        }

    }

    // 保存视频
    public static void saveVideo(File file) {
        String realVideoUrl = wechatInfoLogic.saveVideo(request, file);
        if (realVideoUrl != null) {
            renderJsonUploadResult(0, realVideoUrl);
        } else {
            renderJsonUploadResult(1, realVideoUrl);
        }

    }

    // 退出
    public static void loginOut(long userId) {
        session.clear();
        render("/Application/index.html");

    }
}
