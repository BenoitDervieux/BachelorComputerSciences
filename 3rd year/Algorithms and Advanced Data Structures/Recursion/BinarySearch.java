public class BinarySearch {

    public static int binarySearch(int[] A, int left, int right, int x) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;

        if (x == A[mid]) {
            return mid;
        }
        if (x < A[mid]) {
            return binarySearch(A, left, mid - 1, x);
        }
        return binarySearch(A, mid + 1, right, x);
    }

    public static void main(String[] args) {
        int[] array = {-1, 0, 1, 2, 3, 4, 7, 9, 10, 20};

        System.out.println(binarySearch(array, 0, array.length, 10));
    }
    
}
