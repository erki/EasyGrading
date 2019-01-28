package at.erki.easygrading.backend.interfaces.assembler;

import at.erki.easygrading.backend.domain.model.Student;
import at.erki.easygrading.backend.interfaces.SchoolClassController;
import at.erki.easygrading.backend.interfaces.resource.StudentResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class StudentResourceAssembler extends ResourceAssemblerSupport<Student, StudentResource> {

    public StudentResourceAssembler() {
        super(SchoolClassController.class, StudentResource.class);
    }

    @Override
    public StudentResource toResource(Student student) {
        return new StudentResource(student);
    }
}
