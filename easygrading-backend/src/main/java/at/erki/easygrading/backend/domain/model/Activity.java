package at.erki.easygrading.backend.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Activity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int maxPoints;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private ActivityCategory category;

    @ManyToOne
    @JoinColumn(name = "classId")
    private SchoolClass schoolClass;

    protected Activity() {}

    public Activity(String name, int maxPoints, SchoolClass schoolClass, ActivityCategory category) {
        this.name = name;
        this.maxPoints = maxPoints;
        this.schoolClass = schoolClass;
        this.category = category;
    }

}
