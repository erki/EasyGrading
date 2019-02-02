package at.erki.easygrading.backend;

import at.erki.easygrading.backend.domain.model.*;
import at.erki.easygrading.backend.domain.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

import static java.util.Arrays.asList;

@Configuration
@Slf4j
public class TestDataGenerator {

    @Bean
    @Profile("dev")
    CommandLineRunner populateDatabase(TeacherRepository teacherRepository, SchoolClassRepository schoolClassRepository,
                                       GradingSchemeRepository gradingSchemeRepository,
                                       ActivityRepository activityRepository,
                                       PerformanceRepository performanceRepository) {
        return args -> {
            log.info("Populate DB with testdata");
            Teacher teacher = new Teacher("cory", "Corinna", "Erkinger", "corinna.erkinger@gmail.com", "password"
            );
            teacherRepository.save(teacher);
            SchoolClass schoolClass = new SchoolClass("4B", teacher);
            schoolClass.getStudents().addAll(asList(new Student("Max", "Mustermann", schoolClass), new Student("Lena",
                            "Meier", schoolClass),
                    new Student("Lara", "Lustig", schoolClass)));
            schoolClassRepository.saveAndFlush(schoolClass);
            GradingScheme gradingScheme = new GradingScheme("Englisch");
            gradingScheme.getAssessmentScale().addAll(asList(
                    new AssessmentThreshold("1", "Sehr Gut", 90),
                    new AssessmentThreshold("2", "Gut", 80),
                    new AssessmentThreshold("3", "Befriedigend", 65),
                    new AssessmentThreshold("4", "Genügend", 51),
                    new AssessmentThreshold("5", "Nicht Genügend", 0)));
            ActivityCategory exams = new ActivityCategory("Schularbeiten", 50);
            ActivityCategory homeworks = new ActivityCategory("Hausübungen", 50);
            gradingScheme.getCategories().addAll(asList(exams, homeworks));
            gradingSchemeRepository.saveAndFlush(gradingScheme);

            List<Activity> activities = activityRepository.saveAll(asList(new Activity("Schularbeit 1", 100,
                            schoolClass, exams),
                    new Activity("Schularbeit 2", 100, schoolClass, exams),
                    new Activity("HÜ 1", 20, schoolClass, homeworks)));

            performanceRepository.saveAll(asList(new Performance(80, schoolClass.getStudents().get(0),
                    activities.get(0)), new Performance(12, schoolClass.getStudents().get(0), activities.get(2))));
        };
    }

}
