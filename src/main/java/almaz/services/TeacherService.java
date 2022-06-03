package almaz.services;


import almaz.models.Course;
import almaz.models.Teacher;
import almaz.repositories.CourseRepository;
import almaz.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    public void saveTeacher(Long id, Teacher teacher) {
        Course course = courseRepository.findById(id);
        teacher.setCourse(course);
        course.setTeacher(teacher);
        teacherRepository.saveTeacher(teacher);
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Teacher findById(Long id) {
        return teacherRepository.findById(id);
    }

    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }

    public void update(Long id, Teacher teacher) {
        teacherRepository.update(id, teacher);
    }
}
