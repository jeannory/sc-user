package software.craftsmanship.scuser.entities;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(exclude="user", callSuper = false)
@ToString(exclude="user")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sc_space")
public class Space extends SuperEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(mappedBy = "space", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

}
