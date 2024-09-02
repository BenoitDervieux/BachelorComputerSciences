import java.util.ArrayList;
import java.util.Random;

class Exercice1_1 {

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
        if (arr.size() > k) {
            for (int j = k; j < arr.size(); j++) {
                if (arr.get(j) > knumber.get(knumber.size() - 1)) {
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

        Random rand = new Random();
        int k = 5;
        // Instantiate the array list
        ArrayList<Integer> array = new ArrayList<Integer>();
        // Generate 100 000 numbers
        for (int i = 0; i < 100000; i++) {
            array.add(rand.nextInt(1000000));
        }
        // Copy the array list
        ArrayList<Integer> array2 = new ArrayList<Integer>();
        for (int i = 0; i < 100000; i++) {
            array2.add(rand.nextInt(1000000));
        }

        // The first sorting
        PageOne pageOne = new PageOne();
        long startTime = System.currentTimeMillis();
        ArrayList<Integer> result = pageOne.sortingDecreasing(array);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("First 5th in decreasing : ");
        for (int i = 0; i < k; i++) {
            System.out.println(result.get(i));
        }
        System.out.println("It took : " + estimatedTime + " to run.");
        System.out.println("");

        // The second sorting
        PageOne pageTwo = new PageOne();
        
        startTime = System.currentTimeMillis();
        result = pageTwo.sortFive(array2, k);
        estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Second 5th in decreasing : ");
        for (int i = 0; i < k; i++) {
            System.out.println(result.get(i));
        }
        System.out.println("It took : " + estimatedTime + " to run.");


        System.out.println("End!");
    }
}