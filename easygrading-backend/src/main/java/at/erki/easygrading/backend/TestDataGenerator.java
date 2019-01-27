package at.erki.easygrading.backend;

import at.erki.easygrading.backend.domain.model.*;
import at.erki.easygrading.backend.domain.repository.ActivityRepository;
import at.erki.easygrading.backend.domain.repository.ClassRepository;
import at.erki.easygrading.backend.domain.repository.GradingSchemeRepository;
import at.erki.easygrading.backend.domain.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static java.util.Arrays.asList;

@Configuration
@Slf4j
public class TestDataGenerator {

    @Bean
    @Profile("dev")
    CommandLineRunner populateDatabase(TeacherRepository teacherRepository, ClassRepository classRepository,
                                       GradingSchemeRepository gradingSchemeRepository, ActivityRepository activityRepository) {
        return args -> {
            log.info("Populate DB with testdata");
            teacherRepository.save(new Teacher("cory", "Corinna", "Erkinger", "corinna.erkinger@gmail.com", "password"
            ));
            SchoolClass schoolClass = new SchoolClass("4B");
            schoolClass.getStudents().addAll(asList(new Student("Max", "Mustermann"), new Student("Lena", "Meier"),
                    new Student("Lara", "Lustig")));
            classRepository.saveAndFlush(schoolClass);
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

            activityRepository.saveAll(asList(new Activity("Schularbeit 1", 100, schoolClass, exams),
                    new Activity("Schularbeit 2", 100, schoolClass, exams),
                    new Activity("HÜ 1", 20, schoolClass, homeworks)));
        };
    }

}