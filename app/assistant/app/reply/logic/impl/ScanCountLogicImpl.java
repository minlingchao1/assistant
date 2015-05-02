/**
 * @author mlc
 * @date 2015年3月27日 上午10:55:24
 * @version 1.0
 * 浏览量统计逻辑处理
 */
package assistant.app.reply.logic.impl;

import java.util.List;

import org.slf4j.Logger;

import assistant.app.base.config.LogCongfig;
import assistant.app.base.dto.Statistics;
import assistant.app.reply.dal.IScanCountDao;
import assistant.app.reply.dal.impl.ScanCountDaoImpl;
import assistant.app.reply.logic.IScanCountLogic;
import assistant.app.reply.model.ScanCount;

public class ScanCountLogicImpl implements IScanCountLogic {

    private static final Logger LOG = LogCongfig.SCANCOUNTLOG;

    private static ScanCountLogicImpl instance = new ScanCountLogicImpl();

    private ScanCountLogicImpl() {

    }

    public static ScanCountLogicImpl getInstance() {
        return instance;
    }

    private IScanCountDao scanDao = ScanCountDaoImpl.getInstance();

    @Override
    public long insert(long wechatId, int type, String clickTime) {

        ScanCount scanCount = new ScanCount();
        scanCount.setCount(1);
        scanCount.setClickTime(clickTime);
        scanCount.setClickType(type);
        scanCount.setWechatId(wechatId);
        return scanDao.insert(scanCount);
    }

    @Override
    public List<Statistics> countByType(long wechatId, int type) {

        return scanDao.countByType(wechatId, type);
    }

    @Override
    public List<Statistics> countAllByDays(long wechatId) {

        return scanDao.countAllByDays(wechatId);
    }

}
