package almaz.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Course {
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
    private String courseName;
    private int duration;
    @ManyToMany
    private List<Group> groups;
    @OneToMany
    private List<Teacher> teachers;
    @OneToOne
    private Company company;

    public Course(String courseName,
                  int duration) {
        this.courseName = courseName;
        this.duration = duration;

    }
}
