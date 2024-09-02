import java.util.ArrayList;

public class Exercise12 {

    public static ArrayList<ArrayList<Character>> findHorizWords(ArrayList<ArrayList<Character>> row) {
        ArrayList<ArrayList<Character>> words = new ArrayList<>();
        
        for (int i = 0; i < row.size(); i++) {
            words.add(row.get(i));
            ArrayList<Character> reverse = new ArrayList<>();
            for (int j = row.get(i).size() - 1; j > -1; j--) {
                reverse.add(row.get(i).get(j));
            }
            words.add(reverse);
        }
        return words;
    }

    public static ArrayList<ArrayList<Character>> findVertWords(ArrayList<ArrayList<Character>> row) {
        ArrayList<ArrayList<Character>> words = new ArrayList<>();
        for (int j = 0; j < row.size(); j++) {
            ArrayList<Character> buff = new ArrayList<>();
            for (int i = 0; i < row.size(); i++) {
                buff.add(row.get(i).get(j));
            }
            words.add(buff);
        }
        for (int j = row.size() - 1; j > -1; j--) {
            ArrayList<Character> buff = new ArrayList<>();
            for (int i = row.size() - 1; i > -1; i--) {
                buff.add(row.get(i).get(j));
            }
            words.add(buff);
        }
        return words;
    }
    // Now need to find verticals
    public static ArrayList<ArrayList<Character>> findDiagWords(ArrayList<ArrayList<Character>> row) {
        ArrayList<ArrayList<Character>> words = new ArrayList<>();
        for (int i = 0; i < row.size(); i++) {
            for (int j = 0; j < row.size(); j++) {
                ArrayList<Character> buff = new ArrayList<>();
                buff.add(row.get(i).get(j));
                int p = i;
                int q = j;
                while (p < row.size()  || q < row.size()) {
                    p++;
                    q++;
                    buff.add(row.get(p).get(q));
                } 
                if (buff.size() > 1) {
                    words.add(buff);
                }
            }
        }
        return words;
    }


    public static ArrayList<String> findWordsOnLine(ArrayList<ArrayList<Character>> row) {
        ArrayList<String> words = new ArrayList<>();
        String buff;
        for (ArrayList<Character> s : row) {
            for (int i = 0; i < row.size() - 1; i++) {
                buff = "";
                buff = buff + row.get(i);
                for (int j = i+1; j < row.size(); j++) {
                    buff = buff + row.get(j);
                    words.add(buff);
                }
            }
            for (int i = row.size() - 1; i > 0; i--) {
                buff = "";
                buff = buff + row.get(i);
                for (int j=i-1; j > -1; j--) {
                    buff= buff + row.get(j);
                    words.add(buff);
                }
            }
        }

        return words;
    }

    public static void main(String[] args) {
        Character[] letters = {'t', 'h', 'i', 's', 'w', 'a', 't', 's', 'o', 'a', 'h', 'g', 'f', 'g', 'd', 't'};
        ArrayList<ArrayList<Character>> row = new ArrayList<>();
        // Instantiate the list
        for (int i = 0; i < 4; i++) {
            row.add(new ArrayList<Character>());
        }
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                row.get(i).add(letters[index]);
                index++;
            }
        }

        // Print the list
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j != 0)
                    System.out.print(" - " + row.get(i).get(j) );
                else
                    System.out.print(row.get(i).get(j) );
            }
            System.out.println("");
        }
        ArrayList<ArrayList<Character>> test = new ArrayList<>();
        test = findDiagWords(row);
        for (ArrayList<Character> s : test) {
            System.out.println(s);
        }
    }
    
}
