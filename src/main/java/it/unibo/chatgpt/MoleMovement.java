package it.unibo.chatgpt;

import java.awt.*;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.*;
import javafx.geometry.Point2D;

public class MoleMovement {

    private static final int FENCE_BORDER = 300;
    private static final int MOLE_SIZE = 20;
    
    private int quarterWidth;
    private int quarterHeight;
    private int widthFence;
    private int heightFence;
    private Point2D molePosition;
    private MoleMoves movement;
    private Point2D moleNextPosition;
    private int moleLocation;
    private int moleTimeSpawns;
    private int minTimeUp;
    private int maxTimeUp;
    private MolesManager myManager;

    public MoleMovement(final int height, final int width, MolesManager myManager) {
        //molePosition = new Point2D(0, 0);

        this.myManager = myManager;
        this.moleTimeSpawns = 0;
        this.widthFence = width - FENCE_BORDER;
        this.heightFence = height - FENCE_BORDER;

        this.quarterWidth = widthFence / 2;
        this.quarterHeight = heightFence / 2;

        this.minTimeUp = 1500;
        this.maxTimeUp = 2500;

        molePosition = initialMolePosition();
        moleNextPosition = moleSpawn();
        movement = new MoleMoves(molePosition, moleNextPosition, this);
    }
    
    public void drawMole(Graphics g) {
        g.drawOval((int) molePosition.getX() - MOLE_SIZE / 2,(int) molePosition.getY() - MOLE_SIZE / 2, MOLE_SIZE, MOLE_SIZE);


        //g.drawOval((int) moleNextPosition.getX() - MOLE_SIZE / 2,(int) moleNextPosition.getY() - MOLE_SIZE / 2, MOLE_SIZE, MOLE_SIZE);
    }

    private Point2D initialMolePosition() {
        if (new Random().nextBoolean()) {
            molePosition = new Point2D(randomBetweenTwo(FENCE_BORDER / 2, FENCE_BORDER / 2 + widthFence), 
                (FENCE_BORDER / 2) + new Random().nextInt(heightFence - (FENCE_BORDER / 2)));
        } else {
            molePosition = new Point2D((FENCE_BORDER / 2) + new Random().nextInt(widthFence - (FENCE_BORDER / 2)),
                randomBetweenTwo(FENCE_BORDER / 2, FENCE_BORDER / 2 + heightFence));
        }
        return molePosition;
    }

    private int randomBetweenTwo(int first, int second) {
        if (new Random().nextBoolean()) {
            return first;
        } else {
            return second;
        }
    }

    public Point2D moleSpawn() {
        moleTimerMovement();
        return moleMotion(new Random().nextInt(4));
    }

    public Point2D moleMovement() {
        int newPos = moleLocation;
        while (newPos == moleLocation){
            newPos = new Random().nextInt(4);
        }
        moleTimeSpawns++;
        return moleMotion(newPos);
    }

    private Point2D moleMotion(int choosenLocation) {
        switch (choosenLocation) {
            case 0:
                moleLocation = 0;
                moleNextPosition = new Point2D(moleRandX(), moleRandY());
                break;
            case 1:
                moleLocation = 1;
                moleNextPosition = new Point2D((quarterWidth + moleRandX()), moleRandY());
                break;
            case 2:
                moleLocation = 2;
                moleNextPosition = new Point2D(moleRandX(), quarterHeight + moleRandY());
                break;
            case 3:
                moleLocation = 3;
                moleNextPosition = new Point2D(quarterWidth + moleRandX(), quarterHeight + moleRandY());
                break;
            default:
                break;
        }
        return moleNextPosition;
    }

    private double moleRandX() {
        return (FENCE_BORDER / 2) + MOLE_SIZE / 2 + (new Random().nextDouble(quarterWidth - MOLE_SIZE));

    }

    private double moleRandY() {
        return (FENCE_BORDER / 2) + MOLE_SIZE / 2 + (new Random().nextDouble(quarterHeight - MOLE_SIZE));

    }

    private void moleTimerMovement() {
        Timer moleTimer = new Timer(minTimeUp + new Random().nextInt(maxTimeUp - minTimeUp), new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {
                moleMovement();
                if (moleTimeSpawns > 3) {
                    myManager.moleRemove(getThisMole());
                }
            }
        });
        moleTimer.start();
    }

    private MoleMovement getThisMole() {
        return this;
    }

    public void setMolePosition(Point2D newPosition) {
        this.molePosition = newPosition;
    }
}
