package phonebook.recordextend;

import phonebook.record.Record;

public class RecordExtend extends Record {
    private String zip = "Non zip";

    //region Constructors
    public RecordExtend() {
        super();
    }

    public RecordExtend(String name) {
        super(name);
    }

    public RecordExtend(String name, String phone) {
        super(name, phone);
    }

    public RecordExtend(String name, String phone, String zip) {
        this(name, phone);
        this.zip = zip;
    }
    //endregion

    @Override
    public String toString() {
        return "{Name: " + getName() + ", phone: " + getPhone() + ", zip: " + getZip() + "}\n";
    }

    //region Getters/Setters
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
    //endregion
}
