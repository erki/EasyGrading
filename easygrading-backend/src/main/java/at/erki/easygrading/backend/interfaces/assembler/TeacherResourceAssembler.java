package at.erki.easygrading.backend.interfaces.assembler;

import at.erki.easygrading.backend.domain.model.Teacher;
import at.erki.easygrading.backend.interfaces.TeacherController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class TeacherResourceAssembler implements ResourceAssembler<Teacher, Resource<Teacher>> {
    @Override
    public Resource<Teacher> toResource(Teacher teacher) {
        return new Resource<>(teacher, linkTo(methodOn(TeacherController.class).one(teacher.getUsername())).withSelfRel(),
                linkTo(methodOn(TeacherController.class).all()).withRel("teachers"));
    }
}
