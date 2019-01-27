package at.erki.easygrading.backend.interfaces.exception;

public class GradingSchemeNotFoundException extends RuntimeException {
    public GradingSchemeNotFoundException(Long id) {
        super("Could not find a grading scheme with id " + id);
    }
}
