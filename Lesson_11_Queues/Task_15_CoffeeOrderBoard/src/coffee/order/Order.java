package coffee.order;

public class Order {
    private int number = 0;
    private String name = "Unnamed";

    //region Constructors
    public Order() {
    }

    public Order(String name) {
        this.name = name;
    }

    public Order(int number, String name) {
        this.number = number;
        this.name = name;
    }
    //endregion

    @Override
    public String toString() {
        return String.format("%1$-5d| %2$s", getNumber(), getName());
    }

    //region Getters/Setters
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion
}
