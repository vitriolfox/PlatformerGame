package com.foxgame.stella.objects;

import com.foxgame.stella.framework.GameObject;
import com.foxgame.stella.framework.ObjectId;
import com.foxgame.stella.window.Handler;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {

    private float width = 32, height = 64;
    private float gravity = 0.5f;
    private final float MAX_SPEED = 5;
    private Handler handler;


    public Player(float x, float y, ObjectId id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;

        if (falling || jumping){
            velY += gravity;
        }

        if (velY > MAX_SPEED){
            velY = MAX_SPEED;
        }

        collision(object);
    }

    public void collision(LinkedList<GameObject> object){

        for (int i = 0; i < handler.object.size(); i++){

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ObjectId.Block){

                if (getBoundsTop().intersects(tempObject.getBounds())){
                    y = tempObject.getY() + (height/2);
                    velY = 0;
                }
                if (getBounds().intersects(tempObject.getBounds())){
                    y = tempObject.getY() - height;
                    velY = 0;
                    jumping = false;
                    falling = false;
                } else {
                    falling = true;
                }
                if (getBoundsRight().intersects(tempObject.getBounds())){
                    x = tempObject.getX() + width;
                }

                if (getBoundsLeft().intersects(tempObject.getBounds())){
                    x = tempObject.getX() - width;
                }
            }

        }

    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, (int) width, (int) height);

        /*Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.green);
        g2d.draw(getBounds());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsTop());
*/
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x + ((int) width/2) - ((int) width/2)/2, (int) y + ((int) height/2), (int) width/2, (int) height/2);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) x + ((int) width/2) - ((int) width/2)/2, (int) y, (int) width/2, (int) height/2);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x + (int) width-5, (int) y+5, 5, (int) height-10);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int) x, (int) y+5, 5, (int) height-10);
    }

}
