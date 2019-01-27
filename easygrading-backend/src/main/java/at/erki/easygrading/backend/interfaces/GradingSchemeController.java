package at.erki.easygrading.backend.interfaces;

import at.erki.easygrading.backend.domain.model.GradingScheme;
import at.erki.easygrading.backend.domain.repository.GradingSchemeRepository;
import at.erki.easygrading.backend.interfaces.assembler.GradingSchemeResourceAssembler;
import at.erki.easygrading.backend.interfaces.exception.GradingSchemeNotFoundException;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class GradingSchemeController {

    private GradingSchemeRepository repository;

    private GradingSchemeResourceAssembler assembler;

    public GradingSchemeController(GradingSchemeRepository repository, GradingSchemeResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping(value = "/gradingschemes")
    public Resources<Resource<GradingScheme>> all() {
        List<Resource<GradingScheme>> gradingSchemes = repository.findAll().stream().map(assembler::toResource).collect(Collectors.toList());
        return new Resources<>(gradingSchemes, linkTo(methodOn(GradingSchemeController.class).all()).withSelfRel());
    }

    @GetMapping("/gradingschemes/{id}")
    public Resource<GradingScheme> one(@PathVariable Long id) {
        return assembler.toResource(repository.findById(id).orElseThrow(() -> new GradingSchemeNotFoundException(id)));
    }

}
