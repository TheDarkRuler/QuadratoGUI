package it.unibo.chatgpt;

//import it.unibo.chatgpt.HitBox;
import javafx.geometry.Point2D;
import java.awt.Color;
import java.awt.Graphics;

/**
 * HitBox for the player's hammer and for the moles.
 */
public class CircleHB implements HitBox {

    private Point2D center;
    private final int radius;
    /**
     * Constructor of the class.
     * @param center
     * @param radius
     */
    public CircleHB(final Point2D center, final int radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * Returns the radius of the circle.
     * @return radius
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * Returns the center of the circle.
     * @return center
     */
    @Override
    public Point2D getCenter() {
        return this.center;
    }

    /**
     * Sets the center of the circle with the new parameter.
     * @param newCenter
     */
    @Override
    public void setCenter(final Point2D newCenter) {
        this.center = newCenter;
    }

    public void drawHitBox(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawOval((int) center.getX() - radius,(int) center.getY() - radius, radius * 2, radius * 2);
    }

    /**
     * Checks if the two circle collides.
     * @param circle
     * @return if the circle collides with the parameter
     */
    @Override
    public boolean isColliding(final CircleHB circle) {
        return this.center.distance(circle.getCenter()) <= this.radius + circle.getRadius();
    }

    /**
     * Checks if the circle collides with a rectangle.
     * @param rectangle
     * @return if the circle collides with the parameter
     */
    @Override
    public boolean isColliding(final RectangleHB rectangle) {
        System.out.println( Math.sqrt(Math.pow(this.distanceX(rectangle), 2) + Math.pow(this.distanceY(rectangle), 2)));

        return this.radius >= Math.sqrt(Math.pow(this.distanceX(rectangle), 2) + Math.pow(this.distanceY(rectangle), 2));
    }

    /**
     * Helps with the calculates.
     * @param rectangle
     * @return distance in the x axis
     */
    private double distanceX(final RectangleHB rectangle) {

        if (this.center.getX() < (rectangle.getEdge().getX() + rectangle.getWidth())) {
            return this.center.getX() - Math.max(this.center.getX(), rectangle.getEdge().getX());
        } else {
            return this.center.getX() - Math.max((rectangle.getEdge().getX() + rectangle.getWidth()), rectangle.getEdge().getX());
        }
    }

    /**
     * Helps with the calculates.
     * @param rectangle
     * @return distance in the y axis
     */
    private double distanceY(final RectangleHB rectangle) {

        if (this.center.getY() < (rectangle.getEdge().getY() + rectangle.getHeight())) {
            return this.center.getY() - Math.max(this.center.getY(), rectangle.getEdge().getY());
        } else {
            return this.center.getY() - Math.max((rectangle.getEdge().getY() + rectangle.getHeight()), rectangle.getEdge().getY());
        }
    }

    @Override
    public boolean isColliding(HitBox shape) {
        return shape.isColliding(this);
    }
}
