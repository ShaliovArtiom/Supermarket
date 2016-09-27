package shaliovartiom.supermarket.Controller;

import android.graphics.Canvas;

import shaliovartiom.supermarket.View.GameView;

public class GameManager extends Thread {

    private GameView view;

    private boolean running = false;
    public GameManager(GameView view) {
        this.view = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {

        while(running) {
            Canvas canvas = null;
            try {
                canvas = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.onDraw(canvas);
                }
            } catch (Exception ignored) {
            } finally {
                if (canvas != null){
                    view.getHolder().unlockCanvasAndPost(canvas);
                }
            }

        }
    }
}
