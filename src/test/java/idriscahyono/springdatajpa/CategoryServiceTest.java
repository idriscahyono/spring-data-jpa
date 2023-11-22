package idriscahyono.springdatajpa;

import idriscahyono.springdatajpa.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void success(){
        Assertions.assertThrows(RuntimeException.class, ()->{
            categoryService.create();
        });
    }

    @Test
    void vailed(){
        Assertions.assertThrows(RuntimeException.class,()->{
            categoryService.test();
        });
    }

    @Test
    void programmaticCreateCategory(){
        Assertions.assertThrows(RuntimeException.class,()->{
            categoryService.voidCreateCategories();
        });
    }
}
