package almaz.configurations;

import almaz.models.*;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class Config {
    public static EntityManagerFactory createEntityManagerFactory() {
        Properties properties = new Properties();
        properties.setProperty(Environment.DRIVER,"org.postgresql.Driver");
        properties.setProperty(Environment.URL,"jdbc:postgresql://localhost:5432/java_middle");
        properties.setProperty(Environment.USER,"postgres");
        properties.setProperty(Environment.PASS,"almaz2002");
        properties.setProperty(Environment.HBM2DDL_AUTO,"update");
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty(Environment.SHOW_SQL,"true");
        Configuration configuration = new Configuration();
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(Group.class);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Teacher.class);
        return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);
    }
}
