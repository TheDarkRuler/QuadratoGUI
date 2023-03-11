package it.unibo.chatgpt;

import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.Graphics;

public class MolesManager {

    private static final int FENCE_BORDER = 300;

    private QuadratoGUI world;
    private List<MoleMovement> moles;
    private int widthFence;
    private int heightFence;
    private Dimension dim;

    public MolesManager(QuadratoGUI world, Dimension dim) {
        this.dim = dim;
        this.widthFence = dim.width - FENCE_BORDER;
        this.heightFence = dim.height - FENCE_BORDER;
        this.world = world;
        this.moles = new ArrayList<>();
    
    }

    public void addMole() {
        this.moles.add(new MoleMovement(dim.height, dim.width, this));
    }

    public void moleRemove(MoleMovement mole) {
        moles.remove(mole);
    }

    public void drawMoles(Graphics g) {
        moles.stream().forEach(i -> i.drawMole(g));
    }

    public void drawFence(Graphics g) {
        g.drawRect(FENCE_BORDER / 2, FENCE_BORDER / 2, widthFence, heightFence);
        g.drawLine((FENCE_BORDER / 2) + (widthFence / 2), FENCE_BORDER / 2, (FENCE_BORDER / 2) + (widthFence / 2), heightFence + (FENCE_BORDER / 2));
        g.drawLine((FENCE_BORDER / 2), (FENCE_BORDER / 2) + (heightFence / 2), (FENCE_BORDER / 2) + widthFence, (FENCE_BORDER / 2) + (heightFence/2));
    }
}
