package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

//import javassist.NotFoundException;

import java.util.List;
//import java.util.Optional;
import java.util.Optional;
import javassist.NotFoundException;

@Service
public class ProductService implements IProductService{
    
    private ProductRepository repository;


    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product create(ProductRequest request) {
        Product newProduct = new Product();
        newProduct.setName(request.getName());
        newProduct.setDescription(request.getDescription());
        newProduct.setAmount(request.getAmount());
        newProduct.setUnit(request.getUnit());
        newProduct.setPrice(request.getPrice());
        return this.repository.save(newProduct);
    }


    @Override
    public Product getProductById(Long Id) throws NotFoundException {
        Optional<Product> p = this.repository.findById(Id);
        if (!(p.isPresent())){
            throw new NotFoundException("not found");
        }
        return p.get();
    }


    @Override
    public void deleteById(Long Id) throws NotFoundException {
        Optional<Product> cart = this.repository.findById(Id);
        if (!(cart.isPresent())) throw new NotFoundException("Item with id "+ Id + "was not found!");
        this.repository.deleteById(Id);
    }

    @Override
    public Product updateProduct(long id, ProductRequest request) throws NotFoundException{
        try{
            Product p = getProductById(id);
            if(request.getName()!=null){
                p.setName(request.getName());
            }
            if(request.getDescription()!=null){
                p.setDescription(request.getDescription());
            }
            return p;
        }catch (Exception E){
            throw new NotFoundException("Cart with "+ id + "was not found!");
        }
        
        
    }

    @Override
    public Product addProductAmount(long id, ProductRequest request) throws NotFoundException{
        try{
            Product p = getProductById(id);
            p.setAmount(p.getAmount()+request.getAmount());
            return p;
        }catch (Exception E){
            throw new NotFoundException("Not found!");
        }
    }




}
