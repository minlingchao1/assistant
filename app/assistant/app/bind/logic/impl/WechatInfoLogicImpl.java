/**
 * @Project:assistant
 * @Title: WechatInfoLogicImpl.java
 * @date: 2015-2-1 下午6:40:18
 * @version 1.0
 */
package assistant.app.bind.logic.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;

import play.Play;
import play.mvc.Http.Request;
import assistant.app.base.config.LogCongfig;
import assistant.app.bind.dal.IWechatInfoDao;
import assistant.app.bind.dal.impl.WechatInfoDaoImpl;
import assistant.app.bind.logic.IWechatInfoLogic;
import assistant.app.bind.model.WechatAccountInfo;
import assistant.app.common.util.FIleUrlUtil;
import assistant.app.common.util.RandomCode;
import assistant.app.common.util.wxutil.AccessTokenGetUntil;

/**
 * @ClassName WechatInfoLogicImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-1 下午6:40:18
 */
public class WechatInfoLogicImpl implements IWechatInfoLogic {

    public static final Logger LOG = LogCongfig.WECHATBINDLOG;

    private static WechatInfoLogicImpl instance = new WechatInfoLogicImpl();

    private WechatInfoLogicImpl() {

    }

    public static WechatInfoLogicImpl getInstance() {
        return instance;
    }

    private IWechatInfoDao wechatInfoDao = WechatInfoDaoImpl.getInstance();

    @Override
    public long addWechatInfo(Long userId, String accountName, String type, String authenticate, String appId,
            String appSecret, String wechatNumber, String accountId, String qrCode, String headImage, String url,
            String token) {
        String accessToken = AccessTokenGetUntil.getAccessToken(appId, appSecret);
        return wechatInfoDao.insert(userId, accountName, type, authenticate, appId, appSecret, wechatNumber, accountId,
                qrCode, headImage, url, token, accessToken);
    }

    @Override
    public long autoBind(Long userId, WechatAccountInfo wechatAccountInfo) {
        // String accessToken =
        // AccessTokenGetUntil.getAccessToken(wechatAccountInfo.getAppId(),
        // wechatAccountInfo.getAppSecret());
        return wechatInfoDao.insert(userId, wechatAccountInfo, null);
    }

    @Override
    public String createUrl(Request request, String appId) {
        String url = request.getBase() + "/vertify?appId=" + appId;
        return url;
    }

    @Override
    public String saveImage(Request request, File file) {
        String fileName = file.getName();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        String imgUrl = FIleUrlUtil.getImageUrl();
        LOG.warn("imgUrl:{}", imgUrl);
        try {
            BufferedImage sourceImg = ImageIO.read(new FileInputStream(file));
            String picName = RandomCode.getRandomString(18) + "_" + RandomCode.getRandStr(6) + ".jpg_"
                    + sourceImg.getWidth() + "x" + sourceImg.getHeight();
            FileUtils.copyFile(file, new File(Play.applicationPath + imgUrl + File.separatorChar + picName + "."
                    + fileType));

            String realImgUrl = request.getBase() + imgUrl + File.separatorChar + picName + "." + fileType;
            return realImgUrl;
        } catch (IOException e) {
            LOG.info(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public WechatAccountInfo findById(Long id) {
        return wechatInfoDao.findById(id);
    }

    @Override
    public long update(WechatAccountInfo wechatAccountInfo) {
        return wechatInfoDao.update(wechatAccountInfo);
    }

    @Override
    public Long findWechatId(Long userId) {
        return wechatInfoDao.findWechatId(userId);
    }

    @Override
    public WechatAccountInfo findByAppId(String appId) {
        return wechatInfoDao.findByAppId(appId);
    }

    @Override
    public List<WechatAccountInfo> getWechatInfo(Long userId) {
        return wechatInfoDao.getWXInfo(userId);
    }

    @Override
    public long delete(Long wechatId) {
        return 0;
    }

    @Override
    public String saveVoice(Request request, File file) {
        String fileName = file.getName();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        String voiceUrl = FIleUrlUtil.getVoiceUrl();
        LOG.warn("voiceUrl:{}", voiceUrl);
        try {
            String voiceName = RandomCode.getRandomString(18);
            FileUtils.copyFile(file, new File(Play.applicationPath + voiceUrl + File.separatorChar + voiceName + "."
                    + fileType));

            String realVoiceUrl = request.getBase() + voiceUrl + File.separatorChar + voiceName + "." + fileType;
            return realVoiceUrl;
        } catch (IOException e) {
            LOG.info(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public String saveVideo(Request request, File file) {
        String fileName = file.getName();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        String videoUrl = FIleUrlUtil.getVoiceUrl();
        LOG.warn("videoUrl:{}", videoUrl);
        try {

            String videoName = RandomCode.getRandomString(18);
            FileUtils.copyFile(file, new File(Play.applicationPath + videoUrl + File.separatorChar + videoName + "."
                    + fileType));

            String realVideoUrl = request.getBase() + videoUrl + File.separatorChar + videoName + "." + fileType;
            return realVideoUrl;
        } catch (IOException e) {
            LOG.info(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public WechatAccountInfo findByUserIdAndWechatId(Long userId, long wechatId) {

        return wechatInfoDao.findByUserIdAndWechatId(userId, wechatId);
    }

    // public static void main(String[] args) {
    //
    // NameValuePair[] data = new NameValuePair[] { new
    // NameValuePair("grant_type", "client_credential"),
    // new NameValuePair("appid", "wx4d288fbd3b62713b"),
    // new NameValuePair("secret", "37be1377d7bb4878591c395a2eea0346") };
    // String accessToken = null;
    // try {
    // String result = WebUtil.doPost(WechatConfig.ACCESS_TOKEN_URL, data);
    // JSONObject tokenJson = JSONObject.fromObject(result);
    // System.out.println(tokenJson);
    // if (tokenJson.get("errcode") != null) {
    // // LOG.error("appId:{} get accesstoken error,errcode:{}", appId,
    // // tokenJson.get("errcode"));
    //
    // } else {
    // accessToken = tokenJson.getString("access_token");
    // System.out.println(accessToken);
    // }
    // } catch (HttpException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    //
    // }

}
