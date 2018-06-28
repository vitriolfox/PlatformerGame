package com.foxgame.stella.objects;

import com.foxgame.stella.framework.GameObject;
import com.foxgame.stella.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Block extends GameObject {

    public Block(float x, float y, ObjectId id) {
        super(x, y, id);
    }

    public void tick(LinkedList<GameObject> object) {
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.drawRect((int)x, (int)y, 32, 32);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }


}
