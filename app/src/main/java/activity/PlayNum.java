package activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myclassesbasketball.R;

import java.util.ArrayList;
import java.util.List;

import Person.PlayHistoryfinal;
import Person.Player;
import Person.PlayerLinshi;
import Person.Team;
import adapter.PlayNumAdapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import db.DBManager;

public class PlayNum extends AppCompatActivity {

    ListView listView;
    ArrayList<PlayHistoryfinal> team=new ArrayList<PlayHistoryfinal>(  );
    ArrayList<PlayerLinshi> playerLinshis=new ArrayList<PlayerLinshi>(  );
    ArrayList<PlayerLinshi> playerLinshis1=new ArrayList<PlayerLinshi>(  );
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_play_num );
        listView = (ListView) findViewById( R.id.play_num );
        //排序查找PlayerLinshi
        loadDatePlayer();
      //排序查找PlayerHistory
        loadDate();
        bt=(Button)findViewById( R.id.bt_fanhui );
        bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( PlayNum.this,PlayModel.class );
                startActivity( intent );
            }
        } );
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView text=(TextView)view.findViewById( R.id.setPlayTime );
                int ID=Integer.valueOf( text.getText().toString() );
                Log.d( "success", "onItemClick: "+ID );
                playerLinshis1.clear();
                for(int i=0;i<playerLinshis.size();i++)
                {
                    Log.d( "success", "playerid: "+playerLinshis.get( i ).getPalyid() );
                    if(playerLinshis.get( i ).getPalyid()==ID)
                    {
                        playerLinshis1.add( playerLinshis.get( i ) );
                    }
                    else
                    {
                        Log.d( "result", "onItemClick: "+playerLinshis.get( i ).getPalyid() );

                    }
                }

                for(int i=0;i<team.size();i++)
                {

                    if(playerLinshis1.get( i ).getPalyid()==ID)
                    {

                        Intent intent=new Intent( PlayNum.this,PlayHistory.class );
                        Bundle bundle=new Bundle(  );
                        bundle.putParcelableArrayList( "list", playerLinshis1 );
                        intent.putExtras(bundle);
                        startActivity( intent );
                    }
                    else
                    {
                        Log.d( "result", "onItemClick: "+playerLinshis1.get( i ).getPalyid() );
                    }

                }
            }
        } );
    }

    //查询表PlayHistoryfinal；
    private void loadDate() {

        BmobQuery<PlayHistoryfinal> bmobQuery = new BmobQuery<PlayHistoryfinal>();
        bmobQuery.order( "-createdAt" );// 按照时间降序
        bmobQuery.findObjects( new FindListener<PlayHistoryfinal>() {  //按行查询，查到的数据放到List<Goods>的集合
            @Override
            public void done(List<PlayHistoryfinal> list, BmobException e) {
                if (e == null) {
                    team=(ArrayList<PlayHistoryfinal>)list;
                    listView.setAdapter( new PlayNumAdapter( list, PlayNum.this ) );
                }
                else {
                    Log.d( "result", "done: "+e.getMessage() );
                }

            }
        } );
    }
    //查询表PlayerLinshi；
    private void loadDatePlayer() {

        BmobQuery<PlayerLinshi> bmobQuery = new BmobQuery<PlayerLinshi>();
        bmobQuery.order( "-createdAt" );// 按照时间降序
        bmobQuery.findObjects( new FindListener<PlayerLinshi>() {  //按行查询，查到的数据放到List<Goods>的集合
            @Override
            public void done(List<PlayerLinshi> list, BmobException e) {
                if (e == null) {
                    playerLinshis=(ArrayList<PlayerLinshi>)list;
                }
                else {
                    Log.d( "result", "done: "+e.getMessage() );
                }

            }
        } );
    }
}
