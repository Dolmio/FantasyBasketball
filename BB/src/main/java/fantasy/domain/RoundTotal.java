package fantasy.domain;

import java.io.Serializable;
import java.text.DecimalFormat;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class RoundTotal implements Serializable, Comparable<RoundTotal> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
    private Integer lpPoints = 0;

    @Min(0)
   private Integer lpRebounds = 0;

    @Min(0)
    private Integer lpAssists = 0;

    @Min(0)
    private Integer lpBlocks = 0;

    @Min(0)
    private Integer lpSteals = 0;

    @Min(0)
    private Integer lpFtMade = 0;

    @Min(0)
    private Integer lpThreePointsMade = 0;

    @Min(0)
    private Integer lpFieldGoalPercentage = 0;

    @Max(0)
    private Double lpTurnovers = Double.valueOf(0);
    
    @NotNull
    @ManyToOne
    private Round round;
    
    

    @NotNull
    @ManyToOne
    private Team team;
    
    /**
     * Sets the team and makes sure bidirectional references are also updated
     * @param team
     */
    public void setTeam(Team newTeam){
    	if(this.team != null && newTeam.getId() != this.team.getId()){
    		this.team.removeRoundTotal(this);
    		this.team.merge();
    	}
    	
    	if(newTeam != null){
    		newTeam.addRoundTotal(this);
    	}
    	this.team = newTeam;
    	
    }

    public double getFieldGoalPercentage() {
       if (getFgAttempts() == 0) return 0;
    	//cut after second digit
       	else{
    		 DecimalFormat twoDForm = new DecimalFormat("#.##");
    	     return Double.valueOf(twoDForm.format(100 * (double)getFgMade() / (double)getFgAttempts()));
    	
    	}
    }
    
    /**
     * Set one category of leaguePoints according to the first parameter.
     * @param stat
     * @param points
     */
    public void setLeaguePoints(StatType stat, Number points) {
        switch(stat) {
            case POINTS:
                setLpPoints(points.intValue()); break;
            case REBOUNDS:
                setLpRebounds(points.intValue()); break;
            case ASSISTS:
                setLpAssists(points.intValue()); break;
            case FTMADE:
                setLpFtMade(points.intValue()); break;
            case THREEPOINTSMADE:
                setLpThreePointsMade(points.intValue()); break;
            case BLOCKS:
                setLpBlocks(points.intValue()); break;
            case FGPERCENTAGE:
                setLpFieldGoalPercentage(points.intValue()); break;
            case STEALS:
                setLpSteals(points.intValue()); break;
            case TURNOVERS:
                setLpTurnovers(points.doubleValue()); break;
        }
    }

    public Number getLpStat(StatType stat) {
        switch(stat) {
            case POINTS:
                return getLpPoints();
            case REBOUNDS:
                return getLpRebounds();
            case ASSISTS:
                return getLpAssists();
            case FTMADE:
                return getLpFtMade();
            case THREEPOINTSMADE:
                return getLpThreePointsMade();
            case BLOCKS:
                return getLpBlocks();
            case FGPERCENTAGE:
                return getLpFieldGoalPercentage();
            case STEALS:
                return getLpSteals();
            case TURNOVERS:
                return getLpTurnovers();
            default:
                throw new IllegalArgumentException();
        }
    }

    public Number getStat(StatType stat) {
        switch(stat) {
            case POINTS:
                return getPoints(); 
            case REBOUNDS:
                return getRebounds();
            case ASSISTS:
                return getAssists();
            case FTMADE:
                return getFtMade();
            case THREEPOINTSMADE:
                return getThreePointsMade();
            case BLOCKS:
                return getBlocks();
            case FGPERCENTAGE:
                return getFieldGoalPercentage();
            case STEALS:
                return getSteals();
            case TURNOVERS:
                return getTurnovers();
            default:
               throw new IllegalArgumentException();
        }
    }

    public static String getMethodSignature(StatType stat) {
        switch(stat) {
            case POINTS:
                return "getPoints";
            case REBOUNDS:
                return "getRebounds";
            case ASSISTS:
                return "getAssists";
            case FTMADE:
                return "getFtMade";
            case THREEPOINTSMADE:
                return "getThreePointsMade";
            case TURNOVERS:
                return "getTurnovers";
            case STEALS:
                return "getSteals";
            case BLOCKS:
                return "getBlocks";
            case FGPERCENTAGE:
                return "getFieldGoalPercentage";
            default:
                return null;
        }
    }
    
    /**
     * returns sum of all leaguePoints
     * @return
     */
    public Double getTotalPoints() {
        return getLpTurnovers() + getLpAssists() + getLpBlocks() + 
        		getLpFieldGoalPercentage() + getLpFtMade() + 
        		getLpPoints() + getLpRebounds() + getLpSteals() + 
        		getLpThreePointsMade();
    }

    public void resetStats() {
        points = 0;
        fgAttempts = 0;
        fgMade = 0;
        ftMade = 0;
        threePointsMade = 0;
        turnovers = 0;
        steals = 0;
        blocks = 0;
        assists = 0;
        rebounds = 0;
    }
    /**
     * Returns 1 if this has more totalPoints than argument or points are equal. -1 if argument has more points than this.
     * @param o
     * @return
     */
	@Override
	public int compareTo(RoundTotal otherTotal) {
	
		if(this.equals(otherTotal)) return 0;
		
		//if total points are equal we let the admin declare real winner manually, because it's rare condition and not
		//easy to implement because of the rules. 
		if(getTotalPoints() == otherTotal.getTotalPoints()){
			return 1;
		}
		else{
			double pointDifferential = getTotalPoints() - otherTotal.getTotalPoints();
			//we have to make sure numbers between -1 and 1 aren't rounded to 0. 
			return (int) (pointDifferential < 0 ? Math.floor(pointDifferential) : Math.ceil(pointDifferential));
			
		}
		
	}
	
	/**
     * Saves the entity and makes sure all bidirectional references are updated to db.
     * Is used when entity form is saved.
     */
	public void saveEntity(){
		RoundTotal savedTotal = merge();
		setId(savedTotal.getId());
		setVersion(savedTotal.getVersion());
		if(getTeam() != null){
			getTeam().merge();
		}
	}
	
	/**
     * Deletes the entity and sure all bidirectional references are deleted and updated to db.
     * Is used when entity is deleted via entity form
     */
	public void deleteEntity(){
		if(getTeam() != null){
			getTeam().removeRoundTotal(this);
			getTeam().merge();
		}
		
		remove();
		
	}

	
	
}
