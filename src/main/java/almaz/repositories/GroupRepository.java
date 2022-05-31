package almaz.repositories;

import almaz.configurations.Config;
import almaz.models.Group;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class GroupRepository implements AutoCloseable {
    private final EntityManagerFactory entityManagerFactory = Config.createEntityManagerFactory();

    public void saveGroup(Group group) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(group);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public List<Group> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Group> groups = entityManager.createQuery("select g from Group g", Group.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return groups;
    }

    public Group findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Group group = entityManager.createQuery("select g from Group g where g.id=?1", Group.class).setParameter(1, id).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return group;
    }

    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Group group = entityManager.find(Group.class, id);
        entityManager.remove(group);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateGroup(Long id, Group group) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Group g set g.groupName=?1,g.dateOfStart=?2,g.dateOfFinish=?3 where g.id=?4").
                setParameter(1, group.getGroupName()).
                setParameter(2, group.getDateOfStart()).
                setParameter(3, group.getDateOfFinish()).setParameter(4, id).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
