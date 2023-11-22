package idriscahyono.springdatajpa.service;

import idriscahyono.springdatajpa.entity.Category;
import idriscahyono.springdatajpa.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionOperations;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionOperations transactionOperations;

    public void voidCreateCategories(){
        transactionOperations.executeWithoutResult(transactionStatus -> {
            for (int i = 0; i < 10; i++){
                Category category = new Category();
                category.setName("CAT" + i);
                categoryRepository.save(category);
            }
            throw new RuntimeException("Rollback please");
        });
    }

    @Transactional
    public void create(){
        for (int i = 0; i < 10; i++){
            Category category = new Category();
            category.setName("CAT" + i);
            categoryRepository.save(category);
        }
        throw new RuntimeException("Rollback please");
    }

    public void test(){
        create();
    }

}
