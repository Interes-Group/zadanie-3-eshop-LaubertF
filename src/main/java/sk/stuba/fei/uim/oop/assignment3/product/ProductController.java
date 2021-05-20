package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/*
import sk.stuba.fei.uim.oop.assignment3.product.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.ProductResponse;
import sk.stuba.fei.uim.oop.assignment3.product.IProductService;*/
import org.springframework.web.server.ResponseStatusException;
import javassist.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private IProductService service;

    @GetMapping()
    public List<ProductResponse> getAllProducts() {
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());

    }

    @PostMapping()
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request) {
        return new ResponseEntity<>(new ProductResponse(this.service.create(request)), HttpStatus.CREATED);
    }


    /*@GetMapping("/{name}")
    public List<ProductResponse> getAllProductsByName(@PathVariable("name") String name) {
        return this.service.getAllByName(name).stream().map(ProductResponse::new).collect(Collectors.toList());
    }*/

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable("id") long request) throws NotFoundException {
        try{
            return new ProductResponse(this.service.getProductById(request));
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") long request) throws NotFoundException {
        try{
            this.service.deleteById(request);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/amount")
    public ProductAmountResponse getProductAmount(@PathVariable("id") long request) throws NotFoundException {
        try{
            return new ProductAmountResponse(this.service.getProductById(request));
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/amount")
    public ProductAmountResponse addProductAmount(@PathVariable("id") long id, @RequestBody ProductRequest request) throws NotFoundException {
        try{
            return new ProductAmountResponse(this.service.addProductAmount(id,request));
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable("id") long id,@RequestBody ProductRequest request) throws NotFoundException {
        try{
            return new ProductResponse(this.service.updateProduct(id,request));
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}


/*@PostMapping()
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request) {
        return new ResponseEntity<>(new ProductResponse(this.service.create(request)), HttpStatus.CREATED);
    }*/