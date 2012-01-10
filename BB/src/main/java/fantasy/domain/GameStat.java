package fantasy.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;
import fantasy.domain.Player;
import javax.persistence.ManyToOne;
import java.io.Serializable;
@RooJavaBean
@RooToString
@RooEntity
public class GameStat implements Serializable {

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

    @ManyToOne
    private Player player;
}
