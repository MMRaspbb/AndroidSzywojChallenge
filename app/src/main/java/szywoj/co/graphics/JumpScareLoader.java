package szywoj.co.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.net.ContentHandler;
import java.util.ArrayList;

import szywoj.co.R;

public class JumpScareLoader {
    private int windowWidth;
    private int windowHeight;
    private Bitmap bitmap;
    private Context context;
    public JumpScareLoader(Context context, int windowWidth, int windowHeight){
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.context = context;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.scare_sheet, bitmapOptions);
        bitmap = Bitmap.createScaledBitmap(bitmap, 5400,10950,true);
    }
    public Bitmap getBitmap() {
        return bitmap;
    }
}
