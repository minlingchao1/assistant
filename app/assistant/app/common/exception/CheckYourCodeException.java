package assistant.app.common.exception;


/**
 * 运行期异常
 */
public class CheckYourCodeException extends RuntimeException {
    public CheckYourCodeException(String message) {
        super(message);
    }

    public CheckYourCodeException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
