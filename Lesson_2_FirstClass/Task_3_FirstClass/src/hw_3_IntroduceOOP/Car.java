package hw_3_IntroduceOOP;

public class Car {
    //region Internal methods
    private void startElectricity(){
        System.out.println("This is start electricity");
    }

    private void startCommand(){
        System.out.println("This is start command");
    }

    private void startFuelSystem(){
        System.out.println("This is start fuel system");
    }
    //endregion

    //region External methods
    public void start(){
        System.out.println("Start sequence:");

        this.startElectricity();
        this.startCommand();
        this.startFuelSystem();
    }
    //endregion
}
