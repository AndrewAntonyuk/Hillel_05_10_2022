package hw_6_Barricades;

import hw_6_Members.Member;

public class Barricade {
    private String name;
    protected Double limit;
    protected String instanceName;

    //region Constructors
    public Barricade(String name) {
        this.name = name;
    }
    //endregion

    public Boolean overcome(Member m){
        System.out.println("Overcome");
        return false;
    }

    //region Getters/Setters
    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
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
