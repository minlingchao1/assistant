/**
 * @author mlc
 * @date 2015年3月24日 下午9:47:28
 * @version 1.0
 */
package assistant.app.echart.dto;

import java.util.List;

public class Series {

    private int id;

    private String name;

    private String type;

    private List<Integer> data;

    private MarkPoint markPoint;

    public List<Integer> getData() {
        return data;
    }

    /**
     * 
     */
    public Series() {
    }

    public Series(String name, String type, List<Integer> data, MarkPoint markPoint) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.markPoint = markPoint;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MarkPoint getMarkPoint() {
        return markPoint;
    }

    public void setMarkPoint(MarkPoint markPoint) {
        this.markPoint = markPoint;
    }

}
