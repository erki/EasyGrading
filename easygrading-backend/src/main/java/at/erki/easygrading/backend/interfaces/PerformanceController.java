package at.erki.easygrading.backend.interfaces;

import at.erki.easygrading.backend.domain.model.Performance;
import at.erki.easygrading.backend.domain.repository.PerformanceRepository;
import at.erki.easygrading.backend.interfaces.assembler.PerformanceResourceAssembler;
import at.erki.easygrading.backend.interfaces.exception.PerformanceNotFoundException;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PerformanceController {

    private PerformanceRepository repository;

    private PerformanceResourceAssembler assembler;

    public PerformanceController(PerformanceRepository repository, PerformanceResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/classes/{classId}/students/{studentId}/performances")
    public Resources<Resource<Performance>> all(@PathVariable Long classId, @PathVariable Long studentId) {
        List<Resource<Performance>> performances =
                repository.findByStudentId(studentId)
                        .stream()
                        .map(assembler::toResource)
                        .collect(Collectors.toList());
        return new Resources(performances);
    }

    @GetMapping("/classes/{classId}/students/{studentId}/performances/{performanceId}")
    public Resource<Performance> one(@PathVariable Long classId,
                                     @PathVariable Long studentId, @PathVariable Long performanceId) {
        return assembler.toResource(
                repository.findById(performanceId).orElseThrow(() -> new PerformanceNotFoundException(performanceId)));
    }

}
