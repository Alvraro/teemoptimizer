package es.raro.problem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import es.raro.champion.Champion;
import es.raro.champion.ChampionBuilder;
import es.raro.model.Teemodel;

public class DamageCalculator extends Problem {

	private Champion attacker;
	private Champion defender;
	private int duration;
	private Teemodel model;

	public DamageCalculator(Properties properties) throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, NoSuchFieldException {
		super(properties);
		
		// Attacker config
		attacker = ChampionBuilder.configureChampion("attacker", properties);

		// Defender config
		defender = ChampionBuilder.configureChampion("defender", properties);

		// Seconds of simulation
		duration = Integer.parseInt(properties.getProperty("simulation.duration"));

		model = new Teemodel(seed, attacker, defender, duration);
	}

	public Champion getAttacker() {
		return attacker;
	}

	public void setAttacker(Champion attacker) {
		this.attacker = attacker;
	}

	public Champion getDefender() {
		return defender;
	}

	public void setDefender(Champion defender) {
		this.defender = defender;
	}

	public float calculateDamage() {
		model.start();

		while(model.schedule.step(model)){
			//Logger.getGlobal().info.(model.schedule.getTime());
		}
		
		return defender.getHealth() - defender.getRemainingHealth();
	}

}
