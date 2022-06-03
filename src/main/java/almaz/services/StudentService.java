package almaz.services;

import almaz.models.Group;
import almaz.models.Student;
import almaz.repositories.GroupRepository;
import almaz.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }


    public void saveStudent(Student student,Long id) {
        Group group = groupRepository.findById(id);
        group.addStudent(student);
        studentRepository.saveStudent(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id);
    }

    public Student findByName(String name) {
        return studentRepository.findByName(name);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public void update(Long id, Student student) {
        studentRepository.update(id, student);
    }
}
