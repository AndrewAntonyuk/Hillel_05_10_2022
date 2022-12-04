package products;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductFull extends ProductSale {
    private final LocalDate createDate;

    public ProductFull(ProductsType type, BigDecimal price, boolean sale, LocalDate createDate) {
        super(type, price, sale);
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "{type: " + super.getType()
                + ", price: " + super.getPrice()
                + ", sale: " + super.isSale()
                + ", createDate: " + createDate + "}";
    }

    //region Getters/Setters
    public LocalDate getCreateDate() {
        return createDate;
    }
    //endregion
}
