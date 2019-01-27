package at.erki.easygrading.backend.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class AssessmentThreshold {

    @Id
    @GeneratedValue
    private Long id;

    private String longName;

    private String shortName;

    private double startingPercentage;

    public AssessmentThreshold(String shortName, String longName, double startingPercentage) {
        this.shortName = shortName;
        this.longName = longName;
        this.startingPercentage = startingPercentage;
    }

    protected AssessmentThreshold() {}

}
