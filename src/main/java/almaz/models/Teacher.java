package almaz.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "teachers")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Teacher {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_id_generator"
    )
    @SequenceGenerator(
            name = "teacher_id_generator",
            sequenceName = "teacher_id_seq",
            allocationSize = 1)
    private Long id;
    private String firstName;
    private String email;
    private String lastName;
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Course course;
    public Teacher(String firstName, String email, String lastName) {
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
    }
}
