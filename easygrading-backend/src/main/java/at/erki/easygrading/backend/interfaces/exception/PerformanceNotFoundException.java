package at.erki.easygrading.backend.interfaces.exception;

public class PerformanceNotFoundException extends RuntimeException {
    public PerformanceNotFoundException(Long id) {
        super("Could not find performance with id " + id);
    }
}