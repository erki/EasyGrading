package at.erki.easygrading.backend.domain.repository;

import at.erki.easygrading.backend.domain.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
}
