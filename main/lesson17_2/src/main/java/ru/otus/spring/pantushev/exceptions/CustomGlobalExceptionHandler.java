package ru.otus.spring.pantushev.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public void hadlerBookNotFound(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendError(HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(BookDataWrongException.class)
    public void handlerBookDataWrong(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value());
    }

}
