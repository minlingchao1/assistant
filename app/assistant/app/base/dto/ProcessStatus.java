/**
 * @author mlc
 * @date 2015年5月1日 下午10:08:51
 * @version 1.0
 */
package assistant.app.base.dto;

public class ProcessStatus {

    private boolean isSuccess;

    private String message;

    /**
     * 
     */
    public ProcessStatus(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
