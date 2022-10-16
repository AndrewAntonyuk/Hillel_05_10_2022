package hw_6_Geometry;

public class Circle implements Shape {
    private Double radius;

    //region Constructors
    public Circle(Double radius) {
        this.radius = radius;
    }
    //endregion

    public Double area() {
        return radius * radius * Math.PI;
    }

    //region Getters/Setters
    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }
    //endregion
}
