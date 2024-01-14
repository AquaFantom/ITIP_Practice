// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Test1 {
    public static void main(String[] args) {
        System.out.println(convert(5));
        System.out.println(convert(3));
        System.out.println(convert(8));
        System.out.println(" ");

        System.out.println(fitCalc(15, 1));
        System.out.println(fitCalc(24, 2));
        System.out.println(fitCalc(41, 3));
        System.out.println(" ");

        System.out.println(containers(3, 4, 2));
        System.out.println(containers(5, 0, 2));
        System.out.println(containers(4, 1, 4));
        System.out.println(" ");

        System.out.println(triangleType(5, 5, 5));
        System.out.println(triangleType(5, 4, 5));
        System.out.println(triangleType(3, 4, 5));
        System.out.println(triangleType(5, 1, 1));
        System.out.println(" ");

        System.out.println(ternaryEvaluation(8, 4));
        System.out.println(ternaryEvaluation(1, 11));
        System.out.println(ternaryEvaluation(5, 9));
        System.out.println(" ");

        System.out.println(howManyItems(22, 1.4, 2));
        System.out.println(howManyItems(45, 1.8, 1.9));
        System.out.println(howManyItems(100, 2, 2));
        System.out.println(" ");

        System.out.println(factorial(3));
        System.out.println(factorial(5));
        System.out.println(factorial(7));
        System.out.println(" ");

        System.out.println(gcd(48, 18));
        System.out.println(gcd(52, 8));
        System.out.println(gcd(259, 28));
        System.out.println(" ");

        System.out.println(ticketSeller(70, 1500));
        System.out.println(ticketSeller(24, 950));
        System.out.println(ticketSeller(53, 1250));
        System.out.println(" ");

        System.out.println(tables(5, 2));
        System.out.println(tables(31, 20));
        System.out.println(tables(123, 58));
    }

    //1
    public static double convert(int gal) {
        return (double) gal * 3.785;
    }

    //2
    public static int fitCalc(int time, int intens){
        return time*intens;
    }

    //3
    public static int containers(int k, int m, int b){
        int k_c = 20;
        int m_c = 50;
        int b_c = 100;

        return k * k_c + m * m_c + b * b_c;
    }

    //4
    public static String triangleType(int x, int y, int z){
        if ((x + y <= z) || (x + z <= y) || (y + z <= x)){
            return "not a triangle";
        } else if (x == y && x == z){
            return "isosceles";
        } else if ((x == y) || (x == z) || (y == z)){
            return "equilateral";
        } else if (x != y && x != z && y != z){
            return "differen-sided";
        }
        return " ";
    }

    //5
    public static int ternaryEvaluation(int a, int b){
        return (a > b) ? a : b;
    }

    //6
    public static int howManyItems(double length_all, double width, double length){
        return Integer.parseInt(String.format("%.0f", Math.floor(length_all / (width * length * 2))));
    }

    //7
    public static int factorial(int x) {
            int result = 1;
        for(int i = 1; i < x + 1; i++) {
            result = result * i;
        }
        return result;
    }

    //8
    public static int gcd(int a, int b) {
        for(int i = 10000; ; i--) {
            if (a % i == 0 && b % i == 0) {
                return i;
            }
        }
    }

    //9
    public static int ticketSeller(int t_amount, int t_price) {
        return (int) (t_amount * t_price * 0.72);
    }

    //10
    public static int tables(int students, int tables_amount) {
        int additional_tables = 0;
        while(tables_amount * 2 - students < 0) {
            tables_amount += 1;
            additional_tables += 1;
        }
        return additional_tables;
    }
}