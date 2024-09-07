public class ReverseString {

    public String reverseString(String input) {
        if (input.equals("")) {
            return "";
        }
        return reverseString(input.substring(1)) + input.charAt(0);
    }

    public static void main(String[] args) {
        ReverseString t = new ReverseString();
        System.out.println(t.reverseString("Hello World!"));
    }
    
}
