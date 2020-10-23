package pl.deska.springbootshoponline.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.deska.springbootshoponline.model.Basket;
import pl.deska.springbootshoponline.model.Product;
import pl.deska.springbootshoponline.repo.ShopRepository;


@Service
public class ShopManager {

    private ShopRepository shopRepository;
    private Basket basket;

    @Autowired
    public ShopManager(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
        basket = findAll();
    }

    public Basket findAll() {
        return shopRepository.findAll();
    }

    public void add(Product product) {
        shopRepository.add(product);
    }


}
