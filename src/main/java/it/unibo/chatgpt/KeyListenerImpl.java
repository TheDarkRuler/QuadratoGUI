package it.unibo.chatgpt;

import java.awt.event.*;

public class KeyListenerImpl implements KeyListener {

    private static final int MOVE_SPEED = 5;
    
    protected static int moveX, moveY;
    private boolean spacePressed = false;

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (!MouseListenerImpl.isAnimationGoing){
            switch (keyCode) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    moveY = -MOVE_SPEED;
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    moveY = MOVE_SPEED;
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    moveX = -MOVE_SPEED;
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    moveX = MOVE_SPEED;
                    break;
                case KeyEvent.VK_SPACE:
                    spacePressed = true;
                    break;
            }

        }   
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
            if (!MouseListenerImpl.isAnimationGoing){
            switch (keyCode) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    moveY = 0;
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    moveX = 0;
                    break;
            }
        }
    }

    public int getMoveX() {
        return KeyListenerImpl.moveX;

    }

    public int getMoveY() {
        return KeyListenerImpl.moveY;
    }

    public boolean isSpacePressed() {
        return this.spacePressed;
    }
    
    public void setSpacePressed() {
        this.spacePressed = false;
    }
}
