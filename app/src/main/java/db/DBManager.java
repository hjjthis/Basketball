package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Person.Player;

/**
 * Created by Administrator on 2017/12/21.
 */

public class DBManager {

    public static final String DB_NAME = "basketball";

    public static final int VERSION = 1;

    private static DBManager dbManager;

    private SQLiteDatabase db;

    //生成一个DBHelper对象
    private DBManager(Context context) {
        DBHelper dbHelper = new DBHelper(context,DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

   //同步获取一个staticDBManager对象
    public synchronized static DBManager getInstance(Context context) {
        if (dbManager == null) {
            dbManager = new DBManager(context);
        }
        return dbManager;
    }

   //向表中插入一个球员数据
    public void insertOnePlayer(Player player){
        if(player!=null){
            ContentValues values = new ContentValues();
            values.put("num", player.getNum());
            values.put("name", player.getName());
            values.put("score", player.getScore());
            values.put("isTeamA", player.getIsTeamA());
            db.insert("Player", null, values);
        }
    }

    public void insertOnePlayerall(Player player){
        if(player!=null){
            ContentValues values = new ContentValues();
            values.put("num", player.getNum());
            values.put("name", player.getName());
            values.put( "teamname",player.getTeamname() );
            values.put("score", player.getScore());
            values.put("zhugong", player.getZhugong());
            values.put("lanban", player.getLanban());
            values.put("qiangduan", player.getQiangduan());
            values.put("gaimao", player.getGaimao());
            values.put("fangui", player.getFangui());
            values.put("isTeamA", player.getIsTeamA());
            db.insert("Player", null, values);
        }
    }


   //删除某位球员
    public void deleteOnePlayer(Player player){
        if(player!=null){
            String name = player.getName();
            db.delete("Player", "num=? and name=?", new String[]{name});
        }
    }

   //查询所有的球员数据
    public ArrayList<Player> queryAllPlayers(){
        ArrayList<Player>list = new ArrayList<Player>();
        Cursor cursor = db.query("Player", null, null, null, null, null, null);
        while(cursor.moveToNext()){
            int num = cursor.getInt(cursor.getColumnIndex("num"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int score = cursor.getInt(cursor.getColumnIndex("score"));
            int zhugong = cursor.getInt(cursor.getColumnIndex("zhugong"));
            int lanban = cursor.getInt(cursor.getColumnIndex("lanban"));
            int qiangduan = cursor.getInt(cursor.getColumnIndex("qiangduan"));
            int gaimao = cursor.getInt(cursor.getColumnIndex("gaimao"));
            int fangui = cursor.getInt(cursor.getColumnIndex("fangui"));
            int isTeamA = cursor.getInt(cursor.getColumnIndex("isTeamA"));
            Player player = new Player();
            player.setNum(num);
            player.setName(name);
            player.setScore(score);
            player.setIsTeamA(isTeamA);
            player.setZhugong( zhugong );
            player.setLanban( lanban );
            player.setQiangduan( qiangduan );
            player.setGaimao( gaimao );
            player.setFangui( fangui );
            list.add(player);
        }
        return list;
    }

    //修改球员数据
    public void modifyPlayerScore(Player player){
        if(player!=null){
            ContentValues values = new ContentValues();
            values.put("num", player.getNum());
            values.put("name", player.getName());
            values.put("score", player.getScore());
            values.put("zhugong", player.getZhugong());
            values.put("lanban", player.getLanban());
            values.put("qiangduan", player.getQiangduan());
            values.put("gaimao", player.getGaimao());
            values.put("fangui", player.getFangui());
            values.put("isTeamA", player.getIsTeamA());
            db.update("Player", values, "num=? and name=?",new String[]{""+player.getNum(),player.getName()});
        }
    }

    //清空Player数据

    public void deleteTable(String table){
        if(table!=null){
            if(table.equals("Player")){
                db.execSQL("DELETE FROM Player");
            }else if(table.equals("Scores")){
                db.execSQL("DELETE FROM Scores");
            }
        }
    }

}
