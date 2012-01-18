package fantasy.domain;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = Game.class)
public class GameIntegrationTest {
	
	@Autowired
	TeamDataOnDemand tDod;
	
	@Autowired 
	GameDataOnDemand gDod;
	
    @Test
    public void testAddingTeams() {
    	Game game = new Game();
    	Team team1 = tDod.getSpecificTeam(0);
    	Team team2 = tDod.getSpecificTeam(1);
    	
    	
    	game.persist();
    	
    	game.setHomeTeam(team1);
    	game.setAwayTeam(team2);
    	game.flush();
    	
    	//find out if persisted data is still there
    	Team oldTeam1 = Team.findTeam(team1.getId());
    	Team oldTeam2 = Team.findTeam(team2.getId());
    	
    	
    	boolean homeGameFound = false;
    	boolean awayGameFound = false;
    	
    	//test that team has still info of the game
    	for(Game homeGame : oldTeam1.getHomeGames()){
    		if(homeGame.getHomeTeam().getId() == team1.getId()){
    			homeGameFound = true;
    			break;
    		}
    	}
    	Assert.assertEquals(true, homeGameFound);
    	
    	for(Game awayGame : oldTeam2.getAwayGames()){
    		if(awayGame.getAwayTeam().getId() == team2.getId()){
    			awayGameFound = true;
    			break;
    		}
    	}
    	Assert.assertEquals(true, awayGameFound);
    }
    
    @Test
    public void testOldGameGetsRemovedWhenNewAdded(){
    	Game game = new Game();
    	Team team1 = tDod.getSpecificTeam(0);
    	Team team2 = tDod.getSpecificTeam(1);
    	Team team3 = tDod.getSpecificTeam(2);
    	
    	game.persist();
    	
    	game.setHomeTeam(team1);
    	game.setAwayTeam(team2);
    	game.flush();
    	
    	Game oldGame = Game.findGame(game.getId());
    	oldGame.setHomeTeam(team3);
    	oldGame.merge();
    	
    	Team oldTeam = Team.findTeam(team1.getId());
    	boolean homeGameStillFound = false;
    	
    	for(Game homeGame : oldTeam.getHomeGames()){
    		if(homeGame.getId() == game.getId()){
    			homeGameStillFound = true;
    			break;
    		}
    	}
    	Assert.assertEquals(false, homeGameStillFound);
    }
}
