package at.erki.easygrading.backend.interfaces;

import at.erki.easygrading.backend.domain.repository.SchoolClassRepository;
import at.erki.easygrading.backend.interfaces.assembler.SchoolClassResourceAssembler;
import at.erki.easygrading.backend.interfaces.exception.SchoolClassNotFoundException;
import at.erki.easygrading.backend.interfaces.resource.SchoolClassResource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class SchoolClassController {

    private SchoolClassRepository schoolClassRepository;

    private SchoolClassResourceAssembler schoolClassAssembler;

    public SchoolClassController(SchoolClassRepository schoolClassRepository,
                                 SchoolClassResourceAssembler schoolClassAssembler) {
        this.schoolClassRepository = schoolClassRepository;
        this.schoolClassAssembler = schoolClassAssembler;
    }

    @GetMapping(value = "/classes")
    public Resources<SchoolClassResource> all() {
        List<SchoolClassResource> classes =
                schoolClassRepository.findAll()
                        .stream()
                        .map(schoolClassAssembler::toResource)
                        .collect(Collectors.toList());
        return new Resources<>(classes, linkTo(methodOn(SchoolClassController.class).all()).withSelfRel());
    }

    @GetMapping("/classes/{id}")
    public SchoolClassResource one(@PathVariable Long id) {
        return schoolClassAssembler.toResource(
                schoolClassRepository.findById(id).orElseThrow(() -> new SchoolClassNotFoundException(id)));
    }

}
