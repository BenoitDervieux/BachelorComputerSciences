#include <stdio.h>
#include <stdlib.h>

void mergeSort(int *array, int start, int size);
void merge(int *array, int start, int mid, int size);
int main() {
    int arr[] = {0,44,4,1,66,3,87,9,102};
    int arr_size = sizeof(arr) / sizeof(arr[0]);
    int start = 0;

    printf("Given array is : \n");
    for (int i = 0; i < arr_size; i++) {
        printf("%d ", arr[i]);
    }
    
    mergeSort(arr, start, arr_size - 1);

    printf("Sorted array is : \n");
    for (int i = 0; i < arr_size; i++) {
        printf("%d ", arr[i]);
    }

    return 0;
}

void mergeSort(int *array, int start, int size) {
    if (start < size) {
        int mid = (size - start) / 2;
        mergeSort(array, start, mid);
        mergeSort(array, mid + 1, size);
        merge(array, start, mid, size);
    }
}

void merge(int *arr, int l, int m, int r)
{
    int i, j, k;
    int n1 = m - l + 1;
    int n2 = r - m;

    // Create temp arrays
    int L[n1], R[n2];

    // Copy data to temp arrays L[] and R[]
    for (i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for (j = 0; j < n2; j++)
        R[j] = arr[m + 1 + j];

    // Merge the temp arrays back into arr[l..r
    i = 0;
    j = 0;
    k = l;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        }
        else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    // Copy the remaining elements of L[],
    // if there are any
    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }

    // Copy the remaining elements of R[],
    // if there are any
    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}