package hw_6_Members;

public class Cat extends Member{
    //region Constructors
    public Cat() {
        super("Cat");
    }

    public Cat(String name, Double dist, Double hig) {
        this();
        instanceName = name;
        setMaxDistance(dist);
        setMaxHeight(hig);
    }
    //endregion
}
