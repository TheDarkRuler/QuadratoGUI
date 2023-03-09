package it.unibo.chatgpt;

import java.util.HashMap;
import java.util.Map;
import java.awt.Dimension;
import java.awt.Graphics;

public class MolesManager {

    private static final int FENCE_BORDER = 300;

    private QuadratoGUI world;
    private Map<Integer,MoleMovement> moles;
    private int numberOfMoles;
    private int widthFence;
    private int heightFence;
    private Dimension dim;

    public MolesManager(QuadratoGUI world, Dimension dim) {
        this.dim = dim;
        this.widthFence = dim.width - FENCE_BORDER;
        this.heightFence = dim.height - FENCE_BORDER;
        this.numberOfMoles = 0;
        this.world = world;
        this.moles = new HashMap<>();
    
    }

    public void addMole() {
        this.moles.put(numberOfMoles, new MoleMovement(dim.height, dim.width));
        numberOfMoles++;
    }

    public void moleMoves() {
        for (int i : moles.keySet()) {
            moles.get(i).moleMovement();
        }
    }

    public void drawMoles(Graphics g) {
        for (int i : moles.keySet()) {
            moles.get(i).drawMole(g);
        }
    }

    public void drawFence(Graphics g) {
        g.drawRect(FENCE_BORDER / 2, FENCE_BORDER / 2, widthFence, heightFence);
        g.drawLine((FENCE_BORDER / 2) + (widthFence / 2), FENCE_BORDER / 2, (FENCE_BORDER / 2) + (widthFence / 2), heightFence + (FENCE_BORDER / 2));
        g.drawLine((FENCE_BORDER / 2), (FENCE_BORDER / 2) + (heightFence / 2), (FENCE_BORDER / 2) + widthFence, (FENCE_BORDER / 2) + (heightFence/2));
    }
}
