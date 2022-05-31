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
    @ManyToMany(mappedBy = "courses")
    private List<Group> groups;
    @OneToOne(mappedBy = "course")
    private Teacher teacher;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Company company;
    @Transient
    private Long companyId;
    public Course(Company company) {
      this.company = company;
    }
}
