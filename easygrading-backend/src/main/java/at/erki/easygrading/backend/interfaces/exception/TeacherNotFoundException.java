package at.erki.easygrading.backend.interfaces.exception;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(String username) {
        super("Could not find a teacher with username " + username);
    }
}
