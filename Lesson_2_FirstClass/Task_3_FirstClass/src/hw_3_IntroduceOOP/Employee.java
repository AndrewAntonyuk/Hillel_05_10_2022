package hw_3_IntroduceOOP;

public class Employee {
    //region Internal data about employee
    private String firstName;
    private String middleName;
    private String lastName;
    private String positionInCompany;
    private String eMail;
    private String phoneNumber;
    private Integer age;
    //endregion

    //region Constructors
    public Employee() {
    }

    public Employee(String firstName, String middleName, String lastName, String positionInCompany, String eMail, String phoneNumber, Integer ageOld) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.positionInCompany = positionInCompany;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        this.age = ageOld;
    }
    //endregion

    //region Methods for service
    @Override
    public String toString() {
        return "hw_3_IntroduceOOP.Employee{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", positionInCompany='" + positionInCompany + '\'' +
                ", eMail='" + eMail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", ageOld=" + age +
                '}';
    }
    //endregion

    //region Methods of get
    public String getFirstName(){
        return firstName;
    }
    public String getMiddleName(){
        return middleName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getPositionInCompany(){
        return positionInCompany;
    }
    public String getEMail(){
        return eMail;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public Integer getAgeOld(){
        return age;
    }
    //endregion

    //region Methods of set
    public void setFirstName(String inFirstName){
        firstName = inFirstName;
    }
    public void setMiddleName(String inMiddleName){
        middleName= inMiddleName;
    }
    public void setLastName(String inLastName){
        lastName = inLastName;
    }
    public void setPositionInCompany(String inPositionInCompany){
        positionInCompany = inPositionInCompany;
    }
    public void setEMail(String inEMail){
        eMail = inEMail;
    }
    public void setPhoneNumber(String inPhoneNumber){
        phoneNumber = inPhoneNumber;
    }
    public void setAgeOld(Integer inAgeOld){
        age = inAgeOld;
    }
    //endregion
}
