/**
 * @Project:assistant
 * @Title: WechatInfoDaoImpl.java
 * @date: 2015-2-1 下午6:42:49
 * @version 1.0
 */
package assistant.app.bind.dal.impl;

import java.util.List;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.bind.dal.IWechatInfoDao;
import assistant.app.bind.dao.mapper.IWechatAccessTokenMapper;
import assistant.app.bind.dao.mapper.IWechatAccountInfoMapper;
import assistant.app.bind.dao.mapper.IWechatAccountUserRefMapper;
import assistant.app.bind.model.WechatAccessToken;
import assistant.app.bind.model.WechatAccountInfo;
import assistant.app.bind.model.WechatAccountUserRef;
import assistant.app.custom.dao.mapper.INextOpenIdMapper;
import assistant.app.custom.model.NextOpenId;
import assistant.app.subscribe.dal.ISubscribeDao;
import assistant.app.subscribe.dal.impl.SubscribeDaoImpl;
import assistant.app.subscribe.dao.mapper.ISubscribeMapper;
import assistant.app.subscribe.model.Subscribe;

/**
 * @ClassName WechatInfoDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-1 下午6:42:49
 */
public class WechatInfoDaoImpl implements IWechatInfoDao {

    public static final Logger LOG = LogCongfig.WECHATBINDLOG;

    private static WechatInfoDaoImpl instance = new WechatInfoDaoImpl();

    private ISubscribeDao subscribeDao = SubscribeDaoImpl.getInstance();

    private WechatInfoDaoImpl() {

    }

    public static WechatInfoDaoImpl getInstance() {
        return instance;
    }

