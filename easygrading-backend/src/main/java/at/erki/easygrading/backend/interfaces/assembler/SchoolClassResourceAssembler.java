package at.erki.easygrading.backend.interfaces.assembler;

import at.erki.easygrading.backend.domain.model.SchoolClass;
import at.erki.easygrading.backend.interfaces.SchoolClassController;
import at.erki.easygrading.backend.interfaces.resource.SchoolClassResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

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
        SchoolClassResource resource = new SchoolClassResource(schoolClass.getName());
        resource.add(linkTo(methodOn(SchoolClassController.class).one(schoolClass.getId())).withSelfRel(),
                linkTo(methodOn(SchoolClassController.class).all()).withRel("classes"));
        resource.setStudents(new Resources<>(studentResourceAssembler.toResources(schoolClass.getStudents())));
        return resource;
    }
}
