import java.awt.Color;

public class Circle {
    private double xpos, ypos, radius;
    private Color color;

    public Circle(double x, double y, double r) {
        xpos = x;
        ypos = y;
        radius = r;
        color = Color.BLACK;
    }

    public double calculatePerimeter() {
        return (2 * Math.PI * radius);
    }

    public double calculateArea(){
        return (Math.PI * Math.pow(radius, 2));
    }

    public void setColor(Color newColor){
        color = newColor;
    }

    public void setPos(double newXPos, double newYPos){
        xpos = newXPos;
        ypos = newYPos;
    }

    public void setRadius(double newRadius){
        radius = newRadius;
    }

    public Color getColor(){
        return color;
    }

    public double getXPos(){
        return xpos;
    }

    public double getYPos(){
        return ypos;
    }

    public double getRadius(){
        return radius;
    }
}
// Written by Jeffrey Do, do000043