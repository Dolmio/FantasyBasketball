// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fantasy.domain;

import java.lang.String;

privileged aspect RoundTotal_Roo_ToString {
    
    public String RoundTotal.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Assists: ").append(getAssists()).append(", ");
        sb.append("Blocks: ").append(getBlocks()).append(", ");
        sb.append("FgAttempts: ").append(getFgAttempts()).append(", ");
        sb.append("FgMade: ").append(getFgMade()).append(", ");
        sb.append("FieldGoalPercentage: ").append(getFieldGoalPercentage()).append(", ");
        sb.append("FtMade: ").append(getFtMade()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("LpAssists: ").append(getLpAssists()).append(", ");
        sb.append("LpBlocks: ").append(getLpBlocks()).append(", ");
        sb.append("LpFieldGoalPercentage: ").append(getLpFieldGoalPercentage()).append(", ");
        sb.append("LpFtMade: ").append(getLpFtMade()).append(", ");
        sb.append("LpPoints: ").append(getLpPoints()).append(", ");
        sb.append("LpRebounds: ").append(getLpRebounds()).append(", ");
        sb.append("LpSteals: ").append(getLpSteals()).append(", ");
        sb.append("LpThreePointsMade: ").append(getLpThreePointsMade()).append(", ");
        sb.append("LpTurnovers: ").append(getLpTurnovers()).append(", ");
        sb.append("Points: ").append(getPoints()).append(", ");
        sb.append("Rebounds: ").append(getRebounds()).append(", ");
        sb.append("Round: ").append(getRound()).append(", ");
        sb.append("Steals: ").append(getSteals()).append(", ");
        sb.append("ThreePointsMade: ").append(getThreePointsMade()).append(", ");
        sb.append("Turnovers: ").append(getTurnovers()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }
    
}
