package fantasy.domain;

import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import java.io.Serializable;
import java.util.Set;
import fantasy.domain.Player;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

//import fantasy.domain.RoundTotal;
//import fantasy.domain.Game;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooEntity
@Table(name = "team", uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
public class Team implements Serializable{

    @OneToMany(mappedBy = "team", orphanRemoval=false, cascade={CascadeType.ALL})
    @CascadeOnDelete
    private Set<Player> players = new HashSet<Player>();

//    @OneToMany(orphanRemoval = true)
//    private Set<RoundTotal> roundTotals = new HashSet<RoundTotal>();
//
//    @OneToMany(mappedBy = "homeTeam")
//    private Set<Game> homeGames = new HashSet<Game>();
//
//    @OneToMany(mappedBy = "awayTeam")
//    private Set<Game> awayGames = new HashSet<Game>();

    @NotNull
    private String name;
    
    public void setPlayers(Set<Player> players){
    	removeOldPlayers();
    	this.players = players;
    	for(Player p : players){
    		if(p.getTeam() != null){
    			Team oldTeam = p.getTeam();
    			oldTeam.getPlayers().remove(p);
    		}
    		p.setTeam(this);
    		
    	}
    }
    
    
    private void removeOldPlayers(){
    	for(Player p : players){
    		p.setTeam(null);
    		p.flush();
    	}
    
    }
}
