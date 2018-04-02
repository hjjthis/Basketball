package activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.myclassesbasketball.R;

import java.util.ArrayList;

import Person.PlayHistoryfinal;
import Person.Player;
import adapter.HistoryAdapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import db.DBManager;

public class PlayHistory extends AppCompatActivity {

    private ListView listView;//显示比赛记录
    ArrayList<Player> players=new ArrayList<Player>(  );
    private Button bt;

    @Override
    protected void onDestroy() {
        players.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_play_history );
        bt=(Button)findViewById( R.id.bt_fanhui ) ;
        Bundle bundle = getIntent().getExtras();
        players = bundle.getParcelableArrayList("list");
        listView=(ListView)findViewById( R.id.playerhistorysore );
        HistoryAdapter historyAdapter=new HistoryAdapter(players,PlayHistory.this );
        listView.setAdapter( historyAdapter );
        bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent( PlayHistory.this,PlayNum.class );
               startActivity( intent );
            }
        } );
    }
}
