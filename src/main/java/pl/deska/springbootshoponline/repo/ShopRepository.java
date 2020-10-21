package pl.deska.springbootshoponline.repo;

import org.hibernate.engine.jdbc.batch.internal.BasicBatchKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import pl.deska.springbootshoponline.model.Basket;
import pl.deska.springbootshoponline.model.Product;
import pl.deska.springbootshoponline.service.ShopService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

@Repository
public class ShopRepository {
    private Basket basket;
    private ShopService shopService;

    @Autowired
    public ShopRepository(ShopService shopService) {
        this.shopService = shopService;
        this.basket = new Basket(new ArrayList<>());
    }

    public Basket findAll() {
        return basket;
    }

    public void add(Product product){
        basket.setValue(basket.getValue().add(product.getPrice()));
        basket.getProducts().add(product);
    }

    @EventListener(ApplicationReadyEvent.class)
    public  void addProductsToBasket(){
        basket.getProducts().add(new Product("Headset", getRandomProductPrice()));
        basket.getProducts().add(new Product("Computer mouse", getRandomProductPrice()));
        basket.getProducts().add(new Product("Printer", getRandomProductPrice()));
        basket.getProducts().add(new Product("HDMI cable", getRandomProductPrice()));
        basket.getProducts().add(new Product("USB adapter", getRandomProductPrice()));
          shopService.updatePrice(basket.getProducts());
          setBasketValue();
    }

    private BigDecimal getRandomProductPrice() {
        Random random = new Random();
        int randomPrice = random.nextInt(300 - 50) + 50;
        return BigDecimal.valueOf(randomPrice);
    }


    private void setBasketValue() {
        BigDecimal basketValue = new BigDecimal(0);
        for (int i = 0; i < basket.getProducts().size(); i++) {
            BigDecimal productPrice = basket.getProducts().get(i).getPrice();
            basketValue = basketValue.add(productPrice);
        }
        basket.setValue(basketValue);
    }
}
