package szywoj.co;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import szywoj.co.graphics.JumpScare;
import szywoj.co.graphics.JumpScareLoader;
import szywoj.co.graphics.Sprite;
import szywoj.co.graphics.SpriteSheet;
import szywoj.co.sounds.SoundPlayer;
import szywoj.co.utils.BounceMap;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final JumpScareLoader jumpScareLoader;
    private GameLoop gameLoop;
    private final FloatingFace floatingFace;
    private BounceMap bounceMap;
    private boolean succes = false;
    private JumpScare jumpScare;
    private SoundPlayer soundPlayer;

    public Game(Context context, int windowHeight, int windowWidth, SoundPlayer soundPlayer){
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        double faceWidth = 300;
        double faceHight = 300;
        SpriteSheet spriteSheet = new SpriteSheet(context);
        bounceMap = new BounceMap(windowHeight, windowWidth, faceHight,faceWidth);

        Sprite sprite = spriteSheet.getFaceSprite();

        floatingFace = new FloatingFace(getContext(), 0, 0, bounceMap, sprite);

        jumpScareLoader = new JumpScareLoader(this.getContext(), windowWidth, windowHeight);
        jumpScare = new JumpScare(windowHeight, windowWidth, jumpScareLoader, sprite);

        this.soundPlayer = soundPlayer;

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
                if(bounceMap.hitObject(event.getX(), event.getY())){
                    soundPlayer.stopPlayer();
                    bounceMap.randomizeAndCalculate();
                    jumpScare.beginScare(this.getContext());
                    floatingFace.changeFace();
                    soundPlayer.play(this);
                }
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        floatingFace.draw(canvas);
        jumpScare.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);
    }

    public void drawUPS(Canvas canvas){
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS "+ averageUPS, 100, 50, paint);
    }

    public void drawFPS(Canvas canvas){
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS "+ averageFPS, 100, 120, paint);
    }
    public void update(){
        floatingFace.update();
    }
}
