package es.raro.skill;

import java.util.HashMap;
import java.util.Map.Entry;

import sim.engine.SimState;
import sim.engine.Steppable;
import es.raro.champion.Champion;
import es.raro.champion.ChampionStatType;
import es.raro.model.Teemodel;
import es.raro.model.UnmatchingDescription;


public abstract class Skill {
	protected boolean isReady;
	
	protected String name;
	protected Champion champion;
	protected int level;
	
	protected int range;
	protected String description;

	protected int cost;
	protected double cooldown;
	
	// Stats bonus
	public int health;
	public int healthPerLevel;
	public int healthPercentage;
	public float healthRegen;
	public float healthRegenPerLevel;
	public int mana;
	public int manaPerLevel;
	public float manaRegen;
	public float manaRegenPerLevel;
	public float damage;
	public float damagePerLevel;
	/** Attacks per second */
	public float attackSpeed;
	/** 0-1 rate of armor ignored */
	public float armor;
	public float armorPerLevel;
	/** 0-1 rate of magic resistance ignored */
	public float magicResist;
	public float magicResistPerLevel;
	public float movementSpeed;
	public float percentageArmorPenetration;
	public float flatArmorPenetration;
	/** 0-1 probability of getting a critical hit */
	public float criticalChance;
	/** 0-1 probability of increased damage on critical hit (over the basic 1) */
	public float criticalBonusDamage;
	public float abilityPower;
	public float abilityPowerPerLevel;
	public float magicPenetration;
	public float lifeSteal;
	public float spellVamp;
	public float revival;
	public float goldGain;
	public float experienceGain;
	public float energy;
	public float energyPerLevel;
	public float energyRegen;
	public float energyRegenPerLevel;
	/** 0-1 rate by which skill cooldown is reduced */
	public float cooldownReduction;
	/** 0-1 rate by which skill cooldown is reduced per champion's level */
	public float cooldownReductionPerLevel;
	
	// Damage stats
	public float basePhysicalDamageDone;
	public HashMap<ChampionStatType,Float> bonusPhysicalDamageDone;

	public float baseMagicDamageDone;
	public HashMap<ChampionStatType,Float> bonusMagicDamageDone;
	
	public Skill(String name, Champion champion, int level) throws UnmatchingDescription {
		this.name = name;
		this.champion = champion;
		this.level = level;
		
		cost = defineManaCost(level);
		range = defineRange(level);
		cooldown = defineCooldown(level);
		
		isReady = true;
		
		basePhysicalDamageDone = defineBasePhysicalDamageDone(); 
		bonusPhysicalDamageDone = defineBonusPhysicalDamageDone();
		baseMagicDamageDone = defineBaseMagicDamageDone();
		bonusMagicDamageDone = defineMagicPhysicalDamageDone();
	}

	protected abstract float defineBasePhysicalDamageDone();

	protected abstract HashMap<ChampionStatType, Float> defineBonusPhysicalDamageDone();

	protected abstract float defineBaseMagicDamageDone();

	protected abstract HashMap<ChampionStatType, Float> defineMagicPhysicalDamageDone();

	protected abstract int defineManaCost(int level);

	protected abstract int defineRange(int level);

	protected abstract double defineCooldown(int level);

	protected abstract String defineDescription();

	public void use(Teemodel model, Champion target) {
		// Determine skill's damage
		float damageDone = 0;
		if(doesPhysicalDamage()){
			damageDone = Teemodel.calculatePhysicalDamage(champion, target, this);
		}
		else if(doesMagicDamage()){
			damageDone = Teemodel.calculateMagicDamage(champion, target, this);
		}
		
		// If the skill does damage
		if(damageDone>0){
			target.receiveDamage(damageDone);
		}

		// Skill is not ready after using it
		isReady = false;
				
		// Schedule event to reactivate skill after the cooldown
		cooldown *= (1 - champion.getCooldownReduction());
		model.schedule.scheduleOnce(cooldown, new Steppable() {
			@Override
			public void step(SimState state) {
				isReady = true;
			}
		});
	}

	private boolean doesPhysicalDamage() {
		if(basePhysicalDamageDone>0 || !bonusPhysicalDamageDone.isEmpty())
			return true;
		else
			return false;
	}

	private boolean doesMagicDamage() {
		if(baseMagicDamageDone>0 || !bonusMagicDamageDone.isEmpty())
			return true;
		else
			return false;
	}
	
	public boolean isReady() {
		return isReady;
	}

	public abstract boolean canCriticallyHit();

	public abstract boolean applyOnHitEffects();

	public float getPhysicalDamageDone() {
		float damageDone = 0;
		
		for(Entry<ChampionStatType, Float> d : bonusPhysicalDamageDone.entrySet()){
			ChampionStatType statType = d.getKey();
			Float coef = d.getValue();
			
			damageDone += coef * champion.getStat(statType);
		}
		
		return damageDone;
	}

	public float getMagicDamageDone() {
		float damageDone = 0;
		
		for(Entry<ChampionStatType, Float> d : bonusMagicDamageDone.entrySet()){
			ChampionStatType statType = d.getKey();
			Float coef = d.getValue();
			
			damageDone += coef * champion.getStat(statType);
		}
		
		return damageDone;
	}
}
