package sk.stuba.fei.uim.oop.assignment3.product;


import lombok.Getter;

@Getter
public class ProductAmountResponse {
    private long amount;

    public ProductAmountResponse(Product p){
        this.amount=p.getAmount();
    }
}