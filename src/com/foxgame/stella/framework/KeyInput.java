package com.foxgame.stella.framework;

import com.foxgame.stella.window.Game;
import com.foxgame.stella.window.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){

        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ObjectId.Player){
                if (key == KeyEvent.VK_D) tempObject.setVelX(5);
                if (key == KeyEvent.VK_A) tempObject.setVelX(-5);
                if (key == KeyEvent.VK_LEFT) tempObject.setVelX(-5);
                if (key == KeyEvent.VK_RIGHT) tempObject.setVelX(5);
                if (key == KeyEvent.VK_SPACE && !tempObject.isJumping()){
                    tempObject.setJumping(true);
                    tempObject.setVelY(-12);
                }
                if (key == KeyEvent.VK_UP && !tempObject.isJumping()){
                    tempObject.setJumping(true);
                    tempObject.setVelY(-12);
                }
                if (key == KeyEvent.VK_W && !tempObject.isJumping()){
                    tempObject.setJumping(true);
                    tempObject.setVelY(-12);
                }

            }
        }

        if (key == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }

    }

    public void keyReleased(KeyEvent e){


        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ObjectId.Player){
                if (key == KeyEvent.VK_D) tempObject.setVelX(0);
                if (key == KeyEvent.VK_A) tempObject.setVelX(0);
                if (key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
                if (key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);

            }
        }
    }


}
