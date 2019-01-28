package at.erki.easygrading.backend.interfaces;

import at.erki.easygrading.backend.domain.model.Student;
import at.erki.easygrading.backend.domain.repository.StudentRepository;
import at.erki.easygrading.backend.interfaces.assembler.StudentResourceAssembler;
import at.erki.easygrading.backend.interfaces.exception.StudentNotFoundException;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentController {

    private StudentResourceAssembler studentAssembler;

    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository, StudentResourceAssembler studentResourceAssembler) {
        this.studentAssembler = studentResourceAssembler;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/classes/{id}/students")
    public Resources<Resource<Student>> allStudents(@PathVariable Long id) {
        List<Resource<Student>> students =
                studentRepository.findBySchoolClassId(id)
                        .stream()
                        .map(studentAssembler::toResource)
                        .collect(Collectors.toList());
        return new Resources<>(students);
    }

    @GetMapping("/classes/{classId}/students/{studentId}")
    public Resource<Student> one(@PathVariable Long classId, @PathVariable Long studentId) {
        return studentAssembler.toResource(
                studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId)));
    }

}
