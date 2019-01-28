package at.erki.easygrading.backend.interfaces;

import at.erki.easygrading.backend.domain.model.SchoolClass;
import at.erki.easygrading.backend.domain.model.Student;
import at.erki.easygrading.backend.domain.repository.ClassRepository;
import at.erki.easygrading.backend.interfaces.assembler.SchoolClassResourceAssembler;
import at.erki.easygrading.backend.interfaces.assembler.StudentResourceAssembler;
import at.erki.easygrading.backend.interfaces.exception.SchoolClassNotFoundException;
import at.erki.easygrading.backend.interfaces.resource.SchoolClassResource;
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
public class SchoolClassController {

    private ClassRepository repository;

    private SchoolClassResourceAssembler schoolClassAssembler;

    private StudentResourceAssembler studentAssembler;

    public SchoolClassController(ClassRepository repository, SchoolClassResourceAssembler schoolClassAssembler,
                                 StudentResourceAssembler studentAssembler) {
        this.repository = repository;
        this.schoolClassAssembler = schoolClassAssembler;
        this.studentAssembler = studentAssembler;
    }

    @GetMapping(value = "/classes")
    public Resources<SchoolClassResource> all() {
        List<SchoolClassResource> classes =
                repository.findAll().stream().map(schoolClassAssembler::toResource).collect(Collectors.toList());
        return new Resources<>(classes, linkTo(methodOn(SchoolClassController.class).all()).withSelfRel());
    }

    @GetMapping("/classes/{id}")
    public SchoolClassResource one(@PathVariable Long id) {
        return schoolClassAssembler.toResource(repository.findById(id).orElseThrow(() -> new SchoolClassNotFoundException(id)));
    }

    @GetMapping("/classes/{id}/students")
    public Resources<Resource<Student>> allStudents(@PathVariable Long id) {
        SchoolClass schoolClass = repository.findById(id).orElseThrow(() -> new SchoolClassNotFoundException(id));
        List<Resource<Student>> students =
                schoolClass.getStudents().stream().map(studentAssembler::toResource).collect(Collectors.toList());
        return new Resources<>(students);
    }
}
