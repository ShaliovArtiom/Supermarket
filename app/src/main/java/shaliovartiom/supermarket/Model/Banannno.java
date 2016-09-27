package shaliovartiom.supermarket.Model;


import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

import shaliovartiom.supermarket.View.GameView;

public class Banannno {

    private int x;
    private int y;
    private int speed;

    private GameView gameView;
    private Bitmap bmp;

    public Banannno(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;

        Random random = new Random();
        this.x = random.nextInt(499);
        this.y = 0;
        this.speed = random.nextInt(10);

    }

    public void update(){
        if(speed == 0){
            speed = 10;
        }
        y += speed;

    }

    public void onDraw(Canvas canvas){
        update();
        canvas.drawBitmap(bmp, x, y, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
