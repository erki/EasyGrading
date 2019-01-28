package at.erki.easygrading.backend.interfaces.assembler;

import at.erki.easygrading.backend.domain.model.Student;
import at.erki.easygrading.backend.interfaces.SchoolClassController;
import at.erki.easygrading.backend.interfaces.StudentController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class StudentResourceAssembler implements ResourceAssembler<Student, Resource<Student>> {

    @Override
    public Resource<Student> toResource(Student student) {
        return new Resource<>(student,
                linkTo(methodOn(StudentController.class).one(student.getSchoolClass().getId(),
                        student.getId())).withSelfRel(),
                linkTo(methodOn(SchoolClassController.class).one(student.getSchoolClass().getId())).withRel("class"));
    }

}
