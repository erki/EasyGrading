package at.erki.easygrading.backend.interfaces.assembler;

import at.erki.easygrading.backend.domain.model.Performance;
import at.erki.easygrading.backend.interfaces.PerformanceController;
import at.erki.easygrading.backend.interfaces.StudentController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class PerformanceResourceAssembler implements ResourceAssembler<Performance, Resource<Performance>> {
    @Override
    public Resource<Performance> toResource(Performance performance) {
        return new Resource<>(performance,
                linkTo(methodOn(PerformanceController.class).one(performance.getStudent().getSchoolClass().getId(),
                        performance.getStudent().getId(),
                        performance.getId())).withSelfRel(),
                linkTo(methodOn(StudentController.class).one(performance.getStudent().getSchoolClass().getId(),
                        performance.getStudent().getId())).withRel("student")
        );
    }
}
