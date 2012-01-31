package fantasy.domain;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
@RooJavaBean
@RooToString
@RooEntity
public class GameStat implements Serializable {

    

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date dateWhen = new Date();

    @Min(0)
    private Integer points = 0;

    @Min(0)
    private Integer rebounds = 0;

    @Min(0)
    private Integer assists = 0;

    @Min(0)
    private Integer blocks = 0;

    @Min(0)
    private Integer steals = 0;

    @Min(0)
    private Integer turnovers = 0;

    @Min(0)
    private Integer ftMade = 0;

    @Min(0)
    private Integer threePointsMade = 0;

    @Min(0)
    private Integer fgMade = 0;

    @Min(0)
    private Integer fgAttempts = 0;
    
    @NotNull
    @ManyToOne
    private Player player;
    
    /**
     * Sets the player and makes sure bidirectional references are also updated
     * @param newPlayer
     */
    public void setPlayer(Player newPlayer){
    	Player oldPlayer = player;
    	if(oldPlayer != null && newPlayer.getId() != oldPlayer.getId()){
    		oldPlayer.removeGameStat(this);
    		oldPlayer.merge();
    	}
    	
    	if(newPlayer != null){
    		newPlayer.addGameStat(this);
    	}
    	this.player = newPlayer;
    	
    	
    }
    
    public Double giveFieldGoalPercentage(){
    	if (getFgAttempts() == 0) return Double.valueOf(0);
    	//cut after second digit
       	else{
    		 DecimalFormat twoDForm = new DecimalFormat("#.##");
    	     return Double.valueOf(twoDForm.format(100 * (double)getFgMade() / (double)getFgAttempts()));
    	
    	}
    }
    
    /**
     * Saves the entity and makes sure all bidirectional references are updated to db.
     * Is used when entity form is saved.
     */
    public void saveEntity(){
    	//we have to make merging in two parts because when we first merge stat savedStat
    	//doesn't have information about updates made to reference relationships because they
    	//aren't yet part of the Persistence context. We can't also merge references first because
    	//stat doesn't have id yet.
    	
    	GameStat savedStat = merge();
		this.setId(savedStat.getId());
		this.setVersion(savedStat.getVersion());
		
		if(this.getPlayer() != null){
			this.getPlayer().merge();
		}
    }
    
    /**
     * Deletes the entity and sure all bidirectional references are deleted and updated to db.
     * Is used when entity is deleted via entity form
     */
    public void deleteEntity(){
    	if(this.getPlayer() != null){
    		this.getPlayer().removeGameStat(this);
    		this.getPlayer().merge();
    	}
    	
    	remove();
    	
    }
    
}
