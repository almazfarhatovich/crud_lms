package almaz.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "companies")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Company {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "company_id_generator"
    )
    @SequenceGenerator(
            name = "company_id_generator",
            sequenceName = "company_id_seq",
            allocationSize = 1)
    private Long id;
    private String companyName;
    private String locatedCountry;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company",orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();

    public Company(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }
    public void setCourse(Course course){
        this.courses.add(course);
    }
}
