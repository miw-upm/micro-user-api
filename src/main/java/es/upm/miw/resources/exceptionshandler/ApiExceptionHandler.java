package es.upm.miw.resources.exceptionshandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NotFoundException.class,
            NoResourceFoundException.class,
            ResponseStatusException.class
    })
    @ResponseBody
    public ErrorMessage noResourceFoundRequest(Exception exception) {
        return new ErrorMessage(exception, HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            Exception.class
    })
    @ResponseBody
    public ErrorMessage exception(Exception exception) {
        return new ErrorMessage(new RuntimeException("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
