package ma.zineb.projet.school.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    @ManyToOne
    private Filiere filiere;
}
