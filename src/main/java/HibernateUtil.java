import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            //Configuration configuration = new Configuration();
            //configuration.configure("hibernate.cfg.xml");
            //System.out.println("Hibernate Configuration loaded");

            //ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            //System.out.println("Hibernate serviceRegistry created");

            //sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            BootstrapServiceRegistry bootstrapServiceRegistry =
                    new BootstrapServiceRegistryBuilder().build();
            StandardServiceRegistryBuilder standardServiceRegistryBuilder =
                    new StandardServiceRegistryBuilder(bootstrapServiceRegistry);
            StandardServiceRegistry standardServiceRegistry = standardServiceRegistryBuilder
                    .configure()
                    .build();
            MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
            metadataSources.addResource("hibernate.cfg.xml");
            Metadata metadata = metadataSources.buildMetadata();

            sessionFactory = metadata.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
