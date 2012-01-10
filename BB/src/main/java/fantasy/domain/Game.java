package fantasy.domain;

import java.io.Serializable;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import fantasy.domain.Team;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;
import fantasy.domain.Round;

@RooJavaBean
@RooToString
@RooEntity
public class Game implements Serializable{

    @NotNull
    @ManyToOne
    private Team homeTeam;

    @NotNull
    @ManyToOne
    private Team awayTeam;

    @ManyToOne
    private Team winnerTeam;
    
  
    @ManyToOne
    private Round round;
}
