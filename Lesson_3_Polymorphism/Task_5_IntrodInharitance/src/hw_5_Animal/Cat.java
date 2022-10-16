package hw_5_Animal;

public class Cat extends Animal{
    private static Integer instanceCounter = 0;

    //region Constructors
    public Cat() {
        super("hw_5_Inheritance.Cat");
        maximumRunning = 200;
        maximumSwimming = -1;
        instanceName = "Unnamed";
        instanceCounter ++;
    }

    public Cat(String Name){
        this();
        instanceName = Name;
    }
    //endregion

    @Override
    public void run(Integer distance) {
        if(maximumRunning <= 0){
            System.out.println(super.getName() + " can't running");
        }else if(distance <= maximumRunning){
            System.out.println(super.getName() + " with name " + instanceName
                    + " ran " + distance + " m.");
        }else{
            System.out.println(super.getName() + " with name " + instanceName
                    + " ran " + maximumRunning + " m. from "
                    + distance + " Cause it is maximum");
        }
    }

    @Override
    public void swim(Integer distance) {
        if(maximumSwimming <= 0){
            System.out.println(super.getName() + " can't swimming");
        }else if(distance <= maximumSwimming){
            System.out.println(super.getName() + " with name " + instanceName
                    + " swum " + distance + " m.");
        }else{
            System.out.println(super.getName() + " with name " + instanceName
                    + " swum " + maximumRunning + " m. from "
                    + distance + " Cause it is maximum");
        }
    }

    //region Getters/Setters
    public static Integer getInstanceCounter() {
        return instanceCounter;
    }
    //endregion
}