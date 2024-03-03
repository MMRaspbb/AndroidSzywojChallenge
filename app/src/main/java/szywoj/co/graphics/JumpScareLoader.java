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
    private int jumpScareX;
    private int jumpScareY;
    public JumpScareLoader(Context context, int windowWidth, int windowHeight){
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.context = context;

        int resourceId = context.getResources().getIdentifier("scare_sheet.png", "drawable", context.getPackageName());
        loadJumpScare(resourceId);
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
        jumpScareX = number % 5;
        jumpScareY = (int)(number/5);
    }

    public void loadJumpScare(int resourceId){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, bitmapOptions);
    }


    public Bitmap getBitmap() {
        //Bitmap sprite = Bitmap.createBitmap(bitmap, jumpScareX*300, jumpScareY*600, (jumpScareX + 1) * 300, (jumpScareY + 1) * 600);
        Bitmap sprite = Bitmap.createBitmap(bitmap, 300, 0, 600, 600);
        return Bitmap.createScaledBitmap(sprite, windowWidth, windowHeight, true);
    }
}
