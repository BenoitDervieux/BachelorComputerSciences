public abstract class Shape implements Comparable<Shape> {
    private double area;
    public void setArea(double number) {this.area = number;};
    public double getArea() {return area;};
    @Override 
    public int compareTo(Shape t) {
        if (this.area > t.getArea())
            return 1;
        else if (this.area < t.getArea())
            return -1;
        else 
            return 0;
    };
}