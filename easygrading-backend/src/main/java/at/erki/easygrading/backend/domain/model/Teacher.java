package at.erki.easygrading.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
public class Teacher {

    @Id
    private String username;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    protected Teacher() {}

}
