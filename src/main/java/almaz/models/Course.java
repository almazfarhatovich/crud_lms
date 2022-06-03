package almaz.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "courses")
@Getter@Setter
@ToString
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    private int duration;

    @ManyToOne(cascade = {MERGE, DETACH, REFRESH})
    private Company company;

    @ManyToMany(mappedBy = "courses", cascade = {ALL})
    private List<Group> groups = new ArrayList<>();

    @OneToOne(mappedBy = "course", cascade = ALL)
    private Teacher teacher;

    public Course(String courseName, int duration, Company company, List<Group> groups, Teacher teacher) {
        this.courseName = courseName;
        this.duration = duration;
        this.company = company;
        this.groups = groups;
        this.teacher = teacher;
    }

    public Course() {

    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }
}

