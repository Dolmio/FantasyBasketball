package fantasy.domain;

import java.io.Serializable;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
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
    	if(homeTeam != null && team.getId() != this.homeTeam.getId()){
    		homeTeam.removeHomeGame(this);
    		homeTeam.merge();
    	}
    	
    	if(team != null){
    		team.addHomeGame(this);
    		
    	}
    	this.homeTeam = team;
    	
    }
    
    /**
     * Sets away team and makes sure bidirectional references are also updated
     * @param team
     */
    public void setAwayTeam(Team team){
    	if(awayTeam != null && team.getId() != this.awayTeam.getId()){
    		awayTeam.removeAwayGame(this);
    		awayTeam.merge();
    	}
    	
    	if(team != null){
    		team.addAwayGame(this);
    		
    	}
    	this.awayTeam = team;
    }
    
    /**
     * Sets the round and makes sure bidirectional references are also updated
     * @param newRound
     */
    public void setRound(Round newRound){
    	if(this.round != null && newRound.getId() != this.round.getId()){
    		this.round.removeGame(this);
    		this.round.merge();
    	}
    	
    	if(newRound != null){
    		newRound.addGame(this);
    	}
    	this.round = newRound;
    }
    
    /**
     * Saves the entity and makes sure all bidirectional references are updated to db.
     * Is used when entity form is saved.
     */
    public void saveEntity(){
    
		//we have to make merging in two parts because when we first merge game savedGame 
		//doesn't have information about updates made to reference relationships because they
		//aren't yet part of the Persistence context. We can't also merge references first because
		//game doesn't have id yet.

		Game savedGame = this.merge();
		
		this.setId(savedGame.getId());
		this.setVersion(savedGame.getVersion());
		if(this.getAwayTeam() != null){
			this.getAwayTeam().merge();
		}
		if(this.getHomeTeam() != null){
			this.getHomeTeam().merge();
		}
		if(this.getRound() != null){
			this.getRound().merge();
		}

	}
    
    /**
     * Deletes the entity and sure all bidirectional references are deleted and updated to db.
     * Is used when entity is deleted via entity form
     */
    public void deleteEntity(){
    	//remove refrences to this entity before deleting
    			if(this.getAwayTeam() != null){
    				this.getAwayTeam().removeAwayGame(this);
    				this.getAwayTeam().merge();
    			}
    			if(this.getHomeTeam() != null){
    				this.getHomeTeam().removeHomeGame(this);
    				this.getHomeTeam().merge();
    			}
    			if(this.getRound() != null){
    				this.getRound().removeGame(this);
    				this.getRound().merge();
    			}

    			remove();
    }
    
    
}
