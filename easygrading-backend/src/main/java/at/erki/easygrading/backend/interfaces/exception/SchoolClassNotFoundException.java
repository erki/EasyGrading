package at.erki.easygrading.backend.interfaces.exception;

public class SchoolClassNotFoundException extends RuntimeException {
    public SchoolClassNotFoundException(Long id) {
        super("Could not find class with id " + id);
    }
}
