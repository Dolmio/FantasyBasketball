package fantasy.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import fantasy.domain.positions.PlayerPosition;
import fantasy.domain.positions.TeamPosition;

@RooJavaBean
@RooToString
@RooEntity
public class Player implements Serializable {

    @NotNull
    @Size(min = 2)
    private String firstName;

    @NotNull
    @Size(min = 2)
    private String lastName;

    @ElementCollection
    private Set<PlayerPosition> possiblePositions = new HashSet<PlayerPosition>();

    @Transient
    private HashSet<PlayerPosition> possiblePositionsImp = new HashSet<PlayerPosition>();

    @Enumerated(EnumType.STRING)
    private TeamPosition currentPosition = TeamPosition.BENCH;

    @ManyToOne
    private Team team;
    
    @Min(0)
    private Integer value;
    
    
    @OneToMany(mappedBy = "player", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<GameStat> stats = new HashSet<GameStat>();

    private Boolean injured = false;

    public Set<PlayerPosition> getPossiblePositionsImp() {
        this.possiblePositionsImp = new HashSet<PlayerPosition>(possiblePositions);
        return possiblePositionsImp;
    }

    public void setPossiblePositionsImp(Set<PlayerPosition> possiblePositionsImp) {
        setPossiblePositions(new HashSet<PlayerPosition>(possiblePositionsImp));
        this.possiblePositionsImp = new HashSet<PlayerPosition>(possiblePositionsImp);
        flush();
    }

    public void setStats(Set<GameStat> stats) {
        this.stats = stats;
        for (GameStat stat : stats) {
            stat.setPlayer(this);
        }
    }

    public void addGameStat(GameStat stat) {
        stats.add(stat);
        stat.setPlayer(this);
    }
    
    public int getStatCount(){
    	return stats.size();
    }
    
    public void setTeam(Team team){
    	if(this.team != null && team.getId() != this.team.getId()){
    		this.team.removePlayer(this);
    		this.team.merge();
    	}
    	
    	if(team != null){
    		team.addPlayer(this);
    	}
    	
    	this.team = team;
    }
    
    public EntityManager giveEntityManager(){
    	return entityManager;
    }
    
}
