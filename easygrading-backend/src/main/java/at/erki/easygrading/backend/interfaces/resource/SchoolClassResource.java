package at.erki.easygrading.backend.interfaces.resource;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.hateoas.core.Relation;

@Data
@Relation(value = "class", collectionRelation = "classes")
@EqualsAndHashCode(callSuper = false)
public class SchoolClassResource extends ResourceSupport {

    private String name;

    @JsonUnwrapped
    private Resources<EmbeddedWrapper> embeddeds;

    public SchoolClassResource(String name) {
        this.name = name;
    }

}
