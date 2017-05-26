package net;

/**
 * Created by aaron on 5/26/17.
 */
public class SocketException extends Exception {
    public SocketException() {
    }

    public SocketException(String msg) {
        super(msg);
    }

    public SocketException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public SocketException(Throwable throwable) {
        super(throwable);
    }
}
