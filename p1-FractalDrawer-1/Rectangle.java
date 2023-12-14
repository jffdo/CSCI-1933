import java.awt.Color;

public class Rectangle {
    private double xpos, ypos, width, height;
    private Color color;

    public Rectangle(double x, double y, double w, double h) {
        xpos = x;
        ypos = y;
        width = w;
        height = h;
        color = Color.BLACK;
    }

    public double calculatePerimeter(){
        return (2 * height + 2 * width);
    }

    public double calculateArea(){
        return (width * height);
    }

    public void setColor(Color newColor){
        color = newColor;
    }

    public void setPos(double newXPos, double newYPos){
        xpos = newXPos;
        ypos = newYPos;
    }

    public void setHeight(double newHeight){
        height = newHeight;
    }

    public void setWidth(double newWidth){
        height = newWidth;
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

    public double getHeight(){
        return height;
    }

    public double getWidth(){
        return width;
    }
}
// Written by Jeffrey Do, do000043