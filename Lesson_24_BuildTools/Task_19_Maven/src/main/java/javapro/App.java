package javapro;

import javapro.basemath.MathAddSub;
import javapro.basemath.MathMulDiv;

public class App {
    public static void main(String[] args) {
        Double argument0 = Double.parseDouble(args[0]);
        Double argument1 = Double.parseDouble(args[1]);

        MathAddSub mathAddSub = new MathAddSub();
        MathMulDiv mathMulDiv = new MathMulDiv();

        String result = "Result of math operations for input arguments " +
                argument0 + " and " + argument1 + ": " + System.lineSeparator() +
                "Add: " + mathAddSub.add(argument0, argument1).toString() + System.lineSeparator() +
                "Sub: " + mathAddSub.sub(argument0, argument1).toString() + System.lineSeparator() +
                "Mul: " + mathMulDiv.mul(argument0, argument1).toString() + System.lineSeparator() +
                "Div: " + mathMulDiv.div(argument0, argument1).toString() + System.lineSeparator();

        System.out.println(result);
    }
}
