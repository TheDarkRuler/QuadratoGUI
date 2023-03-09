package it.unibo.chatgpt;

import java.awt.*;
import java.util.Random;
import javafx.geometry.Point2D;

public class MoleMovement {

    private static final int FENCE_BORDER = 300;
    private static final int MOLE_SIZE = 20;
    
    private int quarterWidth;
    private int quarterHeight;
    private int widthFence;
    private int heightFence;
    private Point2D molePosition;
    private int moleLocation;
    private int moleTimeSpawns;

    public MoleMovement(final int height, final int width) {
        //molePosition = new Point2D(0, 0);

        this.moleTimeSpawns = 0;
        this.widthFence = width - FENCE_BORDER;
        this.heightFence = height - FENCE_BORDER;

        this.quarterWidth = widthFence / 2;
        this.quarterHeight = heightFence / 2;

        molePosition = moleSpawn();
    }
    
    public void drawMole(Graphics g) {
        g.drawOval((int) molePosition.getX() - MOLE_SIZE / 2,(int) molePosition.getY() - MOLE_SIZE / 2, MOLE_SIZE, MOLE_SIZE);
    }

    public Point2D moleSpawn() {
        moleTimeSpawns++;
        switch (new Random().nextInt(4)) {
            case 0:
                moleLocation = 0;
                molePosition =  new Point2D((FENCE_BORDER / 2) + MOLE_SIZE / 2 + (new Random().nextDouble(quarterWidth - MOLE_SIZE)),
                        (FENCE_BORDER / 2) + MOLE_SIZE / 2 + (new Random().nextDouble(quarterHeight - MOLE_SIZE)));
                break;
            case 1:
                moleLocation = 1;
                molePosition = new Point2D((quarterWidth + (FENCE_BORDER / 2))+ MOLE_SIZE/2 + (new Random().nextDouble(quarterWidth-MOLE_SIZE)),
                        (FENCE_BORDER / 2) + MOLE_SIZE / 2 + (new Random().nextDouble(quarterHeight-MOLE_SIZE)));
                break;
            case 2:
                moleLocation = 2;
                molePosition = new Point2D((FENCE_BORDER / 2) + MOLE_SIZE / 2 + (new Random().nextDouble(quarterWidth-MOLE_SIZE)),
                        (quarterHeight + (FENCE_BORDER / 2)) + MOLE_SIZE/2 + (new Random().nextDouble(quarterHeight - MOLE_SIZE)));
                break;            
            case 3:
                moleLocation = 3;
                molePosition =  new Point2D((quarterWidth + (FENCE_BORDER / 2)) + MOLE_SIZE / 2 + (new Random().nextDouble(quarterWidth - MOLE_SIZE)),
                        (quarterHeight + (FENCE_BORDER / 2)) + MOLE_SIZE / 2 + (new Random().nextDouble(quarterHeight - MOLE_SIZE)));
                break;            
            default:
                break;
        }
        return molePosition;
    }

    public Point2D moleMovement() {
        if(moleTimeSpawns > 3) {
            moleTimeSpawns = 0;
        }
        int newPos = moleLocation;
        while (newPos == moleLocation){
            newPos = new Random().nextInt(4);
        }
        switch (newPos) {
            case 0:
                moleLocation = 0;
                molePosition = new Point2D((FENCE_BORDER / 2) + MOLE_SIZE / 2 + (new Random().nextDouble(quarterWidth - MOLE_SIZE)),
                        (FENCE_BORDER / 2) + MOLE_SIZE / 2 + (new Random().nextDouble(quarterHeight - MOLE_SIZE)));
                break;
            case 1:
                moleLocation = 1;
                molePosition = new Point2D((quarterWidth + (FENCE_BORDER / 2)) + MOLE_SIZE / 2 + (new Random().nextDouble(quarterWidth - MOLE_SIZE)),
                        (FENCE_BORDER / 2) + MOLE_SIZE / 2 + (new Random().nextDouble(quarterHeight - MOLE_SIZE)));
                break;
            case 2:
                moleLocation = 2;
                molePosition = new Point2D((FENCE_BORDER / 2) + MOLE_SIZE / 2 + (new Random().nextDouble(quarterWidth - MOLE_SIZE)),
                        (quarterHeight + (FENCE_BORDER / 2)) + MOLE_SIZE / 2 + (new Random().nextDouble(quarterHeight - MOLE_SIZE)));
                break;
            case 3:
                moleLocation = 3;
                molePosition = new Point2D((quarterWidth + (FENCE_BORDER / 2)) + MOLE_SIZE / 2 + (new Random().nextDouble(quarterWidth - MOLE_SIZE)),
                        (quarterHeight + (FENCE_BORDER / 2)) + MOLE_SIZE / 2 + (new Random().nextDouble(quarterHeight - MOLE_SIZE)));
                break;
            default:
                break;
        }
        moleTimeSpawns++;
        return molePosition;
    }
}
