package pay;

public class WxPayException extends RuntimeException {
    public WxPayException() {
    }

    public WxPayException(String msg) {
        super(msg);
    }

    public WxPayException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public WxPayException(Throwable throwable) {
        super(throwable);
    }
}
