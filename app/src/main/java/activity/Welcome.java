package activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myclassesbasketball.R;

import clock.MyVedioView;

public class Welcome extends AppCompatActivity {

    private MyVedioView videoview; //自定义的videoView

    final int SPLASH_DISPLAY_LENGHT=1960;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_welcome );
        initView();
        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {
                Intent mainIntent = new Intent(Welcome.this,
                        MainActivity.class);
                Welcome.this.startActivity(mainIntent);
                overridePendingTransition(0,0);
                Welcome.this.finish();
            }

        }, SPLASH_DISPLAY_LENGHT);
    }
    private void initView() {
        videoview = (MyVedioView) findViewById(R.id.videoview);
        //设置播放加载路径
        videoview.setVideoURI( Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.welcome));
        //播放
        videoview.start();
        //循环播放
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoview.start();
            }
        });
    }
    @Override
    protected void onDestroy() {

        videoview.stopPlayback();
        super.onDestroy();

    }

}
