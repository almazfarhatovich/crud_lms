package almaz.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;
import static jakarta.persistence.CascadeType.ALL;


@Entity
@Table(name = "companies")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "located_county")
    private String locatedCountry;

    @OneToMany(mappedBy = "company", cascade = ALL)
    private List<Course> courses = new ArrayList<>();

    public Company(String companyName, String locatedCountry, List<Course> courses) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
        this.courses = courses;
    }

    public void setCourse(Course course) {
        this.courses.add(course);
    }
}