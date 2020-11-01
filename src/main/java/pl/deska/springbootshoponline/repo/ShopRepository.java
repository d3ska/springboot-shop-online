package pl.deska.springbootshoponline.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import pl.deska.springbootshoponline.model.Basket;
import pl.deska.springbootshoponline.model.Product;
import pl.deska.springbootshoponline.service.PriceService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class ShopRepository {
    private Basket basket;


    @Autowired
    public ShopRepository() {
        this.basket = new Basket(new ArrayList<>());
    }


    public Basket getBasket() {
        return basket;
    }


    public void add(Product product){
        basket.updateBasketValue(product.getPrice());
        basket.addProduct(product);
    }


    @EventListener(ApplicationReadyEvent.class)
    public  void addProductsToBasket(){
        basket.getProducts().add(new Product("Headset", getRandomProductPrice()));
        basket.getProducts().add(new Product("Computer mouse", getRandomProductPrice()));
        basket.getProducts().add(new Product("Printer", getRandomProductPrice()));
        basket.getProducts().add(new Product("HDMI cable", getRandomProductPrice()));
        basket.getProducts().add(new Product("USB adapter", getRandomProductPrice()));
    }

    private BigDecimal getRandomProductPrice() {
        Random random = new Random();
        int randomPrice = random.nextInt(300 - 50) + 50;
        return BigDecimal.valueOf(randomPrice);
    }

}
