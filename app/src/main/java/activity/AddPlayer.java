package activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myclassesbasketball.R;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Person.Player;
import Person.Team;
import adapter.AddPlayerAdapter;
import app.MyApplication;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import db.DBManager;

public class AddPlayer extends AppCompatActivity implements View.OnClickListener {


    //定义控件

    private TextView teamA;//球队A的名字
    private TextView teamB;//球队B的名字
    private ListView listTeamA;//球队A添加的球员显示
    private ListView listTeamB;//球队B添加的球员显示
    private ImageView finish;//提交数据进入比赛界面
    private ImageView addPlayerA;//点击跳出dialog，添加球队A的球员
    private ImageView addPlayerB;//点击跳出dialog，添加球队的球员
    private ImageView addimgA;//点击获取手机相册，设置队标A
    private ImageView addimgB;//点击获取手机相册，设置队标B
    private Button bt;//返回按钮
    private Boolean isteamA = false;//队标回传时判断队伍
    private Boolean isteamB = false;//队标回传时判断队伍
    private static final int CODE_PHOTO_REQUEST = 1; //手机相册
    private static final int CODE_PHOTO_CLIP = 3;   //相片裁剪

    //定义数据结构

    private ArrayList<Player> playerTeamA = new ArrayList<Player>();
    private ArrayList<Player> playerTeamB = new ArrayList<Player>();
    private AddPlayerAdapter adapterA;
    private AddPlayerAdapter adapterB;

    //DBManager 的实例
    private DBManager scoreDB;

    //处理adapter中的点击事件
    public static Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        //无标题

        requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView( R.layout.activity_add_player );

        //初始化UI

        initUI();

        //初始化数据
        teamA.setText( MyApplication.getTeamA() );
        teamB.setText( MyApplication.getTeamB() );
        adapterA = new AddPlayerAdapter( playerTeamA, AddPlayer.this );
        adapterB = new AddPlayerAdapter( playerTeamB, AddPlayer.this );
        MyApplication.setCounts( 0 );
        MyApplication.setScoreA( 0 );
        MyApplication.setScoreB( 0 );
        MyApplication.setZhugongA( 0 );
        MyApplication.setZhugongB( 0 );
        MyApplication.setLanbanA( 0 );
        MyApplication.setLanbanB( 0 );
        MyApplication.setQiangdaunA( 0 );
        MyApplication.setQiangdaunB( 0 );
        MyApplication.setFanguiA( 0 );
        MyApplication.setFanguiB( 0 );
        MyApplication.setGaimaoA( 0 );
        MyApplication.setGaimaoB( 0 );
        listTeamA.setAdapter( adapterA );
        listTeamB.setAdapter( adapterB );

        //声明监听器

