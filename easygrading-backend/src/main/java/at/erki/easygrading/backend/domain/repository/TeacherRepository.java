package at.erki.easygrading.backend.domain.repository;

import at.erki.easygrading.backend.domain.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
}
