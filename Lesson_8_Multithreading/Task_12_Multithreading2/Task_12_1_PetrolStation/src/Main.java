public class Main {
    public static void main(String[] args) {
        PetrolStation station = new PetrolStation(2000.0f);

        station.doRefuel(10.0f);
        station.doRefuel(20.0f);
        station.doRefuel(30.0f);
        station.doRefuel(40.0f);
        station.doRefuel(50.0f);
        station.doRefuel(60.0f);
    }
}
