package at.erki.easygrading.backend.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.hateoas.core.Relation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@Relation(value = "student", collectionRelation = "students")
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String firstname;

    private String lastname;

    @ManyToOne
    @JsonIgnore
    private SchoolClass schoolClass;

    public Student(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Student(String firstname, String lastname, SchoolClass schoolClass) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.schoolClass = schoolClass;
    }

    protected Student() {}

}
