public class Recursion {

    public static int function(int x) {
        if (x == 0)
            return 0;
        else 
            return 2 * function(x - 1) + x* x;
    }

    public static void printOut(int n) {
        if (n >= 10)
            printOut(n/10);
        printDigit(n%10);
        // Or possible to use n = n - (n/10);
    }

    public static void printDigit(int n) {
        System.out.print(n);
    }

    public static void main(String[] args) {
        System.out.println("Beginning...");

        int p = 2;
        p = function(p);
        System.out.println("P is now : " + p);

        int m = 76234;
        printOut(m);
        System.out.println();
        
        System.out.println("End!");
    }
    
}
