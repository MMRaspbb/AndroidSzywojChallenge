package szywoj.co.sounds;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;

import szywoj.co.R;

public class SoundPlayer {
    Context context;
    MediaPlayer player;
    String currentSound;
    String[] sounds = new String[]{"vineboom"};
    public SoundPlayer(Context context){
        this.context = context;
    }
    private void rerollSound(){
        currentSound = sounds[(int)(Math.random() * sounds.length)];
    }
    public void play(View view){
        if(player == null){
            //int resourceId = context.getResources().getIdentifier(currentSound, "raw", context.getPackageName());
            //player = MediaPlayer.create(context, resourceId);
            player = MediaPlayer.create(context, R.raw.vineboom);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }
    public void stopPlayer(){
        if(player != null){
            player.release();
            player = null;
        }
    }
}
