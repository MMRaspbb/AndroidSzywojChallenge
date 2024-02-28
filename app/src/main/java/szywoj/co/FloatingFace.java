package szywoj.co;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import szywoj.co.utils.BounceMap;

public class FloatingFace {
    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;
    private BounceMap bounceMap;
    public FloatingFace(Context context, double positionX, double positionY, double radius, BounceMap bounceMap){
        this.positionX = positionX;
        this. positionY = positionY;
        this.radius = radius;
        this.bounceMap = bounceMap;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.floatingFace);
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX, (float)positionY, (float)radius, paint);
        paint.setTextSize(50);
        canvas.drawText("X "+ positionX, 500, 50, paint);
        canvas.drawText("Y "+ positionY, 500, 120, paint);
    }
    public void update() {
        bounceMap.moveBy(30);
        positionX = bounceMap.getCurrentX();
        positionY = bounceMap.getCurrentY();
    }
}
