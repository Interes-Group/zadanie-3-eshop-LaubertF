package sk.stuba.fei.uim.oop.assignment3.cart;

//import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;

import sk.stuba.fei.uim.oop.assignment3.product.ProductCartRequest;

public interface ICartService {
    Cart addCart();
    List<Cart> getAll();
    Cart getCartById(Long Id) throws Exception;
    public void deleteById(Long Id) throws Exception;
    //Cart addPersonToAnimal(long animalId, long personId);
    Cart putToCart(long cartId, ProductCartRequest request) throws Exception;
    String payCart(long cartId) throws Exception;
}
