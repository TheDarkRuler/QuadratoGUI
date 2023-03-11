package it.unibo.chatgpt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import javafx.geometry.Point2D;

public class MoleMoves {

    private static final int MOLE_SPEED = 5;
    
    private HitBox from;
    private HitBox to;
    private double moveX;
    private double moveY;
    private MoleMovement moleToMove;

    public MoleMoves(Point2D from, Point2D to, MoleMovement moleToMove) {
        this.from = new CircleHB(from, MOLE_SPEED);
        this.to = new CircleHB(to, MOLE_SPEED);
        this.moleToMove = moleToMove;
        this.moveX = getMovementSegments(from.getX()) * MOLE_SPEED;
        this.moveY = getMovementSegments(from.getY()) * MOLE_SPEED;
        System.out.println(moveX + "    " + moveY);
        movementOnGoing();
    }

    public void movementOnGoing() {
        Timer movementTimer = new Timer(5, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!from.isColliding(to)) {
                    from.setCenter(from.getCenter().add(from.getCenter().getX() < to.getCenter().getX() ? moveX : -moveY, 0));
                    if (from.getCenter().getY() != to.getCenter().getY()) {
                        from.setCenter(from.getCenter().add(0, from.getCenter().getY() < to.getCenter().getY() ? moveY : -moveY));
                    } else {
                        moleToMove.setMolePosition(from.getCenter());
                    }
                    moleToMove.setMolePosition(from.getCenter());
                }
            }
        });
        movementTimer.start();
    }

    private double getMovementSegments(double temp) {
        return temp / Math.max(from.getCenter().getX(), from.getCenter().getY());
    }
}
