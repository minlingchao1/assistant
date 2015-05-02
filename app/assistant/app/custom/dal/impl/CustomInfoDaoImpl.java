/**
 * @Project:assistant
 * @Title: CustomInfoDaoImpl.java
 * @date: 2015-2-13 下午5:59:10
 * @version 1.0
 */
package assistant.app.custom.dal.impl;

import java.util.List;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.dto.Statistics;
import assistant.app.custom.dal.ICustomInfoDao;
import assistant.app.custom.dao.mapper.ICustomInfoMapper;
import assistant.app.custom.model.CustomInfo;

/**
 * @ClassName CustomInfoDaoImpl
 * @Description TODO
 * @author minlingchao
 * @date 2015-2-13 下午5:59:10
 */
public class CustomInfoDaoImpl implements ICustomInfoDao {

    public static final Logger LOG = LogCongfig.CUSTOMLOG;

    private static CustomInfoDaoImpl instance = new CustomInfoDaoImpl();

    private CustomInfoDaoImpl() {

    }

    public static CustomInfoDaoImpl getInstance() {
        return instance;
    }

    @Override
    public long insert(CustomInfo customInfo) {
        long result = 0l;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        try {
            ICustomInfoMapper customInfoMapper = session.getMapper(ICustomInfoMapper.class);
            result = customInfoMapper.insert(customInfo);
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
    public long update(CustomInfo customInfo) {
        long result = 0l;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        try {
            ICustomInfoMapper customInfoMapper = session.getMapper(ICustomInfoMapper.class);
            result = customInfoMapper.update(customInfo);
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
    public long countByWechatId(Long wechatId) {
        long result = 0l;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            ICustomInfoMapper customInfoMapper = session.getMapper(ICustomInfoMapper.class);
            result = customInfoMapper.countByWechatId(wechatId);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<CustomInfo> getList(long wechatId) {
        List<CustomInfo> result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            ICustomInfoMapper customInfoMapper = session.getMapper(ICustomInfoMapper.class);
            result = customInfoMapper.getList(wechatId);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public CustomInfo showDetail(long id) {
        CustomInfo result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            ICustomInfoMapper customInfoMapper = session.getMapper(ICustomInfoMapper.class);
            result = customInfoMapper.showDetail(id);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<CustomInfo> screen(long startTime, long endTime) {
        List<CustomInfo> result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            ICustomInfoMapper customInfoMapper = session.getMapper(ICustomInfoMapper.class);
            result = customInfoMapper.screen(startTime, endTime);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public long countScreen(long startTime, long endTime) {
        long result = 0l;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            ICustomInfoMapper customInfoMapper = session.getMapper(ICustomInfoMapper.class);
            result = customInfoMapper.countScreen(startTime, endTime);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public long countByOpenId(String openId) {

        long result = 0l;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            ICustomInfoMapper customInfoMapper = session.getMapper(ICustomInfoMapper.class);
            result = customInfoMapper.countByOpenId(openId);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Statistics> countByDays(Long wechatId) {

        List<Statistics> result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            ICustomInfoMapper customInfoMapper = session.getMapper(ICustomInfoMapper.class);
            result = customInfoMapper.countByDays(wechatId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

}
