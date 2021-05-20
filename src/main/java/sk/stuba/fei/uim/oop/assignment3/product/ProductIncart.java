package sk.stuba.fei.uim.oop.assignment3.product;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter

public class ProductIncart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private long amount;
}
