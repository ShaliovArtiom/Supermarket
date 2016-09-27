package shaliovartiom.supermarket.Model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import shaliovartiom.supermarket.View.GameView;

public class Player {
    GameView gameView;
    Bitmap bmp;

    private int x;
    private int y;
    private int speedX = 10;
    private int currentFrame = 250;



    public Player(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;

        this.x = currentFrame;
        this.y = 750;
    }

    public void update() {
        if(x > gameView.getWidth() - bmp.getWidth() - speedX) {
            speedX = -10;
        }
        if(x + speedX < 0){
            speedX = 10;
        }
        x = x + speedX;
    }

    public void moveLeft(){
        if(x + speedX < 0){
            x = speedX;
        }else{
            speedX = - 10;
            x = x + speedX;
        }
    }

    public void moveRight(){
        if(x > gameView.getWidth() - bmp.getWidth() - speedX) {
            speedX = -10;
            x = x + speedX;
        }else{
            speedX = 10;
            x = x + speedX;
        }
    }

    public void onDraw(Canvas canvas){
        canvas.drawBitmap(bmp, x, y, null);
    }


}
