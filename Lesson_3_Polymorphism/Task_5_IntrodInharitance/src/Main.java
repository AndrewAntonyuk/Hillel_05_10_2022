import hw_5_Animal.*;

public class Main {
    public static void main(String[] args) {
        Animal cat = new Cat("Snow");
        Cat cat1 = new Cat();
        Dog dog = new Dog("Rex");
        Animal dog1 = new Dog();
        Dog dog2 = new Dog("Bask");

        cat.run(50);
        cat1.run(300);
        cat.swim(50);

        dog.run(800);
        dog1.run(350);
        dog1.swim(5);

        System.out.println("Animals was created: " + Animal.counter);
        System.out.println("Dogs was created: " + Dog.getInstanceCounter());
        System.out.println("Cats was created: " + Cat.getInstanceCounter());
    }
}
