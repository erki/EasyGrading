package at.erki.easygrading.backend.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Performance {

    @Id
    @GeneratedValue
    private Long id;

    private int achievedPoints;

    @ManyToOne
    @JsonIgnore
    private Student student;

    @ManyToOne
    private Activity activity;

    protected Performance() {}

    public Performance(int achievedPoints, Student student, Activity activity) {
        this.achievedPoints = achievedPoints;
        this.student = student;
        this.activity = activity;
    }

}
