package at.erki.easygrading.backend.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GradingScheme {

    @Id @GeneratedValue
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "gradingSchemeId")
    @Getter
    private List<ActivityCategory> categories = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "gradingSchemeId")
    @OrderBy("startingPercentage")
    @Getter
    private List<AssessmentThreshold> assessmentScale = new ArrayList<>();

    public GradingScheme(String name) {
        this.name = name;
    }

    protected GradingScheme() {}

}
