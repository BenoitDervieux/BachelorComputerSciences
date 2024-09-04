public class Circle extends Shape {
    private double radius;
    private double area;
    Circle(double number) {
        this.radius = number;
        double area = 3.14159 * number * number;
        this.setArea(area);
    }
    public void setArea(double number) {this.area = number;};
    public double getArea() {return area;};
}
