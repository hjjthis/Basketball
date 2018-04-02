package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.administrator.myclassesbasketball.R;

import app.MyApplication;


public class GameSorce extends Fragment {

    private ImageView img_teamA_score;
    private ImageView img_teamB_score;
    private ImageView img_teamA_lanban;
    private ImageView img_teamB_lanban;
    private ImageView img_teamA_qiangduan;
    private ImageView img_teamB_qiangduan;
    private ImageView img_teamA_gaimao;
    private ImageView img_teamB_gaimao;
    private ImageView img_teamA_zhugong;
    private ImageView img_teamB_zhugong;
    private ImageView img_teamA_fangui;
    private ImageView img_teamB_fangui;
    private LinearLayout game;
    private TextView scoreA;
    private TextView scoreB;
    private TextView zhugongA;
    private TextView zhugongB;
    private TextView qiangdaunA;
    private TextView qiangdaunB;
    private TextView gaimaoA;
    private TextView gaimaoB;
    private TextView lanbanA;
    private TextView lanbanB;
    private TextView fanguiA;
    private TextView fanguiB;
    private float WEIGHTA = 1;
    private float WEIGHTB = 1;
    private int counts = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_game_sorce, container, false );
        //初始化UI
        img_teamA_score = (ImageView) view.findViewById( R.id.img_teamA );
        img_teamB_score = (ImageView) view.findViewById( R.id.img_teamB );
        img_teamA_lanban = (ImageView) view.findViewById( R.id.img_teamAl );
        img_teamB_lanban = (ImageView) view.findViewById( R.id.img_teamBl );
        img_teamA_qiangduan = (ImageView) view.findViewById( R.id.img_teamAq );
        img_teamB_qiangduan = (ImageView) view.findViewById( R.id.img_teamBq );
        img_teamA_gaimao = (ImageView) view.findViewById( R.id.img_teamAg );
        img_teamB_gaimao = (ImageView) view.findViewById( R.id.img_teamBg );
        img_teamA_zhugong = (ImageView) view.findViewById( R.id.img_teamAz );
        img_teamB_zhugong = (ImageView) view.findViewById( R.id.img_teamBz );
        img_teamA_fangui = (ImageView) view.findViewById( R.id.img_teamAf );
        img_teamB_fangui = (ImageView) view.findViewById( R.id.img_teamBf );
        scoreA = (TextView) view.findViewById( R.id.defenA );
        scoreB = (TextView) view.findViewById( R.id.defenB );
        zhugongA = (TextView) view.findViewById( R.id.zhugongA );
        zhugongB = (TextView) view.findViewById( R.id.zhugongB );
        lanbanA = (TextView) view.findViewById( R.id.lanbanA );
        lanbanB = (TextView) view.findViewById( R.id.lanbanB );
        qiangdaunA = (TextView) view.findViewById( R.id.qiangduanA );
        qiangdaunB = (TextView) view.findViewById( R.id.qiangduanB );
        gaimaoA = (TextView) view.findViewById( R.id.gaimaoA );
        gaimaoB = (TextView) view.findViewById( R.id.gaimaoB );
        fanguiA = (TextView) view.findViewById( R.id.fanguiA );
        fanguiB = (TextView) view.findViewById( R.id.fanguiB );
        //settext

        counts = MyApplication.getScoreA() + MyApplication.getScoreB() +
                MyApplication.getLanbanA() + MyApplication.getLanbanB() +
                MyApplication.getQiangdaunA() + MyApplication.getQiangdaunB() +
                MyApplication.getZhugongA() + MyApplication.getZhugongB() +
                MyApplication.getGaimaoA() + MyApplication.getGaimaoB() +
                MyApplication.getFanguiA() + MyApplication.getFanguiB();
        //初始化数据
        if (counts == 0) {
            scoreA.setText( String.valueOf( String.valueOf( 0 ) ) );
            scoreB.setText( String.valueOf( String.valueOf( 0 ) ) );
            zhugongA.setText( String.valueOf( String.valueOf( 0 ) ) );
            zhugongB.setText( String.valueOf( String.valueOf( 0 ) ) );
            lanbanA.setText( String.valueOf( String.valueOf( 0 ) ) );
            lanbanB.setText( String.valueOf( String.valueOf( 0 ) ) );
            qiangdaunA.setText( String.valueOf( String.valueOf( 0 ) ) );
            qiangdaunB.setText( String.valueOf( String.valueOf( 0 ) ) );
            fanguiA.setText( String.valueOf( String.valueOf( 0 ) ) );
            fanguiB.setText( String.valueOf( String.valueOf( 0 ) ) );
            gaimaoA.setText( String.valueOf( String.valueOf( 0 ) ) );
            gaimaoB.setText( String.valueOf( String.valueOf( 0 ) ) );
        } else {

            scoreA.setText( String.valueOf( MyApplication.getScoreA() ) );
            scoreB.setText( String.valueOf( MyApplication.getScoreB() ) );
            zhugongA.setText( String.valueOf( MyApplication.getZhugongA() ) );
            zhugongB.setText( String.valueOf( MyApplication.getZhugongB() ) );
            lanbanA.setText( String.valueOf( MyApplication.getLanbanA() ) );
            lanbanB.setText( String.valueOf( MyApplication.getLanbanB() ) );
            qiangdaunA.setText( String.valueOf( MyApplication.getQiangdaunA() ) );
            qiangdaunB.setText( String.valueOf( MyApplication.getQiangdaunB() ) );
            fanguiA.setText( String.valueOf( MyApplication.getFanguiA() ) );
            fanguiB.setText( String.valueOf( MyApplication.getFanguiB() ) );
            gaimaoA.setText( String.valueOf( MyApplication.getGaimaoA() ) );
            gaimaoB.setText( String.valueOf( MyApplication.getGaimaoB() ) );
        }
        if (MyApplication.getFanguiA() == 0 && MyApplication.getFanguiB() == 0) {
            LinearLayout.LayoutParams lpA = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f );
            img_teamA_fangui.setLayoutParams( lpA );
            LinearLayout.LayoutParams lpB = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f );
            img_teamB_fangui.setLayoutParams( lpB );

        } else {
            LinearLayout.LayoutParams lpA = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    Float.valueOf( MyApplication.getFanguiA() ) + 0.0f );
            img_teamA_fangui.setLayoutParams( lpA );
            LinearLayout.LayoutParams lpB = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    Float.valueOf( MyApplication.getFanguiB() ) + 0.0f );
            img_teamB_fangui.setLayoutParams( lpB );
        }
        //盖帽进度条
        if (MyApplication.getGaimaoA() == 0 && MyApplication.getGaimaoB() == 0) {
            LinearLayout.LayoutParams lpA = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f );
            img_teamA_gaimao.setLayoutParams( lpA );
            LinearLayout.LayoutParams lpB = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f );
            img_teamB_gaimao.setLayoutParams( lpB );

        } else {
            LinearLayout.LayoutParams lpA = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    Float.valueOf( MyApplication.getGaimaoA() ) + 0.0f );
            img_teamA_gaimao.setLayoutParams( lpA );
            LinearLayout.LayoutParams lpB = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    Float.valueOf( MyApplication.getGaimaoB() ) + 0.0f );
            img_teamB_gaimao.setLayoutParams( lpB );
        }

        //抢断进度条
        if (MyApplication.getQiangdaunA() == 0 && MyApplication.getQiangdaunB() == 0) {
            LinearLayout.LayoutParams lpA = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f );
            img_teamA_qiangduan.setLayoutParams( lpA );
            LinearLayout.LayoutParams lpB = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f );
            img_teamB_qiangduan.setLayoutParams( lpB );

        } else {
            LinearLayout.LayoutParams lpA = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    Float.valueOf( MyApplication.getQiangdaunA() ) + 0.0f );
            img_teamA_qiangduan.setLayoutParams( lpA );
            LinearLayout.LayoutParams lpB = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    Float.valueOf( MyApplication.getQiangdaunB() ) + 0.0f );
            img_teamB_qiangduan.setLayoutParams( lpB );
        }
        //助攻进度条
        if (MyApplication.getZhugongA() == 0 && MyApplication.getZhugongB() == 0) {
            LinearLayout.LayoutParams lpA = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f );
            img_teamA_zhugong.setLayoutParams( lpA );
            LinearLayout.LayoutParams lpB = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f );
            img_teamB_zhugong.setLayoutParams( lpB );

        } else {
            LinearLayout.LayoutParams lpA = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    Float.valueOf( MyApplication.getZhugongA() ) + 0.0f );
            img_teamA_zhugong.setLayoutParams( lpA );
            LinearLayout.LayoutParams lpB = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    Float.valueOf( MyApplication.getZhugongB() ) + 0.0f );
            img_teamB_zhugong.setLayoutParams( lpB );
        }
        //分数进度条
        if (MyApplication.getScoreA() == 0 && MyApplication.getScoreB() == 0) {
            LinearLayout.LayoutParams lpA = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f );
            img_teamA_score.setLayoutParams( lpA );
            LinearLayout.LayoutParams lpB = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f );
            img_teamB_score.setLayoutParams( lpB );

        } else {
            LinearLayout.LayoutParams lpA = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    Float.valueOf( MyApplication.getScoreA() ) + 0.0f );
            img_teamA_score.setLayoutParams( lpA );
            LinearLayout.LayoutParams lpB = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    Float.valueOf( MyApplication.getScoreB() ) + 0.0f );
            img_teamB_score.setLayoutParams( lpB );
        }
        //篮板进度条
        if (MyApplication.getLanbanA() == 0 && MyApplication.getLanbanB() == 0) {
            LinearLayout.LayoutParams lpA = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f );
            img_teamA_lanban.setLayoutParams( lpA );
            LinearLayout.LayoutParams lpB = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f );
            img_teamB_lanban.setLayoutParams( lpB );

        } else {
            LinearLayout.LayoutParams lpA = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    Float.valueOf( MyApplication.getLanbanA() ) + 0.0f );
            img_teamA_lanban.setLayoutParams( lpA );
            LinearLayout.LayoutParams lpB = new LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT,
                    Float.valueOf( MyApplication.getLanbanB() ) + 0.0f );
            img_teamB_lanban.setLayoutParams( lpB );
        }

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
