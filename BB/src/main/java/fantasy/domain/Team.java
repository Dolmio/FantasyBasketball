package fantasy.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity

//teamName must be unique
@Table(name = "team", uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
public class Team implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @CascadeOnDelete
	@OneToMany(mappedBy = "team", cascade = { CascadeType.ALL })
    private Set<Player> players = new HashSet<Player>();

    @NotNull
    private String name;
    
    @CascadeOnDelete
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<RoundTotal> roundTotals = new HashSet<RoundTotal>();

    @OneToMany(mappedBy = "homeTeam", cascade = CascadeType.ALL)
    private Set<Game> homeGames = new HashSet<Game>();
    
    
    @OneToMany(mappedBy = "awayTeam", cascade = CascadeType.ALL)
    private Set<Game> awayGames = new HashSet<Game>();
  
    
    public void addPlayer(Player player){
    	players.add(player);
    }
    
    public void removePlayer(Player player){
    	players.remove(player);
    }
    
    public void addRoundTotal(RoundTotal total) {
        roundTotals.add(total);
    }
    
    public void removeRoundTotal(RoundTotal total){
    	roundTotals.remove(total);
    }

    public void setRoundTotal(Set<RoundTotal> totals) {
      for (RoundTotal total : totals) {
            total.setTeam(this);
        }
    }
    
    public void addHomeGame(Game game){
    	this.homeGames.add(game);
    }
    
    public void removeHomeGame(Game game){
    	this.homeGames.remove(game);
    }
    
    public void addAwayGame(Game game){
    	this.awayGames.add(game);
    }
    
    public void removeAwayGame(Game game){
    	this.awayGames.remove(game);
    }
    
    public int getGameCount(){
    	return homeGames.size() + awayGames.size();
    	
    }
    
    public int getWinCount(){
    	int wins = 0;
    	for(Game homeGame : homeGames){
    		if(homeGame.getWinner() == GameWinner.HOME){
    			wins++;
    		}
    	}
    	for(Game awayGame : awayGames){
    		if(awayGame.getWinner() == GameWinner.AWAY){
    			wins++;
    		}
    	}
    	
    	return wins;
    }
    
    public int getFinishedGameCount(){
    	List<Game> games = new ArrayList<Game>(homeGames);
    	games.addAll(awayGames);
    	int finishedGames = 0;
    	for (Game g : games){
    		if(g.getWinner() != GameWinner.UNDEFINED){
    			finishedGames++;
    		}
    	}
    	return finishedGames;
    	
    }
    
    public int getRoundCount() {
        return roundTotals.size();
    }

    public int getPlayerCount() {
        return players.size();
    }
}
