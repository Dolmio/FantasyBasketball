package fantasy.domain;

import java.io.Serializable;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import fantasy.domain.Team;
import javax.validation.constraints.NotNull;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import fantasy.domain.Round;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
@RooJavaBean
@RooToString
@RooEntity
public class Game implements Serializable{

    
    @ManyToOne
    private Team homeTeam;

    
    @ManyToOne
    private Team awayTeam;
    
    @Enumerated(EnumType.STRING)
    private GameWinner winner = GameWinner.UNDEFINED;
    
    @ManyToOne
    private Round round;
    
    public void setHomeTeam(Team team){
    	if(homeTeam != null){
    		homeTeam.removeHomeGame(this);
    		homeTeam.merge();
    	}
    	
    	if(team != null){
    		team.addHomeGame(this);
    		//team.merge();
    		//team.flush();
    	}
    	this.homeTeam = team;
    	
    }
    
    public void setAwayTeam(Team team){
    	if(awayTeam != null){
    		awayTeam.removeAwayGame(this);
    		awayTeam.merge();
    	}
    	
    	if(team != null){
    		team.addAwayGame(this);
    		//team.merge();
    		//team.flush();
    	}
    	this.awayTeam = team;
    }
    
    public void setRound(Round newRound){
    	if(this.round != null){
    		this.round.removeGame(this);
    		this.round.merge();
    	}
    	
    	if(newRound != null){
    		newRound.addGame(this);
    		//newRound.merge();
    		//newRound.flush();
    		
    		
    	}
    	this.round = newRound;
    }
    
    public EntityManager giveEntityManager(){
    	return entityManager;
    }
    
    
}
