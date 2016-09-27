package shaliovartiom.supermarket.View;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import shaliovartiom.supermarket.Controller.GameManager;
import shaliovartiom.supermarket.Model.Banannno;
import shaliovartiom.supermarket.Model.Player;
import shaliovartiom.supermarket.R;

public class GameView extends SurfaceView implements Runnable {

    private GameManager gameManager;
    private Player player;
    private Bitmap playerBitmap;
    private List<Banannno> banannnoList = new ArrayList<>();
    private Bitmap banannnoBitmap;
    private Thread thread;

    public GameView(Context context) {
        super(context);
        gameManager = new GameManager(this);
        thread = new Thread(this);
        thread.start();
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

        playerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.smile);
        player = new Player(this, playerBitmap);
        banannnoBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.banano);
        banannnoList.add(new Banannno(this, banannnoBitmap));

    }

    public boolean onTouchEvent(MotionEvent event) {
            if (event.getX() < 250) {
                player.moveLeft();
            }
            if (event.getX() > 250) {
                player.moveRight();
            }
        return super.onTouchEvent(event);
    }

    public void onDraw(Canvas canvas){
        canvas.drawColor(Color.BLACK);

        Iterator<Banannno> iterator = banannnoList.iterator();
        while(iterator.hasNext()) {
            Banannno banannno = iterator.next();
            if(banannno.getY() >= 750 || banannno.getY() <= 750) {
                banannno.onDraw(canvas);
            } else {
                iterator.remove();
            }
        }

        player.onDraw(canvas);

    }

    @Override
    public void run() {
        while(true) {
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(4000));
                banannnoList.add(new Banannno(this, banannnoBitmap));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


