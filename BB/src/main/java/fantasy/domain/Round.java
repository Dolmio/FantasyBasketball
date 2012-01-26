package fantasy.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
@RooJavaBean
@RooToString
@RooEntity

public class Round implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
    /*
     * @Temporal annotation must be specified for persistent fields or properties of type java.util.Date 
     * and java.util.Calendar. It may only be specified for fields or properties of these types.
     */
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
   
  
    
  public int getGameCount(){
	  return games.size();
  }
  
  public void addGame(Game game){
	  games.add(game);
  }
  
  public void removeGame(Game game){
	  games.remove(game);
  }
  
  public static Collection<Round> getRoundsWithGames(){
	  List<Round> allRounds = Round.findAllRounds();
	  List<Round> roundsWithGames = new LinkedList<Round>();
	  for(Round round : allRounds){
		  if(round.getGameCount() != 0){
			  roundsWithGames.add(round);
		  }
	  }
	  return roundsWithGames;
  }
  
  
}
