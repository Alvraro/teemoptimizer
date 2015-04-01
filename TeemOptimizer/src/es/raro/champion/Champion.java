package es.raro.champion;

import java.util.ArrayList;
import java.util.Collections;

import sim.engine.SimState;
import sim.engine.Steppable;
import es.raro.item.Item;
import es.raro.mastery.Mastery;
import es.raro.model.Teemodel;
import es.raro.rune.Rune;
import es.raro.skill.Skill;

public abstract class Champion implements Steppable {
	protected ArrayList<Skill> skills;
	
	protected ArrayList<Mastery> masteries;
	protected ArrayList<Rune> runes;
	protected ArrayList<Item> items;
	
	/** Last champion targeted */
	protected Champion lastTarget;
	
	/** Whether the champion is enabled or not */
	protected boolean isEnabled;

	/** Whether the champion can attack */
	protected boolean attackReady;
	
	/** Whether the champion can use skills */
	protected boolean skillsReady;
		
	protected int level;
	
	protected float health;
	protected float healthRegen;
	protected float mana;
	protected float manaRegen;
	protected float damage;
	protected float attackSpeed;
	protected float armor;
	protected float armorGain;
	protected float magicResist;
	protected float movementSpeed;
	protected float range;
	protected float armorReduction;
	protected float percentageArmorPenetration;
	protected float flatArmorPenetration;
	protected float magicResistReduction;
	protected float percentageMagicPenetration;
	protected float flatMagicPenetration;
	protected float criticalChance;
	protected float criticalBonusDamage;
	
	protected float abilityPower;
	protected float magicPenetration;
	protected float lifeSteal;
	protected float spellVamp;
	protected float revival;
	protected float goldGain;
	protected float experienceGain;
	protected float energy;
	protected float energyRegen;
	protected float cooldownReduction;
	
	protected float baseHealth;
	protected float healthGain;
	protected float baseHealthRegen;
	protected float healthRegenGain;
	protected float baseMana;
	protected float manaGain;
	protected float baseManaRegen;
	protected float manaRegenGain;
	protected float baseDamage;
	protected float damageGain;
	protected float baseAttackSpeed;
	protected float attackSpeedGain;
	protected float baseArmor;
	protected float baseMagicResist;
	protected float magicResistGain;
	protected float baseMovementSpeed;
	protected float baseRange;
	protected float baseArmorReduction;
	protected float basePercentageArmorPenetration;
	protected float baseFlatArmorPenetration;
	protected float baseCriticalChance;
	protected float baseCriticalBonusDamage;
	
	protected float remainingHealth;
	protected float remainingMana;
	
	public Champion(int level, ArrayList<Mastery> masteries,
			ArrayList<Rune> runes, ArrayList<Item> items, 
			float baseHealth, float healthGain, float baseHealthRegen, float healthRegenGain, 
			float baseMana,	float manaGain, float baseManaRegen, float manaRegenGain, 
			float baseDamage, float damageGain, float baseAttackSpeed, float attackSpeedGain, 
			float baseArmor, float armorGain, float baseMagicResist, float magicResistGain, 
			float baseMovementSpeed, float baseRange) {
		this.level = level;
		this.masteries = masteries;
		this.runes = runes;
		this.items = items;

		this.baseHealth = baseHealth;
		this.healthGain = healthGain;
		this.baseHealthRegen = baseHealthRegen;
		this.healthRegenGain = healthRegenGain;
		this.baseMana = baseMana;
		this.manaGain = manaGain;
		this.baseManaRegen = baseManaRegen;
		this.manaRegenGain = manaRegenGain;
		this.baseDamage = baseDamage;
		this.damageGain = damageGain;
		this.baseAttackSpeed = baseAttackSpeed;
		this.attackSpeedGain = attackSpeedGain;
		this.baseArmor = baseArmor;
		this.armorGain = armorGain;
		this.baseMagicResist = baseMagicResist;
		this.magicResistGain = magicResistGain;
		this.baseMovementSpeed = baseMovementSpeed;
		this.baseRange = baseRange;
		this.baseArmorReduction = 0;
		this.basePercentageArmorPenetration = 0;
		this.baseFlatArmorPenetration = 0;
		this.baseCriticalChance = 0;
		this.baseCriticalBonusDamage = 0;
		
		restart();
	}
	
