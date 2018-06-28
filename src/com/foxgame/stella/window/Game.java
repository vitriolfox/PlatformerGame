package com.foxgame.stella.window;

import com.foxgame.stella.framework.GameObject;
import com.foxgame.stella.framework.KeyInput;
import com.foxgame.stella.framework.ObjectId;
import com.foxgame.stella.objects.Block;
import com.foxgame.stella.objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{

    private boolean running = false;
    private Thread thread;
    Handler handler;
    Camera cam;
    public static int WIDTH, HEIGHT;
    private BufferedImage level = null;

    private void init(){

        WIDTH = getWidth();
        HEIGHT = getHeight();

        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadBufferedImage("/TestMap.png");      ////////Loading the level from .png

        handler = new Handler();
        cam = new Camera(0,0);

        loadImageLevel(level);

        this.addKeyListener(new KeyInput(handler));

        //handler.addObject(new Player(100, 100, ObjectId.Player, handler));
        //handler.createLevel();


    }

    public synchronized void start(){
        if (running){
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();

    }

    public void run() {
//////////////////////////////////////////////////////////Gameloop
        init();
        this.requestFocus();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        long now;
        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta=0;
                frames++;
                render();
            }

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }

////////////////////////////////////////////////////////////////////
    }

    private void tick(){

        handler.tick();
        for (int i = 0; i < handler.object.size(); i++){
            if (handler.object.get(i).getId() == ObjectId.Player){
                cam.tick(handler.object.get(i));
            }
        }


    }

    private void render() {

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
//////////////////////////////////////////Graphics place

        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());

        g2d.translate(cam.getX(), cam.getY());          /////Camera start
        handler.render(g);
        g2d.translate(-cam.getX(), -cam.getY());        /////Camera end


//////////////////////////////////////////
        g.dispose();
        bs.show();
    }

    private void loadImageLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();

        for (int xx = 0; xx < h; xx++){
            for (int yy = 0; yy < w; yy++){
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255 && green == 255 && blue == 255){
                    handler.addObject(new Block(xx*32, yy*32, ObjectId.Block));
                }
                if (red == 0 && green == 0 && blue == 255){
                    handler.addObject(new Player(xx*32, yy*32, ObjectId.Player, handler));
                }

            }
        }
    }

    public static void main(String[] args) {
        new Window (800, 600, "Stella", new Game());
    }
}
