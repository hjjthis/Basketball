package adapter;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.myclassesbasketball.R;

import java.util.ArrayList;
import java.util.List;

import Person.Player;
import activity.AddPlayer;
import clock.MyImageView;

/**
 * Created by Administrator on 2017/12/22.
 */

public class AddPlayerAdapter extends BaseAdapter {
    //定义数据结构

    private List<Player> mData = new ArrayList<Player>();
    private Context context;

    public AddPlayerAdapter(List<Player> mData, Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Player player = mData.get(position);
        ViewHolder holder =null;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_player, parent,false);
            holder = new ViewHolder();
            //获取控件id
            holder.num = (TextView)convertView.findViewById( R.id.adPlayer_tv_num);
            holder.name=(TextView)convertView.findViewById(R.id.adPlayer_tv_name);
            holder.edit = (MyImageView)convertView.findViewById(R.id.adPlayer_iv_edit);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.edit.setIndex(position);
        holder.num.setText("号码："+player.getNum());
        holder.name.setText("姓名："+player.getName());

        //listview里面的点击事件
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = ((MyImageView)v).getIndex();
                Player myPlayer = mData.get(position);
                Message msg = new Message();
                msg.what = 1;
                msg.obj= myPlayer;
                AddPlayer.handler.sendMessage(msg);
            }
        });
        return convertView;
    }

    static class ViewHolder{
        TextView num;
        TextView name;
        MyImageView edit;
    }
}
