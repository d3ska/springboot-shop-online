package pl.deska.springbootshoponline.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import pl.deska.springbootshoponline.model.Basket;
import pl.deska.springbootshoponline.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Service
@ConfigurationProperties(prefix = "package-status")
public class ShopService {

    private BigDecimal discount;
    private BigDecimal vat;

    public void setBasketValue(Basket basket) {
        BigDecimal basketValue = new BigDecimal(0);
        for (int i = 0; i < basket.getProducts().size(); i++) {
            BigDecimal productPrice = basket.getProducts().get(i).getPrice();
            basketValue = basketValue.add(productPrice);
        }
        basket.setValue(basketValue);
    }

    public void updatePrice(List<Product> basket){
        basket.forEach(product -> {
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


    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }


}
