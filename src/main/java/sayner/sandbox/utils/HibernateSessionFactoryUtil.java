package sayner.sandbox.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sayner.sandbox.models.Article;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                configuration.addAnnotatedClass(Article.class);

                StandardServiceRegistryBuilder standardServiceRegistryBuilder
                        = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(standardServiceRegistryBuilder.build());
            } catch (Exception ex) {
                Logger logger = LoggerFactory.getLogger("HibernateSessionFactoryUtil");
                logger.error("This is an ERROR in hiberrnate session factory creating" + ex.getMessage());
            }
        }

        return sessionFactory;
    }
}