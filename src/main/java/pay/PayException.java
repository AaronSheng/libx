package pay;

public class PayException extends RuntimeException {
    public PayException() {
    }

    public PayException(String msg) {
        super(msg);
    }

    public PayException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public PayException(Throwable throwable) {
        super(throwable);
    }
}
