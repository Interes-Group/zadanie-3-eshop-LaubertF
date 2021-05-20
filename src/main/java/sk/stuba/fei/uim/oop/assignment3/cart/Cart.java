package sk.stuba.fei.uim.oop.assignment3.cart;

import sk.stuba.fei.uim.oop.assignment3.product.ProductIncart;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private float Price;
    @OneToMany
    private List<ProductIncart> shoppingList = new ArrayList<ProductIncart>();
    private Boolean payed = false;
}