package it.unibo.chatgpt;
/**
 * interface made for the HitBoxes.
 */

//import it.unibo.chatgpt.CircleHB;
//import it.unibo.chatgpt.RectangleHB;
import javafx.geometry.Point2D;
import java.awt.Graphics;

/**
 * interface for the hitboxes shapes.
 */
public interface HitBox {

    /**
     * returns the center of the shape.
     * @return center
     */
    Point2D getCenter();

    /**
     * sets the center of the shape with the new parameter.
     * @param newCenter
     */
    void setCenter(Point2D newCenter);

    /**
     * checks if the shapes are colliding.
     * @param circle
     * @return if the two shapes are colliding
     */
    boolean isColliding(CircleHB circle);

    /**
     * checks if the two shapes are colliding.
     * @param rectangle
     * @return if the two shapes are colliding
     */
    boolean isColliding(RectangleHB rectangle);

    boolean isColliding(HitBox shape);

    void drawHitBox(Graphics g);

}
