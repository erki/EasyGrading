package at.erki.easygrading.backend.interfaces;

import at.erki.easygrading.backend.domain.model.Teacher;
import at.erki.easygrading.backend.domain.repository.TeacherRepository;
import at.erki.easygrading.backend.interfaces.assembler.TeacherResourceAssembler;
import at.erki.easygrading.backend.interfaces.exception.TeacherNotFoundException;
import org.springframework.hateoas.Resource;
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
public class TeacherController {

    private TeacherRepository repository;

    private TeacherResourceAssembler assembler;

    public TeacherController(TeacherRepository repository, TeacherResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping(value = "/teachers")
    public Resources<Resource<Teacher>> all() {
        List<Resource<Teacher>> teachers =
                repository.findAll().stream().map(assembler::toResource).collect(Collectors.toList());
        return new Resources<>(teachers, linkTo(methodOn(TeacherController.class).all()).withSelfRel());
    }

    @GetMapping("/teachers/{username}")
    public Resource<Teacher> one(@PathVariable String username) {
        return assembler.toResource(repository.findById(username).orElseThrow(() -> new TeacherNotFoundException(username)));
    }

    @PostMapping("/teachers")
    public ResponseEntity<?> newTeacher(@RequestBody Teacher teacher) throws URISyntaxException {
        Resource<Teacher> resource = assembler.toResource(repository.save(teacher));
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @DeleteMapping("/teachers/{username}")
    public ResponseEntity<Teacher> delete(@PathVariable String username) {
        repository.deleteById(username);
        return ResponseEntity.noContent().build();
    }

}
