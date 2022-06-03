package almaz.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    @Column(name = "date_of_start", nullable = false)
    private Date dateOfStart;

    @Column(name = "date_of_finish", nullable = false)
    private Date dateOfFinish;

    @ManyToMany
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<Student> students = new ArrayList<>();

    public void addCourse(Course course) {
        this.courses.add(course);
    }
    public void addStudent(Student student) {
        this.students.add(student);
    }
}