package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;

import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import sk.stuba.fei.uim.oop.assignment3.product.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.ProductCartRequest;
//import javassist.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.ProductIncart;

import java.util.List;
//import java.util.Optional;
import java.util.Optional;

//import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


@Service
public class CartService implements ICartService{
    
    private CartRepository repository;


    @Autowired
    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private IProductService productService;

    @Override
    public List<Cart> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Cart addCart() {
        Cart newCart = new Cart();
        return this.repository.save(newCart);
    }

    @Override
    public Cart getCartById(Long Id) throws NotFoundException {
        Optional<Cart> p = this.repository.findById(Id);
        if (!(p.isPresent())){
            throw new NotFoundException("404");
        }
        return p.get();
    }

    @Override
    public void deleteById(Long Id) throws NotFoundException {
        Optional<Cart> cart = this.repository.findById(Id);
        if (!(cart.isPresent())) throw new NotFoundException("Cart with "+ Id + "was not found!");
        this.repository.deleteById(Id);
    }

    @Override
    public Cart putToCart(long cartId, ProductCartRequest request) throws Exception,NotFoundException{
        Cart cart = getCartById(cartId);
        if (cart.getPayed()){
            throw new BadHttpRequest();
        }
        Product product = this.productService.getProductById(request.getProductId());
        long productRemaining = product.getAmount()-request.getAmount();
        if (productRemaining<0){
            throw new BadHttpRequest();
        }
        product.setAmount(productRemaining);
        ProductIncart productIncart = new ProductIncart();
        productIncart.setProductId(product.getId());
        productIncart.setAmount(request.getAmount());
        List<ProductIncart> list = cart.getShoppingList();
        for (ProductIncart temp : list) {
            if (temp.getProductId()==productIncart.getProductId()){
                temp.setAmount(temp.getAmount()+request.getAmount());
                cart.setPrice(cart.getPrice()+request.getAmount()*product.getPrice());
                return cart;
            }
        }
        list.add(productIncart);
        cart.setPrice(cart.getPrice()+request.getAmount()*product.getPrice());
        return cart;
    }

    @Override
    public String payCart(long cartId) throws Exception{
        Cart cart = getCartById(cartId);
        if (cart.getPayed())
            throw new BadHttpRequest();
        cart.setPayed(true);

        return Float.toString(cart.getPrice());
    }

}
