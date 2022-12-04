package products;

import java.math.BigDecimal;

public class ProductSale extends Product {
    private boolean sale;

    public ProductSale(ProductsType type, BigDecimal price, boolean sale) {
        super(type, price);
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "{type: " + super.getType()
                + ", price: " + super.getPrice()
                + ", sale: " + sale + "}";
    }

    //region Getters/Setters
    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }
    //endregion
}
