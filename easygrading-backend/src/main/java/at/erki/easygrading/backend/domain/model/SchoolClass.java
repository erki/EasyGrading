package at.erki.easygrading.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class SchoolClass {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "classId")
    private List<Student> students = new ArrayList<>();

    public SchoolClass(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    protected SchoolClass() {}
}
