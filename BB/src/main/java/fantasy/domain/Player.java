package fantasy.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
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

// Name combination must be unique, if you want to persist entity to db.
@Table(name = "player", uniqueConstraints = @UniqueConstraint(columnNames = { "firstName", "lastName" }))
public class Player implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
    @Size(min = 2)
    private String firstName;

    @NotNull
    @Size(min = 2)
    private String lastName;

    @ElementCollection(fetch=FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<PlayerPosition> possiblePositions = new HashSet<PlayerPosition>();
    
    /*We have to use dummy-copy of possiblePositions because Vaadin
     * multiselect requires that collections which are used with it implements cloneable
     * Set doesn't do that so we have to make dummy-copy with concrete-implementation.
     * In the other hand for ORM the field has to be Set.
     */
    @Transient
    private HashSet<PlayerPosition> possiblePositionsImp = new HashSet<PlayerPosition>();

    @Enumerated(EnumType.STRING)
    private TeamPosition currentPosition = TeamPosition.BENCH1;

    @ManyToOne
    private Team team;
    
    @Min(0)
    private Integer value;
    
    //orphanRemoval =  true = gameStats are deleted automatically when they are removed from the collection
    @OneToMany(mappedBy = "player", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<GameStat> stats = new HashSet<GameStat>();
    
    //field ready for future updates. No use yet-
    private Boolean injured = false;

    public Set<PlayerPosition> getPossiblePositionsImp() {
        this.possiblePositionsImp = new HashSet<PlayerPosition>(possiblePositions);
        return possiblePositionsImp;
    }

    public void setPossiblePositionsImp(Set<PlayerPosition> possiblePositionsImp) {
        setPossiblePositions(new HashSet<PlayerPosition>(possiblePositionsImp));
        this.possiblePositionsImp = new HashSet<PlayerPosition>(possiblePositionsImp);
      
    }

    public void setStats(Set<GameStat> stats) {
        this.stats = stats;
        for (GameStat stat : stats) {
            stat.setPlayer(this);
        }
    }

    public void addGameStat(GameStat stat) {
        stats.add(stat);
        
    }
    
    public void removeGameStat(GameStat stat){
    	stats.remove(stat);
    }
    
    public int getStatCount(){
    	return stats.size();
    }
    
    /**
     * Sets the team and makes sure bidirectional references are also updated
     * @param team
     */
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
    
   
    /**
    * Saves the entity and makes sure all bidirectional references are updated to db.
    * Is used when entity form is saved.
    */
    public void saveEntity(){
    	
    	//we have to make merging in two parts because when we first merge player savedPlayer
    	//doesn't have information about updates made to reference relationships because they
    	//aren't yet part of the Persistence context. We can't also merge references first because
    	//player doesn't have id yet.
    	
		Player savedPlayer = this.merge();
		
		this.setId(savedPlayer.getId());
		this.setVersion(savedPlayer.getVersion());
		
		if(this.getTeam() != null){
			this.getTeam().merge();
		}
		
    }
    
    /**
     * Deletes the entity and sure all bidirectional references are deleted and updated to db.
     * Is used when entity is deleted via entity form
     */
    public void deleteEntity(){
    	if(this.getTeam() != null){
    		this.getTeam().removePlayer(this);
    		this.getTeam().merge();
    	}
    	this.remove();
    }
    
}
