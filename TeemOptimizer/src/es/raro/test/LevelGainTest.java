package es.raro.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

import es.raro.champion.RiotAPIChampion;
import es.raro.model.Teemodel;

public class LevelGainTest {

	@Test
	public void testChampion() throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileReader("config.properties"));
		
		Teemodel.initializeRiotAPI(properties.getProperty("key"), properties.getProperty("region"));
		
		RiotAPIChampion alistar1 = new RiotAPIChampion("Alistar", 1);
		assertEquals(613, alistar1.getHealth(), 1);
		
		RiotAPIChampion alistar2 = new RiotAPIChampion("Alistar", 2);
		assertEquals(686.4, alistar2.getHealth(), 1);
	}

}
