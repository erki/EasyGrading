package at.erki.easygrading.backend.domain.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class ActivityCategory {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int weight;

    public ActivityCategory(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    protected ActivityCategory() {}

}
