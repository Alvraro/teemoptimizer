package es.raro.test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import es.raro.champion.RiotAPIChampion;
import es.raro.model.Teemodel;

public class LevelGainTest {

	@Test
	public void testChampion() throws FileNotFoundException, IOException {
		String key = "053867d6-8f46-4309-b7ad-e5656c3a2364";
		String region = "EUW";
		
		Teemodel.initializeRiotAPI(key, region);
		
		RiotAPIChampion alistar1 = new RiotAPIChampion("Alistar", 1);
		assertEquals(613, alistar1.getHealth(), 1);
		
		RiotAPIChampion alistar2 = new RiotAPIChampion("Alistar", 2);
		assertEquals(686.4, alistar2.getHealth(), 1);
	}

}
