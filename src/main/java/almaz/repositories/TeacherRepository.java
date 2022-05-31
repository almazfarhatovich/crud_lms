package almaz.repositories;

import almaz.configurations.Config;
import almaz.models.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TeacherRepository implements AutoCloseable{
    private final EntityManagerFactory entityManagerFactory = Config.createEntityManagerFactory();

    public void saveTeacher(Teacher teacher) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public List<Teacher> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Teacher> teachers = entityManager.createQuery("select t from Teacher t", Teacher.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return teachers;
    }

    public Teacher findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Teacher teacher = entityManager.find(Teacher.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return teacher;
    }

    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Teacher teacher = entityManager.find(Teacher.class, id);
        entityManager.remove(teacher);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void update(Long id,Teacher teacher) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Teacher s set s.firstName=?1,s.lastName=?2,s.email=?3 where s.id=?4")
                .setParameter(1,teacher.getFirstName()).setParameter(2,teacher.getLastName()).setParameter(3,teacher.getEmail())
              .setParameter(4,id).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
