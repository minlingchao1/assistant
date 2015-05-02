/**
 * @author mlc
 * @date 2015年3月27日 上午10:21:34
 * @version 1.0
 * 浏览量统计
 */
package assistant.app.reply.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import assistant.app.base.models.basic.BasicModel;

@Entity(name = ScanCount.TABLE_NAME)
public class ScanCount extends BasicModel {

    public static final String TABLE_NAME = "scan_count";

    /**
     * wechatId
     */
    @Column(name = "wechat_id")
    private Long wechatId;

    /**
     * 点击类型
     */
    @Column(name = "click_type")
    private int clickType;

    /**
     * 点击数
     */
    @Column(name = "count")
    private int count;

    /**
     * 点击时间
     */
    @Column(name = "click_time")
    private String clickTime;

    public String getClickTime() {
        return clickTime;
    }

    public void setClickTime(String clickTime) {
        this.clickTime = clickTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getClickType() {
        return clickType;
    }

    public void setClickType(int clickType) {
        this.clickType = clickType;
    }

    public Long getWechatId() {
        return wechatId;
    }

    public void setWechatId(Long wechatId) {
        this.wechatId = wechatId;
    }

}
