package at.erki.easygrading.backend.interfaces.exception.handler;

import at.erki.easygrading.backend.interfaces.exception.GradingSchemeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GradingSchemeNotFoundHandler  {

    @ResponseBody
    @ExceptionHandler(GradingSchemeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String gradingSchemeNotFoundHandler(GradingSchemeNotFoundException e) {
        return e.getMessage();
    }

}
