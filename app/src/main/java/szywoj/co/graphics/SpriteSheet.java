package szywoj.co.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import szywoj.co.R;

public class SpriteSheet {
    private Bitmap bitmap;
    public SpriteSheet(Context context){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheet, bitmapOptions);
    }
    public Sprite getFaceSprite(){
        return new Sprite(this, new Rect(0,0,300,300));
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
