package at.erki.easygrading.backend.interfaces.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long studentId) {
        super("Could not find student with id " + studentId);
    }
}
