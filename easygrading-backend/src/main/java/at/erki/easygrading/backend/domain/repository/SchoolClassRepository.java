package at.erki.easygrading.backend.domain.repository;

import at.erki.easygrading.backend.domain.model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

}
