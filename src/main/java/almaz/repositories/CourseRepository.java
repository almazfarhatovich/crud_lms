package almaz.repositories;

import almaz.configurations.Config;
import almaz.models.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseRepository implements AutoCloseable{
    private final EntityManagerFactory entityManagerFactory = Config.createEntityManagerFactory();


    public void saveCourse(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public Course findById(Long courseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Course course = entityManager.createQuery("select c from Course c where c.id=?1", Course.class).setParameter(1, courseId).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return course;
    }

    public List<Course> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Course> courses = entityManager.createQuery("select c from Course c", Course.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return courses;
    }

    public void update(Long id, Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Course c set c.courseName=?1,c.duration=?2 where c.id=?3").
                setParameter(1,course.getCourseName()).setParameter(2, course.getDuration()).setParameter(3,id).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteById(Long courseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Course c where c.id = ?1").setParameter(1, courseId).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
