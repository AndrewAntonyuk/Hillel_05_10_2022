package phonebook;

public class Record {
    private String name;
    private String phone;

    //region Constructors
    public Record() {
    }

    public Record(String name) {
        this.name = name;
    }

    public Record(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    //endregion

    @Override
    public String toString() {
        return "\n{Name: " + getName() + ", phone: " + getPhone() + "}";
    }

    //region Getters/Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    //endregion
}
