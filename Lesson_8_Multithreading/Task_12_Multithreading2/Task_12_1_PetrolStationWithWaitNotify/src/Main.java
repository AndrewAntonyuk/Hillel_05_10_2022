public class Main {
    public static void main(String[] args) {
        PetrolStation petrolStation = new PetrolStation(3, 100.0);

        petrolStation.doRefuel(10.0);
        petrolStation.doRefuel(11.0);
        petrolStation.doRefuel(12.0);
        petrolStation.doRefuel(200.0);
        petrolStation.doRefuel(2000.0);
        petrolStation.doRefuel(200000.0);
        petrolStation.doRefuel(13.0);
        petrolStation.doRefuel(14.0);
        petrolStation.doRefuel(15.0);
    }
}
