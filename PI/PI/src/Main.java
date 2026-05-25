//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int n = 100000;
        double piDigit = bbp(n);
        int digit = (int) (piDigit * 16);

        System.out.println(
                Integer.toHexString(digit)
                        .toUpperCase()
        );
    }

    static double bbp(int n) {
        double x =
                  4 * sum(1, n)
                - 2 * sum(4, n)
                -     sum(5, n)
                -     sum(6, n);

        return x - Math.floor(x);
    }

    static double sum(int j, int n) {
        double s = 0;

        for (int k = 0; k <= n; k++) {
            int r = 8 * k + j;
            s += (double)
            modPow(16, n-k, r) / r;
            s -= Math.floor(s);
        }
        return s;
    }

    static long modPow(long base,
                       long exp,
                       long mod) {

        long result = 1;

        base %= mod;

        while (exp > 0) {
            if((exp & 1) == 1) {
                result =
                        (result * base) % mod;

            }

            base =
                    (base * base) % mod;

            exp >>= 1;
        }

        return result;
    }
}