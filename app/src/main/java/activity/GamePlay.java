package activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myclassesbasketball.R;

import Person.PlayHistoryfinal;
import Person.Player;
import Person.PlayerLinshi;
import app.MyApplication;
import clock.MyClock;
import clock.MyCountDownClock;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import db.DBManager;
import fragment.GamePlayer;
import fragment.GamePlayerB;
import fragment.GameSorce;

import static android.content.ContentValues.TAG;

public class GamePlay extends AppCompatActivity implements View.OnClickListener {
    // 退出时间
    private long currentBackPressedTime = 0;
    // 退出间隔
    private static final int BACK_PRESSED_INTERVAL = 2000;
    private static int index_play=1;
    private int msorceA = 0;
    private int mzhugongA = 0;
    private int mfanguiA = 0;
    private int mlanbanA = 0;
    private int mqiangduanA = 0;
    private int mgaimaoA = 0;
    private int msorceB = 0;
    private int mfanguiB = 0;
    private int mzhugongB = 0;
    private int mlanbanB = 0;
    private int mqiangduanB = 0;
    private int mgaimaoB = 0;
    //定义数据结构
    private LinearLayout game; //比赛页面空白处的点击事件
    private GamePlayer gamePlayer; //队伍A的球员数据
    private GamePlayerB gamePlayerB;//队伍B的球员数据
    private GameSorce gameSorce;//两队比赛数据统计，通过点击页面空白处传入
    private LinearLayout addsetoffA;//动态添加删除暂定原点A区域
    private LinearLayout addsetoffB;//动态添加删除暂停原点B区域
    private Button setoffA;//点击减少暂停A
    private Button setoffB;//点击减少暂停B
    private Button playerA;//点击加入teamA的Fragment球员数据
    private Button playerB;//点击加入teamB的Fragment球员数据
    private TextView stealsA;//显示teamA的单节球队犯规数目
    private TextView stealsB;//显示teamB的单节球队犯规数目
    private TextView TeamAname;//显示球队A的名字
    private TextView TeamBname;//显示球队B的名字
    private TextView setdijijie;//显示当前比赛进行到第几节
    private ImageView img_teamA;//设置队标A
    private ImageView img_teamB;//设置队标B
    private TextView TeamAscore;//显示teamA的得分
    private TextView TeamBscore;//显示teamB的得分
    private MyClock attack;//进攻时间倒计时
    private MyCountDownClock play;//单节比赛时间倒计时

