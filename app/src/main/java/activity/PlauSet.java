package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myclassesbasketball.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Person.Team;
import app.MyApplication;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class PlauSet extends AppCompatActivity implements View.OnClickListener {


    private EditText onetime;//单节比赛时间
    private EditText attacktime;//进攻时间
    private EditText oneoffnum;//单节暂停数目
    private EditText onePower;//单节犯规奖励
    private EditText personnum;//单节个人犯满数目
    private EditText teamAname;//球队A名字
    private EditText teamBname;//球队B名字
    private Button login;//确认
    private Button bt;

    private  static String teamA_name=null;
    private  static String teamB_name=null;
    private static String min_play_time=null;
    private static String min_attack_time=null;
    private static String one_off_num=null;
    private static String one_power_num=null;
    private static String perisonnum=null;
    public static String counts = null;
    public static int index=1;
    public static int index1=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_plau_set );
        initlayout();
        login.setOnClickListener( this );
        bt=(Button)findViewById( R.id.bt_fanhui );
        bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( PlauSet.this,PlayModel.class );
                startActivity( intent );
            }
        } );
    }

    //初始化UI
    public void initlayout() {
        teamAname = (EditText) this.findViewById( R.id.dm1 );
        teamBname = (EditText) this.findViewById( R.id.dm2 );
        onetime = (EditText) this.findViewById( R.id.dj_time );
        attacktime = (EditText) this.findViewById( R.id.jg_time );
        oneoffnum = (EditText) this.findViewById( R.id.dj_zt );
        onePower = (EditText) this.findViewById( R.id.dj_fgjl );
        personnum = (EditText) this.findViewById( R.id.dr_fms );
        login = (Button) findViewById( R.id.btn_confirm );

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_confirm:
//                if(teamAname.getText().toString()==null||teamBname.getText().toString()==null||
//                        oneoffnum.getText().toString()==null||onetime.getText().toString()==null
//                        ||attacktime.getText().toString()==null||onePower.getText().toString()==null||
//                        personnum.getText().toString()==null)

                teamA_name = teamAname.getText().toString();
                teamB_name = teamBname.getText().toString();
                one_off_num = oneoffnum.getText().toString();
                min_play_time = onetime.getText().toString();
                min_attack_time = attacktime.getText().toString();
                one_power_num =  onePower.getText().toString() ;
                perisonnum =  personnum.getText().toString() ;

                if (teamA_name != null&&teamB_name!=null&&min_play_time != null &&
                                isNumber( String.valueOf( min_play_time ) )&&
                        one_off_num!=null&&isNumber( one_off_num )&&
                        min_attack_time != null && isNumber(  min_attack_time )
                        &&one_power_num != null && isNumber(  one_power_num  )
                        &&perisonnum != null && isNumber( perisonnum ) )
                {

                    MyApplication.setTeamA( teamA_name );
                    MyApplication.setTeamB( teamB_name );
                    MyApplication.setOneoffnum( Integer.parseInt( one_off_num ) );
                    MyApplication.setBminplaytime( Integer.parseInt( min_play_time ) );
                    MyApplication.setBminattacktime( Integer.parseInt( min_attack_time ) );
                    MyApplication.setOnepower( Integer.parseInt( one_power_num ) );
                    MyApplication.setOnefullnum( Integer.parseInt( perisonnum ) );
                    Intent intent = new Intent( PlauSet.this, AddPlayer.class );
                    Team p2 = new Team();
                    p2.setTeamid( index++ );
                    MyApplication.setIndex( index1++ );
                    p2.setTeamA(teamA_name);
                    p2.setTeamB(teamB_name);
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
                    startActivity( intent );
                }
                else {
//                    new AlertDialog.Builder( PlauSet.this ).setTitle( "警告" )
//                            .setMessage( "所有输入框都不能为空并且价格必须是数字" ).setNegativeButton( "确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    Toast.makeText( PlauSet.this, "请重新输入", Toast.LENGTH_SHORT ).show();
//                                }
//                            }
//                    ).show();
                    Toast.makeText( PlauSet.this, "请重新输入", Toast.LENGTH_SHORT ).show();
                }

                break;
            default:
                break;


        }
    }

    //判断是否是数字
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