	public void restart(){
		lastTarget = null;
		
		isEnabled = true;
		attackReady = true;
		skillsReady = true;
		
		health = baseHealth + (level-1)*healthGain;
		healthRegen = increaseStat(baseHealthRegen, healthRegenGain, level);
		mana = increaseStat(baseMana, manaGain, level);
		manaRegen = increaseStat(baseManaRegen, manaRegenGain, level);
		damage = increaseStat(baseDamage, damageGain, level);
		attackSpeed = increaseStat(baseAttackSpeed, attackSpeedGain, level);
		armor = increaseStat(baseArmor, armorGain, level);
		magicResist = increaseStat(baseMagicResist, magicResistGain, level);
		movementSpeed = baseMovementSpeed;
		range = baseRange;
		armorReduction = baseArmorReduction;
		percentageArmorPenetration = basePercentageArmorPenetration;
		flatArmorPenetration = baseFlatArmorPenetration;
		criticalChance = baseCriticalChance;
		criticalBonusDamage = baseCriticalBonusDamage;
		
		applyMasteriesBonuses();
		applyRunesBonuses();
		applyItemsBonuses();
		
		remainingHealth = health;
		remainingMana = mana;
	}
	
	private float increaseStat(float baseValue, float gain, int level){
		if(gain>0)
			return baseValue + gain * (7f * level * level / 400f + 267f *level / 400 - 137f / 200f);
		else
			return baseValue;
	}
	
	private void applyMasteriesBonuses() {
		for(Mastery mastery : masteries){
			health+=mastery.getHealth();
			healthRegen+=mastery.getHealthRegen();
			mana+=mastery.getMana();
			manaRegen+=mastery.getManaRegen();
			damage+=mastery.getDamage();
			attackSpeed+=mastery.getAttackSpeed();
			armor+=mastery.getArmor();
			magicResist+=mastery.getMagicResist();
			movementSpeed+=mastery.getMovementSpeed();
			range+=mastery.getRange();
			armorReduction+=mastery.getArmorReduction();
			percentageArmorPenetration+=mastery.getPercentageArmorPenetration();
			flatArmorPenetration+=mastery.getFlatArmorPenetration();
			criticalChance+=mastery.getCriticalChance();
			criticalBonusDamage+=mastery.getCriticalBonusDamage();			
		}
	}
	
	private void applyRunesBonuses() {
		for(Rune rune : runes){
			health+=rune.health;
			health+=level*rune.healthPerLevel;
			//healthPercentage+=rune.healthPercentage; TODO health percentage
			healthRegen+=rune.healthRegen;
			healthRegen+=level*rune.healthRegenPerLevel;
			mana+=rune.mana;
			mana+=level*rune.manaPerLevel;
			manaRegen+=rune.manaRegen;
			manaRegen+=level*rune.manaRegenPerLevel;
			damage+=rune.damage;
			damage+=level*rune.damagePerLevel;
			attackSpeed+=rune.attackSpeed;
			armor+=rune.armor;
			armor+=level*rune.armorPerLevel;
			magicResist+=rune.magicResist;
			magicResist+=level*rune.magicResistPerLevel;
			movementSpeed+=rune.movementSpeed;
			percentageArmorPenetration+=rune.percentageArmorPenetration;
			flatArmorPenetration+=rune.flatArmorPenetration;
			criticalChance+=rune.criticalChance;
			criticalBonusDamage+=rune.criticalBonusDamage;
			abilityPower+=rune.abilityPower;
			abilityPower+=level*rune.abilityPowerPerLevel;
			magicPenetration+=rune.magicPenetration;
			lifeSteal+=rune.lifeSteal;
			spellVamp+=rune.spellVamp;
			revival+=rune.revival;
			goldGain+=rune.goldGain;
			experienceGain+=rune.experienceGain;
			energy+=rune.energy;
			energy+=level*rune.energyPerLevel;
			energyRegen+=rune.energyRegen;
			energyRegen+=level*rune.energyRegenPerLevel;
			cooldownReduction+=rune.cooldownReduction;
			cooldownReduction+=level*rune.cooldownReductionPerLevel;
		}
	}

	private void applyItemsBonuses() {
		for(Item item : items){
			health+=item.health;
			health+=level*item.healthPerLevel;
			//healthPercentage+=item.healthPercentage; TODO health percentage
			healthRegen+=item.healthRegen;
			healthRegen+=level*item.healthRegenPerLevel;
			mana+=item.mana;
			mana+=level*item.manaPerLevel;
			manaRegen+=item.manaRegen;
			manaRegen+=level*item.manaRegenPerLevel;
			damage+=item.damage;
			damage+=level*item.damagePerLevel;
			attackSpeed+=item.attackSpeed;
			armor+=item.armor;
			armor+=level*item.armorPerLevel;
			magicResist+=item.magicResist;
			magicResist+=level*item.magicResistPerLevel;
			movementSpeed+=item.movementSpeed;
			percentageArmorPenetration+=item.percentageArmorPenetration;
			flatArmorPenetration+=item.flatArmorPenetration;
			criticalChance+=item.criticalChance;
			criticalBonusDamage+=item.criticalBonusDamage;
			abilityPower+=item.abilityPower;
			abilityPower+=level*item.abilityPowerPerLevel;
			magicPenetration+=item.magicPenetration;
			lifeSteal+=item.lifeSteal;
			spellVamp+=item.spellVamp;
			revival+=item.revival;
			goldGain+=item.goldGain;
			experienceGain+=item.experienceGain;
			energy+=item.energy;
			energy+=level*item.energyPerLevel;
			energyRegen+=item.energyRegen;
			energyRegen+=level*item.energyRegenPerLevel;
			cooldownReduction+=item.cooldownReduction;
			cooldownReduction+=level*item.cooldownReductionPerLevel;
		}
	}	
	
