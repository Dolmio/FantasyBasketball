package fantasy.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooEntity
public class GameStat {

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date dateWhen;
    
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
}
