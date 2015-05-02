/**
 * @Project:assistant
 * @Title: CustomInfoLogicImpl.java
 * @date: 2015-2-13 下午6:10:21
 * @version 1.0
 */
package assistant.app.custom.logic.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.config.WechatConfig;
import assistant.app.base.dto.Statistics;
import assistant.app.common.util.DateUtils;
import assistant.app.common.util.WebUtil;
import assistant.app.custom.dal.ICustomInfoDao;
import assistant.app.custom.dal.impl.CustomInfoDaoImpl;
import assistant.app.custom.dto.CustomListDto;
import assistant.app.custom.logic.ICustomInfoLogic;
import assistant.app.custom.model.CustomInfo;

/**
 * @ClassName CustomInfoLogicImpl
 * @Description 用户信息管理
 * @author minlingchao
 * @date 2015-2-13 下午6:10:21
 */
public class CustomInfoLogicImpl implements ICustomInfoLogic {

    public static final Logger LOG = LogCongfig.CUSTOMLOG;

    private static CustomInfoLogicImpl instance = new CustomInfoLogicImpl();

    private CustomInfoLogicImpl() {

    }

    public static CustomInfoLogicImpl getInstance() {
        return instance;
    }

    private ICustomInfoDao customInfoDao = CustomInfoDaoImpl.getInstance();

    @Override
    public long insert(CustomInfo customInfo) {
        return customInfoDao.insert(customInfo);
    }

    @Override
    public String getCustomList(Long wechatId, String accessToken, String nextOpenId) {
        CustomListDto customListDto = null;
        String nextOpenIdInfo = null;

        if (nextOpenId == null) {
            nextOpenId = "";
        }
        String url = WechatConfig.CUSTOM_LIST_GET_URL;
        url = url.replace("ACCESS_TOKEN", accessToken).replace("NEXT_OPENID", nextOpenId);
        String result = WebUtil.httpRequest(url, "GET", null);
        JSONObject resjson = JSONObject.fromObject(result);
        // 如果请求成功
        if (result != null) {
            try {

                customListDto = new CustomListDto();
                customListDto.setCount(resjson.getInt("count"));
                customListDto.setTotal(resjson.getInt("total"));

                JSONObject dataObject = (JSONObject) resjson.get("data");
                customListDto.setOpenIdList(JSONArray.toList(dataObject.getJSONArray("openid"), List.class));

                for (String openId : customListDto.getOpenIdList()) {
                    LOG.warn("存储用户：{}信息中....", openId);
                    saveCustomInfo(wechatId, accessToken, openId);
                }
                if (!resjson.getString("next_openid").isEmpty()) {

                    nextOpenIdInfo = resjson.getString("next_openid");
                    LOG.warn("nextOpenIdInfo is not empty：{}", nextOpenIdInfo);
                } else {

                    nextOpenIdInfo = "end";
                    LOG.warn("nextOpenIdInfo is  empty：{}", nextOpenIdInfo);
                }

            } catch (Exception e) {
                int errcode = resjson.getInt("errcode");
                String errMsg = resjson.getString("errmsg");
                LOG.error(errMsg, errcode);
            }

        }
        return nextOpenIdInfo;
    }

    @Override
    public long saveCustomInfo(Long wechatId, String accessToken, String openId) {

        CustomInfo customInfo = null;
        String url = WechatConfig.CUSTOM_INGO_GET_URL;
        url = url.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);

        String result = WebUtil.httpRequest(url, "GET", null);
        JSONObject resjson = JSONObject.fromObject(result);
        if (resjson != null) {
            try {
                customInfo = new CustomInfo();
                customInfo.setOpenId(openId);
                customInfo.setSubscribe(resjson.getInt("subscribe"));
                customInfo.setSubscribeTime(Long.valueOf(resjson.getString("subscribe_time")));
                customInfo.setNickName(resjson.getString("nickname"));
                customInfo.setSex(resjson.getInt("sex"));
                customInfo.setCountry(resjson.getString("country"));
                customInfo.setProvince(resjson.getString("province"));
                customInfo.setCity(resjson.getString("city"));
                customInfo.setLanguage(resjson.getString("language"));
                customInfo.setHeadImgUrl(resjson.getString("headimgurl"));
                customInfo.setWechatId(wechatId);
                long count = countByOpenId(openId);
                if (count != 0) {
                    update(customInfo);
                } else {
                    insert(customInfo);
                }

            } catch (Exception e) {
                if (0 == customInfo.getSubscribe()) {
                    LOG.error("用户{}已取消关注", customInfo.getOpenId());
                } else {
                    int errcode = resjson.getInt("errcode");
                    String errMsg = resjson.getString("errmsg");
                    LOG.error(errMsg, errcode);
                }
                // e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public long update(CustomInfo customInfo) {
        return customInfoDao.update(customInfo);
    }

    @Override
    public long countByWechatId(Long wechatId) {
        return customInfoDao.countByWechatId(wechatId);
    }

    @Override
    public long countByOpenId(String openId) {

        return customInfoDao.countByOpenId(openId);
    }

    @Override
    public List<CustomInfo> getList(long wechatId) {
        return customInfoDao.getList(wechatId);
    }

    @Override
    public CustomInfo showDetail(long id) {
        return customInfoDao.showDetail(id);
    }

    @Override
    public List<Statistics> countByDays(Long wechatId) {

        return customInfoDao.countByDays(wechatId);
    }

    @Override
    public List<CustomInfo> screen(int type) {
        long startTime = 0l;
        long endTime = 0l;

        // 今日新增客户||今日关注客户
        if (type == 0 || type == 2) {
            startTime = DateUtils.setBeginTime(0);
            endTime = DateUtils.setEndTime(0);
        } else if (type == 3) {
            // 近七天关注客户
            startTime = DateUtils.setBeginTime(7);
            endTime = DateUtils.setEndTime(0);
        } else if (type == 4) {
            // 近30天关注用户
            startTime = DateUtils.setBeginTime(30);
            endTime = DateUtils.setEndTime(0);
        } else if (type == 5) {
            // 30天前关注用户
            endTime = DateUtils.setEndTime(30);
        }

        return customInfoDao.screen(startTime, endTime);
    }

    @Override
    public long countScreen(int type) {
        long startTime = 0l;
        long endTime = 0l;

        // 今日新增客户||今日关注客户
        if (type == 0 || type == 2) {
            startTime = DateUtils.setBeginTime(0);
            endTime = DateUtils.setEndTime(0);
        } else if (type == 3) {
            // 近七天关注客户
            startTime = DateUtils.setBeginTime(7);
            endTime = DateUtils.setEndTime(0);
        } else if (type == 4) {
            // 近30天关注用户
            startTime = DateUtils.setBeginTime(30);
            endTime = DateUtils.setEndTime(0);
        } else if (type == 5) {
            // 30天前关注用户
            endTime = DateUtils.setEndTime(30);
        }
        return customInfoDao.countScreen(startTime, endTime);
    }

}
