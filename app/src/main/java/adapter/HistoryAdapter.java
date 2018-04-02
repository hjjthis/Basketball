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
 * Created by Administrator on 2017/12/26.
 */

public class HistoryAdapter extends BaseAdapter {
    private ArrayList<Player> mData = new ArrayList<Player>();
    private Context context;


    public HistoryAdapter(ArrayList<Player> mData, Context context) {
        super();
        this.mData = mData;
        this.context = context;
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
        Player player = mData.get(position);
        ViewHolder holder=null;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate( R.layout.adapter_play_history, parent,false);
            holder=new ViewHolder();
            //获取控件id
            holder.num = (TextView)convertView.findViewById(R.id.num);
            holder.name=(TextView)convertView.findViewById( R.id.name);
            holder.score=(TextView)convertView.findViewById(R.id.sorce);
            holder.lanban=(TextView)convertView.findViewById(R.id.lanban);
            holder.qiangdaun=(TextView)convertView.findViewById(R.id.qiangduan);
            holder.gaimao=(TextView)convertView.findViewById(R.id.gaimao);
            holder.fangui=(TextView)convertView.findViewById(R.id.fangui);
            holder.zhugong=(TextView)convertView.findViewById(R.id.zhugong);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //判断item，设置颜色
        if(player.getIsTeamA()==0)
        {
            convertView.setBackgroundResource(R.color.white);}
        else {
            convertView.setBackgroundResource( R.color.divider );
        }
        holder.num.setText(""+ String.valueOf( player.getNum() ));
        holder.name.setText(player.getName());
        holder.score.setText(""+ String.valueOf( player.getScore() ));
        holder.qiangdaun.setText(""+String.valueOf( player.getQiangduan() ));
        holder.lanban.setText(""+String.valueOf( player.getLanban() ));
        holder.zhugong.setText(""+String.valueOf( player.getZhugong() ));
        holder.fangui.setText(""+String.valueOf( player.getFangui() ));
        holder.gaimao.setText(""+String.valueOf( player.getGaimao() ));
        return convertView;
    }


    //成员封装
    public  static class ViewHolder{
        TextView num;
        TextView name;
        TextView score;
        TextView lanban;
        TextView zhugong;
        TextView qiangdaun;
        TextView gaimao;
        TextView fangui;
    }
}
