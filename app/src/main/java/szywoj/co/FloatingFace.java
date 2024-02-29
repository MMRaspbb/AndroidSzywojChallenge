package szywoj.co;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

import szywoj.co.graphics.Sprite;
import szywoj.co.utils.BounceMap;

public class FloatingFace {
    private double positionX;
    private double positionY;
    private Paint paint;
    private BounceMap bounceMap;
    private Sprite sprite;

    public FloatingFace(Context context, double positionX, double positionY, BounceMap bounceMap, Sprite sprite){
        this.positionX = positionX;
        this. positionY = positionY;
        this.bounceMap = bounceMap;
        this.sprite = sprite;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.floatingFace);
        paint.setColor(color);
    }
    public void changeFace(){
        sprite.reSprite();
    }

    public void draw(Canvas canvas) {
        //canvas.drawCircle((float)positionX, (float)positionY, 100, paint); //usun
        sprite.draw(canvas,(int)positionX,(int)positionY);
    }
    public void update() {
        bounceMap.moveBy(10);
        positionX = bounceMap.getCurrentX();
        positionY = bounceMap.getCurrentY();
    }
}
