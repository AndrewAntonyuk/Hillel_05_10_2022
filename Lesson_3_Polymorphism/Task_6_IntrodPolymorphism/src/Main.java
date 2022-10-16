import hw_6_Barricades.*;
import hw_6_Geometry.*;
import hw_6_Members.*;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();

        //region Geometry shapes
        Collection<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(10.0));
        shapes.add(new Triangle(10.0, 10.0));
        shapes.add(new Square(10.0));

        main.summaryArea(shapes);
        //endregion

        //region Members and barricades
        Collection<Member> members = new ArrayList<>();
        Collection<Barricade> barricades = new ArrayList<>();

        members.add(new Cat("Snow",300.0,1.8));
        members.add(new Human("Robin",100.0,2.1));
        members.add(new Robot("Dron",500.0,0.5));
        barricades.add(new Racetrack("Hardway",150.0));
        barricades.add(new Wall("Bestwall",1.7));
        barricades.add(new Racetrack("Endwall",80.0));

        main.doSport(members, barricades);
        //endregion
    }

    public void summaryArea(Collection<Shape> s) {
        Double sum = 0.0;
        for (Shape shape : s) {
            Double area = shape.area();
            System.out.println("Area of " + shape.getClass() + " is " + area);
            sum += area;
        }
        System.out.println("Summary area is " + sum);
    }

    public void doSport(Collection<Member> m, Collection<Barricade> b) {
        for(Member member : m){
            for(Barricade barricade : b){
                if(barricade.overcome(member)){
                    break;
                }
            }
        }
    }
}
