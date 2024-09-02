public class Recursion {

    public static int function(int x) {
        if (x == 0)
            return 0;
        else 
            return 2 * function(x - 1) + x* x;
    }

    public static void main(String[] args) {
        System.out.println("Beginning...");

        int p = 2;
        p = function(p);
        System.out.println("P is now : " + p);
        
        System.out.println("End!");
    }
    
}
