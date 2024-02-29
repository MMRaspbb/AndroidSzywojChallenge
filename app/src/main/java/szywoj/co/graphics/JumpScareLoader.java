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

        String[] scares = new String[]{"brodacz", "sbixon", "wampir", "zjaraniec"};
        String scare = scares[(int)(Math.random()) * 3];

        int resourceId = context.getResources().getIdentifier(scare, "drawable", context.getPackageName());
        loadJumpScare(resourceId);
    }

    public void reloadJumpScare(String face){
        String[] images = new String[]{"brodacz"};
        switch (face){
            case "szywoj":
                images = new String[]{"brodacz", "zjaraniec"};
                break;
            case "seba":
                images = new String[]{"sebixon", "wampir"};
                break;
        }
        int rand = (int)(Math.floor(Math.random() * images.length));
        int resourceId = context.getResources().getIdentifier(images[rand], "drawable", context.getPackageName());
        loadJumpScare(resourceId);
    }

    public void loadJumpScare(int resourceId){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, bitmapOptions);
        bitmap = Bitmap.createScaledBitmap(bitmap, windowWidth,windowHeight,true);
    }


    public Bitmap getBitmap() {
        return bitmap;
    }
}
