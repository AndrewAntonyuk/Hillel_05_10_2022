import hw_3_IntroduceOOP.Car;
import hw_3_IntroduceOOP.Employee;

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee("sdsdc", "acsdc", "sdcsdc", "ijcdj", "sdcsdcsdc", "sdcsdc", 45);
        System.out.println(employee.toString());

        Employee employee1 = new Employee();
        System.out.println(employee1.toString());

        Car car = new Car();
        car.start();
    }
}
