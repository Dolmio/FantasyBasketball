package fantasy.domain;

import java.io.Serializable;
import java.text.DecimalFormat;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import fantasy.domain.Round;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;
import fantasy.domain.Team;

@RooJavaBean
@RooToString
@RooEntity
public class RoundTotal implements Serializable, Comparable {

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

    private Double lpTurnovers = Double.valueOf(0);

    @NotNull
    @ManyToOne
    private Team team;
    
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
        System.out.println("FgAttempts: " + getFgAttempts() + " FgMade: " + getFgMade());
    	if (getFgAttempts() == 0) return 0;
    	else{
    		 DecimalFormat twoDForm = new DecimalFormat("#.##");
    	     return Double.valueOf(twoDForm.format((double)getFgMade() / (double)getFgAttempts()));
    	
    	}
    }

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

	@Override
	public int compareTo(Object o) {
		RoundTotal otherTotal = (RoundTotal) o;
		if(this.equals(o)) return 0;
		
		//if total points are equal we let the admin declare real winner manually, because it's rare condition.
		if(getTotalPoints() == otherTotal.getTotalPoints()){
			return 1;
		}
		else{
			double pointDifferential = getTotalPoints() - otherTotal.getTotalPoints();
			return (int) (pointDifferential < 0 ? Math.floor(pointDifferential) : Math.ceil(pointDifferential));
			
		}
		
	}
	
	
	public void saveEntity(){
		RoundTotal savedTotal = merge();
		setId(savedTotal.getId());
		setVersion(savedTotal.getVersion());
		if(getTeam() != null){
			getTeam().merge();
		}
	}
	
	public void deleteEntity(){
		if(getTeam() != null){
			getTeam().removeRoundTotal(this);
			getTeam().merge();
		}
		
		remove();
		
	}
	
}
