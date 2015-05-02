/**
 * 
 */
package assistant.app.base.job.base;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;

/**
 * 
 * @title OnceCycleJob.java
 * @author lizhong.chen
 * @data 2014-1-17下午5:47:35
 * @description 一次循环数据处理基础job -需要实现getAllData()
 * @version V1.0
 * 
 */
public abstract class OnceJob<T> extends BasicJob {

    @Override
    public void doJob() {
        if (!isEnable()) {
            getLog().info("job is unenable..");
            return;
        }
        getLog().info("job begin...");
        initData();
        List<T> list = getAllData();
        if (CollectionUtils.isEmpty(list)) {
            // log("job end ... because list is null");
            getLog().info("job end ... because list is null");
        } else {
            getLog().info("num of data got:" + list.size());
            for (T t : list) {
                try {
                    dealOne(t);
                } catch (Exception e) {
					// 对单个处理进行异常捕获，防止中途异常,导致程序异常中断
                    getLog().warn(e.getMessage());
                }
            }
        }
        afterJob();
        getLog().info("job end ... finish");
    }

    /**
	 * 获取循环数据
	 * 
	 * @param offset
	 * @param limit
	 * @return
	 */
    public abstract List<T> getAllData();

    /**
	 * 进行单个处理
	 * 
	 * @param t
	 * @return
	 */
    public abstract boolean dealOne(T t);

    /**
	 * 日志记录
	 * 
	 */
    public abstract Logger getLog();

    /**
	 * 是否开启
	 * 
	 * @return
	 */
    public boolean isEnable() {
        return true;
    }

    /**
	 * 初始化数据
	 */
    public void initData() {
        // this is a hook
    }

    /**
	 * 运行完成后执行
	 */
    public void afterJob() {
        // this is a hook
    }

}
