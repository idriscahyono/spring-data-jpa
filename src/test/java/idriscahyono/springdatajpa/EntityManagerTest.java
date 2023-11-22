package idriscahyono.springdatajpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@SpringBootTest
public class EntityManagerTest {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    void testEntityManagerFactory(){
        Assertions.assertNotNull(entityManagerFactory);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Assertions.assertNotNull(entityManager);

        entityManager.close();
    }
}
