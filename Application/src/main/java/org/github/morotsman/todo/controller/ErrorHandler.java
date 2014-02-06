package org.github.morotsman.todo.controller;


import org.github.morotsman.todo.service.exceptions.ResourceExistException;
import org.github.morotsman.todo.service.exceptions.ResourceNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ErrorHandler {

    //TODO add logger

    @ExceptionHandler(ResourceExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody
    void handleResourceAlreadyExistsException(Exception exception) {
        //exception.printStackTrace(System.out);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    void handleServiceException(Exception exception) {
        exception.printStackTrace(System.out);
    }

    @ExceptionHandler({ResourceNotFoundException.class, ObjectNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    void handleResourceNotFoundException(Exception exception) {
        //exception.printStackTrace(System.out);
    }
}
