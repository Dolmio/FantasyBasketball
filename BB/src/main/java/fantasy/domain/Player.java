package fantasy.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.vaadin.data.Item;

import javax.persistence.EnumType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import java.util.HashSet;
import javax.persistence.ElementCollection;

import fantasy.domain.positions.PlayerPosition;
import fantasy.domain.positions.TeamPosition;
import javax.persistence.Enumerated;
import fantasy.domain.Team;
import javax.persistence.ManyToOne;
//import fantasy.domain.GameStat;


@RooJavaBean
@RooToString
@RooEntity
//@Table(name = "player", uniqueConstraints = @UniqueConstraint(columnNames = { "firstName", "lastName" }))
public class Player implements Serializable{

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

//    @OneToMany(orphanRemoval=true)
//    private Set<GameStat> stats = new HashSet<GameStat>();
//
//    public void setCurrentPosition(TeamPosition position) {
//        if (team == null) return;
//        if (position == TeamPosition.BENCH) currentPosition = position; 
//        else {
//            boolean isAbleToPlay = false;
//            for (PlayerPos playerPos : getPossiblePositions()) {
//                if (playerPos.getPlayerPosition().canPlay(position)) {
//                    isAbleToPlay = true;
//                    break;
//                }
//            }
//            if (!isAbleToPlay) return;
//            for (Player player : team.getPlayers()) {
//                if (!player.equals(this)) {
//                    if (player.getCurrentPosition() == position) {
//                        return;
//                    }
//                }
//            }
//            currentPosition = position;
//        }
//    }

//    public void setTeam(Team team) {
////    	if(this.team != null){
////
////    		this.team.getPlayers().remove(this);
////    	}
//    	if(team != null){
//    	this.team = team;
//    	
//    	team.getPlayers().add(this);
//    	team.flush();
//    	}
//    }

	public Set<PlayerPosition> getPossiblePositionsImp() {
		this.possiblePositionsImp = new HashSet<PlayerPosition>(possiblePositions);
		return possiblePositionsImp;
	}

	public void setPossiblePositionsImp(Set<PlayerPosition> possiblePositionsImp) {
		setPossiblePositions(new HashSet<PlayerPosition>(possiblePositionsImp));
		this.possiblePositionsImp = new HashSet<PlayerPosition>(possiblePositionsImp);
		flush();
	}
	
	

	
}
