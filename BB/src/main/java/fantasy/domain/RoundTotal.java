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
    private Integer points;
	
	@Min(0)
    private Integer rebounds;
	
	@Min(0)
    private Integer assists;
	
	@Min(0)
    private Integer blocks;
	
	@Min(0)
    private Integer steals;
	
	@Min(0)
    private Integer turnovers;
	
	@Min(0)
    private Integer ftMade;
	
	@Min(0)
    private Integer threePointsMade;
	
	@Min(0)
    private Integer fgMade;
	
	@Min(0)
    private Integer fgAttempts;
	
	@Min(0)
	@Max(20)
    private Integer lpPoints;
	
	@Min(0)
	@Max(20)
    private Integer lpRebounds;
	
	@Min(0)
	@Max(20)
    private Integer lpAssists;
	
	@Min(0)
	@Max(10)
    private Integer lpBlocks;
	
	@Min(0)
	@Max(10)
    private Integer lpSteals;
	
	
	@Min(0)
	@Max(10)
    private Integer lpFtMade;
	
	@Min(0)
	@Max(10)
    private Integer lpThreePointsMade;

	@Min(0)
	@Max(10)
	private Integer lpFieldGoalPercentage;

    @NotNull
    @ManyToOne
    private Round round;
}
