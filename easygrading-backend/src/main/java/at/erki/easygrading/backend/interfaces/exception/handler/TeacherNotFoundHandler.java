package at.erki.easygrading.backend.interfaces.exception.handler;

import at.erki.easygrading.backend.interfaces.exception.TeacherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TeacherNotFoundHandler {

    @ResponseBody
    @ExceptionHandler(TeacherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String teacherNotFoundHandler(TeacherNotFoundException e) {
        return e.getMessage();
    }

}
