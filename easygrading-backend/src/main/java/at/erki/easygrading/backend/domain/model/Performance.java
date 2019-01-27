package at.erki.easygrading.backend.domain.model;

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
    private Student student;

    @ManyToOne
    private Activity activity;

    protected Performance() {}

}
