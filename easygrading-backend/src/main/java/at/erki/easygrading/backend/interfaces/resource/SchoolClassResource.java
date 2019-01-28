package at.erki.easygrading.backend.interfaces.resource;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;

@Data
public class SchoolClassResource extends ResourceSupport {

    private String name;

    private Resources<StudentResource> students;

    public SchoolClassResource(String name) {
        this.name = name;
    }

}
