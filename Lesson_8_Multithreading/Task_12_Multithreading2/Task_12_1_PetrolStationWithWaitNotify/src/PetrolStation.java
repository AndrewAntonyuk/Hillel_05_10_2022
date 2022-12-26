import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class PetrolStation {
    private final Queue<RefuelRequest> queueCheckedRequests = new LinkedList<>();
    private final Queue<RefuelRequest> queueConcurrentRequests = new LinkedList<>();
    private int maximumRequests = 3;
    private int counterRequests = 0;
    private int numberRequests = 0;
    private double amountAvailableFuel = 0.0;
    private double amountRequestedFuel = 0.0;

    public PetrolStation(int maximumRequests, double amountAvailableFuel) {
        this.maximumRequests = maximumRequests;
        this.amountAvailableFuel = amountAvailableFuel;
    }

    public void doRefuel(Double subtractFuel) {
        checkRequest(subtractFuel);

        updateConcurrentRequests();
        processRequest();
    }

    private void checkRequest(double subtractFuel) {
        if (amountRequestedFuel + subtractFuel > amountAvailableFuel) {
            System.out.println("Amount fuel " + subtractFuel + " for refuel #" + numberRequests + " greater than available amount of the fuel");
        } else {
            int refuelingTime = (new Random().nextInt(10000 - 3000) + 3000);

            queueCheckedRequests.offer(new RefuelRequest(numberRequests, subtractFuel, refuelingTime));
            amountRequestedFuel += subtractFuel;
        }

        numberRequests++;
    }

    private void updateConcurrentRequests() {
        new Thread(() -> {
            while (!queueCheckedRequests.isEmpty()) {
                synchronized (queueConcurrentRequests) {
                    while (counterRequests >= maximumRequests) {
                        try {
                            queueConcurrentRequests.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    RefuelRequest currentRequest = queueCheckedRequests.poll();
                    if (currentRequest != null) {
                        System.out.println("Refuel process started for request " + currentRequest);
                        queueConcurrentRequests.add(currentRequest);
                        counterRequests++;
                    }
                }
            }
        }) {
        }.start();
    }

    private void processRequest() {
        new Thread(() -> {
            while (!queueCheckedRequests.isEmpty() || !queueConcurrentRequests.isEmpty()) {
                RefuelRequest currentRequest;
                synchronized (queueConcurrentRequests) {
                    currentRequest = queueConcurrentRequests.poll();
                }

                if (currentRequest != null) {
                    try {
                        Thread.sleep(currentRequest.getRefuelingTime());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    synchronized (queueConcurrentRequests) {
                        counterRequests--;
                        amountAvailableFuel -= currentRequest.getRequestedFuel();
                        queueConcurrentRequests.notifyAll();
                        System.out.println("Refuel process finished for request " + currentRequest + ". Available fuel: " + amountAvailableFuel);
                    }
                }
            }
        }) {
        }.start();
    }

    private static class RefuelRequest {
        private int ID;
        private double requestedFuel;
        private int refuelingTime;

        public RefuelRequest(int ID, double requestedFuel, int refuelingTime) {
            this.ID = ID;
            this.requestedFuel = requestedFuel;
            this.refuelingTime = refuelingTime;
        }

        @Override
        public String toString() {
            return "{ "
                    + "ID: " + ID
                    + "; requested fuel: " + requestedFuel
                    + "; refueling time: " + refuelingTime
                    + "}";
        }

        public int getID() {
            return ID;
        }

        public double getRequestedFuel() {
            return requestedFuel;
        }

        public int getRefuelingTime() {
            return refuelingTime;
        }
    }
}
