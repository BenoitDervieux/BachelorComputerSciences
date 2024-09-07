public class HelloMyFriends {

    public String A() {
        return "hello " + B();
    }
    public String B() {
        return "my " + C();
    }
    public String C() {
        return "friends.";
    }
    public static void main(String[] args) {
        HelloMyFriends t = new HelloMyFriends();
        System.out.println(t.A());
    }
}