    //初始化DBManager
    private DBManager scoreDB;
    //初始化数据变量
    public Boolean start = false;
    public Boolean attackstart = false;
    int countsA = 0;
    int countsB = 0;
    int index = 1;//记录比赛结束
    Boolean ispressA = false;
    Boolean ispressB = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_game_play );
        initlayout();
        scoreDB = DBManager.getInstance( this );
        TeamAname.setText( MyApplication.getTeamA() );
        TeamBname.setText( MyApplication.getTeamB() );
        stealsA.setText( String.valueOf( 0 ) );
        stealsB.setText( String.valueOf( 0 ) );
        play.initTime( 60 * MyApplication.getBminplaytime() );
        attack.initTime( 1 * MyApplication.getBminattacktime() );
        if(MyApplication.getImgteamA().getDrawable()!=null&&MyApplication.getImgteamB().getDrawable()!=null)
        {
            img_teamB.setBackground( MyApplication.getImgteamB().getDrawable() );
            img_teamA.setBackground( MyApplication.getImgteamA().getDrawable() );
        }



        MyApplication.setOnefanguiA( 1 );
        MyApplication.setOnefanguiB( 1 );

        setdijijie.setText( "第" + index + "节" );
        //监听事件
        setoffA.setOnClickListener( this );
        setoffB.setOnClickListener( this );
        game.setOnClickListener( this );
        playerA.setOnClickListener( this );
        playerB.setOnClickListener( this );
        play.setOnClickListener( this );
        play.setOnTimeCompleteListener( new MyCountDownClock.OnTimeCompleteListener() {
            @Override
            public void onTimeComplete() {
                play.initTime( 60 * MyApplication.getBminplaytime() );
                attack.initTime( 1 * MyApplication.getBminattacktime() );
                index++;
                setdijijie.setText( "第" + index + "节" );
                stealsA.setText( String.valueOf( 0 ) );
                stealsB.setText( String.valueOf( 0 ) );
                addViewItemA( null );
                addViewItemB( null );
                MyApplication.setOnefanguiA( index );
                MyApplication.setOnefanguiB( index );
                if (index == 5) {
                    AlertDialog.Builder builder = new AlertDialog.Builder( getApplicationContext() );
                    builder.setTitle( "警告" ).setMessage( "本次比赛结束请保存数据" );
                    builder.setPositiveButton( "确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            PlayHistoryfinal playHistoryfinal=new PlayHistoryfinal();
                            playHistoryfinal.setPlayid( String.valueOf( MyApplication.getIndex() ));
                            playHistoryfinal.setNameA( MyApplication.getTeamA() );
                            playHistoryfinal.setNameB( MyApplication.getTeamB() );
                            playHistoryfinal.setScoreA( String.valueOf( MyApplication.getScoreA() ) );
                            playHistoryfinal.setScoreB( String.valueOf( MyApplication.getScoreB() ) );
                            playHistoryfinal.save( new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    Log.d( "result", "done: succcess" );
                                }
                            } );
                            for (int i = 0; i < MyApplication.getPlayersA().size(); i++) {
                                PlayerLinshi playerA = new PlayerLinshi();
                                playerA.setNum( MyApplication.getPlayersA().get( i ).getNum() );
                                playerA.setName( MyApplication.getPlayersA().get( i ).getName() );
                                msorceA = msorceA + MyApplication.getPlayersA().get( i ).getScore();
                                playerA.setScore( MyApplication.getPlayersA().get( i ).getScore() );
                                mfanguiA = mfanguiA + MyApplication.getPlayersA().get( i ).getFangui();
                                playerA.setFangui( MyApplication.getPlayersA().get( i ).getFangui() );
                                mgaimaoA = mgaimaoA + MyApplication.getPlayersA().get( i ).getGaimao();
                                playerA.setGaimao( MyApplication.getPlayersA().get( i ).getGaimao() );
                                mzhugongA = mzhugongA + MyApplication.getPlayersA().get( i ).getZhugong();
                                playerA.setZhugong( MyApplication.getPlayersA().get( i ).getZhugong() );
                                mlanbanA = mlanbanA + MyApplication.getPlayersA().get( i ).getLanban();
                                playerA.setLanban( MyApplication.getPlayersA().get( i ).getLanban() );
                                mqiangduanA = mqiangduanA + MyApplication.getPlayersA().get( i ).getQiangduan();
                                playerA.setQiangduan( MyApplication.getPlayersA().get( i ).getQiangduan() );
                                playerA.setIsTeamA( 0 );
                                playerA.setPalyid( MyApplication.getIndex() );
                                playerA.save( new SaveListener<String>(){
                                    @Override
                                    public void done(String s, BmobException e) {
                                        if (e == null) {
                                            Log.d( "success", "done: success" );
                                        } else {
                                            Log.d( "defet", "done: defet" );
                                        }
                                    }
                                } );
                            }
                            for (int i = 0; i <  MyApplication.getPlayersB().size(); i++) {
                                PlayerLinshi playerB = new PlayerLinshi();
                                playerB.setNum( MyApplication.getPlayersB().get( i ).getNum() );
                                playerB.setName( MyApplication.getPlayersB().get( i ).getName() );
                                msorceB = msorceB + MyApplication.getPlayersB().get( i ).getScore();
                                playerB.setScore( MyApplication.getPlayersB().get( i ).getScore() );
                                mfanguiB = mfanguiB + MyApplication.getPlayersB().get( i ).getFangui();
                                playerB.setFangui( MyApplication.getPlayersB().get( i ).getFangui() );
                                mgaimaoB = mgaimaoB + MyApplication.getPlayersB().get( i ).getGaimao();
                                playerB.setGaimao( MyApplication.getPlayersB().get( i ).getGaimao() );
                                mzhugongB = mzhugongB + MyApplication.getPlayersB().get( i ).getZhugong();
                                playerB.setZhugong( MyApplication.getPlayersB().get( i ).getZhugong() );
                                mlanbanB = mlanbanB + MyApplication.getPlayersB().get( i ).getLanban();
                                playerB.setLanban( MyApplication.getPlayersB().get( i ).getLanban() );
                                mqiangduanB = mqiangduanB + MyApplication.getPlayersB().get( i ).getQiangduan();
                                playerB.setQiangduan(  MyApplication.getPlayersB().get( i ).getQiangduan() );
                                playerB.setIsTeamA( 1 );
                                playerB.setPalyid( MyApplication.getIndex() );
                                playerB.save( new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                        if (e == null) {
                                            Log.d( "success", "done: success" );
                                        } else {
                                            Log.d( "defet", "done: defet" );
                                        }
                                    }
                                } );
                            }
                            finish();

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
                play.stop();
                attack.stop();
                attack.setTextColor( Color.rgb( 19, 164, 159 ) );
                start = false;
            }
        } );
        attack.setOnClickListener( this );
        attack.setOnTimeCompleteListener( new MyClock.OnTimeCompleteListener() {
            @Override
            public void onTimeComplete1() {
                attack.initTime( 1 * MyApplication.getBminattacktime() );
            }
        } );
        addViewItemA( null );
        addViewItemB( null );
        setFragment( 1 );
    }

    //初始化UI
    private void initlayout() {
        setoffA = (Button) this.findViewById( R.id.offA );
        setoffB = (Button) this.findViewById( R.id.offB );
        playerA = (Button) this.findViewById( R.id.btPlayA );
        playerB = (Button) this.findViewById( R.id.btPlayB );
        addsetoffA = (LinearLayout) this.findViewById( R.id.setoffA );
        addsetoffB = (LinearLayout) this.findViewById( R.id.setoffB );
        game = (LinearLayout) this.findViewById( R.id.game_title );
        TeamAname = (TextView) this.findViewById( R.id.game_tv_teamA );
        TeamBname = (TextView) this.findViewById( R.id.game_tv_teamB );
        stealsA = (TextView) this.findViewById( R.id.game_tv_stealA );
        stealsB = (TextView) this.findViewById( R.id.game_tv_stealB );
        TeamAscore = (TextView) this.findViewById( R.id.sorceA );
        TeamBscore = (TextView) this.findViewById( R.id.sorceB );
        setdijijie = (TextView) this.findViewById( R.id.setPlayTime );
        attack = (MyClock) this.findViewById( R.id.game_attack_time );
        play = (MyCountDownClock) this.findViewById( R.id.game_play_time );
        img_teamA = (ImageView) this.findViewById( R.id.img_game_teamA );
        img_teamB = (ImageView) this.findViewById( R.id.img_game_teamB );
    }

    //添加暂停
    private void addViewItemA(View view) {
        if (addsetoffA.getChildCount() == 0) {
            for (int i = 0; i < MyApplication.getOneoffnum(); i++) {
                LayoutInflater _inflater = getLayoutInflater();
                View addViewA = _inflater.inflate( R.layout.imageadd2, null );
                addsetoffA.addView( addViewA );
                countsA++;
            }
        }

    }

    private void addViewItemB(View view) {
        if (addsetoffB.getChildCount() == 0) {
            for (int i = 0; i < MyApplication.getOneoffnum(); i++) {
                LayoutInflater _inflater = getLayoutInflater();
                View addViewB = _inflater.inflate( R.layout.imageadd, null );
                addsetoffB.addView( addViewB );
                countsB++;
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.offA:
                final View childAtA = addsetoffA.getChildAt( countsA - 1 );
                addsetoffA.removeView( childAtA );
                countsA--;
                if (countsA == 0) {
                    Toast.makeText( GamePlay.this, "最后一个了", Toast.LENGTH_SHORT ).show();
                } else if (countsA < 0) {
                    Toast.makeText( GamePlay.this, "你已经用完了", Toast.LENGTH_SHORT ).show();

                }
                break;
            case R.id.offB:
                final View childAtB = addsetoffB.getChildAt( countsB - 1 );
                addsetoffB.removeView( childAtB );
                countsB--;
                if (countsB == 0) {
                    Toast.makeText( GamePlay.this, "最后一个了", Toast.LENGTH_SHORT ).show();
                } else if (countsB < 0) {
                    Toast.makeText( GamePlay.this, "你已经用完了", Toast.LENGTH_SHORT ).show();

                }
                break;
            case R.id.btPlayA:
                setFragment( 0 );
                if (ispressA == false) {
                     playerB.setBackgroundColor( Color.rgb( 165,109,83 ) );
                    playerA.setBackgroundColor(  Color.rgb( 237,236,173 ) );
                    ispressA = true;
                    ispressB = false;
                }
                break;
            case R.id.btPlayB:
                setFragment( 2 );
                if (ispressB == false) {
                   playerA.setBackgroundColor( Color.rgb( 165,109,83) );
                    playerB.setBackgroundColor(  Color.rgb( 237,236,173  ) );
                    ispressB = true;
                    ispressA = false;
                }
                break;
            case R.id.game_title:
                setFragment( 1 );

                ispressA = false;
                ispressB = false;
                playerA.setBackgroundColor( Color.rgb( 165,109,83 ) );
                playerB.setBackgroundColor(  Color.rgb( 165,109,83 ) );
                break;
            case R.id.game_play_time:
                if (start == false) {
                    play.start();
                    attack.start();
                    play.setTextColor( Color.rgb( 19, 164, 159 ) );
                    attackstart = true;
                    start = true;
                } else if (start = true) {
                    play.stop();
                    attack.stop();
                    play.setTextColor( Color.rgb( 235, 10, 10 ) );
                    start = false;
                    attackstart = false;
                }
                break;
            case R.id.game_attack_time:
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
                    attack.initTime( 1 * MyApplication.getBminattacktime() );
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

    private void setFragment(int i) {
        // 开启一个Fragment事务
        android.support.v4.app.FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments( transaction );
        switch (i) {
            case 0:
                if (gamePlayer == null) {
                    gamePlayer = new GamePlayer();
                    transaction.add( R.id.show, gamePlayer );
                } else {
                    transaction.show( gamePlayer );
                }
                break;
            case 1:

                gameSorce = new GameSorce();
                transaction.add( R.id.show, gameSorce );

                break;
            case 2:
                if (gamePlayerB == null) {
                    gamePlayerB = new GamePlayerB();
                    transaction.add( R.id.show, gamePlayerB );
                } else {
                    transaction.show( gamePlayerB );
                }
                break;

        }
        transaction.commit();
    }

    private void hideFragments(android.support.v4.app.FragmentTransaction transaction) {
        if (gamePlayer != null) {
            transaction.hide( gamePlayer );
        }
        if (gameSorce != null) {
            transaction.hide( gameSorce );
        }
        if (gamePlayerB != null) {
            transaction.hide( gamePlayerB );
        }

    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - currentBackPressedTime > BACK_PRESSED_INTERVAL) {
            currentBackPressedTime = System.currentTimeMillis();
            AlertDialog.Builder builder = new AlertDialog.Builder( this );
            builder.setTitle( "警告" ).setMessage( "是否保存并退出" );
            builder.setPositiveButton( "确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    PlayHistoryfinal playHistoryfinal=new PlayHistoryfinal();
                    playHistoryfinal.setPlayid( String.valueOf( MyApplication.getIndex() ));
                    playHistoryfinal.setNameA( MyApplication.getTeamA() );
                    playHistoryfinal.setNameB( MyApplication.getTeamB() );
                    playHistoryfinal.setScoreA( String.valueOf( MyApplication.getScoreA() ) );
                    playHistoryfinal.setScoreB( String.valueOf( MyApplication.getScoreB() ) );
                    playHistoryfinal.save( new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            Log.d( "result", "done: succcess" );
                        }
                    } );
                    for (int i = 0; i < MyApplication.getPlayersA().size(); i++) {
                        PlayerLinshi playerA = new PlayerLinshi();
                        playerA.setNum( MyApplication.getPlayersA().get( i ).getNum() );
                        playerA.setName( MyApplication.getPlayersA().get( i ).getName() );
                        msorceA = msorceA + MyApplication.getPlayersA().get( i ).getScore();
                        playerA.setScore( MyApplication.getPlayersA().get( i ).getScore() );
                        mfanguiA = mfanguiA + MyApplication.getPlayersA().get( i ).getFangui();
                        playerA.setFangui( MyApplication.getPlayersA().get( i ).getFangui() );
                        mgaimaoA = mgaimaoA + MyApplication.getPlayersA().get( i ).getGaimao();
                        playerA.setGaimao( MyApplication.getPlayersA().get( i ).getGaimao() );
                        mzhugongA = mzhugongA + MyApplication.getPlayersA().get( i ).getZhugong();
                        playerA.setZhugong( MyApplication.getPlayersA().get( i ).getZhugong() );
                        mlanbanA = mlanbanA + MyApplication.getPlayersA().get( i ).getLanban();
                        playerA.setLanban( MyApplication.getPlayersA().get( i ).getLanban() );
                        mqiangduanA = mqiangduanA + MyApplication.getPlayersA().get( i ).getQiangduan();
                        playerA.setQiangduan( MyApplication.getPlayersA().get( i ).getQiangduan() );
                        playerA.setIsTeamA( 0 );
                        playerA.setPalyid( MyApplication.getIndex() );
                        playerA.save( new SaveListener<String>(){
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    Log.d( "success", "done: success" );
                                } else {
                                    Log.d( "defet", "done: defet" );
                                }
                            }
                        } );
                    }
                    for (int i = 0; i <  MyApplication.getPlayersB().size(); i++) {
                        PlayerLinshi playerB = new PlayerLinshi();
                        playerB.setNum( MyApplication.getPlayersB().get( i ).getNum() );
                        playerB.setName( MyApplication.getPlayersB().get( i ).getName() );
                        msorceB = msorceB + MyApplication.getPlayersB().get( i ).getScore();
                        playerB.setScore( MyApplication.getPlayersB().get( i ).getScore() );
                        mfanguiB = mfanguiB + MyApplication.getPlayersB().get( i ).getFangui();
                        playerB.setFangui( MyApplication.getPlayersB().get( i ).getFangui() );
                        mgaimaoB = mgaimaoB + MyApplication.getPlayersB().get( i ).getGaimao();
                        playerB.setGaimao( MyApplication.getPlayersB().get( i ).getGaimao() );
                        mzhugongB = mzhugongB + MyApplication.getPlayersB().get( i ).getZhugong();
                        playerB.setZhugong( MyApplication.getPlayersB().get( i ).getZhugong() );
                        mlanbanB = mlanbanB + MyApplication.getPlayersB().get( i ).getLanban();
                        playerB.setLanban( MyApplication.getPlayersB().get( i ).getLanban() );
                        mqiangduanB = mqiangduanB + MyApplication.getPlayersB().get( i ).getQiangduan();
                        playerB.setQiangduan(  MyApplication.getPlayersB().get( i ).getQiangduan() );
                        playerB.setIsTeamA( 1 );
                        playerB.setPalyid( MyApplication.getIndex() );
                        playerB.save( new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    Log.d( "success", "done: success" );
                                } else {
                                    Log.d( "defet", "done: defet" );
                                }
                            }
                        } );
                    }
                    finish();
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
