package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
    List<Product> findAll();

    List<Product> findByName(String name);

}
