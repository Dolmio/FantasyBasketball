package fantasy.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import junit.framework.Assert;

import org.junit.Test;

public class DataUpdaterTester {

	@Test
	public void testUpdateAllRoundTotals() {
		//fail("Not yet implemented");
	}

	@Test
	public void testUpdateRoundLeaguePoints() {
		//fail("Not yet implemented");
	}
	
	
	@Test
	public void testSorting(){
		RoundTotal r1 = new RoundTotal();
		r1.setAssists(10);
		RoundTotal r2 = new RoundTotal();
		r2.setAssists(0);
		ArrayList<RoundTotal> totals = new ArrayList<RoundTotal>(Arrays.asList(new RoundTotal[] {r1, r2}));
		Assert.assertEquals(r1, totals.get(0));
		Collections.sort(totals, new BeanComparator(RoundTotal.class, RoundTotal.getMethodSignature(StatType.ASSISTS), false));
		Assert.assertEquals(r1, totals.get(0));
		
		totals = new ArrayList<RoundTotal>(Arrays.asList(new RoundTotal[] {r2, r1}));
		Collections.sort(totals, new BeanComparator(RoundTotal.class, RoundTotal.getMethodSignature(StatType.ASSISTS), false));
		Assert.assertEquals(r1, totals.get(0));
		
		r1.setBlocks(5);
		r2.setBlocks(0);
		totals = new ArrayList<RoundTotal>(Arrays.asList(new RoundTotal[] {r2, r1}));
		Collections.sort(totals, new BeanComparator(RoundTotal.class, RoundTotal.getMethodSignature(StatType.BLOCKS), false));
		Assert.assertEquals(r1, totals.get(0));
		
		r1.setBlocks(0);
		totals = new ArrayList<RoundTotal>(Arrays.asList(new RoundTotal[] {r2, r1}));
		Collections.sort(totals, new BeanComparator(RoundTotal.class, RoundTotal.getMethodSignature(StatType.BLOCKS), false));
		Assert.assertEquals(r2, totals.get(0));
		
	}
}
