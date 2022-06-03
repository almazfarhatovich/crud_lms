package almaz.models;

import almaz.enams.StudyFormat;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "study_format")
    private StudyFormat studyFormat;

    @ManyToOne
    private Group group;
}