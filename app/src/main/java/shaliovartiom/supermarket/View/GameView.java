package shaliovartiom.supermarket.View;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import shaliovartiom.supermarket.Controller.GameManager;
import shaliovartiom.supermarket.Model.Player;
import shaliovartiom.supermarket.R;

public class GameView extends SurfaceView {

    private GameManager gameManager;
    private Player player;
    private Bitmap bitmap;

    public GameView(Context context) {
        super(context);
        gameManager = new GameManager(this);
        getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameManager.setRunning(true);
                gameManager.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameManager.setRunning(false);
                while(retry){
                    try {
                        gameManager.join();
                        retry = false;
                    }
                    catch (InterruptedException ignored) { }
                }

            }
        });

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.smile);
        player = new Player(this, bitmap);

    }

    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if (event.getX() < 250) {
                player.moveLeft();
            }
            if (event.getX() > 250) {
                player.moveRight();
            }
        }
        return super.onTouchEvent(event);
    }

    public void onDraw(Canvas canvas){
        canvas.drawColor(Color.BLACK);
        player.onDraw(canvas);
    }
}


