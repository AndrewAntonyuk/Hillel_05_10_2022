package products;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    private ProductsType type;
    private BigDecimal price;

    public Product(ProductsType type, BigDecimal price) {
        BigDecimal bigDecimal = price.setScale(2, RoundingMode.HALF_UP);
        this.type = type;
        this.price = bigDecimal;
    }

    @Override
    public String toString() {
        return "{type: " + type
                + ", price: " + price + "}";
    }

    //region Getters/Setters
    public ProductsType getType() {
        return type;
    }

    public void setType(ProductsType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    //endregion
}
