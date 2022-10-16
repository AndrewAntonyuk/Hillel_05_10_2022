package hw_6_Members;

public class Member {
    private Double maxDistance;
    private Double maxHeight;
    private String name;
    protected String instanceName = "Unnamed";

    //region Constructors
    public Member(String name) {
        this.name = name;
    }
    //endregion

    public void run() {
        System.out.print(getName() + " with name " + getInstanceName()
                + " running through ");
    }

    public void jump() {
        System.out.print(getName() + " with name " + getInstanceName()
                + " jumping through ");
    }

    //region Getters/Setters
    public Double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(Double maxDistance) {
        this.maxDistance = maxDistance;
    }

    public Double getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(Double maxHeight) {
        this.maxHeight = maxHeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
    //endregion
}
