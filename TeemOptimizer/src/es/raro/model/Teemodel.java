package es.raro.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.logging.Logger;

import sim.engine.SimState;
import sim.engine.Steppable;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;

import es.raro.champion.Champion;
import es.raro.champion.RiotAPIChampion;
import es.raro.item.Item;
import es.raro.item.RiotAPIItem;
import es.raro.mastery.Mastery;
import es.raro.rune.Rune;
import es.raro.rune.RuneStatType;
import es.raro.rune.RuneType;
import es.raro.skill.Skill;

public class Teemodel extends SimState {
	private static final long serialVersionUID = 1L;
	
	/** State refresh period (s) */ 
	private static final double REFRESH_TIME = 0.1;

	/** Global cooldown (s) */
	public static final double GLOBAL_COOLDOWN = 1;
	
	private Champion attacker;
	private Champion defender;
	
	/** Native Random object as an alternative to SimState's MersenneTwisterFast */
	private Random jrandom;

	private int duration;
	
	public Teemodel(long seed, Champion attacker, Champion defender, int duration) {
		super(seed);
		
		jrandom = new Random(seed);
		
		this.attacker = attacker;
		this.defender = defender;
		this.duration = duration;
	}

	@Override
	public void start() {
		super.start();
		schedule.scheduleRepeating(0, attacker, REFRESH_TIME);
		schedule.scheduleRepeating(0, defender, REFRESH_TIME);
		schedule.scheduleOnce(duration, new Steppable() {
			
			@Override
			public void step(SimState state) {
				Teemodel model = (Teemodel) state;
				model.finish();
			}
		});
		System.out.println("Simulation started");
	}
	
	@Override
	public void finish() {
		super.finish();
		System.out.println("Simulation finished");
	}
	
	public Champion getDefender() {
		return defender;
	}
	
	public static float calculatePhysicalDamage(Champion attacker, Champion target, Skill skill){
		// Calculate target's armor
		float targetArmor = target.getArmor();
		
		targetArmor -= attacker.getArmorReduction();
		
		if(targetArmor>0)
			targetArmor *= (1 - attacker.getPercentageArmorPenetration());
		
		if(targetArmor>0)
			targetArmor -= attacker.getFlatArmorPenetration();
		
		// Calculate damage reduction factor
		float damageReduction;
		if(targetArmor>=0)
			damageReduction = 100f / (100 + targetArmor);
		else
			damageReduction = 2 - 100f / (100 - targetArmor);

		// Calculate physical damage of the auto-attack or skill
		float physicalDamageDone = 0;
		if(skill!=null){
			physicalDamageDone = skill.getPhysicalDamageDone();
		}
		else{
			physicalDamageDone = attacker.getDamage();
		}
		
		// Calculate damage multiplier factor
		float damageMultiplier = 1;  
		if(skill==null || skill.canCriticallyHit())
			damageMultiplier = 1 + (attacker.getCriticalChance() * (1 + attacker.getCriticalBonusDamage()));
		else
			damageMultiplier = 1;

		// Calculate final damage
		float finalDamage = Math.round(physicalDamageDone * damageReduction * damageMultiplier);
		
		return finalDamage;
	}

	public static float calculateMagicDamage(Champion attacker, Champion target, Skill skill){
		// Calculate target's resistance
		float targetResistance = target.getMagicResist();
		
		targetResistance -= attacker.getMagicResistReduction();
		
		if(targetResistance>0)
			targetResistance *= (1 - attacker.getPercentageMagicPenetration());
		
		if(targetResistance>0)
			targetResistance -= attacker.getFlatMagicPenetration();
		
		// Calculate damage reduction factor
		float damageReduction;
		if(targetResistance>=0)
			damageReduction = 100 / (100 + targetResistance);
		else
			damageReduction = 2 - 100 / (100 - targetResistance);
		
		float magicDamageDone = 0;
		magicDamageDone = skill.getMagicDamageDone();
		
		// Calculate final damage
		float finalDamage = Math.round(magicDamageDone * damageReduction);
		
		return finalDamage;
	}	
	
	public static Champion getChampionInstance(String name, int level) {
		try{
			Class[] championConstructorTypes = new Class[]{Integer.class};
			Class<Champion> championClass = (Class<Champion>) Class.forName(Champion.class.getPackage().getName()+"."+name);
			return championClass.getConstructor(championConstructorTypes).newInstance(new Object[]{level});
		} catch(Exception e){
			Logger.getGlobal().info("Can't instantiate subclass for champion '"+name+"'. Using generic "+RiotAPIChampion.class.getName());
			return new RiotAPIChampion(name, level);
		}
	}

	public static void initializeRiotAPI(String key, String region) {
        RiotAPI.setMirror(Region.valueOf(region));
        RiotAPI.setRegion(Region.valueOf(region));
        RiotAPI.setAPIKey(key);
	}

	public static Skill getSkillInstance(String name, int level, Champion champion) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		Class[] skillConstructorTypes = new Class[]{Champion.class, Integer.class};
		Class<Skill> skillClass = (Class<Skill>) Class.forName(Skill.class.getPackage().getName()+"."+name);
		return skillClass.getConstructor(skillConstructorTypes).newInstance(champion, level);
	}

	public static Rune getRuneInstance(String name, String type) throws NumberFormatException, FileNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, IOException {
		return new Rune(RuneStatType.valueOf(name), RuneType.valueOf(type));
		
		// TODO runes as items (via riotAPI)
		/*Class[] runeConstructorTypes = new Class[]{RuneStatType.class, RuneType.class};
		Class[] runeConstructorTypes = new Class[]{RuneStatType.class, RuneType.class};
		Class<Rune> runeClass = (Class<Rune>) Class.forName(Rune.class.getPackage().getName()+"."+name);
		return runeClass.getConstructor(runeConstructorTypes).newInstance(attacker, runeLevel);*/
	}

	public static Item getItemInstance(String name) {
		try{
			Class<Item> itemClass = (Class<Item>) Class.forName(Item.class.getPackage().getName()+"."+name);
			return itemClass.newInstance();
		} catch(Exception e){
			Logger.getGlobal().info("Can't instantiate subclass for item '"+name+"'. Using generic "+RiotAPIItem.class.getName());
			return new RiotAPIItem(name);
		}
	}

	public static Mastery getMasteryInstance(String masteryName, int masteryLevel) {
		// TODO Instantiate masteries via riotAPI
		return null;
	}

}
