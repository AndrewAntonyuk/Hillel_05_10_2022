package javapro.basemath;

public class MathMulDiv {
    public Double mul(Double mull1, Double mull2) {
        return mull1 * mull2;
    }

    public Double div(Double dividend, Double divisor) {
        if (divisor != 0.0) {
            return dividend / divisor;
        } else {
            throw new IllegalArgumentException("Divisor can't be 0");
        }
    }
}
