package szywoj.co.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class JumpScare {
    private final Sprite sprite;
    private int notTransparentFrames = 20;
    private int fadeOutFrames = 60;
    private int timer = 0;
    private int windowHeight;
    private int windowWidth;
    private Rect rect;
    private Bitmap bitmap;
    private int currentAlpha = 255;
    private Paint paint;
    private double alphaStep = 255/fadeOutFrames;
    private JumpScareLoader jumpScareLoader;
    public JumpScare(int windowHeight, int windowWidth, JumpScareLoader jumpScareLoader, Sprite sprite){
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        rect = new Rect(0,0, windowWidth, windowHeight);
        this.jumpScareLoader = jumpScareLoader;
        this.sprite = sprite;
    }
    public void drawNotTransparrent(Canvas canvas) {
        canvas.drawBitmap(
                bitmap,
                rect,
                rect,
                null
        );
    }
    public void drawFadeOut(Canvas canvas, Paint paint){
        canvas.drawBitmap(
                bitmap,
                rect,
                rect,
                paint
        );
    }

    public void draw(Canvas canvas){
        if(timer > 0){
            timer -= 1;
            if(timer > fadeOutFrames){
                drawNotTransparrent(canvas);
            } else{
                Paint paint = new Paint();
                paint.setAlpha(currentAlpha);
                currentAlpha -= alphaStep;
                drawFadeOut(canvas, paint);
            }
        }
    }
    public void beginScare(Context context){
        timer = notTransparentFrames + fadeOutFrames;
        currentAlpha = 255;
        paint = new Paint();
        //jumpScareLoader.reloadJumpScare(sprite.getCurrentFace());
        //int resourceId = context.getResources().getIdentifier("wampir", "drawable", context.getPackageName());
        //jumpScareLoader.loadJumpScare(resourceId);
        bitmap = jumpScareLoader.getBitmap();
    }
}
