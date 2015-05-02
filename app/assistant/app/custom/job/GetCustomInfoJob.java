/**
 * @Project:assistant
 * @Title: GetCustomInfoJob.java
 * @date: 2015-2-13 下午7:12:15
 * @version 1.0
 */
package assistant.app.custom.job;

import java.util.List;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.job.base.OnceJob;
import assistant.app.bind.logic.IWechatAccessTokenLogic;
import assistant.app.bind.logic.impl.WechatAccessTokenLogicImpl;
import assistant.app.bind.model.WechatAccessToken;
import assistant.app.custom.logic.ICustomInfoLogic;
import assistant.app.custom.logic.INextOpenIdLogic;
import assistant.app.custom.logic.impl.CustomInfoLogicImpl;
import assistant.app.custom.logic.impl.NextOpenIdLogicImpl;
import assistant.app.custom.model.NextOpenId;

/**
 * @ClassName GetCustomInfoJob
 * @Description 获取用户基本信息
 * @author minlingchao
 * @date 2015-2-13 下午7:12:15
 */
// @Every("10s")
// @On("0 30 1 * * ?")
public class GetCustomInfoJob extends OnceJob<NextOpenId> {

    public static final Logger LOG = LogCongfig.CUSTOMLOG;

    private INextOpenIdLogic nextOpenIdLogic = NextOpenIdLogicImpl.getInstance();
    private IWechatAccessTokenLogic tokenLogic = WechatAccessTokenLogicImpl.getInstance();
    private ICustomInfoLogic customInfoLogic = CustomInfoLogicImpl.getInstance();

    @Override
    public List<NextOpenId> getAllData() {
        return nextOpenIdLogic.getAll();
    }

    @Override
    public boolean dealOne(NextOpenId nextOpenId) {

        long wechatId = nextOpenId.getWechatId();
        String openId = nextOpenId.getNextOpenId();
        WechatAccessToken accessToken = tokenLogic.findByWechatId(wechatId);
        String next = customInfoLogic.getCustomList(wechatId, accessToken.getAccessToken(), openId);

        // 循环获取用户openId
        while (!next.equals("end")) {
            LOG.warn("nextOpenId:{}", next);
            nextOpenId.setNextOpenId(next);
            nextOpenIdLogic.update(nextOpenId);
            next = customInfoLogic.getCustomList(wechatId, accessToken.getAccessToken(), openId);
        }
        LOG.warn("nextOpenId:{}", next);
        next = null;
        LOG.warn("nextOpenId:{}", next);
        nextOpenId.setNextOpenId(next);
        nextOpenIdLogic.update(nextOpenId);
        return false;
    }

    @Override
    public Logger getLog() {
        return LOG;
    }

}
