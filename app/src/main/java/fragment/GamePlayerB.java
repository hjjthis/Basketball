package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.myclassesbasketball.R;

import java.util.ArrayList;

import Person.Player;
import adapter.GamePlayerAdapter;
import app.MyApplication;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import db.DBManager;

import static android.content.ContentValues.TAG;


public class GamePlayerB extends Fragment {
    private ArrayList<Player> playerB = new ArrayList<Player>();
    private ArrayList<Player> playerB1 = new ArrayList<Player>();
    private ListView listViewB;
    private Button jia;
    private TextView scoreB;
    private TextView fanguiB;
    private int msorceB = 0;
    private int mfanguiB = 0;
    private int mzhugongB = 0;
    private int mlanbanB = 0;
    private int mqiangduanB = 0;
    private int mgaimaoB = 0;
    private static int one = 0;
    private static int two = 0;
    private static int three = 0;
    private static int four = 0;
    private DBManager dbManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_game_player_b, container, false );
        Bundle bundle = getActivity().getIntent().getExtras();
        playerB = bundle.getParcelableArrayList( "playerB" );
        scoreB = getActivity().findViewById( R.id.sorceB );
        dbManager = DBManager.getInstance( getContext() );
        fanguiB = getActivity().findViewById( R.id.game_tv_stealB );
        listViewB = (ListView) view.findViewById( R.id.playersore );

        final GamePlayerAdapter gamePlayerAdapterB = new GamePlayerAdapter( playerB, getActivity() );
        listViewB.setAdapter( gamePlayerAdapterB );
        jia = (Button) view.findViewById( R.id.bt_jiahao );
        jia.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePlayerAdapterB.notifyDataSetChanged();
                playerB1.clear();
                for (int i = 0; i < playerB.size(); i++) {
                    Player playerB = new Player();
                    playerB.setNum( MyApplication.getPlayers().get( i ).getNum() );
                    playerB.setName( MyApplication.getPlayers().get( i ).getName() );
                    msorceB = msorceB + MyApplication.getPlayers().get( i ).getScore();
                    playerB.setScore( MyApplication.getPlayers().get( i ).getScore() );
                    mfanguiB = mfanguiB + MyApplication.getPlayers().get( i ).getFangui();
                    playerB.setFangui( MyApplication.getPlayers().get( i ).getFangui() );
                    mgaimaoB = mgaimaoB + MyApplication.getPlayers().get( i ).getGaimao();
                    playerB.setGaimao( MyApplication.getPlayers().get( i ).getGaimao() );
                    mzhugongB = mzhugongB + MyApplication.getPlayers().get( i ).getZhugong();
                    playerB.setZhugong( MyApplication.getPlayers().get( i ).getZhugong() );
                    mlanbanB = mlanbanB + MyApplication.getPlayers().get( i ).getLanban();
                    playerB.setLanban( MyApplication.getPlayers().get( i ).getLanban() );
                    mqiangduanB = mqiangduanB + MyApplication.getPlayers().get( i ).getQiangduan();
                    playerB.setQiangduan(  MyApplication.getPlayers().get( i ).getQiangduan() );
                    playerB.setIsTeamA( 1 );
                    playerB.setPalyid( MyApplication.getIndex() );
                    playerB1.add( playerB );
                    dbManager.modifyPlayerScore( playerB );
                    Log.d( TAG, "onClick: " + i );
                }
                MyApplication.setPlayersB( playerB1 );
                Log.d( TAG, "onClick: success"  );
                scoreB.setText( String.valueOf( msorceB ) );
                MyApplication.setScoreB( msorceB );
                MyApplication.setFanguiB( mfanguiB );
                MyApplication.setLanbanB( mlanbanB );
                MyApplication.setQiangdaunB( mqiangduanB );
                MyApplication.setGaimaoB( mgaimaoB );
                MyApplication.setZhugongB( mzhugongB );
                if (MyApplication.getOnefanguiA() == 1) {
                    if (mfanguiB >= MyApplication.getOnefullnum()) {
                        fanguiB.setText( String.valueOf( mfanguiB ) );
                        one = mfanguiB;
                        Toast.makeText( getActivity().getApplicationContext(), "球队犯规数目满"
                                , Toast.LENGTH_SHORT ).show();
                    } else {
                        fanguiB.setText( String.valueOf( mfanguiB ) );
                        one = mfanguiB;
                    }

                }
                if (MyApplication.getOnefanguiA() == 2) {
                    if (mfanguiB >= MyApplication.getOnefullnum()) {
                        fanguiB.setText( String.valueOf( mfanguiB - one ) );
                        two = mfanguiB - one;
                        Toast.makeText( getActivity().getApplicationContext(), "球队犯规数目满"
                                , Toast.LENGTH_SHORT ).show();
                    } else {
                        two = mfanguiB - one;
                        fanguiB.setText( String.valueOf( two ) );
                    }

                }
                if (MyApplication.getOnefanguiA() == 3) {
                    if (mfanguiB >= MyApplication.getOnefullnum()) {
                        fanguiB.setText( String.valueOf( mfanguiB - two ) );
                        three = mfanguiB - two;
                        Toast.makeText( getActivity().getApplicationContext(), "球队犯规数目满"
                                , Toast.LENGTH_SHORT ).show();
                    } else {
                        three = mfanguiB - two;
                        fanguiB.setText( String.valueOf( three ) );
                    }

                }
                if (MyApplication.getOnefanguiA() == 4) {
                    if (mfanguiB >= MyApplication.getOnefullnum()) {
                        fanguiB.setText( String.valueOf( mfanguiB - three ) );
                        four = mfanguiB - three;
                        Toast.makeText( getActivity().getApplicationContext(), "球队犯规数目满"
                                , Toast.LENGTH_SHORT ).show();
                    } else {
                        four = mfanguiB - three;
                        fanguiB.setText( String.valueOf( four ) );
                    }

                }
                msorceB = 0;
                mfanguiB = 0;
            }
        } );

        return view;
    }

}
