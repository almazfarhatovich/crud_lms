package almaz.models;

import almaz.enams.StudyFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "students")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_id_generator"
    )
    @SequenceGenerator(
            name = "student_id_generator",
            sequenceName = "student_id_seq",
            allocationSize = 1)
    private Long id;
    private String firstName;
    private String email;
    private String lastName;
    private StudyFormat studyFormat;
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Group group;
    public Student(String firstName, String email, String lastName, StudyFormat studyFormat) {
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.studyFormat = studyFormat;
    }
}