    @Override
    public long insert(Long userId, String accountName, String type, String authenticate, String appId,
            String appSecret, String wechatNumber, String accountId, String qrCode, String headImage, String url,
            String token, String accessToken) {

        Long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        try {

            // 添加微信公众号基本信息
            WechatAccountInfo wechatAccountInfo = new WechatAccountInfo(accountName, type, authenticate, wechatNumber,
                    accountId, appId, appSecret, url, token, qrCode, headImage, accessToken);
            IWechatAccountInfoMapper wechatAccountInfoMapper = session.getMapper(IWechatAccountInfoMapper.class);
            result = wechatAccountInfoMapper.insert(wechatAccountInfo);
            if (result > 0) {
                Long wechatId = wechatAccountInfo.getId();

                // 建立威信-用户关联
                WechatAccountUserRef wechatAccountUserRef = new WechatAccountUserRef(userId, wechatId);
                IWechatAccountUserRefMapper accountUserRefMapper = session.getMapper(IWechatAccountUserRefMapper.class);
                accountUserRefMapper.insert(wechatAccountUserRef);

                // 获取AccessToken
                WechatAccessToken wechatAccessToken = new WechatAccessToken();
                wechatAccessToken.setWechatId(wechatId);
                wechatAccessToken.setAccessToken(accessToken);
                wechatAccessToken.setCreateTime(System.currentTimeMillis());

                IWechatAccessTokenMapper accessTokenMapper = session.getMapper(IWechatAccessTokenMapper.class);
                accessTokenMapper.insert(wechatAccessToken);

                // 获取关注用户列表
                NextOpenId nextOpenId = new NextOpenId();
                nextOpenId.setWechatId(wechatId);
                nextOpenId.setNextOpenId(null);
                INextOpenIdMapper nextOpenIdMapper = session.getMapper(INextOpenIdMapper.class);
                nextOpenIdMapper.insert(nextOpenId);

                result = wechatAccountInfo.getId();
            }
            session.commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public long insert(Long userId, WechatAccountInfo wechatAccountInfo, String accessToken) {
        Long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        try {

            // 添加微信公众号基本信息
            // WechatAccountInfo wechatAccountInfo = new
            // WechatAccountInfo(accountName, type, authenticate, wechatNumber,
            // accountId, appId, appSecret, url, token, qrCode, headImage,
            // accessToken);
            IWechatAccountInfoMapper wechatAccountInfoMapper = session.getMapper(IWechatAccountInfoMapper.class);
            result = wechatAccountInfoMapper.insert(wechatAccountInfo);
            if (result > 0) {
                Long wechatId = wechatAccountInfo.getId();

                // 建立威信-用户关联
                WechatAccountUserRef wechatAccountUserRef = new WechatAccountUserRef(userId, wechatId);
                IWechatAccountUserRefMapper accountUserRefMapper = session.getMapper(IWechatAccountUserRefMapper.class);
                accountUserRefMapper.insert(wechatAccountUserRef);

                // 获取AccessToken
                WechatAccessToken wechatAccessToken = new WechatAccessToken();
                wechatAccessToken.setWechatId(wechatId);
                wechatAccessToken.setAccessToken(accessToken);
                wechatAccessToken.setCreateTime(System.currentTimeMillis());

                IWechatAccessTokenMapper accessTokenMapper = session.getMapper(IWechatAccessTokenMapper.class);
                accessTokenMapper.insert(wechatAccessToken);

                // 获取关注用户列表
                NextOpenId nextOpenId = new NextOpenId();
                nextOpenId.setWechatId(wechatId);
                nextOpenId.setNextOpenId(null);
                INextOpenIdMapper nextOpenIdMapper = session.getMapper(INextOpenIdMapper.class);
                nextOpenIdMapper.insert(nextOpenId);

                result = wechatAccountInfo.getId();
            }
            session.commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public WechatAccountInfo findById(Long id) {
        WechatAccountInfo result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            IWechatAccountInfoMapper wechatAccountInfoMapper = session.getMapper(IWechatAccountInfoMapper.class);
            result = wechatAccountInfoMapper.findById(id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public long update(WechatAccountInfo wechatAccountInfo) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        try {
            IWechatAccountInfoMapper wechatAccountInfoMapper = session.getMapper(IWechatAccountInfoMapper.class);
            result = wechatAccountInfoMapper.update(wechatAccountInfo);
            session.commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Long findWechatId(Long userId) {
        Long result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            IWechatAccountUserRefMapper wechatAccountInfoMapper = session.getMapper(IWechatAccountUserRefMapper.class);
            result = wechatAccountInfoMapper.findWechatId(userId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public WechatAccountInfo findByAppId(String appId) {
        WechatAccountInfo result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            IWechatAccountInfoMapper wechatAccountInfoMapper = session.getMapper(IWechatAccountInfoMapper.class);
            result = wechatAccountInfoMapper.findByAppId(appId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<WechatAccountInfo> getWXInfo(Long userId) {
        List<WechatAccountInfo> result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            IWechatAccountInfoMapper wechatAccountInfoMapper = session.getMapper(IWechatAccountInfoMapper.class);
            result = wechatAccountInfoMapper.getWXInfo(userId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public long deleteAllInfo(long wechatId) {
        long result = 0L;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        try {
            IWechatAccountInfoMapper wechatAccountInfoMapper = session.getMapper(IWechatAccountInfoMapper.class);
            result = wechatAccountInfoMapper.delete(wechatId);

            // 删除首次关注回复及回复信息
            Subscribe subscribe = subscribeDao.findByWechatId(wechatId);
            ISubscribeMapper subscribeMapper = session.getMapper(ISubscribeMapper.class);

            // 删除关键字回复

            // 删除用户分组

            // 删除用户基本信息

            // 删除二维码信息

            session.commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public WechatAccountInfo findByUserIdAndWechatId(Long userId, long wechatId) {

        WechatAccountInfo result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            IWechatAccountInfoMapper wechatAccountInfoMapper = session.getMapper(IWechatAccountInfoMapper.class);
            result = wechatAccountInfoMapper.findByUserIdAndWechatId(userId, wechatId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

}
