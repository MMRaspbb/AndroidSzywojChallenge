package szywoj.co;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import szywoj.co.sounds.SoundPlayer;

public class MainActivity extends AppCompatActivity {
    private SoundPlayer soundPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        this.soundPlayer = new SoundPlayer(this);

        setContentView(new Game(this, height, width,soundPlayer));
    }
    @Override
    protected void onStop(){
        super.onStop();
        soundPlayer.stopPlayer();
    }
}