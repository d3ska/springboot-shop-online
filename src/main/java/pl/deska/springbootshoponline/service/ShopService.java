package pl.deska.springbootshoponline.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import pl.deska.springbootshoponline.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Service
@ConfigurationProperties(prefix = "package-status")
public class ShopService {

    private BigDecimal discount;
    private BigDecimal vat;


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
            product.setPrice(priceBeforeDiscount.subtract(discountInPercentage()));
        }
    }

    private BigDecimal discountInPercentage() {
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
