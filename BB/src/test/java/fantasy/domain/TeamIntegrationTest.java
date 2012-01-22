package fantasy.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = Team.class)
public class TeamIntegrationTest {
	@Autowired
	TeamDataOnDemand tDod;
	@Autowired
	PlayerDataOnDemand pDod;
	@Autowired 
	GameDataOnDemand gDod;
    
	@Test
    public void testGameAdding() {
		Team team = tDod.getRandomTeam();
		Game game = gDod.getRandomGame();
		Set<Game> homeGames = new HashSet<Game>();
		homeGames.add(game);
		team.setHomeGames(homeGames);
		team.persist();
		team.flush();
	
		
		team.setRoundTotal(new HashSet<RoundTotal>(Arrays.asList(new RoundTotal[]{new RoundTotal()})));
	}
    
    @Test
    public void testPlayerOrpahnRemoval(){
    	Team testTeam = tDod.getRandomTeam();
    	Player p1 = pDod.getSpecificPlayer(0);
    	Player p2 = pDod.getSpecificPlayer(1);
    	testTeam.getPlayers().add(p1);
    	testTeam.getPlayers().add(p2);
    	testTeam.persist();
    	testTeam.flush();
    	Team sameTeam = Team.findTeam(testTeam.getId());
    	sameTeam.remove();
    	Assert.assertEquals(null, Player.findPlayer(p1.getId()));
    	Assert.assertEquals(null, Player.findPlayer(p2.getId()));
    	
    }
    
    @Test 
    public void tryAddSamePlayerToManyTeams(){
    	Team testTeam1 = tDod.getSpecificTeam(0);
    	Team testTeam2 = tDod.getSpecificTeam(1);
    	Player p1 = pDod.getSpecificPlayer(0);
    	//testTeam1.getPlayers().add(p1);
    	p1.setTeam(testTeam1);
    	
    	testTeam1.persist();
    	testTeam1.flush();
    	
    	//testTeam2.getPlayers().add(p1);
    	p1.setTeam(testTeam2);
    	testTeam2.persist();
    	testTeam2.flush();
    	Team sameTeam1 = Team.findTeam(testTeam1.getId());
    	Assert.assertEquals(false, sameTeam1.getPlayers().contains(p1));
    	
    	
    	
    }
}
