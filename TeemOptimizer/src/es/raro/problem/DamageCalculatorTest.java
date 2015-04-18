package es.raro.problem;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.junit.Test;

public class DamageCalculatorTest {

	@Test
	public void testGetDamage() throws FileNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, NoSuchFieldException, IOException {
		Properties properties = new Properties();
		properties.setProperty("key", "053867d6-8f46-4309-b7ad-e5656c3a2364");
		properties.setProperty("region", "EUW");
		properties.setProperty("seed", "666");

		properties.setProperty("attacker.champion", "Gangplank");
		properties.setProperty("attacker.autoattack", "false");
		properties.setProperty("attacker.level", "3");
		properties.setProperty("attacker.skills", "2*Parrrley");
		properties.setProperty("attacker.runes", "9*Mark*criticalBonusDamage+9*Seal*criticalBonusDamage+9*Glyph*criticalBonusDamage+3*Quintessence*criticalBonusDamage");

		properties.setProperty("attacker.items", "1*BrawlersGloves+1*BrawlersGloves+1*InfinityEdge+1*InfinityEdge");

		properties.setProperty("defender.champion", "Caitlyn");
		properties.setProperty("defender.level", "3");
		properties.setProperty("defender.autoattack", "false");

		properties.setProperty("simulation.duration", "1");

		DamageCalculator calc = new DamageCalculator(properties);
		float damage1 = calc.calculateDamage();
		assertEquals(439, damage1, 1);
		
		float damage2 = calc.calculateDamage();
		assertEquals(439, damage2, 1);
	}

}
