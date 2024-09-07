public class MergeSort {
    public static void mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    public static void merge(int[] arr, int start, int mid, int end) {
        // build temp array to avoid modifying the original content
        int[] temp = new int[end - start + 1];

        int i = start, j = mid + 1, k = 0;
        // While both sub-arrays have values, then try and merge them in sorted order
        while( i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        // add the rest of the value from the left sub-array into the result
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        // Add the rest of the value from the right sub array into the result
        while(j <= end) {
            temp[k++] = arr[j++];
        }

        for (i = start; i <= end; i++) {
            arr[i] = temp[i - start];
        }
    }
    

    public static void main(String[] args) {

        int[] data = new int[] {-5, 20, 10, 3, 2, 0};
        mergeSort(data, 0, data.length - 1);
        System.out.println("Stop");
        for (int e : data ) {
            System.out.println(e);
        }
    }
}
