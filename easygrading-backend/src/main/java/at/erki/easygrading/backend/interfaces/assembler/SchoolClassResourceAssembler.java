package at.erki.easygrading.backend.interfaces.assembler;

import at.erki.easygrading.backend.domain.model.SchoolClass;
import at.erki.easygrading.backend.interfaces.SchoolClassController;
import at.erki.easygrading.backend.interfaces.TeacherController;
import at.erki.easygrading.backend.interfaces.resource.SchoolClassResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrappers;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class SchoolClassResourceAssembler implements ResourceAssembler<SchoolClass, SchoolClassResource> {

    private StudentResourceAssembler studentResourceAssembler;

    public SchoolClassResourceAssembler(StudentResourceAssembler studentResourceAssembler) {
        this.studentResourceAssembler = studentResourceAssembler;
    }

    @Override
    public SchoolClassResource toResource(SchoolClass schoolClass) {
        EmbeddedWrappers wrapper = new EmbeddedWrappers(true);
        SchoolClassResource resource = new SchoolClassResource(schoolClass.getName());
        resource.add(linkTo(methodOn(SchoolClassController.class).one(schoolClass.getId())).withSelfRel(),
                linkTo(methodOn(SchoolClassController.class).all()).withRel("all"),
                linkTo(methodOn(TeacherController.class).one(schoolClass.getTeacher().getUsername())).withRel(
                        "teacher"));
        resource.setEmbeddeds(
                new Resources<>(schoolClass.getStudents().stream().map(studentResourceAssembler::toResource)
                        .map(wrapper::wrap)
                        .collect(Collectors.toList())));
        return resource;
    }
}
