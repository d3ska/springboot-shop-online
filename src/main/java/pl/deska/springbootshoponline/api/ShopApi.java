package pl.deska.springbootshoponline.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.deska.springbootshoponline.manager.ShopManager;
import pl.deska.springbootshoponline.model.Basket;
import pl.deska.springbootshoponline.model.Product;

@RestController
@RequestMapping("/shop/basket")
public class ShopApi {

    private ShopManager shopManager;

    @Autowired
    public ShopApi(ShopManager shopManager) {
        this.shopManager = shopManager;
    }

    @GetMapping("/all")
    public Basket findAll(){
        return shopManager.findAll();
    }

    @PostMapping
    public void add(@RequestBody Product product){
        shopManager.add(product);
    }
}
