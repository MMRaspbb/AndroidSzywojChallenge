package szywoj.co.utils;

public class SquareFormula {
    public static double positiveZeroPlace(double A, double B, double C){
        double delta = B*B-4*A*C;
        if(delta > 0) {
            double result1 = (-B - Math.sqrt(delta)) / (2 * A);
            double result2 = (-B + Math.sqrt(delta)) / (2 * A);
            return Math.max(result1, result2);
        } else if (delta == 0) {
            return -B/(2*A);
        }
        return 0;
    }
}
