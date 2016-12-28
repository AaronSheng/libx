package pay;

public class AlipayExeception extends RuntimeException {
    public AlipayExeception() {
    }

    public AlipayExeception(String msg) {
        super(msg);
    }

    public AlipayExeception(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public AlipayExeception(Throwable throwable) {
        super(throwable);
    }
}
