package springDataTests;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootTest
public class RepositoryArticleTest {


    public RepositoryArticleTest() {
    }

    @Test
    public void LoggerTest(){

        Logger logger= LoggerFactory.getLogger(this.getClass());
        logger.info("TEST LOGGER RECORD");
    }

}