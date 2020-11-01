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


    public void setValue(BigDecimal value) {
        this.value = value;
    }


    public void addProduct(Product product){
        products.add(product);
    }

    public void updateBasketValue(BigDecimal productPrice){
        value.add(productPrice);
    }

    @Override
    public String toString() {
        return "Basket{" +
                "products=" + products +
                ", value=" + value +
                '}';

    }
}
