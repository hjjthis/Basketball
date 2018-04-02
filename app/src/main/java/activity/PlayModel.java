package activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.myclassesbasketball.R;

import db.DBManager;

public class PlayModel extends AppCompatActivity implements View.OnClickListener {

    private Button bt_caipan;
    private Button bt_fanhui;
    private Button bt_chuangjian;
    private Button bt_guanzhong;
    private ImageView img_key;
    private ImageView img_baseketball;
    private ImageView img_guanzhong;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_play_model );
        initlayout();
        dbManager=DBManager.getInstance( this );
        bt_chuangjian.setOnClickListener( this );
        bt_fanhui.setOnClickListener( this );
        bt_caipan.setOnClickListener( this );
        bt_guanzhong.setOnClickListener( this );
        img_key.setOnClickListener( this );
        img_baseketball.setOnClickListener( this );
        img_guanzhong.setOnClickListener( this );
    }

    private void initlayout() {
        bt_caipan = (Button) findViewById( R.id.bt_key );
        bt_chuangjian = (Button) findViewById( R.id.bt_chuangjian );
        bt_guanzhong = (Button) findViewById( R.id.bt_guanzhong );
        bt_fanhui = (Button) findViewById( R.id.bt_fanhui );
        img_key = (ImageView) findViewById( R.id.img_key );
        img_baseketball = (ImageView) findViewById( R.id.img_basketball );
        img_guanzhong = (ImageView) findViewById( R.id.img_guanzhong );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_key:
                new AlertDialog.Builder( PlayModel.this ).setTitle( "警告" )
                        .setMessage( "是否删除比赛记录" ).setNegativeButton( "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dbManager.deleteTable( "Player" );
                                Toast.makeText( PlayModel.this, "删除成功", Toast.LENGTH_SHORT ).show();

                            }
                        }
                ).show();
                break;
            case R.id.bt_chuangjian:
                Intent intent = new Intent( PlayModel.this, PlauSet.class );
                startActivity( intent );
                break;
            case R.id.bt_guanzhong:
                Intent intent_guanzhong_bt = new Intent( PlayModel.this, PlayNum.class );
                startActivity( intent_guanzhong_bt );
                break;
            case R.id.img_key:
                new AlertDialog.Builder( PlayModel.this ).setTitle( "警告" )
                        .setMessage( "是否删除比赛记录" ).setNegativeButton( "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dbManager.deleteTable( "Player" );
                                Toast.makeText( PlayModel.this, "删除成功", Toast.LENGTH_SHORT ).show();

                            }
                        }
                ).show();
                break;
            case R.id.img_basketball:
                Intent intent_img_basketball = new Intent( PlayModel.this, PlauSet.class );
                startActivity( intent_img_basketball );
                break;
            case R.id.img_guanzhong:
                Intent intent_guanzhong = new Intent( PlayModel.this, PlayNum.class );
                startActivity( intent_guanzhong );
                break;
            case R.id.bt_fanhui:
                Intent main = new Intent( PlayModel.this, MainActivity.class );
                startActivity( main );

        }
    }
}
