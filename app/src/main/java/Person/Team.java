package Person;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/1/6.
 */

public class Team extends BmobObject{
    int teamid;
    public int getTeamid() {
        return teamid;
    }
    public void setTeamid(int teamid) {
        this.teamid = teamid;
    }
    String teamA;
    String teamB;
    String teamAScore;
    String teamBScore;
    public String getTeamAScore() {
        return teamAScore;
    }
    public void setTeamAScore(String teamAScore) {
        this.teamAScore = teamAScore;
    }
    public String getTeamBScore() {
        return teamBScore;
    }
    public void setTeamBScore(String teamBScore) {
        this.teamBScore = teamBScore;
    }
    public String getTeamA() {
        return teamA;
    }
    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }
    public String getTeamB() {
        return teamB;
    }
    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }
}
