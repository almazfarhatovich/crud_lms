package almaz.models;

import jakarta.persistence.*;
import lombok.*;
import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "teachers")
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @OneToOne(cascade = {MERGE, DETACH, REFRESH})
    private Course course;
}