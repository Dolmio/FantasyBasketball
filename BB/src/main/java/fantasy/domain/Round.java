package fantasy.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Set;
import fantasy.domain.Game;
import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import javax.persistence.OneToMany;
@RooJavaBean
@RooToString
@RooEntity

public class Round implements Serializable  {
	
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date startDate;
	
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date endDate;
	
    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL)
    private Set<Game> games = new HashSet<Game>();
    
    @NotNull
    private String name;
   
   /*
  public void setGames(Set<Game> games){
	  for(Game game : games){
		  game.setRound(this);
	  }
	  this.games = games;
  }
  */
    
  public int getGameCount(){
	  return games.size();
  }
  
  public void addGame(Game game){
	  games.add(game);
  }
  
  public void removeGame(Game game){
	  games.remove(game);
  }
  
  
  
}
