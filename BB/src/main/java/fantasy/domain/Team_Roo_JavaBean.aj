// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fantasy.domain;

import fantasy.domain.Game;
import fantasy.domain.Player;
import fantasy.domain.RoundTotal;
import java.lang.String;
import java.util.Set;

privileged aspect Team_Roo_JavaBean {
    
    public Set<Player> Team.getPlayers() {
        return this.players;
    }
    
    public String Team.getName() {
        return this.name;
    }
    
    public void Team.setName(String name) {
        this.name = name;
    }
    
    public Set<RoundTotal> Team.getRoundTotals() {
        return this.roundTotals;
    }
    
    public void Team.setRoundTotals(Set<RoundTotal> roundTotals) {
        this.roundTotals = roundTotals;
    }
    
    public Set<Game> Team.getHomeGames() {
        return this.homeGames;
    }
    
    public void Team.setHomeGames(Set<Game> homeGames) {
        this.homeGames = homeGames;
    }
    
    public Set<Game> Team.getAwayGames() {
        return this.awayGames;
    }
    
    public void Team.setAwayGames(Set<Game> awayGames) {
        this.awayGames = awayGames;
    }
    
}
