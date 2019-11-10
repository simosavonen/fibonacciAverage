package fi.simosavonen.fiboavg;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Main {

    public static void main(String[] args) {
        FibonacciAverage fa = new FibonacciAverage();
        System.out.println("fibonacci sequence: 1, 1, 2, 3, 5, 8, 13, 21, ...");
        System.out.println("n = 2, (1+1) / 2 = " + fa.fibonacciAverage(2));
        System.out.println("n = 3, (1+1+2) / 3 = " + fa.fibonacciAverage(3));
        System.out.println("n = 4, (1+1+2+3) / 4 = " + fa.fibonacciAverage(4));
        System.out.println("n = 5, (1+1+2+3+5) / 5 = " + fa.fibonacciAverage(5));
        System.out.println("n = 6, (1+1+2+3+5+8) / 6 = " + fa.fibonacciAverage(6));
        //System.out.println("big integers cause overflow: " + fa.fibonacciAverage(50));

        System.out.println("n = 2, (1+1) / 2 = " + fa.bigFibonacciAverage(2));
        System.out.println("n = 3, (1+1+2) / 3 = " + fa.bigFibonacciAverage(3));
        System.out.println("n = 4, (1+1+2+3) / 4 = " + fa.bigFibonacciAverage(4));
        System.out.println("n = 5, (1+1+2+3+5) / 5 = " + fa.bigFibonacciAverage(5));
        System.out.println("n = 6, (1+1+2+3+5+8) / 6 = " + fa.bigFibonacciAverage(6));
        System.out.println("n = 50, " + fa.bigFibonacciAverage(50));
    }
}

class FibonacciAverage {
    public float fibonacciAverage(int n) {

        if(n == 0) return 0;
        if(n == 1) return 1.0f;

        int[] fs = new int[n]; // fibonacci sequence
        fs[0] = 1;
        fs[1] = 1;
        int sum = 2;

        for(int i = 2; i < n; i++) {
            fs[i] = fs[i - 1] + fs[i - 2];
            sum += fs[i];
            if(fs[i] < 0 || sum < 0) {
                throw new IllegalArgumentException("The integer given as argument caused an overflow error.");
            }
        }

        return (float) sum / n;
    }

    public BigDecimal bigFibonacciAverage(int n) {

        if(n == 0) return BigDecimal.ZERO;
        if(n == 1) return BigDecimal.ONE;

        // [ 1,  1,  2,  3,  5,  8,  13, ... ]
        // [n0, n1, n2, ...]
        BigInteger n0 = BigInteger.ONE;
        BigInteger n1 = BigInteger.ONE;
        BigInteger n2 = n0.add(n1);
        BigInteger total = n2;

        for(int i = 3; i <= n; i++) {
            total = total.add(n2);
            n0 = n1;
            n1 = n2;
            n2 = n1.add(n0);
        }

        BigDecimal result = new BigDecimal(total);
        return result.divide(BigDecimal.valueOf(n), 3, RoundingMode.HALF_UP);

    }

}
