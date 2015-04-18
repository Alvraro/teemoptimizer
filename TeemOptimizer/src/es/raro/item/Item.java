package es.raro.item;

import java.util.HashSet;

import es.raro.skill.Skill;

public abstract class Item {
	protected String name;
	
	public int totalCost;
	
	// Stats bonus
	public float health;
	public float healthPerLevel;
	public float healthPercentage;
	public float healthRegen;
	public float healthRegenPerLevel;
	public float mana;
	public float manaPerLevel;
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
	public float energy;
	public float energyPerLevel;
	public float energyRegen;
	public float energyRegenPerLevel;
	/** 0-1 rate by which skill cooldown is reduced */
	public float cooldownReduction;
	/** 0-1 rate by which skill cooldown is reduced per champion's level */
	public float cooldownReductionPerLevel;

	public HashSet<Item> uniqueEffects;
	public HashSet<Skill> uniqueSkills;
	
	public Item(String name){
		this.name = name;
		
		totalCost = defineTotalCost();
		health = defineHealth();
		healthPerLevel = defineHealthPerLevel();
		healthPercentage = defineHealthPercentage();
		healthRegen = defineHealthRegen();
		healthRegenPerLevel = defineHealthRegenPerLevel();
		mana = defineMana();
		manaPerLevel = defineManaPerLevel();
		manaRegen = defineManaRegen();
		manaRegenPerLevel = defineManaRegenPerLevel();
		damage = defineDamage();
		damagePerLevel = defineDamagePerLevel();
		attackSpeed = defineAttackSpeed();
		armor = defineArmor();
		armorPerLevel = defineArmorPerLevel();
		magicResist = defineMagicResist();
		magicResistPerLevel = defineMagicResistPerLevel();
		movementSpeed = defineMovementSpeed();
		percentageArmorPenetration = definePercentageArmorPenetration();
		flatArmorPenetration = defineFlatArmorPenetration();
		criticalChance = defineCriticalChance();
		criticalBonusDamage = defineCriticalBonusDamage();
		abilityPower = defineAbilityPower();
		abilityPowerPerLevel = defineAbilityPowerPerLevel();
		magicPenetration = defineMagicPenetration();
		lifeSteal = defineLifeSteal();
		spellVamp = defineSpellVamp();
		energy = defineEnergy();
		energyPerLevel = defineEnergyPerLevel();
		energyRegen = defineEnergyRegen();
		energyRegenPerLevel = defineEnergyRegenPerLevel();
		cooldownReduction = defineCooldownReduction();
		cooldownReductionPerLevel = defineCooldownReductionPerLevel();
		
		uniqueEffects = defineUniqueEffects();
		uniqueSkills = defineUniqueSkills();
	}

	protected abstract HashSet<Item> defineUniqueEffects();

	protected abstract HashSet<Skill> defineUniqueSkills();

	public abstract boolean applyOnHitEffects();
	
	protected abstract int defineTotalCost();

	protected abstract float defineHealth();
	
	protected abstract float defineHealthPerLevel();
	
	protected abstract float defineHealthPercentage();
	
	protected abstract float defineHealthRegen();
	
	protected abstract float defineHealthRegenPerLevel();
	
	protected abstract float defineMana();
	
	protected abstract float defineManaPerLevel();
	
	protected abstract float defineManaRegen();
	
	protected abstract float defineManaRegenPerLevel();
	
	protected abstract float defineDamage();
	
	protected abstract float defineDamagePerLevel();
	
	protected abstract float defineAttackSpeed();
	
	protected abstract float defineArmor();
	
	protected abstract float defineArmorPerLevel();
	
	protected abstract float defineMagicResist();
	
	protected abstract float defineMagicResistPerLevel();
	
	protected abstract float defineMovementSpeed();
	
	protected abstract float definePercentageArmorPenetration();
	
	protected abstract float defineFlatArmorPenetration();
	
	protected abstract float defineCriticalChance();
	
	protected abstract float defineCriticalBonusDamage();
	
	protected abstract float defineAbilityPower();
	
	protected abstract float defineAbilityPowerPerLevel();
	
	protected abstract float defineMagicPenetration();
	
	protected abstract float defineLifeSteal();
	
	protected abstract float defineSpellVamp();
	
	protected abstract float defineEnergy();
	
	protected abstract float defineEnergyPerLevel();
	
	protected abstract float defineEnergyRegen();
	
	protected abstract float defineEnergyRegenPerLevel();
	
	protected abstract float defineCooldownReduction();
	
	protected abstract float defineCooldownReductionPerLevel();
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Item){
			return name.equals(((Item) obj).name);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
