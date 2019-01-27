package at.erki.easygrading.backend.domain.repository;

import at.erki.easygrading.backend.domain.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
