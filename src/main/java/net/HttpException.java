package net;

/**
 * Created by aaron on 12/26/16.
 */
public class HttpException extends Exception {
    public HttpException() {
    }

    public HttpException(String msg) {
        super(msg);
    }

    public HttpException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public HttpException(Throwable throwable) {
        super(throwable);
    }
}
