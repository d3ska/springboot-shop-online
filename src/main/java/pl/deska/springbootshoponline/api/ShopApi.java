package pl.deska.springbootshoponline.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.deska.springbootshoponline.manager.ShopManager;
import pl.deska.springbootshoponline.model.Basket;
import pl.deska.springbootshoponline.model.Product;
import pl.deska.springbootshoponline.repo.ShopRepository;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ShopApi {

    private ShopManager shopManager;

    @Autowired
    public ShopApi(ShopManager shopManager) {
        this.shopManager = shopManager;
    }

    @GetMapping("/all")
    public Basket findAll(){
        return shopManager.getBasket();
    }

    @PostMapping
    public void add(@RequestBody Product product){
            shopManager.add(product);
    }
}
