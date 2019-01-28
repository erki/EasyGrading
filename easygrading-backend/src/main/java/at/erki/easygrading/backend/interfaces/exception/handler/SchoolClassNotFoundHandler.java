package at.erki.easygrading.backend.interfaces.exception.handler;

import at.erki.easygrading.backend.interfaces.exception.SchoolClassNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SchoolClassNotFoundHandler {

    @ResponseBody
    @ExceptionHandler(SchoolClassNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String schoolClassNotFoundHandler(SchoolClassNotFoundException e) {
        return e.getMessage();
    }

}
