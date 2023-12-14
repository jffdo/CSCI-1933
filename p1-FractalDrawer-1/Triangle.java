import java.awt.Color;

public class Triangle {
    private double xpos, ypos, width, height;
    private Color color;

    public Triangle(double x, double y, double w, double h) {
        xpos = x;
        ypos = y;
        width = w;
        height = h;
        color = Color.BLACK;
    }

    public double calculatePerimeter(){
        return width + (2 * Math.sqrt(Math.pow((width/2), 2) + Math.pow(height, 2)));
    }

    public double calculateArea(){
        return ((width * height) / 2);
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