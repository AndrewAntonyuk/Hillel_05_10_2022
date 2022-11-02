import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        PetrolStation station = new PetrolStation(2000.0f);

        System.out.println("Fuel before refuels: " + station.getAmount());

        station.doRefuel(10.0f);
        station.doRefuel(20.0f);
        station.doRefuel(30.0f);
        station.doRefuel(40.0f);
        station.doRefuel(50.0f);
        station.doRefuel(60.0f);

        station.getExecutor().shutdown();

        System.out.println("Refuels are processing...");

        while (!station.getExecutor().isTerminated()) {
        }

        System.out.println("Remained fuel after all refuels: " + station.getAmount());
    }
}
