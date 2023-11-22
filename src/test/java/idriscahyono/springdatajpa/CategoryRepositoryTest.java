package idriscahyono.springdatajpa;

import idriscahyono.springdatajpa.entity.Category;
import idriscahyono.springdatajpa.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testCreateCategory(){
        Category category = new Category();
        category.setName("CAT 1");

        categoryRepository.save(category);

        Assertions.assertNotNull(category.getId());
    }

    @Test
    void testUpdateCategory(){
        Category category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(category);

        category.setName("CAT 2");
        categoryRepository.save(category);

        category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(category);
        Assertions.assertEquals("CAT 2", category.getName());
    }

    @Test
    void testDeleteCategory(){
        Category category = categoryRepository.findById(2L).orElse(null);
        Assertions.assertNotNull(category);

        categoryRepository.delete(category);

        category = categoryRepository.findById(2L).orElse(null);
        Assertions.assertNull(category);
    }

    @Test
    void testQueryMethod(){
        Category category = categoryRepository.findFirstByName("CAT1").orElse(null);
        Assertions.assertNotNull(category);
        Assertions.assertEquals("CAT1", category.getName());

        List<Category> categories = categoryRepository.findAllByNameLike("ABC%");
        Assertions.assertEquals(1, categories.size());
        Assertions.assertEquals("ABC0", categories.get(0).getName());
    }
}