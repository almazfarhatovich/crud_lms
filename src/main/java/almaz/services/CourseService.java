package almaz.services;

import almaz.models.Company;
import almaz.models.Course;
import almaz.repositories.CompanyRepository;
import almaz.repositories.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    public CourseService(CourseRepository courseRepository, CompanyRepository companyRepository) {
        this.courseRepository = courseRepository;
        this.companyRepository = companyRepository;
    }
    @Transactional
    public void saveCourse(Long courseId, Course course) {
        Company company = companyRepository.findById(courseId);
        company.setCourse(course);
        course.setCompany(company);

        courseRepository.saveCourse(course);
    }

    public Course findById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public void update(Long id, Course course) {
        courseRepository.update(id, course);
    }

    public void deleteById(Long courseId) {
        courseRepository.deleteById(courseId);
    }


}
