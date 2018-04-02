package Person;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/1/6.
 */

public class PlayHistoryfinal extends BmobObject {
    String Playid;
    String nameA;
    String nameB;
    String ScoreA;
    String ScoreB;

    public String getPlayid() {
        return Playid;
    }

    public void setPlayid(String playid) {
        Playid = playid;
    }

    public String getNameA() {
        return nameA;
    }

    public void setNameA(String nameA) {
        this.nameA = nameA;
    }

    public String getNameB() {
        return nameB;
    }

    public void setNameB(String nameB) {
        this.nameB = nameB;
    }

    public String getScoreA() {
        return ScoreA;
    }

    public void setScoreA(String scoreA) {
        ScoreA = scoreA;
    }

    public String getScoreB() {
        return ScoreB;
    }

    public void setScoreB(String scoreB) {
        ScoreB = scoreB;
    }
}
