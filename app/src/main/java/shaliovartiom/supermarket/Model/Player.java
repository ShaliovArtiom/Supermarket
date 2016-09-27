package shaliovartiom.supermarket.Model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import shaliovartiom.supermarket.View.GameView;

public class Player {

    private GameView gameView;
    private Bitmap bmp;

    private int x;
    private int y;
    private int speedX = 15;
    private int currentFrame = 250;



    public Player(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;

        this.x = currentFrame;
        this.y = 750;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getX() < 250) {
            moveLeft();
        }
        if (event.getX() > 250) {
            moveRight();
        }
        return onTouchEvent(event);
    }

    public void moveLeft(){
        if(x <= 0){
            x = 0;
        }else{
            speedX = - 10;
            x = x + speedX;
        }
    }

    public void moveRight(){
        if(x >= gameView.getWidth() - bmp.getWidth() - speedX) {
            x = gameView.getWidth() - bmp.getWidth() - speedX ;
        }else{
            speedX = 10;
            x = x + speedX;
        }
    }

    public void onDraw(Canvas canvas){
        canvas.drawBitmap(bmp, x, y, null);
    }
}
