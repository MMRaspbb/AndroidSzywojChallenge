package szywoj.co.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.core.content.ContextCompat;

import szywoj.co.R;

public class Sprite {
    private final SpriteSheet spriteSheet;
    private Rect rect;
    private static final int MAX_I = 0;
    private static final int MAX_J = 3;

    private static final String[] faceIdentifier= {"szywoj", "szywoj", "seba", "seba"};
    private int previousSprite = 0;

    public Sprite(SpriteSheet spriteSheet, Rect rect) {
        this.spriteSheet = spriteSheet;
        this.rect = rect;
    }

    public void reSprite(){
        int rand = (int)(Math.floor(Math.random() * (MAX_I + 1) * (MAX_J + 1)));
        while(rand == previousSprite){
            rand = (int)(Math.floor(Math.random() * (MAX_I + 1) * (MAX_J + 1)));
        }
        previousSprite = rand;
        int i = (int)(Math.floor(rand/5));
        int j = rand%5;
        rect = new Rect(j*300,i*300,(j+1)*300, (i+1)*300);
    }

    public String getCurrentFace(){
        return faceIdentifier[previousSprite];
    }

    public void draw(Canvas canvas,int x,int y) {
        canvas.drawBitmap(
                spriteSheet.getBitmap(),
                rect,
                new Rect(x - 150, y - 150, x + 150,y + 150),
                null
        );
    }
}
