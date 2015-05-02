/**
 * @author mlc
 * @date 2015年3月24日 下午9:17:27
 * @version 1.0
 * 按日统计关注者人数
 */
package assistant.app.base.dto;

public class Statistics {

    private String days;

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

}
