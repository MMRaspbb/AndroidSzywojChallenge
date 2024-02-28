package szywoj.co;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import szywoj.co.utils.BounceMap;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private final FloatingFace floatingFace;
    private BounceMap bounceMap;
    private double clickX = 0;//usun
    private double clickY = 0;//usun
    private boolean succes = false;

    public Game(Context context, int windowHeight, int windowWidth){
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        double circleRadius = 100;
        bounceMap = new BounceMap(windowHeight, windowWidth, 2*circleRadius,2*circleRadius);
        floatingFace = new FloatingFace(getContext(), 500, 500, 100, bounceMap);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                clickX = event.getX();
                clickY = event.getY();
                if(bounceMap.hitObject(event.getX(), event.getY())){
                    bounceMap.randomizeAndCalculate();
                    succes = true;
                } else{
                    succes = false;
                }
                return true;
        }
        return super.onTouchEvent(event);
    }
    //usun
    public void drawClick(Canvas canvas){
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("Xclick "+ clickX, 100, 50, paint);
        canvas.drawText("Yclick "+ clickY, 100, 120, paint);
        canvas.drawText("Succesful Click " + succes, 100, 200, paint);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);
        drawClick(canvas);
        floatingFace.draw(canvas);
    }

    public void drawUPS(Canvas canvas){
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        //canvas.drawText("UPS "+ averageUPS, 100, 50, paint);
    }

    public void drawFPS(Canvas canvas){
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        //canvas.drawText("FPS "+ averageFPS, 100, 120, paint);
    }
    public void update(){
        floatingFace.update();
    }
}
