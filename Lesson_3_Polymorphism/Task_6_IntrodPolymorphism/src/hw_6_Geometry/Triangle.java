package hw_6_Geometry;

public class Triangle implements Shape {
    private Double base;
    private Double verticalHeight;

    //region Constructors
    public Triangle(Double base, Double verticalHeight) {
        this.base = base;
        this.verticalHeight = verticalHeight;
    }
    //endregion

    @Override
    public Double area() {
        return base * verticalHeight / 2;
    }

    //region Setters/Getters
    public Double getBase() {
        return base;
    }

    public void setBase(Double base) {
        this.base = base;
    }

    public Double getVerticalHeight() {
        return verticalHeight;
    }

    public void setVerticalHeight(Double verticalHeight) {
        this.verticalHeight = verticalHeight;
    }
    //endregion
}
