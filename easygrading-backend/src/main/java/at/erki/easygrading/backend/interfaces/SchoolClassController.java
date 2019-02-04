package at.erki.easygrading.backend.interfaces;

import at.erki.easygrading.backend.domain.model.SchoolClass;
import at.erki.easygrading.backend.domain.model.Teacher;
import at.erki.easygrading.backend.domain.repository.SchoolClassRepository;
import at.erki.easygrading.backend.interfaces.assembler.SchoolClassResourceAssembler;
import at.erki.easygrading.backend.interfaces.exception.SchoolClassNotFoundException;
import at.erki.easygrading.backend.interfaces.resource.SchoolClassResource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class SchoolClassController {

    private SchoolClassRepository repository;

    private SchoolClassResourceAssembler assembler;

    public SchoolClassController(SchoolClassRepository repository,
                                 SchoolClassResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping(value = "/classes")
    public Resources<SchoolClassResource> all() {
        List<SchoolClassResource> classes =
                repository.findAll()
                        .stream()
                        .map(assembler::toResource)
                        .collect(Collectors.toList());
        return new Resources<>(classes, linkTo(methodOn(SchoolClassController.class).all()).withSelfRel());
    }

    @GetMapping("/classes/{id}")
    public SchoolClassResource one(@PathVariable Long id) {
        return assembler.toResource(
                repository.findById(id).orElseThrow(() -> new SchoolClassNotFoundException(id)));
    }

    @PostMapping("/classes")
    public ResponseEntity<?> newSchoolClass(@RequestBody SchoolClass schoolClass) throws URISyntaxException {
        SchoolClassResource resource = assembler.toResource(repository.save(schoolClass));
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @DeleteMapping("/classes/{id}")
    public ResponseEntity<Teacher> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
