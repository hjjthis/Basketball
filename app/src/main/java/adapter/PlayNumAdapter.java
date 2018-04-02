package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.myclassesbasketball.R;

import java.util.ArrayList;
import java.util.List;

import Person.PlayHistoryfinal;
import Person.Player;
import Person.Team;
import app.MyApplication;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by Administrator on 2018/1/8.
 */

public class PlayNumAdapter extends BaseAdapter {
    private List<PlayHistoryfinal> mData = new ArrayList<PlayHistoryfinal>();
    private Context context;

    public PlayNumAdapter(List<PlayHistoryfinal> mData, Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        PlayHistoryfinal team = mData.get(position);
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate( R.layout.adapter_playnum, parent,false);
            holder=new PlayNumAdapter.ViewHolder();
            //获取控件id
            holder.et_playtiame = (TextView)convertView.findViewById(R.id.setPlayTime);
            holder.et_teamAname=(TextView)convertView.findViewById( R.id.setteamA);
            holder.et_teamBname=(TextView)convertView.findViewById(R.id.setteamB);
            holder.scoreA=(TextView)convertView.findViewById(R.id.scoreAA);
            holder.scoreB=(TextView)convertView.findViewById(R.id.scoreBB);
            holder.playtime=(TextView)convertView.findViewById(R.id.playtime);
            holder.teamA=(TextView)convertView.findViewById(R.id.teamA);
            holder.teamB=(TextView)convertView.findViewById(R.id.teamB);
            convertView.setTag(holder);
        }else{
            holder = (PlayNumAdapter.ViewHolder) convertView.getTag();
        }
        holder.et_playtiame.setText(String.valueOf( team.getPlayid() ));
        holder.et_teamAname.setText(team.getNameA());
        holder.et_teamBname.setText(team.getNameB());
        holder.scoreA.setText( String.valueOf( team.getScoreA() ) );
        holder.scoreB.setText( String.valueOf( team.getScoreB() ) );
        return convertView;
    }
    static  class ViewHolder{
        TextView playtime;
        TextView teamA;
        TextView teamB;
        TextView scoreA;
        TextView scoreB;
        TextView et_playtiame;
        TextView et_teamAname;
        TextView et_teamBname;
    }
}
