package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.product.ProductIncart;

import java.util.List;

@Getter
public class CartResponse {
    private Long id;
    private List<ProductIncart> shoppingList;
    private Boolean payed;



    public CartResponse(Cart p){
        this.id=p.getId();
        this.shoppingList=p.getShoppingList();
        this.payed=p.getPayed();
    }
}
