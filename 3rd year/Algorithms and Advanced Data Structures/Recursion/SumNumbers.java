public class SumNumbers {

    public static int recursiveSum(int n) {
        if (n <= 1) {
            return n;
        }
        return recursiveSum(n-1) + n;
    }

    public static void main(String[] args) {
        System.out.println(recursiveSum(10));
    }
    
}
