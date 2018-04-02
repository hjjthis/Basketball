package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import cn.bmob.v3.listener.UpdateListener;
import db.DBManager;

import static android.content.ContentValues.TAG;


public class GamePlayer extends Fragment {
    private ArrayList<Player> playerA = new ArrayList<Player>();
    private ArrayList<Player> playerA1 = new ArrayList<Player>();
    private ListView listViewA;
    private int msorceA = 0;
    private int mzhugongA = 0;
    private int mfanguiA = 0;
    private int mlanbanA = 0;
    private int mqiangduanA = 0;
    private int mgaimaoA = 0;
    private static int one = 0;
    private static int two = 0;
    private static int three = 0;
    private static int four = 0;
    Boolean isSorce = false;

    private ImageView imgteaA;
    private ImageView imgteaB;
    private DBManager dbManager;
    private Button jia;
    private Button jian;
    int mposition;
    private TextView scoreA;
    private TextView fanguiA;
    int num=MyApplication.getOnefullnum();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.fragment_game_player, container, false );

        dbManager = DBManager.getInstance( getContext() );

        listViewA = (ListView) view.findViewById( R.id.playersore );
        scoreA = (TextView) getActivity().findViewById( R.id.sorceA );
        fanguiA = (TextView) getActivity().findViewById( R.id.game_tv_stealA );
        Bundle bundle = getActivity().getIntent().getExtras();
        playerA = bundle.getParcelableArrayList( "playerA" );
        final GamePlayerAdapter playerAdapterA = new GamePlayerAdapter( playerA, getActivity() );
        listViewA.setAdapter( playerAdapterA );
        jia = (Button) view.findViewById( R.id.bt_jiahao );
        jia.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerAdapterA.notifyDataSetChanged();
                playerA1.clear();
                for (int i = 0; i < playerA.size(); i++) {
                    Player playerA = new Player();
                    playerA.setNum( MyApplication.getPlayers().get( i ).getNum() );
                    playerA.setName( MyApplication.getPlayers().get( i ).getName() );
                    msorceA = msorceA + MyApplication.getPlayers().get( i ).getScore();
                    playerA.setScore( MyApplication.getPlayers().get( i ).getScore() );
                    mfanguiA = mfanguiA + MyApplication.getPlayers().get( i ).getFangui();
                    playerA.setFangui( MyApplication.getPlayers().get( i ).getFangui() );
                    mgaimaoA = mgaimaoA + MyApplication.getPlayers().get( i ).getGaimao();
                    playerA.setGaimao( MyApplication.getPlayers().get( i ).getGaimao() );
                    mzhugongA = mzhugongA + MyApplication.getPlayers().get( i ).getZhugong();
                    playerA.setZhugong( MyApplication.getPlayers().get( i ).getZhugong() );
                    mlanbanA = mlanbanA + MyApplication.getPlayers().get( i ).getLanban();
                    playerA.setLanban( MyApplication.getPlayers().get( i ).getLanban() );
                    mqiangduanA = mqiangduanA + MyApplication.getPlayers().get( i ).getQiangduan();
                    playerA.setQiangduan( MyApplication.getPlayers().get( i ).getQiangduan() );
                    playerA.setIsTeamA( 0 );
                    playerA.setPalyid( MyApplication.getIndex() );
                    playerA1.add( playerA );
                    dbManager.modifyPlayerScore( playerA );
                    Log.d( TAG, "onClick: " + i );
                }
                MyApplication.setPlayersA( playerA1 );
                scoreA.setText( String.valueOf( msorceA ) );
                MyApplication.setScoreA( msorceA );
                MyApplication.setFanguiA( mfanguiA );
                MyApplication.setLanbanA( mlanbanA );
                MyApplication.setQiangdaunA( mqiangduanA );
                MyApplication.setGaimaoA( mgaimaoA );
                MyApplication.setZhugongA( mzhugongA );
                MyApplication.setCounts( 0 );
                if (MyApplication.getOnefanguiA() == 1) {
                    if (mfanguiA >= MyApplication.getOnefullnum()) {
                        fanguiA.setText( String.valueOf( mfanguiA ) );
                        one = mfanguiA;

                   if(mfanguiA>=(num++))
                   {
                       Toast.makeText( getActivity().getApplicationContext(), "球队犯规数目满,该罚球了"
                               , Toast.LENGTH_SHORT ).show();
                   }

                    } else {
                        fanguiA.setText( String.valueOf( mfanguiA ) );
                        one = mfanguiA;
                    }

                }
                if (MyApplication.getOnefanguiA() == 2) {
                    if (mfanguiA >=2* MyApplication.getOnefullnum()) {
                        fanguiA.setText( String.valueOf( mfanguiA - one ) );
                        two = mfanguiA - one;
                        Toast.makeText( getActivity().getApplicationContext(), "球队犯规数目满,该罚球了"
                                , Toast.LENGTH_SHORT ).show();
                    } else {
                        two = mfanguiA - one;
                        fanguiA.setText( String.valueOf( two ) );
                    }

                }
                if (MyApplication.getOnefanguiA() == 3) {
                    if (mfanguiA >=3* MyApplication.getOnefullnum()) {
                        fanguiA.setText( String.valueOf( mfanguiA - two ) );
                        three = mfanguiA - two;
                        Toast.makeText( getActivity().getApplicationContext(), "球队犯规数目满,该罚球了"
                                , Toast.LENGTH_SHORT ).show();
                    } else {
                        three = mfanguiA - two;
                        fanguiA.setText( String.valueOf( three ) );
                    }

                }
                if (MyApplication.getOnefanguiA() == 4) {
                    if (mfanguiA >= 4*MyApplication.getOnefullnum()) {
                        fanguiA.setText( String.valueOf( mfanguiA - three ) );
                        four = mfanguiA - three;
                        Toast.makeText( getActivity().getApplicationContext(), "球队犯规数目满,该罚球了"
                                , Toast.LENGTH_SHORT ).show();
                    } else {
                        four = mfanguiA - three;
                        fanguiA.setText( String.valueOf( four ) );
                    }

                }
//               else
//               {
//                   fanguiA.setText( String.valueOf( mfanguiA ));
//                   Toast.makeText( getActivity().getApplicationContext(),"球队犯规数目满"
//                           ,Toast.LENGTH_SHORT).show();
//               }

                msorceA = 0;
                mfanguiA = 0;
            }
        } );

        jian = (Button) view.findViewById( R.id.bt_jianhao );
        jian.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );

        return view;
    }

}
