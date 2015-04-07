package es.raro.model;

import java.util.Random;

import sim.engine.SimState;
import es.raro.champion.Champion;
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
	
	public Teemodel(long seed, Champion attacker, Champion defender) {
		super(seed);
		
		jrandom = new Random(seed);
		
		this.attacker = attacker;
		this.defender = defender;
	}

	@Override
	public void start() {
		super.start();
		schedule.scheduleRepeating(attacker, REFRESH_TIME);
		schedule.scheduleRepeating(defender, REFRESH_TIME);
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
			targetArmor *= (100 - attacker.getPercentageArmorPenetration());
		
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
			damageMultiplier = (100 + (attacker.getCriticalChance() * (100 + attacker.getCriticalBonusDamage()))) / 100f;
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
			targetResistance *= (100 - attacker.getPercentageMagicPenetration());
		
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
	
}
