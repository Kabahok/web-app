package org.egoravdeev.exeption;

public class NotFoundExeption extends RuntimeException{
    public NotFoundExeption() {
        super();
    }

    public NotFoundExeption(String message) {
        super(message);
    }

    public NotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundExeption(Throwable cause) {
        super(cause);
    }

    protected NotFoundExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
