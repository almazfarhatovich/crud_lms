package almaz.services;

import almaz.models.Group;
import almaz.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void saveGroup(Group group) {
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
