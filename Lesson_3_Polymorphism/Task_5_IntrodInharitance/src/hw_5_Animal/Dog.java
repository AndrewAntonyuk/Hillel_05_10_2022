package hw_5_Animal;

public class Dog extends Animal{
    private static Integer instanceCounter = 0;

    //region Constructors
    public Dog() {
        super("hw_5_Inheritance.Dog");
        maximumRunning = 500;
        maximumSwimming = 10;
        Dog.instanceCounter ++;
    }

    public Dog(String Name){
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