package it.unibo.chatgpt;

import java.awt.event.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.geometry.Point2D;

public class MouseListenerImpl implements MouseMotionListener, MouseListener {

    private static final int INCREASE_RATE = 2;
    private static final int MAX_RANGE = 100;

    protected static boolean isAnimationGoing;
    private boolean hammerSmashed;
    private boolean hammerIsSmashing;
    private boolean onScreen;
    private int hammerIncrease;
    private int hammerRange;
    private ScheduledExecutorService animationTime;
    private Point2D hammerLocation;

    public MouseListenerImpl() {
        hammerSmashed = false;
        onScreen = false;
        hammerIsSmashing = false;
        hammerRange = 0;
        MouseListenerImpl.isAnimationGoing = false;
        animationTime = Executors.newSingleThreadScheduledExecutor();
        hammerIncrease = 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!hammerIsSmashing) {
            hammerIsSmashing = true;
            animationTime.schedule(hammerExpands(), 300, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!hammerSmashed){
            hammerSmashed = true;
            hammerIncrease = 0;
            this.hammerLocation = new Point2D(e.getX(), e.getY());
            animationGoing();
            if (QuadratoGUI.hammerHitBox.isColliding(QuadratoGUI.moleHitBox)) {
                System.out.println(QuadratoGUI.moleHitBox.getCenter());
            }
            animationTime.schedule(animation(), 500, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        onScreen = true;
        System.out.println(onScreen);
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!hammerSmashed && onScreen){
            this.hammerLocation = new Point2D(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!hammerSmashed && onScreen){
            this.hammerLocation = new Point2D(e.getX(), e.getY());
        }
    }



    private Runnable hammerExpands() {
        return new Runnable() {

            @Override
            public void run() {
                if (!hammerSmashed){
                    hammerIncrease = INCREASE_RATE;
                }
            } 
        };
    }

    private Runnable animation() {
        return new Runnable() {
            @Override
            public void run() {
                hammerRange = 0;
                animationDone();
            }
        };
    }

    private void animationGoing() {
        KeyListenerImpl.moveY = 0;
        KeyListenerImpl.moveX = 0;
        MouseListenerImpl.isAnimationGoing = true;
    }

    private void animationDone() {
        KeyListenerImpl.moveY = 0;
        KeyListenerImpl.moveX = 0;
        MouseListenerImpl.isAnimationGoing = false;
        this.hammerSmashed = false;
        this.hammerIsSmashing = false;
    }

    public void setHammerRange() {
        if (hammerRange <= MAX_RANGE){
            hammerRange += hammerIncrease;
        }
    }

    public int getHammerRange() {
        return this.hammerRange;
    }

    public Point2D getHammerLocation() {
        return this.hammerLocation;
    }

    public boolean getMouseOnScreen() {
        return this.onScreen;
    }
}
