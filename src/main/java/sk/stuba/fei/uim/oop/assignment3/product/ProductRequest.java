package sk.stuba.fei.uim.oop.assignment3.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequest {
    private long id;
    private String name;
    private String description;
    private long amount;
    private String unit;
    private float price;
}
