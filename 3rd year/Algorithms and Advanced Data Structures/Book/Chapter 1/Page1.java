import java.util.ArrayList;

class PageOne {

    public ArrayList<Integer> sortingDecreasing(ArrayList<Integer> arr) {
        Integer maximum, buff;
        int arrIndex = 0;
        for (int i = 0; i < arr.size(); i++) {
            maximum = arr.get(i);
            for (int j = i; j < arr.size(); j++) {
                buff = arr.get(j);
                if (buff > maximum) {
                    maximum = buff;
                    arrIndex = j;
                }
            }
            buff = arr.get(arrIndex);
            arr.set(arrIndex, arr.get(i));
            arr.set(i, buff);
        }
        return arr;
    }

    public ArrayList<Integer> sortFive(ArrayList<Integer> arr, int k) {
        ArrayList<Integer> knumber = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (arr.size() >= i + 1) {
                knumber.add(arr.get(i));
            }    
        }
        PageOne needed = new PageOne();
        knumber = needed.sortingDecreasing(knumber);
        System.out.println("In the method : ");
        for (int i = 0; i < k; i++) {
            System.out.println(knumber.get(i));
        }

        if (arr.size() > k) {
            for (int j = k; j < arr.size(); j++) {
                if (arr.get(j) > knumber.get(knumber.size() - 1)) {
                    System.out.println("Five size : " + knumber.size());
                    knumber.remove(knumber.size() - 1);
                    for (int p = 0; p < knumber.size(); p++) {
                        if (arr.get(j) < knumber.get(knumber.size() - 1)) {
                            knumber.add(knumber.size(), arr.get(j));
                            break;
                        }
                        if (arr.get(j) > knumber.get(p)) {
                            knumber.add(p, arr.get(j));
                            break;
                        }
                    }
                }

            }
        }
        return knumber;
    }

    public static void main(String[] args) {
        System.out.println("Beggining...");

        // Instantiate the array list
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(1);array.add(12);array.add(6);array.add(51);array.add(23);array.add(40);array.add(19);array.add(15);array.add(70);array.add(50);
        array.add(88);array.add(42);array.add(87);array.add(56);array.add(75);array.add(77);array.add(45);array.add(14);array.add(34);array.add(86);
        // 1,12,6,51,23,40,19,15,70,50,88,42,87,56,75,77,45,14,34,86
        // Sorted increasing : 1, 6, 12, 14, 15, 19, 23, 34, 40, 42, 45, 50, 51, 56, 70, 75, 77, 86, 87, 88
        
        // Copy the array list
        ArrayList<Integer> array2 = new ArrayList<Integer>();
        for (Integer i : array) {
            array2.add(i);
        }

        // The first sorting
        PageOne pageOne = new PageOne();
        ArrayList<Integer> result = pageOne.sortingDecreasing(array);
        for (Integer r : result) {
            System.out.println(r);
        }
        System.out.println("First 5th in decreasing : ");
        for (int i = 0; i < 5; i++) {
            System.out.println(result.get(i));
        }
        System.out.println("");

        // The second sorting
        PageOne pageTwo = new PageOne();
        int k = 4;
        result = pageTwo.sortFive(array2, 4);
        System.out.println("Second 5th in decreasing : ");
        for (int i = 0; i < k; i++) {
            System.out.println(result.get(i));
        }


        System.out.println("End!");
    }
}