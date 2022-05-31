package almaz.repositories;


import almaz.configurations.Config;
import almaz.models.Company;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CompanyRepository implements AutoCloseable{
    private final EntityManagerFactory entityManagerFactory = Config.createEntityManagerFactory();

    public void saveCompany(Company company) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(company);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public Company findById(Long companyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Company company = entityManager.createQuery("select c from Company c where c.id=?1", Company.class).setParameter(1, companyId).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return company;
    }

    public List<Company> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Company> companies = entityManager.createQuery("select c from Company c", Company.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return companies;
    }

    public void update(Long id, Company company) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Company c set c.companyName=?1,c.locatedCountry=?2 where c.id=?3").setParameter(1, company.getCompanyName()).setParameter(2, company.getLocatedCountry()).setParameter(3, id).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteById(Long companyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Company c where c.id = ?1").setParameter(1, companyId).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
