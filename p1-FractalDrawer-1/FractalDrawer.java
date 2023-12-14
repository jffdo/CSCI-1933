// FractalDrawer class draws a fractal of a shape indicated by user input
import java.awt.Color;
import java.util.Scanner;

public class FractalDrawer {
    private double totalArea=0;  // member variable for tracking the total area

    public FractalDrawer() {}  // contructor


    // drawFractal creates a new Canvas object
    // and determines which shapes to draw a fractal by calling appropriate helper function
    // drawFractal returns the area of the fractal
    public double drawFractal(String type) {
        Canvas drawing = new Canvas(800, 800);
        switch (type){
            case "triangle":
                drawTriangleFractal(300, 300, 400- (300/2), 400, Color.blue, drawing, 7);
                break;
            case "circle":
                drawCircleFractal(100, 400, 400, Color.blue, drawing, 7);
                break;
            case "rectangle":
                drawRectangleFractal(200, 200, 400-(200/2), 400-(200/2), Color.blue, drawing, 7);
                break;
            default:
                System.out.println("Invalid shape name");
                break;
        }
        return totalArea;
    }


    // drawTriangleFractal draws a triangle fractal using recursive techniques
    public void drawTriangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level) {
        if (level == 1) {
            Triangle myTriangle = new Triangle(x, y, width, height);
            myTriangle.setColor(c);
            totalArea += myTriangle.calculateArea();
            can.drawShape(myTriangle);
        } else {
            Color[] newColor = {(Color.RED), (Color.BLUE), (Color.GREEN)};
            Triangle myTriangle = new Triangle(x, y, width, height);
            myTriangle.setColor(c);
            totalArea += myTriangle.calculateArea();
            can.drawShape(myTriangle);
            drawTriangleFractal(width/2, height/2, x+(width/4), y+(height/2), newColor[(level-1)%3], can, level-1);
            drawTriangleFractal(width/2, height/2, x-(width/4), y-(height/2), newColor[(level-1)%3], can, level-1);
            drawTriangleFractal(width/2, height/2, x+(3*width/4), y-(height/2), newColor[(level-1)%3], can, level-1);
        }
    }


    // drawCircleFractal draws a circle fractal using recursive techniques
    public void drawCircleFractal(double radius, double x, double y, Color c, Canvas can, int level) {
        if (level == 1) {
            Circle myCircle = new Circle(x, y, radius);
            myCircle.setColor(c);
            totalArea += myCircle.calculateArea();
            can.drawShape(myCircle);
        } else {
            Color[] newColor = {(Color.RED), (Color.BLUE), (Color.GREEN)};
            Circle myCircle = new Circle(x, y, radius);
            myCircle.setColor(c);
            totalArea += myCircle.calculateArea();
            can.drawShape(myCircle);
            drawCircleFractal(radius/2, x-radius, y, newColor[(level-1)%3], can, level-1);
            drawCircleFractal(radius/2, x+radius, y, newColor[(level-1)%3], can, level-1);
            drawCircleFractal(radius/2, x, y-radius, newColor[(level-1)%3], can, level-1);
            drawCircleFractal(radius/2, x, y+radius, newColor[(level-1)%3], can, level-1);
        }
    }


    // drawRectangleFractal draws a rectangle fractal using recursive techniques
    public void drawRectangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level) {
        if (level == 1) {
            Rectangle myRectangle = new Rectangle(x, y, width, height);
            myRectangle.setColor(c);
            totalArea += myRectangle.calculateArea();
            can.drawShape(myRectangle);
        } else {
            Color[] newColor = {(Color.RED), (Color.BLUE), (Color.GREEN)};
            Rectangle myRectangle = new Rectangle(x, y, width, height);
            myRectangle.setColor(c);
            totalArea += myRectangle.calculateArea();
            can.drawShape(myRectangle);
            drawRectangleFractal(width/2, height/2, x-width/4, y-height/4, newColor[(level-1)%3], can, level-1);
            drawRectangleFractal(width/2, height/2, x-width/4, y+(3*height/4), newColor[(level-1)%3], can, level-1);
            drawRectangleFractal(width/2, height/2, x+(3*width/4), y-height/4, newColor[(level-1)%3], can, level-1);
            drawRectangleFractal(width/2, height/2, x+(3*width/4), y+(3*height/4), newColor[(level-1)%3], can, level-1);
        }
    }

    // main should ask user for shape input, and then draw the corresponding fractal.
    // should print area of fractal
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        FractalDrawer fract = new FractalDrawer();
        System.out.print("Type 'triangle', 'circle', or 'rectangle' to draw a fractal of the desired shape: ");
        String shape = s.nextLine();
        System.out.println(fract.drawFractal(shape));
        s.close();
    }
}
// Written by Jeffrey Do, do000043