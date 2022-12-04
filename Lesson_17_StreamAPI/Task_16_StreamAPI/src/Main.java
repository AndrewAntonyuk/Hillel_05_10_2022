import products.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static products.ProductsType.*;

public class Main {
    public static void main(String[] args) {
        //region Initial variables
        Main main = new Main();
        Predicate<Product> productPredicate;
        Predicate<ProductSale> productSalePredicate;
        Predicate<ProductFull> productFullPredicate;
        List<Product> testList_1 = Arrays.asList(new Product(BOOK, new BigDecimal("140.99")),
                new Product(BOOK, new BigDecimal("340.26")),
                new Product(TOY, new BigDecimal("1024.24")),
                new Product(CAR, new BigDecimal("574896.36")),
                new Product(BOOK, new BigDecimal("289.98")));
        List<ProductSale> testList_2 = Arrays.asList(new ProductSale(BOOK, new BigDecimal("140.99"), true),
                new ProductSale(BOOK, new BigDecimal("340.26"), false),
                new ProductSale(TOY, new BigDecimal("1024.24"), false),
                new ProductSale(CAR, new BigDecimal("574896.36"), true),
                new ProductSale(BOOK, new BigDecimal("289.98"), true));
        List<ProductFull> testList_3 = Arrays.asList(new ProductFull(BOOK, new BigDecimal("140.99"), true, LocalDate.of(2019, 9, 10)),
                new ProductFull(BOOK, new BigDecimal("70.26"), false, LocalDate.of(2022, 8, 21)),
                new ProductFull(TOY, new BigDecimal("1024.24"), false, LocalDate.of(2017, 2, 10)),
                new ProductFull(CAR, new BigDecimal("574896.36"), true, LocalDate.of(2022, 8, 1)),
                new ProductFull(BOOK, new BigDecimal("45.98"), true, LocalDate.of(2022, 11, 18)));
        //endregion

        //region Test of task 1: get all books with price > 250
        System.out.println("===================================================");
        System.out.println("Test of task 1: get all books with price > 250");

        productPredicate = o -> o.getPrice().compareTo(new BigDecimal("250.0")) > 0 && Objects.equals(o.getType(), BOOK);
        System.out.println(main.getExpensiveProducts(testList_1, productPredicate));
        System.out.println("===================================================");
        //endregion

        //region Test of task 2: get all books with sale 10%
        System.out.println("===================================================");
        System.out.println("Test of task 2: get all books with sale 10%");

        productSalePredicate = o -> o.isSale() && Objects.equals(o.getType(), BOOK);
        System.out.println(main.getSaleProducts(testList_2, 10.0, productSalePredicate));
        System.out.println("===================================================");
        //endregion

        //region Test of task 3: get the cheepest product
        System.out.println("===================================================");
        System.out.println("Test of task 3: get the cheepest product");

        System.out.println("The cheepest product: " + main.getCheepestProduct(testList_2, BOOK));
        System.out.println("===================================================");
        //endregion

        //region Test of task 4: get last three products
        System.out.println("===================================================");
        System.out.println("Test of task 4: get last three products");

        System.out.println(main.getLastThreeProducts(testList_3));
        System.out.println("===================================================");
        //endregion

        //region Test of task 5: get sum of products
        System.out.println("===================================================");
        System.out.println("Test of task 5: get sum of products");

        productFullPredicate = o -> Objects.equals(o.getType(), BOOK) && o.getPrice().compareTo(new BigDecimal("75.0")) < 0
                && o.getCreateDate().getYear() == LocalDate.now().getYear();
        System.out.println("The sum of products: " + main.getSumPriceProducts(testList_3, productFullPredicate));
        System.out.println("===================================================");
        //endregion

        //region Test of task 6: get grouped products
        System.out.println("===================================================");
        System.out.println("Test of task 6: get grouped products");

        System.out.println(main.getGroupedProducts(testList_3));
        System.out.println("===================================================");
        //endregion
    }

    public List<Product> getExpensiveProducts(List<Product> list, Predicate<Product> predicate) {
        return list.stream()
                .filter(predicate)
                .toList();
    }

    public List<ProductSale> getSaleProducts(List<ProductSale> list, double inputSale, Predicate<ProductSale> predicate) {
        double actualSale = (100.0 - inputSale) / 100.0;

        return list.stream()
                .filter(predicate)
                .map(e -> new ProductSale(e.getType(), e.getPrice().multiply(new BigDecimal(actualSale)), e.isSale()))
                .toList();
    }

    public ProductSale getCheepestProduct(List<ProductSale> list, ProductsType category) {
        Optional<ProductSale> cheepestBook = list.stream()
                .filter(o -> o.getType().equals(category))
                .min(Comparator.comparing(Product::getPrice));

        if (cheepestBook.isPresent()) {
            return cheepestBook.get();
        } else {
            throw new NoSuchElementException("Product in the [category: " + category + "] not found");
        }
    }

    public List<ProductFull> getLastThreeProducts(List<ProductFull> list) {
        return list.stream()
                .sorted(Comparator.comparing(ProductFull::getCreateDate))
                .skip(list.size() - 3)
                .toList();
    }

    public BigDecimal getSumPriceProducts(List<ProductFull> list, Predicate<ProductFull> predicate) {
        return list.stream()
                .filter(predicate)
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<ProductsType, List<ProductFull>> getGroupedProducts(List<ProductFull> list) {
        return list.stream()
                .collect(Collectors.groupingBy(Product::getType));
    }
}
