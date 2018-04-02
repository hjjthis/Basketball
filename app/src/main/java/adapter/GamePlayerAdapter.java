package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myclassesbasketball.R;

import java.util.ArrayList;

import Person.Player;
import app.MyApplication;

/**
 * Created by Administrator on 2017/12/22.
 */

public class GamePlayerAdapter extends BaseAdapter{
    private ArrayList<Player> mData = new ArrayList<Player>();
    private Context context;

    //初始化数据

    private static int score=0;
    private static int zhugong=0;
    private static int qiangduan=0;
    private static int lanban=0;
    private static int fangui=0;
    private static int gaimao=0;
    private static int counts1=0;
    private static int counts2=0;
    private static int counts3=0;
    private static int counts4=0;
    private static int counts5=0;
    private static int counts6=0;

    public GamePlayerAdapter(ArrayList<Player> mData, Context context) {
        super();
        this.mData = mData;
        this.context = context;
        //初始化数据
        for(int i=0;i<mData.size();i++)
        {
            mData.get( i ).setScore( 0 );
            mData.get( i ).setQiangduan( 0 );
            mData.get( i ).setLanban( 0 );
            mData.get( i ).setFangui( 0 );
            mData.get( i ).setGaimao( 0 );
            mData.get( i ).setZhugong( 0 );
        }
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Player player = mData.get(position);
        counts1= MyApplication.getCounts();
        counts2= MyApplication.getCounts();
        counts3= MyApplication.getCounts();
        counts4= MyApplication.getCounts();
        counts5= MyApplication.getCounts();
        counts6= MyApplication.getCounts();
        MyApplication.setPlayers( mData );
        ViewHolder holder = null;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate( R.layout.adapter_player_score, parent,false);
            holder=new ViewHolder();
            //获取控件id
            holder.num = (TextView)convertView.findViewById(R.id.num);
            holder.name=(TextView)convertView.findViewById( R.id.name);
            holder.score=(Button)convertView.findViewById(R.id.sorce);
            holder.lanban=(Button)convertView.findViewById(R.id.lanban);
            holder.qiangdaun=(Button)convertView.findViewById(R.id.qiangduan);
            holder.gaimao=(Button)convertView.findViewById(R.id.gaimao);
            holder.fangui=(Button)convertView.findViewById(R.id.fangui);
            holder.zhugong=(Button)convertView.findViewById(R.id.zhugong);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //判断item，设置颜色
        if(position%2==0)
        {
        convertView.setBackgroundResource(R.color.white);}
        else {
            convertView.setBackgroundResource( R.color.divider );
        }

        holder.num.setText(""+ player.getNum());

        holder.name.setText(player.getName());

        holder.score.setText(""+player.getScore());
        //设置默认点击颜色
        holder.score.setBackgroundResource( R.color.white );

        final ViewHolder finalHolder = holder;

        //分数

        holder.score.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score=Integer.valueOf( mData.get( position ).getScore());
               counts1++;
               if(counts1<4)
               {
                   mData.get( position ).setScore(  ++score );
               }
               else {
                   counts1=0;
                   Toast.makeText( context,"一次最多加3分",Toast.LENGTH_SHORT ).show();

               }
                if(counts1==1)
                {
                    finalHolder.score.setBackgroundResource( R.color.blue );
                }
                else if(counts1==2)
                {
                    finalHolder.score.setBackgroundResource( R.color.green );
                }
                else if(counts1==3)
                {
                    finalHolder.score.setBackgroundResource( R.color.red);
                }

            }
        } );

        holder.qiangdaun.setText(""+player.getQiangduan());
        //设置默认颜色
        holder.qiangdaun.setBackgroundResource( R.color.white );

        //抢断

        holder.qiangdaun.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qiangduan=Integer.valueOf( mData.get( position ).getQiangduan());
                counts2++;
                if(counts2<4)
                {
                    mData.get( position ).setQiangduan(  ++qiangduan );
                }
                else {
                    counts2=0;
                    Toast.makeText( context,"一次最多加3分",Toast.LENGTH_SHORT ).show();

                }
                if(counts2==1)
                {
                    finalHolder.qiangdaun.setBackgroundResource( R.color.blue );
                }
                else if(counts2==2)
                {
                    finalHolder.qiangdaun.setBackgroundResource( R.color.green );
                }
                else if(counts2==3)
                {
                    finalHolder.qiangdaun.setBackgroundResource( R.color.red);
                }

            }
        } );

        //篮板
        holder.lanban.setText(""+player.getLanban());
        holder.lanban.setBackgroundResource( R.color.white );
        holder.lanban.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanban=Integer.valueOf( mData.get( position ).getLanban());
                counts3++;
                if(counts3<4)
                {
                    mData.get( position ).setLanban(  ++lanban );
                }
                else {
                    counts3=0;
                    Toast.makeText( context,"一次最多加3分",Toast.LENGTH_SHORT ).show();

                }
                if(counts3==1)
                {
                    finalHolder.lanban.setBackgroundResource( R.color.blue );
                }
                else if(counts3==2)
                {
                    finalHolder.lanban.setBackgroundResource( R.color.green );
                }
                else if(counts3==3)
                {
                    finalHolder.lanban.setBackgroundResource( R.color.red);
                }
            }
        } );
        //助攻

        holder.zhugong.setText(""+player.getZhugong());
        holder.zhugong.setBackgroundResource( R.color.white );
        holder.zhugong.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhugong=Integer.valueOf( mData.get( position ).getZhugong());
                counts4++;
                if(counts4<4)
                {
                    mData.get( position ).setZhugong(  ++zhugong );
                }
                else {
                    counts4=0;
                    Toast.makeText( context,"一次最多加3分",Toast.LENGTH_SHORT ).show();

                }
                if(counts4==1)
                {
                    finalHolder.zhugong.setBackgroundResource( R.color.blue );
                }
                else if(counts4==2)
                {
                    finalHolder.zhugong.setBackgroundResource( R.color.green );
                }
                else if(counts4==3)
                {
                    finalHolder.zhugong.setBackgroundResource( R.color.red);
                }
            }
        } );

        //犯规
        holder.fangui.setText(""+player.getFangui());
        holder.fangui.setBackgroundResource( R.color.white );
        holder.fangui.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fangui=Integer.valueOf( mData.get( position ).getFangui());
                counts5++;
                if(counts5<4)
                {
                    mData.get( position ).setFangui(  ++fangui );
                }
                else {
                    counts5=0;
                    Toast.makeText( context,"一次最多加3分",Toast.LENGTH_SHORT ).show();

                }
                if(fangui>=5)
                {
                    fangui=5;
                    Toast.makeText( context,mData.get( position ).getName()+"已经犯规五次了",Toast.LENGTH_SHORT ).show();
                    finalHolder.fangui.setBackgroundResource( R.color.red);
                    mData.get( position ).setFangui(  fangui );
                    finalHolder.fangui.setEnabled( false );
                }
                if(counts5==1)
                {
                    finalHolder.fangui.setBackgroundResource( R.color.blue );
                }
                else if(counts5==2)
                {
                    finalHolder.fangui.setBackgroundResource( R.color.green );
                }
                else if(counts5==3)
                {
                    finalHolder.fangui.setBackgroundResource( R.color.red);
                }
            }
        } );

        //盖帽
        holder.gaimao.setText(""+player.getGaimao());
        holder.gaimao.setBackgroundResource( R.color.white);
        holder.gaimao.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gaimao=Integer.valueOf( mData.get( position ).getGaimao());
                counts6++;
                if(counts6<4)
                {
                    mData.get( position ).setGaimao(  ++gaimao );
                }
                else {
                    counts6=0;
                    Toast.makeText( context,"一次最多加3分",Toast.LENGTH_SHORT ).show();

                }
                if(counts6==1)
                {
                    finalHolder.gaimao.setBackgroundResource( R.color.blue );
                }
                else if(counts6==2)
                {
                    finalHolder.gaimao.setBackgroundResource( R.color.green );
                }
                else if(counts6==3)
                {
                    finalHolder.gaimao.setBackgroundResource( R.color.red);
                }            }
        } );

        return convertView;
    }


    //成员封装
  public  static class ViewHolder{
        TextView num;
        TextView name;
        Button score;
        Button lanban;
        Button zhugong;
        Button qiangdaun;
        Button gaimao;
        Button fangui;
    }
}
