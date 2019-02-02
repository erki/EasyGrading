package at.erki.easygrading.backend.interfaces.exception.handler;

import at.erki.easygrading.backend.interfaces.exception.PerformanceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PerformanceNotFoundHandler {

    @ResponseBody
    @ExceptionHandler(PerformanceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String performanceNotFoundHandler(PerformanceNotFoundException e) {
        return e.getMessage();
    }

}
