package sk.stuba.fei.uim.oop.assignment3.product;


import lombok.Getter;

@Getter
public class ProductResponse {
    private Long id = null;
    private String name;
    private String description;
    private long amount;
    private String unit;
    private float price;

    public ProductResponse(Product p){
        this.id=p.getId();
        this.name=p.getName();
        this.description=p.getDescription();
        this.amount=p.getAmount();
        this.unit=p.getUnit();
        this.price=p.getPrice();
    }
}
