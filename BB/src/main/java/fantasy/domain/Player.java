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
import fantasy.domain.GameStat;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;

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

    @OneToMany(mappedBy = "player", orphanRemoval=true, cascade = CascadeType.ALL)
    private Set<GameStat> stats = new HashSet<GameStat>();

    public Set<PlayerPosition> getPossiblePositionsImp() {
        this.possiblePositionsImp = new HashSet<PlayerPosition>(possiblePositions);
        return possiblePositionsImp;
    }

    public void setPossiblePositionsImp(Set<PlayerPosition> possiblePositionsImp) {
        setPossiblePositions(new HashSet<PlayerPosition>(possiblePositionsImp));
        this.possiblePositionsImp = new HashSet<PlayerPosition>(possiblePositionsImp);
        flush();
    }
    
    
    public void setStats(Set<GameStat> stats){
    	this.stats = stats;
    	for(GameStat stat : stats){
    		stat.setPlayer(this);
    	}
    }
}
