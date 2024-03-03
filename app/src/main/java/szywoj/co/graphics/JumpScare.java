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
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    public JumpScare(int windowHeight, int windowWidth, JumpScareLoader jumpScareLoader, Sprite sprite){
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        rect = new Rect(0,0, windowWidth, windowHeight);
        this.sprite = sprite;

        this.bitmap = jumpScareLoader.getBitmap();
    }
    public void drawNotTransparrent(Canvas canvas) {
        Rect newRect = new Rect(startX, startY, endX, endY);
        canvas.drawBitmap(
                bitmap,
                newRect,
                rect,
                null
        );
    }
    public void drawFadeOut(Canvas canvas, Paint paint){
        Rect newRect = new Rect(startX, startY, endX, endY);
        canvas.drawBitmap(
                bitmap,
                newRect,
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

    public void reloadJumpScare(String face){
        int number = 0;
        switch(face){
            case "szywoj":
                number = (int)(Math.random() * 2);
                break;
            case "seba":
                number = (int)(Math.random() * 2) + 2;
                break;
        }
        int jumpScareX = number % 5;
        int jumpScareY = (int)(number/5);
        startX = jumpScareX * windowWidth;
        endX = startX + windowWidth;
        startY = jumpScareY * windowHeight;
        endY = startY + windowHeight;
    }
    public void beginScare(Context context){
        timer = notTransparentFrames + fadeOutFrames;
        currentAlpha = 255;
        paint = new Paint();
        reloadJumpScare(sprite.getCurrentFace());
    }
}
