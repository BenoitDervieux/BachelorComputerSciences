public class Square extends Shape {
    private double side;
    private double area;
    Square(double side) {
        this.side = side;
        double area = side * side;
        this.setArea(area);
    }
    public void setArea(double number) {this.area = number;};
    public double getArea() {return area;};
}