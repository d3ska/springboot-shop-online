package pl.deska.springbootshoponline.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.deska.springbootshoponline.model.Basket;
import pl.deska.springbootshoponline.model.Product;
import pl.deska.springbootshoponline.repo.ShopRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@ConfigurationProperties(prefix = "package-status")
public class PriceService {

    private BigDecimal discount;
    private BigDecimal vat;

    public void setBasketValue(Basket basket) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : basket.getProducts()) {
            sum = sum.add(product.getPrice());
        }
        basket.setValue(sum);
    }

    public void updatePrice(Basket basket){
        basket.getProducts().forEach(product -> {
            includeVatTax(product);
            updatePriceByDiscount(product);
        });
    }

    private void includeVatTax(Product product) {
        if(vat != null) {
            BigDecimal priceWithoutVat = product.getPrice();
            product.setPrice(priceWithoutVat.subtract(vat));
        }
    }

    private void updatePriceByDiscount(Product product) {
        if(discount != null){
            BigDecimal priceBeforeDiscount = product.getPrice();
            product.setPrice(priceBeforeDiscount.subtract(priceWithDiscountInPercetnage()));
        }
    }

    private BigDecimal priceWithDiscountInPercetnage() {
        BigDecimal fullPrice = BigDecimal.valueOf(1);
        return fullPrice.subtract(discount);
    }


}
