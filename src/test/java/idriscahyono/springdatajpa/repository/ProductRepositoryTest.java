package idriscahyono.springdatajpa.repository;

import idriscahyono.springdatajpa.entity.Category;
import idriscahyono.springdatajpa.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void createProduct(){
        Category category = categoryRepository.findById(31L).orElse(null);
        Assertions.assertNotNull(category);

        {
            Product product = new Product();
            product.setName("Product 1");
            product.setPrice(1_000_000L);
            product.setCategory(category);
            productRepository.save(product);
        }

        {
            Product product = new Product();
            product.setName("Product 2");
            product.setPrice(2_000_000L);
            product.setCategory(category);
            productRepository.save(product);
        }

    }

    @Test
    void findProducts(){
        List<Product> products = productRepository.findAllByCategory_Name("ABC0");

        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals("Product 1", products.get(0).getName());
        Assertions.assertEquals("Product 2", products.get(1).getName());
    }

    @Test
    void findProductsSort(){
        Sort sort = Sort.by(Sort.Order.desc("id"));
        List<Product> products = productRepository.findAllByCategory_Name("ABC0", sort);

        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals("Product 2", products.get(0).getName());
        Assertions.assertEquals("Product 1", products.get(1).getName());
    }

    @Test
    void pageable(){
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")));
        Page<Product> products = productRepository.findAllByCategory_Name("ABC0", pageable);
        Assertions.assertEquals(1, products.getContent().size());
        Assertions.assertEquals(0, products.getNumber());
        Assertions.assertEquals(2, products.getTotalPages());
        Assertions.assertEquals(2, products.getTotalElements());
        Assertions.assertEquals("Product 2", products.getContent().get(0).getName());


        pageable = PageRequest.of(1, 1, Sort.by(Sort.Order.desc("id")));
        products = productRepository.findAllByCategory_Name("ABC0", pageable);
        Assertions.assertEquals(1, products.getContent().size());
        Assertions.assertEquals(1, products.getNumber());
        Assertions.assertEquals(2, products.getTotalPages());
        Assertions.assertEquals(2, products.getTotalElements());
        Assertions.assertEquals("Product 1", products.getContent().get(0).getName());
    }

    @Test
    void deleteByName(){
        Category category = categoryRepository.findById(31L).orElse(null);
        Assertions.assertNotNull(category);

        Product product = new Product();
        product.setName("SAMSUNG 1");
        product.setPrice(10_000_000L);
        product.setCategory(category);
        productRepository.save(product);

        int deleteProduct = productRepository.deleteByName("SAMSUNG 1");
        Assertions.assertEquals(1, deleteProduct);

        deleteProduct = productRepository.deleteByName("SAMSUNG 1");
        Assertions.assertEquals(0, deleteProduct);
    }

}