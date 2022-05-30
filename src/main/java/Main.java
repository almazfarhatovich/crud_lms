import almaz.models.Company;
import almaz.models.Course;
import almaz.models.Group;
import almaz.repositories.CompanyRepository;
import almaz.repositories.GroupRepository;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        GroupRepository groupRepository = new GroupRepository();
        groupRepository.saveGroup(new Group("Java",new Date(10-11-2022),new Date(10-12-2022)));
        groupRepository.close();
    }
}
