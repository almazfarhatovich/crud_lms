package almaz.services;

import almaz.models.Course;
import almaz.models.Group;
import almaz.repositories.CourseRepository;
import almaz.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;


    public GroupService(GroupRepository groupRepository, CourseRepository courseRepository) {
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
    }

    public void saveGroup(Group group, Long id) {
        Course course = courseRepository.findById(id);
        course.addGroup(group);
        group.addCourse(course);
        groupRepository.saveGroup(group);
    }

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Group findById(Long id) {
        return groupRepository.findById(id);
    }

    public void deleteById(Long id) {
        groupRepository.deleteById(id);
    }

    public void updateGroup(Long id, Group group) {
        groupRepository.updateGroup(id, group);
    }
}
