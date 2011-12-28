package fantasy.domain;

import javax.validation.ConstraintViolationException;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.roo.addon.test.RooIntegrationTest;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import fantasy.domain.positions.PlayerPosition;
import fantasy.domain.positions.TeamPosition;

@RooIntegrationTest(entity = Player.class)
public class PlayerIntegrationTest {
	@Autowired
	private PlayerDataOnDemand dod;
	@Autowired TeamDataOnDemand tDod;

    @Test
    public void testMarkerMethod() {
    	
    }
    
    
    
    @Test
	public void testAddingNameAlreadyInDB() {
		dod.init();
		Player org = dod.getSpecificPlayer(0);
		Player newP = new Player();
		Team t = tDod.getRandomTeam();
		newP.setTeam(t);
		newP.setFirstName(org.getFirstName());
		newP.setLastName(org.getLastName());
		try{
			newP.persist();
			t.persist();
			t.flush();
			Assert.fail("Added player with the same name");
		}
		catch (JpaSystemException e){
			Assert.assertTrue(e.contains(MySQLIntegrityConstraintViolationException.class));
		}

	}
	
	@Test
	public void testSettingUnavailablePosition(){
		
		Player p = new Player();
		PlayerPos pp = new PlayerPos();
		pp.setPlayerPosition(PlayerPosition.C);
		p.getPossiblePositions().add(pp);
		
		//can't change position if team is null
		for(TeamPosition currentPos : TeamPosition.values()){
			p.setCurrentPosition(currentPos);
			Assert.assertEquals(TeamPosition.BENCH, p.getCurrentPosition());
		}
		
		Team team = new Team();
		p.setTeam(team);
		
		p.setCurrentPosition(TeamPosition.C);
		Assert.assertEquals(TeamPosition.C, p.getCurrentPosition());
		
		p.setCurrentPosition(TeamPosition.SG);
		Assert.assertEquals(TeamPosition.C, p.getCurrentPosition());
		
		p.setCurrentPosition(TeamPosition.PF_C);
		Assert.assertEquals(TeamPosition.PF_C, p.getCurrentPosition());
		
		p.setCurrentPosition(TeamPosition.BENCH);
		Assert.assertEquals(TeamPosition.BENCH, p.getCurrentPosition());
		
		p.setCurrentPosition(TeamPosition.ANY);
		Assert.assertEquals(TeamPosition.ANY, p.getCurrentPosition());
		
		pp.setPlayerPosition(PlayerPosition.PF);
		p.getPossiblePositions().add(pp);
		p.setCurrentPosition(TeamPosition.PF);
		Assert.assertEquals(TeamPosition.PF, p.getCurrentPosition());
		
		p.setCurrentPosition(TeamPosition.PF_C);
		Assert.assertEquals(TeamPosition.PF_C, p.getCurrentPosition());
		
		//can't add to position where can't play
		p.setCurrentPosition(TeamPosition.PG);
		Assert.assertEquals(TeamPosition.PF_C, p.getCurrentPosition());
		
	Player p2 = new Player();
		p2.setTeam(team);
		p2.getPossiblePositions().add(pp);
		
	//can't add to occupied position
		p.setCurrentPosition(TeamPosition.PF_C);
		Assert.assertEquals(TeamPosition.BENCH, p2.getCurrentPosition());
		
		//can set To pf
		p2.setCurrentPosition(TeamPosition.PF);
		Assert.assertEquals(TeamPosition.PF, p2.getCurrentPosition());
		
		//can't add two players to any
		p.setCurrentPosition(TeamPosition.ANY);
		p2.setCurrentPosition(TeamPosition.ANY);
		Assert.assertEquals(TeamPosition.PF, p2.getCurrentPosition());
		
	}

}