	public void step(SimState state) {
		step((Teemodel)state);
	}
	
	public void step(Teemodel model) {
		Champion target = chooseTarget(model);
		
		// Check if we can do anything
		if(!isEnabled()){
			return;
		}
		
		// Use one random ready skill
		if(canUseSkills()){
			Collections.shuffle(skills);
			for(Skill skill : skills){
				if(skill.isReady()){
					useSkill(model, skill, target);
					return;
				}
			}
		}
		
		// Else, attack if possible
		if(canAttack()){
			attack(model, target);
			return;
		}
	}

	private boolean canAttack() {
		return attackReady && isEnabled();
	}

	private boolean canUseSkills() {
		return skillsReady && isEnabled();
	}

	private boolean isEnabled() {
		return isEnabled;
	}

	protected Champion chooseTarget(Teemodel model) {
		return (model.getDefender());
	}

	protected void useSkill(Teemodel model, Skill s, Champion target) {
		s.use(model, target);
		
		// Skills are not ready after using it
		skillsReady = false;
		
		// After attack there is a global cooldown for all skills
		startGlobalCooldown(model);
	}

	protected void attack(Teemodel model, Champion target) {
		// Do damage
		float damageDone = Teemodel.calculatePhysicalDamage(this, target, null);
		target.receiveDamage(damageDone);
		
		// Attack is not ready after using it
		attackReady = false;
	
		// Schedule event to reactivate skill after the cooldown
		model.schedule.scheduleOnce(1.0 / getAttackSpeed(), new Steppable() {
			@Override
			public void step(SimState state) {
				attackReady = true;
			}
		});
		
		// After attack there is a global cooldown
		startGlobalCooldown(model);
	}

	public void receiveDamage(float damageDone) {
		remainingHealth -= damageDone;
	}

	private void startGlobalCooldown(Teemodel model) {
		model.schedule.scheduleOnce(Teemodel.GLOBAL_COOLDOWN, new Steppable() {
			@Override
			public void step(SimState state) {
				isEnabled = true;
			}
		});
	}
	
	public ArrayList<Skill> getSkills() {
		return skills;
	}

	public ArrayList<Mastery> getMasteries() {
		return masteries;
	}

	public ArrayList<Rune> getRunes() {
		return runes;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public Champion getLastTarget() {
		return lastTarget;
	}

	public float getHealth() {
		return health;
	}

	public float getHealthGain() {
		return healthGain;
	}

	public float getHealthRegen() {
		return healthRegen;
	}

	public float getHealthRegenGain() {
		return healthRegenGain;
	}

	public float getMana() {
		return mana;
	}

	public float getManaGain() {
		return manaGain;
	}

	public float getManaRegen() {
		return manaRegen;
	}

	public float getManaRegenGain() {
		return manaRegenGain;
	}

	public float getDamage() {
		return damage;
	}

	public float getDamageGain() {
		return damageGain;
	}

	public float getAttackSpeed() {
		return attackSpeed;
	}

	public float getAttackSpeedGain() {
		return attackSpeedGain;
	}

	public float getArmor() {
		return armor;
	}

	public float getArmorGain() {
		return armorGain;
	}

	public float getMagicResist() {
		return magicResist;
	}

	public float getMagicResistGain() {
		return magicResistGain;
	}

	public float getMovementSpeed() {
		return movementSpeed;
	}

	public float getRange() {
		return range;
	}

	public int getLevel() {
		return level;
	}

	public float getArmorReduction() {
		return armorReduction;
	}
	
	public float getPercentageArmorPenetration() {
		return percentageArmorPenetration;
	}

	public float getFlatArmorPenetration() {
		return flatArmorPenetration;
	}

	public float getMagicResistReduction() {
		return magicResistReduction;
	}
	
	public float getPercentageMagicPenetration() {
		return percentageMagicPenetration;
	}

	public float getFlatMagicPenetration() {
		return flatMagicPenetration;
	}
	
	public float getCriticalChance() {
		return criticalChance;
	}

	public float getCriticalBonusDamage() {
		return criticalBonusDamage;
	}

	public float getCooldownReduction() {
		return cooldownReduction;
	}

	public float getAbilityPower() {
		return abilityPower;
	}

	public void setSkills(ArrayList<Skill> skills) {
		this.skills = skills;
	}

}
