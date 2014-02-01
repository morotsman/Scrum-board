package org.github.morotsman.todo.service.exceptions;


public class ResourceExistException extends RuntimeException {

    public ResourceExistException() {
    }

    public ResourceExistException(String s) {
        super(s);
    }

    public ResourceExistException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ResourceExistException(Throwable throwable) {
        super(throwable);
    }
}
