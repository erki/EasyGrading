package at.erki.easygrading.backend.domain.repository;

import at.erki.easygrading.backend.domain.model.GradingScheme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradingSchemeRepository extends JpaRepository<GradingScheme, Long> {
}
