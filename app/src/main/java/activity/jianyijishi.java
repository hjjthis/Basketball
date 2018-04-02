package activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myclassesbasketball.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.MyApplication;

public class jianyijishi extends AppCompatActivity implements View.OnClickListener {

    private EditText min_play_time; //单节时间
    private EditText min_attack_time;//进攻时间
    private Button login;
    private Button bt;
    //初始化数据
    int minPlay = 0;
    int minAttack = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_jianyijishi );
        initLayout();
        login.setOnClickListener( this );
        bt=(Button)findViewById( R.id.bt_fanhui );
        bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( jianyijishi.this,MainActivity.class );
                startActivity( intent );
            }
        } );
    }

    //初始化控件
    private void initLayout() {
        min_play_time = (EditText) findViewById( R.id.set_play_time );
        min_attack_time = (EditText) findViewById( R.id.set_attack_time );
        login = (Button) findViewById( R.id.login );
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.login:
                String min_play = min_play_time.getText().toString();
                String min_attack = min_attack_time.getText().toString();
                if (min_play != null && isNumber( min_play ) && min_attack != null && isNumber( min_attack )) {
                    minPlay = Integer.parseInt( min_play );
                    MyApplication.setMinPlayTime( minPlay );
                    minAttack = Integer.parseInt( min_attack );
                    MyApplication.setMinAttackTime( minAttack );
                    Intent intent = new Intent( jianyijishi.this, Cotrol_Time.class );
                    startActivity( intent );
                } else {
                    new AlertDialog.Builder( jianyijishi.this ).setTitle( "警告" )
                            .setMessage( "所有输入框都不能为空并且价格必须是数字" ).setNegativeButton( "确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText( jianyijishi.this, "请重新输入", Toast.LENGTH_SHORT ).show();
                                }
                            }
                    ).show();
                }
                break;
        }
    }

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
}
