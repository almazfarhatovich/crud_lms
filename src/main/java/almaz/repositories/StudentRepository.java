package almaz.repositories;

import almaz.configurations.Config;
import almaz.models.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StudentRepository implements AutoCloseable{
    private final EntityManagerFactory entityManagerFactory = Config.createEntityManagerFactory();

    public void saveStudent(Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public List<Student> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Student> students = entityManager.createQuery("select s from Student s", Student.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return students;
    }

    public Student findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Student student = entityManager.createQuery("select s from Student s where s.id=?1", Student.class).setParameter(1, id).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return student;
    }

    public Student findByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Student student = entityManager.createQuery("select s from Student s where s.firstName=?1", Student.class).setParameter(1, name).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return student;
    }
    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void update(Long id,Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Student s set s.firstName=?1,s.lastName=?2,s.email=?3,s.studyFormat=?4 where s.id=?5")
                        .setParameter(1,student.getFirstName()).setParameter(2,student.getLastName()).setParameter(3,student.getEmail())
                        .setParameter(4,student.getStudyFormat()).setParameter(5,id).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
