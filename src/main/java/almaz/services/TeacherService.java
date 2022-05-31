package almaz.services;


import almaz.models.Teacher;
import almaz.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void saveTeacher(Teacher teacher) {
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
