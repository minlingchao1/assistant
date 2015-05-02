/**
 * @author mlc
 * @date 2015年3月24日 下午11:45:17
 * @version 1.0
 */
package assistant.app.echart.dto;

public class Data {

    /**
     * 
     */
    private String type;

    private String name;

    /**
     * 
     */
    public Data() {
    }

    public Data(String type, String name) {
        this.type = type;
        this.name = name;
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

}
