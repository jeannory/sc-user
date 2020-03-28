package software.craftsmanship.scuser.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.craftsmanship.scuser.enums.Gender;
import software.craftsmanship.scuser.enums.Status;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sc_user",uniqueConstraints={@UniqueConstraint(columnNames = "email")})
public class User extends SuperEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Status status;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @OneToOne(mappedBy = "user")
    private Space space;
}
