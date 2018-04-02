package Person;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/12/21.
 */

public class Player  extends BmobObject implements Parcelable {
    private int num;
    private String name;
    private String teamname;
    private int palyid;

    public int getPalyid() {
        return palyid;
    }

    public void setPalyid(int palyid) {
        this.palyid = palyid;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    private int score = 0;
    private int isTeamA;
    private int zhugong = 0;
    private int lanban = 0;
    private int qiangduan = 0;
    private int fangui = 0;
    private int gaimao = 0;

    protected Player(Parcel in) {
        num = in.readInt();
        name = in.readString();
        score = in.readInt();
        isTeamA = in.readInt();
        zhugong = in.readInt();
        lanban = in.readInt();
        qiangduan = in.readInt();
        fangui = in.readInt();
        gaimao = in.readInt();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player( in );
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public int getZhugong() {
        return zhugong;
    }

    public void setZhugong(int zhugong) {
        this.zhugong = zhugong;
    }

    public int getLanban() {
        return lanban;
    }

    public void setLanban(int lanban) {
        this.lanban = lanban;
    }

    public int getQiangduan() {
        return qiangduan;
    }

    public void setQiangduan(int qiangduan) {
        this.qiangduan = qiangduan;
    }

    public int getFangui() {
        return fangui;
    }

    public void setFangui(int fangui) {
        this.fangui = fangui;
    }

    public int getGaimao() {
        return gaimao;
    }

    public void setGaimao(int gaimao) {
        this.gaimao = gaimao;
    }

    public Player(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public Player(int isTeamA) {
        this.isTeamA = isTeamA;
    }

    public Player() {
    }

    public Player(int num, String name, int score, int isTeamA) {
        this.num = num;
        this.name = name;
        this.score = score;
        this.isTeamA = isTeamA;
    }

    public Player(int num, String name, int isTeamA) {
        this.num = num;
        this.name = name;
        this.isTeamA = isTeamA;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIsTeamA() {
        return isTeamA;
    }

    public void setIsTeamA(int isTeamA) {
        this.isTeamA = isTeamA;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt( num );
        dest.writeString( name );
        dest.writeInt( score );
        dest.writeInt( isTeamA );
        dest.writeInt( zhugong );
        dest.writeInt( lanban );
        dest.writeInt( qiangduan );
        dest.writeInt( fangui );
        dest.writeInt( gaimao );
    }
}
