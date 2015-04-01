package es.raro.skill;

import sim.engine.SimState;
import sim.engine.Steppable;
import es.raro.champion.Champion;
import es.raro.model.Teemodel;
import es.raro.model.UnmatchingDescription;


public abstract class Skill {
	protected boolean isReady;
	
	protected Champion champion;
	protected int level;
	
	protected int range;
	protected String description;

	protected int cost;
	protected int cooldown;
	
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
	public float attackSpeed;
	public float armor;
	public float armorPerLevel;
	public float magicResist;
	public float magicResistPerLevel;
	public float movementSpeed;
	public float percentageArmorPenetration;
	public float flatArmorPenetration;
	public float criticalChance;
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
	public float cooldownReduction;
	public float cooldownReductionPerLevel;
	
	public float basePhysicalDamageDone;
	public float bonusPhysicalDamagePerAttackDamage;

	public float baseMagicDamageDone;
	public float bonusMagicDamagePerAbilityPower;
	
	public Skill(Champion champion, int level) throws UnmatchingDescription {
		this.champion = champion;
		this.level = level;
		
		cost = defineCost(level);
		range = defineRange(level);
		cooldown = defineCooldown(level);
		description = defineDescription();
		parseDescription(level, description);
		
		isReady = true;
	}

	protected abstract int defineCost(int level);

	protected abstract int defineRange(int level);

	protected abstract int defineCooldown(int level);

	protected abstract String defineDescription();

	protected abstract void parseDescription(int level, String description) throws UnmatchingDescription;

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
		cooldown *= (100 - champion.getCooldownReduction());
		model.schedule.scheduleOnce(cooldown, new Steppable() {
			@Override
			public void step(SimState state) {
				isReady = true;
			}
		});
	}

	private boolean doesMagicDamage() {
		if(basePhysicalDamageDone>0 || bonusPhysicalDamagePerAttackDamage>0)
			return true;
		else
			return false;
	}

	private boolean doesPhysicalDamage() {
		if(baseMagicDamageDone>0 || bonusMagicDamagePerAbilityPower>0)
			return true;
		else
			return false;
	}

	public boolean isReady() {
		return isReady;
	}

	public abstract boolean canCriticallyHit();
}
