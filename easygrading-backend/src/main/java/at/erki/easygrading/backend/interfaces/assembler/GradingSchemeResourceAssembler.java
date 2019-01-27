package at.erki.easygrading.backend.interfaces.assembler;

import at.erki.easygrading.backend.domain.model.GradingScheme;
import at.erki.easygrading.backend.interfaces.GradingSchemeController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class GradingSchemeResourceAssembler implements ResourceAssembler<GradingScheme, Resource<GradingScheme>> {

    @Override
    public Resource<GradingScheme> toResource(GradingScheme gradingScheme) {
        return new Resource<>(gradingScheme,
                linkTo(methodOn(GradingSchemeController.class).one(gradingScheme.getId())).withSelfRel(),
                linkTo(methodOn(GradingSchemeController.class).all()).withRel("gradingschemes"));
    }

}
