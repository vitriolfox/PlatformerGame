package com.foxgame.stella.window;

import com.foxgame.stella.framework.GameObject;
import com.foxgame.stella.framework.ObjectId;
import com.foxgame.stella.objects.Block;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    private GameObject tempObject;


    public void tick(){
        for (int i = 0; i < object.size(); i++){
            tempObject = object.get(i);
            tempObject.tick(object);
        }
    }

    public void render(Graphics g){
        for (int i = 0; i < object.size(); i++){
            tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }


    public void createLevel(){
        for (int j = 0; j < Game.WIDTH*2; j += 32) {
            addObject(new Block(j, Game.HEIGHT - 64, ObjectId.Block));
        }

        for(int j = 200; j < 600; j += 32){
            addObject(new Block(j, 400, ObjectId.Block));
        }

        /*for(int j = 0; j < Game.HEIGHT-32; j += 32){
            addObject(new Block(Game.WIDTH-34, j, ObjectId.Block));
        }*/

        for(int j = 0; j < Game.HEIGHT-32; j += 32){
            addObject(new Block(10, j, ObjectId.Block));
        }
    }

}
