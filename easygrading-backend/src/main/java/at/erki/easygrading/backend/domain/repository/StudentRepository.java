package at.erki.easygrading.backend.domain.repository;

import at.erki.easygrading.backend.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findBySchoolClassId(Long id);

}
