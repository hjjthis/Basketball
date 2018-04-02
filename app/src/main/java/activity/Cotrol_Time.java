package activity;

import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.myclassesbasketball.R;

import app.MyApplication;
import clock.MyClock;
import clock.MyCountDownClock;

public class Cotrol_Time extends AppCompatActivity implements View.OnClickListener {

    // 退出时间

    private long currentBackPressedTime = 0;
    // 退出间隔

    private static final int BACK_PRESSED_INTERVAL = 2000;
    //初始化变量
    public Boolean start = false;
    public Boolean attackstart = false;
    //定义数据类型
    private MyCountDownClock play;  //单节时间倒计时
    private MyClock attack;  //进攻时间倒计时
    private ImageView playstart; //控制比赛开始和结束
    private ImageView playredo;//控制进攻时间恢复
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cotrol__time );
        //获取震动服务
//        mVibrator = (Vibrator) getApplication().getSystemService( Service.VIBRATOR_SERVICE );
        //
        bt=(Button)findViewById( R.id.bt_fanhui );
        bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( Cotrol_Time.this,jianyijishi.class );
                startActivity( intent );
            }
        } );
        play = (MyCountDownClock) this.findViewById( R.id.setPlayTime );
        attack = (MyClock) this.findViewById( R.id.setAttackTime );
        playstart = (ImageView) findViewById( R.id.img_bt_start );
        playredo = (ImageView) findViewById( R.id.img_bt_redo );
        playstart.setBackgroundResource( R.drawable.starticon );
        //设置监听器
        playredo.setOnClickListener( this );
        playstart.setOnClickListener( this );
        play.setOnClickListener( this );
        attack.setOnClickListener( this );
        //初始化倒计时
        play.initTime( 60 * MyApplication.getMinPlayTime() );
        attack.initTime( 1 * MyApplication.getMinAttackTime() );
        //play:设施倒计时流完的时候执行操作

        play.setOnTimeCompleteListener( new MyCountDownClock.OnTimeCompleteListener() {
            @Override
            public void onTimeComplete() {
                play.initTime( 60 * MyApplication.getMinPlayTime() );
                attack.initTime( 1 * MyApplication.getMinAttackTime() );
                play.stop();
                attack.stop();
                playstart.setBackgroundResource( R.drawable.starticon );
                attack.setTextColor( Color.rgb( 19, 164, 159 ) );
                start = false;
            }
        } );

        //attack:设施倒计时流完的时候执行操作
        attack.setOnTimeCompleteListener( new MyClock.OnTimeCompleteListener() {
            @Override
            public void onTimeComplete1() {
                attack.initTime( 1 * MyApplication.getMinAttackTime() );
            }
        } );
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.img_bt_start:
                if (start == false) {
                    play.start();
                    attack.start();
                    playstart.setBackgroundResource( R.drawable.pauseicon );
                    attackstart = true;
                    start = true;
                } else if (start = true) {
                    play.stop();
                    attack.stop();
                    playstart.setBackgroundResource( R.drawable.starticon );
                    start = false;
                    attackstart = false;
                }
                break;
            case R.id.img_bt_redo:
                if (MyCountDownClock.mNextTime <= 24) {
                    attack.initTime( MyCountDownClock.mNextTime );
                    if (start == true) {
                        attack.start();
                        attack.setTextColor( Color.rgb( 19, 164, 159 ) );
                    } else if (start == false) {
                        attack.stop();
                        attack.setTextColor( Color.rgb( 19, 164, 159 ) );
                    }
                } else {
                    attack.initTime( 1 * MyApplication.getMinAttackTime() );
                    if (start == true) {
                        attack.start();
                        attack.setTextColor( Color.rgb( 19, 164, 159 ) );
                    } else if (start == false) {
                        attack.stop();
                        attack.setTextColor( Color.rgb( 19, 164, 159 ) );
                    }
                }
                break;

        }

    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - currentBackPressedTime > BACK_PRESSED_INTERVAL) {
            currentBackPressedTime = System.currentTimeMillis();
            AlertDialog.Builder builder = new AlertDialog.Builder( this );
            builder.setTitle( "警告" ).setMessage( "确定退出计时界面？退出后将不保存任何数据" );
            builder.setPositiveButton( "确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    overridePendingTransition( android.R.anim.fade_in,
                            android.R.anim.fade_out );
                }
            } );
            builder.setNegativeButton( "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            } );
            builder.create().show();

        }
    }
}
