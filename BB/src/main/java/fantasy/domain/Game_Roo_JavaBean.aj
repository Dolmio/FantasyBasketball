// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fantasy.domain;

import fantasy.domain.Team;

privileged aspect Game_Roo_JavaBean {
    
    public Team Game.getHomeTeam() {
        return this.homeTeam;
    }
    
    public void Game.setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }
    
    public Team Game.getAwayTeam() {
        return this.awayTeam;
    }
    
    public void Game.setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }
    
    public Team Game.getWinnerTeam() {
        return this.winnerTeam;
    }
    
    public void Game.setWinnerTeam(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }
    
}
