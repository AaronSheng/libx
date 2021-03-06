package cipher;

public class DigestException extends RuntimeException {
    public DigestException() {
        super();
    }

    public DigestException(String msg) {
        super(msg);
    }

    public DigestException(Throwable throwable) {
        super(throwable);
    }

    public DigestException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
