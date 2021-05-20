package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/*
import sk.stuba.fei.uim.oop.assignment3.Cart.CartRequest;
import sk.stuba.fei.uim.oop.assignment3.Cart.CartResponse;
import sk.stuba.fei.uim.oop.assignment3.Cart.ICartService;*/
import org.springframework.web.server.ResponseStatusException;

import javassist.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.ProductCartRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private ICartService service;

    @GetMapping()
    public List<CartResponse> getAllCarts() {
        return this.service.getAll().stream().map(CartResponse::new).collect(Collectors.toList());

    }

    @PostMapping()
    public ResponseEntity<CartResponse> addCart() {
        return new ResponseEntity<>(new CartResponse(this.service.addCart()), HttpStatus.CREATED);
    }
    
    
    @GetMapping("/{id}")
    public CartResponse getCartById(@PathVariable("id") long request) throws NotFoundException {
        try{
            return new CartResponse(this.service.getCartById(request));
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable("id") long request) throws NotFoundException {
        try{
            this.service.deleteById(request);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

   @PostMapping("/{id}/add")
    public CartResponse addProductToCart(@PathVariable("id") long cartId,@RequestBody ProductCartRequest request) throws Exception,NotFoundException {
        try{
            return new CartResponse(this.service.putToCart(cartId, request));
        }
        catch (NotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/pay")
    public ResponseEntity<String> payCart(@PathVariable("id") long cartId) throws Exception {
        try{
            
            return ResponseEntity.ok(this.service.payCart(cartId));
        }
        catch (NotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}