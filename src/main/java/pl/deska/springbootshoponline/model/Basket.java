package pl.deska.springbootshoponline.model;

import java.math.BigDecimal;
import java.util.List;

public class Basket {
    private List<Product> products;
    private BigDecimal value;

    public Basket(List<Product> basket) {
        this.products = basket;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setBasket(List<Product> basket) {
        this.products = basket;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }


}
