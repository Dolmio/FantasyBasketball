package fantasy.util;

import org.junit.Test;

import fantasy.web.FantasyApplication;

public class HashTest {
	
	@Test
	public void test(){
		System.out.println("HASH: " + FantasyApplication.getHash("testi"));
	}
	
}
