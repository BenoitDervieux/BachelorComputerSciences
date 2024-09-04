public class Rectangle extends Shape {
    private double longueur;
    private double largeur;
    private double area;
    Rectangle(double longueur, double largeur) {
        this.longueur = longueur;
        this.largeur = largeur;
        double area = longueur * largeur;
        this.setArea(area);
    }
    public void setArea(double number) {this.area = number;};
    public double getArea() {return area;};
}