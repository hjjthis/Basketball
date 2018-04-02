package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.myclassesbasketball.R;

import cn.bmob.v3.Bmob;
import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_EXIT = "exit";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        Bmob.initialize(this, "e2308b716934a5696972c6173ba4953f");
        SQLiteStudioService.instance().start( this );
    }

    @Override
    protected void onDestroy() {
        SQLiteStudioService.instance().stop();
        super.onDestroy();
    }

    //简易计分模式
    public void jifen(View view) {
        Intent intent=new Intent();
        intent.setClass(MainActivity.this,jianyijifen.class);
        overridePendingTransition(0,0);
        startActivity(intent);

    }

    //简易计时模式
    public void jishi(View view) {
        Intent intent=new Intent();
        intent.setClass(MainActivity.this,jianyijishi.class);
        overridePendingTransition(0,0);
        startActivity(intent);
    }

    //比赛模式
    public void bisia(View view) {
        Intent intent=new Intent( MainActivity.this,PlayModel.class );
        startActivity( intent );
        overridePendingTransition( 0,0 );
    }
    public void quit(View view)
    {

        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(MainActivity.TAG_EXIT, true);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            boolean isExit = intent.getBooleanExtra(TAG_EXIT, false);
            if (isExit) {
                this.finish();
            }
        }
    }

}
