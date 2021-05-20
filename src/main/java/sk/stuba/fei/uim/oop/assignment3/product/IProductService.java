package sk.stuba.fei.uim.oop.assignment3.product;

//import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request);

    Product getProductById(Long Id) throws Exception;
    public void deleteById(Long Id) throws Exception;
    Product updateProduct(long id, ProductRequest request) throws Exception;
    Product addProductAmount(long id, ProductRequest request) throws Exception;

}
