package fantasy.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import fantasy.domain.Round;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooEntity
public class RoundTotal {
	
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
	
	@Min(0)
	@Max(20)
    private Integer lpPoints = 0;
	
	@Min(0)
	@Max(20)
    private Integer lpRebounds = 0;
	
	@Min(0)
	@Max(20)
    private Integer lpAssists = 0;
	
	@Min(0)
	@Max(10)
    private Integer lpBlocks = 0;
	
	@Min(0)
	@Max(10)
    private Integer lpSteals = 0;
	
	
	@Min(0)
	@Max(10)
    private Integer lpFtMade = 0;
	
	@Min(0)
	@Max(10)
    private Integer lpThreePointsMade = 0;

	@Min(0)
	@Max(10)
	private Integer lpFieldGoalPercentage = 0;

    @NotNull
    @ManyToOne
    private Round round;
    
    public void resetStats(){
    	points = 0;
    	fgAttempts = 0;
    	fgMade = 0;
    	ftMade = 0;
    	threePointsMade = 0;
    	turnovers = 0;
    	steals = 0;
    	blocks = 0;
    	assists  = 0;
    	rebounds = 0;
    }
}
