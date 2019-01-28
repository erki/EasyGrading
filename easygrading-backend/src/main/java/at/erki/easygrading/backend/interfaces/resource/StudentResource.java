package at.erki.easygrading.backend.interfaces.resource;

import at.erki.easygrading.backend.domain.model.Student;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class StudentResource extends Resource<Student> {
    public StudentResource(Student content, Link... links) {
        super(content, links);
    }

    public StudentResource(Student content, Iterable<Link> links) {
        super(content, links);
    }
}
