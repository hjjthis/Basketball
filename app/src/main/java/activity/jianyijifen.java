package activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myclassesbasketball.R;

import java.util.ArrayList;
import java.util.List;

public class jianyijifen extends AppCompatActivity implements View.OnClickListener {

    // 退出时间
    private long currentBackPressedTime = 0;
    // 退出间隔
    private static final int BACK_PRESSED_INTERVAL = 2000;
    private Button addA; //队伍A分数+1；
    private Button addB;//队伍B分数+1
    private Button redo;//撤回之前步骤
    private TextView SorceA;//显示A队得分
    private TextView SorceB;//显示B队得分
    //初始化数据
    static int A = 0;
    static int B = 0;
    static int indexA = 0;
    static Boolean isSorceA = false;
    static Boolean isSorceB = false;
    List<Boolean> isSorce = new ArrayList<Boolean>();//记录加分点击顺序


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_jianyijifen );
        initLayout();
        addA.setOnClickListener( this );
        addB.setOnClickListener( this );
        redo.setOnClickListener( this );
    }

    //初始化UI
    private void initLayout() {
        addA = (Button) findViewById( R.id.add1 );
        addB = (Button) findViewById( R.id.add2 );
        redo = (Button) findViewById( R.id.reback );
        SorceA = (TextView) findViewById( R.id.Ascore );
        SorceB = (TextView) findViewById( R.id.Bscore );
    }

    //teamA+1
    public void add1() {
        SorceA.setText( String.valueOf( ++A ) );
        isSorceA = true;
        isSorceB = false;
        isSorce.add( true );
        indexA = indexA + 1;

    }

    //teamB+1
    public void add2() {
        SorceB.setText( String.valueOf( ++B ) );
        isSorceA = false;
        isSorceB = true;
        isSorce.add( false );
        indexA = indexA + 1;

    }

    //撤回
    public void Reback() {
        if (Integer.valueOf( SorceA.getText().toString() ) != 0 || Integer.valueOf( SorceB.getText().toString() ) != 0) {
            if (isSorce.get( indexA - 1 ) == false) {
                B--;
                SorceB.setText( String.valueOf( B ) );
                isSorce.remove( indexA - 1 );
                indexA = indexA - 1;
                Log.d( "resule", "indexA" + indexA );
            } else if (isSorce.get( indexA - 1 ) == true) {
                A--;
                SorceA.setText( String.valueOf( A ) );
                isSorce.remove( indexA - 1 );
                indexA = indexA - 1;
                Log.d( "resule1", "indexA" + indexA );
            }
        } else {
            Toast.makeText( jianyijifen.this, "已经是0:0了", Toast.LENGTH_SHORT ).show();
            indexA = 0;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add1:
                add1();
                break;
            case R.id.add2:
                add2();
                break;
            case R.id.reback:
                Reback();
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
