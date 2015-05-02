/**
 * @author mlc
 * @date 2015年3月27日 上午10:49:22
 * @version 1.0
 */
package assistant.app.reply.dal.impl;

import java.util.List;

import mybatisplay.IbatisSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.dto.Statistics;
import assistant.app.reply.dal.IScanCountDao;
import assistant.app.reply.dao.mapper.IScanCountMapper;
import assistant.app.reply.model.ScanCount;

public class ScanCountDaoImpl implements IScanCountDao {

    private static final Logger LOG = LogCongfig.SCANCOUNTLOG;

    private static ScanCountDaoImpl instance = new ScanCountDaoImpl();

    private ScanCountDaoImpl() {

    }

    public static ScanCountDaoImpl getInstance() {
        return instance;
    }

    @Override
    public long insert(ScanCount scanCount) {

        long result = 0l;
        SqlSession session = IbatisSessionFactory.get().openSession(false);
        try {
            IScanCountMapper scanCountMapper = session.getMapper(IScanCountMapper.class);
            result = scanCountMapper.insert(scanCount);
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
    public List<Statistics> countByType(long wechatId, int type) {

        List<Statistics> result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            IScanCountMapper scanCountMapper = session.getMapper(IScanCountMapper.class);
            result = scanCountMapper.countByType(wechatId, type);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Statistics> countAllByDays(long wechatId) {

        List<Statistics> result = null;
        SqlSession session = IbatisSessionFactory.get().openSession();
        try {
            IScanCountMapper scanCountMapper = session.getMapper(IScanCountMapper.class);
            result = scanCountMapper.countAllByDays(wechatId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            session.rollback();
        } finally {
            session.close();
        }
        return result;
    }

}