        finish.setOnClickListener( this );
        addPlayerA.setOnClickListener( this );
        addPlayerB.setOnClickListener( this );
        addimgA.setOnClickListener( this );
        addimgB.setOnClickListener( this );
        bt=(Button)findViewById( R.id.bt_fanhui );
        bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( AddPlayer.this,PlauSet.class );
                startActivity( intent );
            }
        } );

        //获取DBManager实例

        scoreDB = DBManager.getInstance( this );

        //listview点击事件

        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        Player player = (Player) msg.obj;
                        modifyPlayer( player ); //修改球员数据
                        break;
                    default:
                        break;
                }
            }
        };
    }

    //初始化ui

    private void initUI() {
        addPlayerA = (ImageView) this.findViewById( R.id.iv_addPlayerA );
        addPlayerB = (ImageView) this.findViewById( R.id.iv_addPlayerB );
        finish = (ImageView) this.findViewById( R.id.acAdd_iv_start );
        listTeamA = (ListView) this.findViewById( R.id.list_playerA );
        listTeamB = (ListView) this.findViewById( R.id.list_playerB );
        teamA = (TextView) this.findViewById( R.id.tv_teamA );
        teamB = (TextView) this.findViewById( R.id.tv_teamB );
        addimgA = (ImageView) this.findViewById( R.id.img_teamA );
        addimgB = (ImageView) findViewById( R.id.img_teamB );
    }

    //点击事件

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.acAdd_iv_start:
                Intent intent = new Intent( AddPlayer.this, GamePlay.class );
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList( "playerA", playerTeamA );
                bundle.putParcelableArrayList( "playerB", playerTeamB );
                intent.putExtras( bundle );
                startActivity( intent );
                break;
            case R.id.iv_addPlayerA:
                //队伍A添加队员
                addPlayer( "A" );
                break;
            case R.id.iv_addPlayerB:
                //队伍B添加队员
                addPlayer( "B" );
                break;
            case R.id.img_teamA:
                isteamA = true;
                isteamB = false;
                getPicFromLocal();
                break;
            case R.id.img_teamB:
                isteamB = true;
                isteamA = false;
                getPicFromLocal();

        }
    }


    //添加球员数据

    private void addPlayer(String string) {
        final String AorB = string;
        //动态加载[view_addplayer]布局
        View view = LayoutInflater.from( AddPlayer.this ).inflate( R.layout.view_addplayer, null, false );
        ActionBar.LayoutParams params = new ActionBar.LayoutParams( ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT );
        view.setLayoutParams( params );
        //设置为dialog
        AlertDialog.Builder builder = new AlertDialog.Builder( AddPlayer.this );
        builder.setView( view );
        final AlertDialog dialog = builder.show();
        //获取控件id
        final EditText et_num = (EditText) view.findViewById( R.id.viewadd_et_num );
        final EditText et_name = (EditText) view.findViewById( R.id.viewadd_et_name );
        Button btn_confirm = (Button) view.findViewById( R.id.viewadd_btn_confirm );
        Button btn_cancel = (Button) view.findViewById( R.id.viewadd_btn_cancel );
        //取消按钮的点击事件
        btn_cancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        } );
        //确认按钮的点击事件
        btn_confirm.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = et_num.getText().toString();
                if (num != null && isNumber( num ) == false) {
                    Toast.makeText( AddPlayer.this, "输入错误，号码应该为数字", Toast.LENGTH_SHORT ).show();
                    dialog.dismiss();
                } else {
                    String name = et_name.getText().toString();
                    Player player = new Player();
                    player.setNum( Integer.parseInt( num ) );
                    player.setName( name );

                    if ("A".equals( AorB )) {
                        //0代表TeamA
                        player.setIsTeamA( 0 );
                        //将player添加到list中，并更新数据库和UI
                        playerTeamA.add( player );
                        //向数据库添加一名球员
                        scoreDB.insertOnePlayer( player );
                        Player player1=new Player(  );
                        player1.setPalyid( MyApplication.getIndex() );
                        player1.setNum( Integer.valueOf( num ) );
                        player1.setName(name);
                        player1.setIsTeamA(0);
                        player1.save(new SaveListener<String>() {
                            @Override
                            public void done(String objectId,BmobException e) {
                                if(e==null){
                                    Log.d( "success", "done: success" );

                                }else{
                                    Log.d( "defet", "done: defet" );
                                }
                            }
                        });
                        //刷新listview数据
                        adapterA.notifyDataSetChanged();
                    } else if ("B".equals( AorB )) {
                        //1代表TeamB
                        player.setIsTeamA( 1 );
                        //将player添加到list中，并更新数据库和UI
                        playerTeamB.add( player );
                        //向数据库添加一名球员
                        scoreDB.insertOnePlayer( player );
                        Player p2 = new Player();
                        p2.setNum( Integer.valueOf( num ) );
                        p2.setName(name);
                        p2.setIsTeamA(1);
                        p2.save(new SaveListener<String>() {
                            @Override
                            public void done(String objectId,BmobException e) {
                                if(e==null){
                                    Log.d( "success", "done: success" );
                                }else{
                                    Log.d( "defet", "done: defet" );
                                }
                            }
                        });
                        //刷新listview数据
                        adapterB.notifyDataSetChanged();
                    }
                    //退出dialog
                    dialog.dismiss();
                }
            }
        } );

    }

    //修改球员数据

    protected void modifyPlayer(final Player player) {
        View view = LayoutInflater.from( AddPlayer.this ).
                inflate( R.layout.view_modifyplayer, null, false );
        ActionBar.LayoutParams params = new ActionBar.LayoutParams( ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT );
        view.setLayoutParams( params );
        //新建dialog
        AlertDialog.Builder builder = new AlertDialog.Builder( AddPlayer.this );
        builder.setView( view );
        final AlertDialog dialog = builder.show();
        final EditText et_num = (EditText) view.findViewById( R.id.viewadd_et_num );
        final EditText et_name = (EditText) view.findViewById( R.id.viewadd_et_name );
        Button btn_confirm = (Button) view.findViewById( R.id.viewadd_btn_confirm );
        Button btn_cancel = (Button) view.findViewById( R.id.viewadd_btn_cancel );
        Button btn_delete = (Button) view.findViewById( R.id.viewadd_btn_delete );
        et_num.setText( player.getNum() + "" );
        et_name.setText( player.getName() );

        //删除球员

        btn_delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreDB.deleteOnePlayer( player );
                //判断属于哪一只球队
                if (player.getIsTeamA() == 0) {
                    playerTeamA.remove( player );
                    adapterA.notifyDataSetChanged();
                } else {
                    playerTeamB.remove( player );
                    adapterB.notifyDataSetChanged();
                }
                dialog.dismiss();
            }
        } );
        //取消按钮点击事件
        btn_cancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        } );
        //确认按钮点击事件
        btn_confirm.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = et_num.getText().toString();
                if (num != null && isNumber( num ) == false) {
                    Toast.makeText( AddPlayer.this, "输入错误，号码应该为数字", Toast.LENGTH_SHORT ).show();
                    dialog.dismiss();
                } else {
                    String name = et_name.getText().toString();
                    Player player_update = new Player();
                    player_update.setNum( Integer.parseInt( num ) );
                    player_update.setName( name );
                    if (player.getIsTeamA() == 0) {
                        //0代表TeamA
                        player_update.setIsTeamA( 0 );
                        scoreDB.modifyPlayerScore( player );
                        playerTeamA.remove( player );
                        playerTeamA.add( player_update );
                        adapterA.notifyDataSetChanged();
                    } else if (player.getIsTeamA() == 1) {
                        //1代表TeamB
                        player_update.setIsTeamA( 1 );
                        scoreDB.modifyPlayerScore( player );
                        playerTeamB.remove( player );
                        playerTeamB.add( player_update );
                        adapterB.notifyDataSetChanged();
                    }
                    dialog.dismiss();
                }
            }
        } );
    }

    //判断输入的是否为整数

    public Boolean isNumber(String str) {
        if (str == null || "".equals( str.trim() )) {
            return false;
        }
        Pattern pattern = Pattern.compile( "[0-9]*" );
        Matcher isNum = pattern.matcher( str.trim() );
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    //销毁数据
    @Override
    protected void onDestroy() {
        //清空Player表中的所有记录
//        if (playerTeamB.size() > 0 || playerTeamA.size() > 0) {
//
//            scoreDB.deleteTable("Player");
//        }
        super.onDestroy();
    }

    //获取本机相册
    private void getPicFromLocal() {
        Intent intent = new Intent();
        // 获取本地相册

        intent.setAction( Intent.ACTION_GET_CONTENT );
        intent.setType( "image/*" );

        startActivityForResult( intent, CODE_PHOTO_REQUEST );
    }

    //图片裁剪

    private void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent();
        intent.setAction( "com.android.camera.action.CROP" );
        intent.setDataAndType( uri, "image/*" );
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra( "crop", "true" );
        // aspectX aspectY 是宽高的比例
        intent.putExtra( "aspectX", 1 );
        intent.putExtra( "aspectY", 1 );
    /*outputX outputY 是裁剪图片宽高
     *这里仅仅是头像展示，不建议将值设置过高
     * 否则超过binder机制的缓存大小的1M限制
     * 报TransactionTooLargeException
     */
        intent.putExtra( "outputX", 150 );
        intent.putExtra( "outputY", 150 );
        intent.putExtra( "return-data", true );
        startActivityForResult( intent, CODE_PHOTO_CLIP );
    }

    //设置队伍A的图标
    private void setImageToHeadViewA(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable( "data" );
            addimgA.setImageBitmap( photo );
            MyApplication.setImgteamA( addimgA );
        }
    }

    //设置队伍B的图标
    private void setImageToHeadViewB(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable( "data" );
            addimgB.setImageBitmap( photo );
            MyApplication.setImgteamB( addimgB );
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText( AddPlayer.this, "设置失败", Toast.LENGTH_LONG ).show();
            return;
        }
        switch (requestCode) {
            case CODE_PHOTO_REQUEST:
                if (data != null) {
                    photoClip( data.getData() );
                }
                break;
            case CODE_PHOTO_CLIP:
                if (data != null && isteamA == true) {
                    setImageToHeadViewA( data );
                }
                if (data != null && isteamB == true) {
                    setImageToHeadViewB( data );
                }
                break;
        }
        super.onActivityResult( requestCode, resultCode, data );
    }
}
