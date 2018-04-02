package app;

import android.app.Application;
import android.widget.ImageView;

import java.util.ArrayList;

import Person.Player;

/**
 * Created by Administrator on 2017/12/19.
 */

public class MyApplication extends Application {
    private static int MinPlayTime;
    private static int MinAttackTime;
    private static int finaltime;
    private static String teamA;
    private static String teamB;
    private static int Bminplaytime;
    private static int Bminattacktime;
    private static int oneoffnum;
    private static int onepower;
    private static int onefullnum;
    private static int scoreA;
    private static int counts = 0;
    private static int zhugongA;
    private static int zhugongB;
    private static int qiangdaunA;
    private static int qiangdaunB;
    private static int gaimaoA;
    private static int gaimaoB;
    private static int lanbanA;
    private static int lanbanB;
    private static int fanguiA;
    private static int onefanguiA;
    private static int onefanguiB;
    private static int index;

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        MyApplication.index = index;
    }

    public static int getOnefanguiA() {
        return onefanguiA;
    }

    public static void setOnefanguiA(int onefanguiA) {
        MyApplication.onefanguiA = onefanguiA;
    }

    public static int getOnefanguiB() {
        return onefanguiB;
    }

    public static void setOnefanguiB(int onefanguiB) {
        MyApplication.onefanguiB = onefanguiB;
    }

    private static int fanguiB;
    private static ImageView imgteamA;

    public static ImageView getImgteamA() {
        return imgteamA;
    }

    public static void setImgteamA(ImageView imgteamA) {
        MyApplication.imgteamA = imgteamA;
    }

    public static ImageView getImgteamB() {
        return imgteamB;
    }

    public static void setImgteamB(ImageView imgteamB) {
        MyApplication.imgteamB = imgteamB;
    }

    private static ImageView imgteamB;

    public static int getZhugongA() {
        return zhugongA;
    }

    public static void setZhugongA(int zhugongA) {
        MyApplication.zhugongA = zhugongA;
    }

    public static int getZhugongB() {
        return zhugongB;
    }

    public static void setZhugongB(int zhugongB) {
        MyApplication.zhugongB = zhugongB;
    }

    public static int getQiangdaunA() {
        return qiangdaunA;
    }

    public static void setQiangdaunA(int qiangdaunA) {
        MyApplication.qiangdaunA = qiangdaunA;
    }

    public static int getQiangdaunB() {
        return qiangdaunB;
    }

    public static void setQiangdaunB(int qiangdaunB) {
        MyApplication.qiangdaunB = qiangdaunB;
    }

    public static int getGaimaoA() {
        return gaimaoA;
    }

    public static void setGaimaoA(int gaimaoA) {
        MyApplication.gaimaoA = gaimaoA;
    }

    public static int getGaimaoB() {
        return gaimaoB;
    }

    public static void setGaimaoB(int gaimaoB) {
        MyApplication.gaimaoB = gaimaoB;
    }

    public static int getLanbanA() {
        return lanbanA;
    }

    public static void setLanbanA(int lanbanA) {
        MyApplication.lanbanA = lanbanA;
    }

    public static int getLanbanB() {
        return lanbanB;
    }

    public static void setLanbanB(int lanbanB) {
        MyApplication.lanbanB = lanbanB;
    }

    public static int getFanguiA() {
        return fanguiA;
    }

    public static void setFanguiA(int fanguiA) {
        MyApplication.fanguiA = fanguiA;
    }

    public static int getFanguiB() {
        return fanguiB;
    }

    public static void setFanguiB(int fanguiB) {
        MyApplication.fanguiB = fanguiB;
    }

    public static int getCounts() {
        return counts;
    }

    public static void setCounts(int counts) {
        MyApplication.counts = counts;
    }

    public static int getScoreA() {
        return scoreA;
    }

    public static void setScoreA(int scoreA) {
        MyApplication.scoreA = scoreA;
    }

    public static int getScoreB() {
        return scoreB;
    }

    public static void setScoreB(int scoreB) {
        MyApplication.scoreB = scoreB;
    }

    private static int scoreB;
    private static ArrayList<Player> players = new ArrayList<Player>();
    private static ArrayList<Player> playersB = new ArrayList<Player>();
    private static ArrayList<Player> playersA = new ArrayList<Player>();

    public static ArrayList<Player> getPlayersA() {
        return playersA;
    }

    public static void setPlayersA(ArrayList<Player> playersA) {
        MyApplication.playersA = playersA;
    }

    public static ArrayList<Player> getPlayersB() {
        return playersB;
    }

    public static void setPlayersB(ArrayList<Player> playersB) {
        MyApplication.playersB = playersB;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(ArrayList<Player> players) {
        MyApplication.players = players;
    }

    public static int getBminplaytime() {
        return Bminplaytime;
    }

    public static void setBminplaytime(int bminplaytime) {
        Bminplaytime = bminplaytime;
    }

    public static int getBminattacktime() {
        return Bminattacktime;
    }

    public static void setBminattacktime(int bminattacktime) {
        Bminattacktime = bminattacktime;
    }

    public static int getOneoffnum() {
        return oneoffnum;
    }

    public static void setOneoffnum(int oneoffnum) {
        MyApplication.oneoffnum = oneoffnum;
    }

    public static int getOnepower() {
        return onepower;
    }

    public static void setOnepower(int onepower) {
        MyApplication.onepower = onepower;
    }

    public static int getOnefullnum() {
        return onefullnum;
    }

    public static void setOnefullnum(int onefullnum) {
        MyApplication.onefullnum = onefullnum;
    }

    public static int getFinaltime() {
        return finaltime;
    }

    public static void setFinaltime(int finaltime) {
        MyApplication.finaltime = finaltime;
    }


    public MyApplication() {
    }

    public static String getTeamA() {
        return teamA;
    }

    public static void setTeamA(String teamA) {
        MyApplication.teamA = teamA;
    }

    public static String getTeamB() {
        return teamB;
    }

    public static void setTeamB(String teamB) {
        MyApplication.teamB = teamB;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static int getMinPlayTime() {
        return MinPlayTime;
    }

    public static void setMinPlayTime(int minPlayTime) {
        MinPlayTime = minPlayTime;
    }

    public static int getMinAttackTime() {
        return MinAttackTime;
    }

    public static void setMinAttackTime(int minAttackTime) {
        MinAttackTime = minAttackTime;
    }
}
